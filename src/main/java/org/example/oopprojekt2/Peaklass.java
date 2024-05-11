package org.example.oopprojekt2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Peaklass extends Application {

    private static int kasutajaPunktid = 0;
    private static int mitmesKüsimus = 0;
    private static int mituÕigesti = 0;
    private static List<AvatudKüsimus> valestiVastatudKüsimused = new ArrayList<>();
    private static AvatudKüsimus suvalineKüsimus;
    private static List<KüsimusteTeema> teemadeMassiv;
    private static KüsimusteTeema suvalineTeema;
    final private static Button esitaVastusNupp = new Button("Esita vastus");
    private static List<RadioButton> valikud;
    private static long algusAeg;
    private static VBox küsimusVBox;
    final private static Button järgmineKüsimus = new Button("Liigu järgmisele küsimusele");




    @Override
    public void start(Stage pealava) throws IOException {
        BorderPane juur = new BorderPane();

        // Pealkiri
        StackPane pealkiriStackPane = new StackPane();
        Text pealkiri = new Text("Küsimuste mäng: JavaFX edition");
        pealkiri.setTextAlignment(TextAlignment.CENTER);
        Font font = new Font("Segoe UI", 25);
        pealkiri.setFont(font);
        pealkiriStackPane.getChildren().add(pealkiri);
        juur.setTop(pealkiriStackPane);

        // Nupud
        VBox nupudVBox = new VBox();
        Button alustaMängu = new Button("Alusta mängu");
        Button mänguInfo = new Button("Kuva sissejuhatus");
        Button suleMäng = new Button("Sule mäng");
        alustaMängu.setPrefHeight(50);
        alustaMängu.setPrefWidth(125);
        nupudVBox.getChildren().addAll(alustaMängu, mänguInfo, suleMäng);
        nupudVBox.setSpacing(10);
        nupudVBox.setAlignment(Pos.CENTER);
        juur.setCenter(nupudVBox);

        alustaMängu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    alustaMäng(pealava);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        mänguInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox sissejuhatusVBox = new VBox();
                String sissejuhatusTekst = "Programmi kaustas on valmis tehtud küsimused igale teemale. Neid saab omal soovil muuta.;" +
                        "Küsimuste liike on kokku kaks: valikvastustega ja avatud küsimused.;" +
                        "Kasutaja saab mängule öelda, et kas soovib valikvastusega küsimusi ka või mitte.;" +
                        "Kasutaja saab mängule öelda, mitu teemat ta soovib võtta ja millised need oleks.;" +
                        "Pärast teemade valimist mäng algab.;" +
                        "Mäng toimub kiiruse peale: kui vastad 15 sekundi jooksul, siis saad rohkem punkte.;" +
                        "Punkte saab kui esitab õige vastuse;" +
                        "Kui mäng lõpeb, siis tagastakse punktide summa ja küsitakse, et kas soovid üle vaadata küsimused, mis läksid valesti.";
                lisatekstVBoxi(sissejuhatusVBox, sissejuhatusTekst);
                sissejuhatusVBox.setAlignment(Pos.CENTER);

                Scene sissejuhatusStseen = new Scene(sissejuhatusVBox, 700, 175);

                Stage sissejuhatusLava = new Stage();
                sissejuhatusLava.setTitle("Küsimuste mängu sissejuhatus");
                sissejuhatusLava.setScene(sissejuhatusStseen);
                sissejuhatusLava.setResizable(false);
                sissejuhatusLava.show();

            }
        });

        suleMäng.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pealava.close();
            }
        });

        // Projekti info
        VBox projektiInfoVBox = new VBox();
        Text autorid = new Text("Autorid: Sten-Egert Märtson, Mirko Martjak.");
        Text rühmatöö = new Text("Objektorienteeritud Programmeerimise 2. rühmatöö");
        projektiInfoVBox.getChildren().addAll(autorid, rühmatöö);
        projektiInfoVBox.setAlignment(Pos.CENTER);
        juur.setBottom(projektiInfoVBox);

        Scene stseen = new Scene(juur, 600, 400);
        pealava.setTitle("Küsimuste mäng");
        pealava.setScene(stseen);
        pealava.setResizable(false);
        pealava.show();
    }

    private void lisatekstVBoxi(VBox vbox, String tekst) {
        String[] tekstOsadena = tekst.split(";");

        for (String rida : tekstOsadena) {
            Text ridaText = new Text(rida);
            vbox.getChildren().add(ridaText);
        }
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Loeb etteantud failist küsimused välja ning sorteerib need kas avatud vastusega või valikvastustega küsimusteks
     * @param failinimi Loetava faili nimi
     * @return ArrayListide massiiv, mis koosneb avatud vastusega küsimustest indeksil 0 ning valikvastustega küsimustest indeksil 0
     */
    public static ArrayList<AvatudKüsimus>[] loeFail(String failinimi) throws IOException {

        ArrayList<AvatudKüsimus>[] kysimused = new ArrayList[2];
        kysimused[0] = new ArrayList<>();
        kysimused[1] = new ArrayList<>();
        // Tagastame massiivi, kus on kaks elementi
        // Esimene element on avatud vastustega küsimuste massiiv
        // Teine element on valikvastustega küsimuste massiiv

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] massiiv = rida.split(";");
                if (massiiv.length == 2) {
                    AvatudKüsimus kysimus = new AvatudKüsimus(massiiv[0],massiiv[1]);
                    kysimused[0].add(kysimus);
                }
                else if (massiiv.length == 3){
                    ValikvastustegaKüsimus kysimus = new ValikvastustegaKüsimus(massiiv[0],massiiv[1],massiiv[2].split(", "));
                    kysimused[1].add(kysimus);
                }
            }
        }

        return kysimused;
    }

    private static void alustaMäng(Stage lava) throws IOException, InterruptedException {

        BorderPane piiripaan = new BorderPane();

        VBox vbox = new VBox(5);
        piiripaan.setCenter(vbox);
        vbox.setAlignment(Pos.CENTER);

        Text erind = new Text();

        Text küsimuseTüüp = new Text("Mis küsimusi soovite?");
        RadioButton avatudVastustega = new RadioButton("Avatud vastustega");
        RadioButton valikVastustega = new RadioButton("Valikvastustega");
        final ToggleGroup lülitusGrupp = new ToggleGroup();
        avatudVastustega.setToggleGroup(lülitusGrupp);
        valikVastustega.setToggleGroup(lülitusGrupp);
        valikVastustega.setSelected(true);

        erind.setFill(Color.RED);
        Text juhis = new Text("Valige soovitud teemad (vähemalt üks)");
        CheckBox ajalooValik = new CheckBox("Ajalugu");
        CheckBox eestiKeeleValik = new CheckBox("Eesti keel");
        CheckBox kirjanduseValik = new CheckBox("Kirjandus");
        CheckBox ühiskonnaValik = new CheckBox("Ühiskond");

        Text arvuKüsimus = new Text("Sisestage küsimuste arv (1-5), mida soovite saada iga teema puhul.");
        TextField mituKüsimust = new TextField();
        mituKüsimust.setMaxWidth(50);

        // Lubame ainult arvude sisestamist
        mituKüsimust.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mituKüsimust.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button edasiNupp = new Button("Valikud tehtud");

        List<CheckBox> valikud = new ArrayList<>();
        valikud.add(ajalooValik);
        valikud.add(eestiKeeleValik);
        valikud.add(kirjanduseValik);
        valikud.add(ühiskonnaValik);

        edasiNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String erindString = "";

                boolean vähemaltÜksValitud = false;
                for (CheckBox checkBox : valikud) {
                    if (checkBox.isSelected()) {
                        vähemaltÜksValitud = true;
                        break;
                    }
                }
                if (!vähemaltÜksValitud)
                    erindString += "Te ei ole ühtki teemat valinud!";


                boolean kasSobivKüsimusteArv = false;
                int mituKüsimustInt = 0;
                if (mituKüsimust.getText().isEmpty() || mituKüsimust.getText() == null) {
                    erindString += " Te ei ole sisestanud küsimuste arvu!";
                } else {
                    mituKüsimustInt = Integer.parseInt(mituKüsimust.getText());
                    if (mituKüsimustInt < 1 || mituKüsimustInt > 5)
                        erindString += " Sisestatud küsimuste arv ei ole sobivas vahemikus (1-5)!";
                    else
                        kasSobivKüsimusteArv = true;
                }

                erind.setText(erindString.trim());


                if (kasSobivKüsimusteArv && vähemaltÜksValitud) {
                    boolean kasValikvastustega = valikVastustega.isSelected();
                    List<String> misTeemad = new ArrayList<>();
                    for (CheckBox teemaValik : valikud) {
                        if (teemaValik.isSelected())
                            misTeemad.add(teemaValik.getText());
                    }
                    try {
                        alustaKüsimist(kasValikvastustega, misTeemad, mituKüsimustInt, lava);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        vbox.getChildren().addAll(erind, küsimuseTüüp, valikVastustega, avatudVastustega, juhis, ajalooValik,eestiKeeleValik,kirjanduseValik, ühiskonnaValik, arvuKüsimus, mituKüsimust, edasiNupp);

        Scene algusStseen = new Scene(piiripaan, 600, 400);
        lava.setScene(algusStseen);
    }

    /**
     * Meetod, kus tegelikult hakkame küsimusi küsima
     * @param kasValikvastused Kas küsimused on valikvastustega
     * @param misTeemad Mis teemasid küsitakse
     * @param mituKüsimust Mitu küsimust küsitakse igast teemast;
     */
    private static void alustaKüsimist(boolean kasValikvastused, List<String> misTeemad, int mituKüsimust, Stage lava) throws IOException, InterruptedException {

        teemadeMassiv = new ArrayList<>(misTeemad.size());

        for (String teemaString : misTeemad) {
            String teemaFail = leiaTeemaFail(teemaString);
            ArrayList<AvatudKüsimus>[] teemaKüsimused = loeFail(teemaFail);
            KüsimusteTeema lisatavTeema = new KüsimusteTeema(teemaString, teemaKüsimused[0], teemaKüsimused[1]);
            teemadeMassiv.add(lisatavTeema);
        }

        int mituKüsimustKokku = mituKüsimust * misTeemad.size();

        oota3Sekundit(lava, "Esimene küsimus tuleb 3 sekundi pärast...");
        // Väike paus enne küsimuste küsimist

        // Selles tsükklis küsime küsimusi
        // Peab mõtlema, kas küsimused lähevad suvaliselt teemade vahel või ühe teema küsimused tulevad järjest

        int suvaliseTeemaIndeks = (int) (teemadeMassiv.size()*Math.random());
        suvalineTeema = teemadeMassiv.get(suvaliseTeemaIndeks);

        if (kasValikvastused) suvalineKüsimus = suvalineTeema.tagastaSuvalineValikKüsimus();
        else suvalineKüsimus = suvalineTeema.tagastaSuvalineAvatudKüsimus();

        küsimusVBox = new VBox(10);
        valikud = new ArrayList<>();
        Scene küsimusStseen = kuvaKüsimus(suvalineKüsimus, kasValikvastused, küsimusVBox, valikud);
        TextField avatudVastusVäli = new TextField();
        if (kasValikvastused)
            valikud.getFirst().setSelected(true);
        else
            küsimusVBox.getChildren().add(avatudVastusVäli);
        küsimusVBox.getChildren().add(esitaVastusNupp);
        lava.setScene(küsimusStseen);

        Button lõpetaMäng = new Button("Kuva mängu tulemus.");
        lõpetaMäng.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                kuvaTulemused(lava, mituKüsimustKokku);
            }
        });

        // Küsime ja ootame vastust
        // Sõltuvalt kui kiiresti vastas võime lisada boonuspunkte
        // Iga küsimuse eest võiks anda mingi koguse baaspunkte

        algusAeg = System.currentTimeMillis();
        esitaVastusNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Kasutame seda nuppu, et tekitada for loop efekti
                // For loopi koostada küsimuste väljastamiseks on raske, sest see vajaks mingi imeliku ootamise metoodika implementeerimist

                long lõppAeg = System.currentTimeMillis();
                long küsimuseleVastamiseAeg = (lõppAeg-algusAeg)/1000;

                // Kasutaja vastuse sisestamine muutujasse
                String kasutajaVastus = "";
                if (kasValikvastused) {
                    for (RadioButton valik : valikud) {
                        if (valik.isSelected())
                            kasutajaVastus = valik.getText();
                    }
                } else {
                    kasutajaVastus = avatudVastusVäli.getText();
                }

                boolean kasÕige = kasutajaVastus.equalsIgnoreCase(suvalineKüsimus.getÕigeVastus());
                Text tulemus = new Text();
                küsimusVBox.getChildren().add(tulemus);
                tulemus.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
                if (kasÕige) {
                    // Mängija saab boonuspunkte, kui on vastanud 15 sekundi sees;
                    // Mida kiiremini, seda rohkem punkte
                    // Iga sekund mis kaob võtab boonuspunktide hulgast 5 punkti ära
                    int boonuspunktid = (int) (Math.max((15 - küsimuseleVastamiseAeg), 0)*5);
                    kasutajaPunktid += 50 + boonuspunktid;
                    mituÕigesti++;
                    tulemus.setText("Õige vastus!");
                    tulemus.setFill(Color.GREEN);
                } else{
                    valestiVastatudKüsimused.add(suvalineKüsimus);
                    tulemus.setText("Vale vastus... Õige vastus: " + suvalineKüsimus.getÕigeVastus());
                    tulemus.setFill(Color.RED);
                }

                // Kas küsimused on otsa saanud?
                if (mitmesKüsimus >= mituKüsimustKokku - 1) {

                    Text lõpuTekst = new Text("Mäng on läbi!");
                    küsimusVBox.getChildren().add(lõpuTekst);
                    küsimusVBox.getChildren().add(lõpetaMäng);

                } else {

                    // Kui teemast on küsimused otsa saanud, siis eemaldame seda
                    if (suvalineTeema.getAvatudVastusegaKüsimused().isEmpty() || suvalineTeema.getValikvastusegaKüsimused().isEmpty())
                        teemadeMassiv.remove(suvaliseTeemaIndeks);

                    küsimusVBox.getChildren().add(järgmineKüsimus);

                }
            }
        });

        järgmineKüsimus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Otsime uue suvalise küsimuse
                int suvaliseTeemaIndeks = (int) (teemadeMassiv.size()*Math.random());
                suvalineTeema = teemadeMassiv.get(suvaliseTeemaIndeks);
                if (kasValikvastused) suvalineKüsimus = suvalineTeema.tagastaSuvalineValikKüsimus();
                else suvalineKüsimus = suvalineTeema.tagastaSuvalineAvatudKüsimus();

                // Teeme uue stseeni, kus kuvame uue küsimuse
                küsimusVBox = new VBox(10);
                valikud = new ArrayList<>();
                Scene küsimusStseen = kuvaKüsimus(suvalineKüsimus, kasValikvastused, küsimusVBox, valikud);
                TextField avatudVastusVäli = new TextField();
                if (kasValikvastused)
                    valikud.getFirst().setSelected(true);
                else
                    küsimusVBox.getChildren().add(avatudVastusVäli);
                küsimusVBox.getChildren().add(esitaVastusNupp);
                lava.setScene(küsimusStseen);
                mitmesKüsimus++;
            }
        });


        /*

        if (vastusteÜlevaade()) {
            for (AvatudKüsimus küsimus : valestiVastatudKüsimused) {
                System.out.println("Küsimus: " + küsimus.getKysimus());
                System.out.println("Vastus: " + küsimus.getÕigeVastus());
                System.out.println();
            }
        }

         */

    }

    private static void kuvaTulemused(Stage lava, int mituKüsimustKokku) {

        VBox tulemusVBox = new VBox();
        tulemusVBox.setAlignment(Pos.CENTER);
        Text läbi = new Text("Mäng on lõppenud!");
        läbi.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        Text tulemus = new Text("Tulemus: " + mituÕigesti + "/" + mituKüsimustKokku + " õiget vastust.");
        tulemusVBox.getChildren().addAll(läbi, tulemus);
        Scene lõppStseen = new Scene(tulemusVBox, 600, 400);

        lava.setScene(lõppStseen);
    }

    private static Scene kuvaKüsimus(AvatudKüsimus suvalineKüsimus, boolean kasValikvastused, VBox küsimusedJaVastusedVBox, List<RadioButton> valikud) {
        küsimusedJaVastusedVBox.setAlignment(Pos.CENTER);

        Text küsimus = new Text(suvalineKüsimus.getKysimus());
        küsimus.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        küsimusedJaVastusedVBox.getChildren().add(küsimus);

        if (kasValikvastused) {

            List<String> valikvastusedArrayList = Arrays.asList(((ValikvastustegaKüsimus) suvalineKüsimus).getValikvastused());
            Collections.shuffle(valikvastusedArrayList);

            ToggleGroup lülitusGrupp = new ToggleGroup();

            for (int i = 0; i < valikvastusedArrayList.size() - 1; i += 2) {
                String reaEsimeneValikvastus = valikvastusedArrayList.get(i);
                String reaTeineValikvastus = valikvastusedArrayList.get(i+1);

                HBox rida = new HBox(10);
                rida.setAlignment(Pos.CENTER);
                RadioButton reaEsimeneValikvastusNupp = new RadioButton(reaEsimeneValikvastus);
                reaEsimeneValikvastusNupp.setToggleGroup(lülitusGrupp);
                valikud.add(reaEsimeneValikvastusNupp);
                RadioButton reaTeineValikvastusNupp = new RadioButton(reaTeineValikvastus);
                reaTeineValikvastusNupp.setToggleGroup(lülitusGrupp);
                valikud.add(reaTeineValikvastusNupp);
                rida.getChildren().addAll(reaEsimeneValikvastusNupp, reaTeineValikvastusNupp);

                küsimusedJaVastusedVBox.getChildren().add(rida);
            }

            if (valikvastusedArrayList.size() % 2 == 1) {
                RadioButton viimaneValikNupp = new RadioButton(valikvastusedArrayList.getLast());
                viimaneValikNupp.setToggleGroup(lülitusGrupp);
                valikud.add(viimaneValikNupp);
                küsimusedJaVastusedVBox.getChildren().add(viimaneValikNupp);
            }
        }

        return new Scene(küsimusedJaVastusedVBox, Math.max(600, küsimus.getLayoutBounds().getWidth() + 20), 400);
    }

    private static void oota3Sekundit(Stage lava, String midaKuvatakse) throws InterruptedException {
        VBox algusVBox = new VBox(10);
        algusVBox.setAlignment(Pos.CENTER);
        Text algus = new Text(midaKuvatakse);
        algusVBox.getChildren().addAll(algus);
        Scene algusStseen = new Scene(algusVBox, 600, 400);
        lava.setScene(algusStseen);

        TimeUnit.SECONDS.sleep(3);
    }

    private static String leiaTeemaFail(String teemaString) {

        if (teemaString.equals("Ajalugu")) return "ajalooküsimused.txt";
        else if (teemaString.equals("Ühiskond")) return "ühiskonnaküsimused.txt";
        else if (teemaString.equals("Eesti keel")) return "eestikeeleküsimused.txt";
        else if (teemaString.equals("Kirjandus")) return "kirjanduseküsimused.txt";
        else System.out.println("ERROR, midagi on valesti, vaata leiaTeemaFail meetodit");

        return "kood on katki";

    }
}