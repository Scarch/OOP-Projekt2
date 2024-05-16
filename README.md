# Küsimuste mäng: JavaFX edition

Antud repo sisaldab objektorienteeritud programmeerimise 2. rühmatöö faile.

## Autorid

- Sten-Egert Märtson
- Mirko Martjak

### Panused, ajakulu

Märkus: 2. rühmatöösse ei ole lisatud 1. rühmatöö tunde. 

- Sten-Egert Märtson (ajakulu: ~10h)
  - Kõik eelnev 1.rühmatööst
  - Klasside loomine (EdetabeliTulemus, AvatudKüsimus, ValikvastustegaKüsimused, KüsimusteTeema)
  - Peaklassi üldise funktsionaalsuse koostamine
  - JavaFX disain
  - Punktide edetabel
- Mirko Martjak (ajakulu: ~2h)
  - Kõik eelnev 1.rühmatööst
  - Readme fail ja selle vormistuse kontroll
  - Mängu bugide otsimine
  - JavaFX bugi-de leidmine ja parandamine
  - Üritada teha Dark Mode mängule.

### Mured

- Sten-Egert Märtson
  - Algul tahtsin koostada küsimuste küsimimise meetodi for-tsükkliga, kuid tuli välja, et see vist ei ole võimalik
    - For-tsükkli kasutamiseks oleks pidanud tsükklis rakendama mingi vastuse ootamise funktsiooni, et for-tsükkel koheselt ei läheks lõppu ning lõpetaks tsükkli
    - Lahenduseks oli klassiväljade kasutamine, et jälgida nt mitmenda küsimuse juures on kasutaja, mitu küsimust kasutaja on õigesti vastanud, mis küsimustele valesti vastanud jne
  - Edetabelisse tulemuste kirjutamisel pidin looma EdetabelTulemus klassi
    - Algul tahtsin kasutada `Map<String, Integer>` (String: kasutaja nimi, Integer: kasutaja punktiskoor), et salvestada eelnevad tulemused ja uue kasutaja tulemuse, kuid raskus seisneb sorteerimises: tahtsin, et see map oleks sorteeritud punktiskooride poolest, mis on tegelikult üllatavalt raske. Seega otsustasin lihtsalt luua uue klassi, kus kasutasin `implements Comparable`, et lihtsalt sorteerida alates suuremast tulemusest
 
- Mirko Martjak
  - Mõtlesin mängule juurde luua Dark Mode igale Scene lehele, sedasi, et see muudaks ka teksti valgeks. Kahjuks jäi see tegematta, sest UI oli ehitatud VBox, HBox ja BorderPane     peale ja seega seal olevad Scene tagatausta muutmine ei olnud võimalik, ning kui uurida, et kuidas muuta läbi VBox, HBox ja BorderPane, siis see oleks teinud koodi rohkem        keeruliseks ja mu originaal proovi kood, mis töötas oleks nagunii raisku läinud.
  - Mõtlesin, et muudan UI disaini, aga mul polnud väga palju kogemust kuidas liigutada neid paremaks läbi VBox ja HBox, seega pidin otsima uusi võimalusi kuidas mängu muuta.
  - Mõtlesime, et loome mängule timeri juurde, aga siis oleks oleks pidanud kasutama Thread meetoteid, mida on otseselt raske kirjutada JavaFX sisse + suur ajakulu.
  - Soovinud rohkem panustada mängu JavaFX loomisesse. Tekkis vahepeal ideede puudus.

## Projekti kirjeldus

### Programmi eesmärk

- Programmi kaustas on valmis tehtud küsimused igale teemale. Neid saab omal soovil muuta.
- Küsimuste liike on kokku kaks: valikvastustega ja avatud küsimused.
- Programm algab mängu lühikese tutvustusega, mida kasutaja võib ka vahele jätta.
- Kasutaja saab mängule öelda, et kas soovib valikvastusega küsimusi või mitte.
- Kasutaja saab mängule öelda, mitu teemat ta soovib võtta ja millised need oleks.
- Pärast teemade valimist mäng algab.
- Mäng toimub kiiruse peale: kui vastad 15 sekundi jooksul, siis saad rohkem punkte.
- Punktid sõltuvad õige vastuse ning sisestamis kiirusest.
- Kui mäng ära lõpeb, siis tagastakse punkti summa ja küsitakse, et kas soovid üle vaadata küsimused, mis läksid valesti.

### Protsess

- Otsustasime projekti jätkata (mõlemad)
- Alustasime peaekraani koostamisega, mida kuvatakse, kui mäng avatakse. Rakendasime selleks BorderPane'i.
- Lõime mängu sulgemise ja mängu kirjelduse nupud ning lisasime vajaliku funktsionaalsuse.
- Koostasime nupu mängu alustamiseks ning seejärel lõime mängu seadistamise võimaluse (alustaMäng meetodi kujul)
- Seejärel koostasime küsimuste küsimise meetodi (alustaKüsimist), mis nõudis võrreldes muude meetoditega kõige rohkem aega.
- Koostasime tulemuseekraani ning vastava klassi tulemuste isenditeks.
- Tekitasime võimaluse kuvada edetabelit ning salvestada tulemusi sinna.
- Viimaseks sammuks oli üldine viimistlemine ning debugimine.
- Debugimise all leida kõik üleliigesed nupu vajutamis ning teksti sisestamis vead. 

### Klassid

- AvatudKüsimus: peaklassis loeme teema küsimuste failist saadud sisu, milles read muudame antud klassi isendteks, mis sisaldavad küsimust ja vastust.
- ValikvastustegaKüsimused: peaklassis loeme failist saadud sisu, milles read muudame antud klassi isendteks, mis sisaldavad küsimust, õiget vastust ja valikvastuseid.
- KüsimusteTeema: Klass, mille kaudu teeme isendid ning hoiustame erinevate teemade küsimusi: nii avatud kui ka valikutega küsimuste hoidmine eraldi massiividena
- EdetabeliTulemus: Lihtsustab edetabelis tulemuste sorteerimist ning üldist käideldamist
- Peaklass: Klass, milles mäng käivitatakse

### Meetodid

#### Peaklass

- `alustaKüsimist`: Muudab pealava stseeni, et kuvataks küsimusi ning pakutaks võimalust neile vastata.
- `alustaMäng`: Tegelikult on see mängu algse seadistuse määramiseks. Muudab pealava nii, et kasutaja saaks valida, milliste teemade küsimusi kuvatakse, millist sorti küsimusi (valik või avatud küsimused) küsitakse ja mitu küsimust esitatakse.
- `leiaTeemaFail`: Tagastab teemale vastava küsimuste .txt faili
- `kirjutaEdetabetabeliFaili`: Võtab argumendiks kasutaja nime ning lisab tema edetabeli faili (`edetabel.txt`). Punktisumma saab klassimuutujast.
- `koostaKüsimuseStseen`: Koostab vastavalt parameetritele sobiva küsimuse stseeni. Paneb paika valikvastused loetaval moel.
- `kuvaTulemused`: Kuvab pealavale mängu lõppemisel tulemuste stseeni, kust saab uut mängu alustada, mängu sulgeda või edetabelit kuvada.
- `loeFail`: Meetod, mis loeb ära txt faili sisu ning paneb sisu põhjal küsimused massiivi.
- `lisatekstVBoxi`: Abiks mängu kirjelduse kuvamisel. Muudab sõne tükkideks ning lisab sõne tükid eraldi ridadele.
- `loeEdetabelFail`: Loeb edetabelist kõik tulemused ning tagastab need listis `EdetabeliTulemus` isenditena.
- `oota3Sekundit`: Kuvab pealavale ooteekraani soovitud tekstiga, mis kestab 3 sekundit.

- `alustaMänguNupp`: Vajutusel käitab `alustaMäng` meetodi.
- `kuvaEdetabel`: Vajutusel kuvab uue akna, kus on kuvatud edetabel.
- `suleMäng`: Paneb mängu kõik aknad kinni.

#### AvatudKüsimus

- Peamine küsimuste klass, mille põhjal nt tegime küsimuste massiivid
- Isendiväljad küsimuse ja vastuse jaoks
- Meetodid isendiväljadele juurde pääsemiseks

#### ValikvastusegaKüsimus

- Alamklass, mis põhineb AvatudKüsimus klassil
- Arvestab valikvastuste olemasoluga (uus isendiväli)

#### KüsimusteTeema

- tagastaSuvalineAvatudKüsimus meetod: tagastab suvalise avatud küsimuse ning eemaldab selle avatud küsimuste massiivist
- tagastaSuvalineValikKüsimus: tagastab suvalise valikvastustega küsimuse ning eemaldab selle valikvastusega küsimuste massiivist

### Testimine

- Otsisime mängu buge, mida võib JavaFX tekidada.
- Proovisime mängu loomulikult mängida.

## Hinnang

- Tuli üllatavalt mängitav programm välja.
- Kasutajaliides näeb korralik välja JavaFX abil isegi, kui mõningaid uusi meetoeid olnud raske juurde lisada.

### Läks Hästi:

- Kood töötab (vist)
- Edetabel: nii kuvamine, faili kirjutamine kui ka failist lugemine
- Küsimuste kuvamine (for-tsükkel nupuvajutuste kujul)
- Sai katsetada tagatausta värvimuutmist.

### Vajab arendamist:

- Mängu välimuse disain
  - Antud kujul on peamiselt valge ekraan musta tekstiga. Mõnes kohas on aga fonte muudetud või kujundust kohandatud, et see oleks loetav (edetabel muudab suurust vastavalt edetabeli sisule)
- Programm kogub kasutaja poolt valesti vastatud küsimused kogutud listi, aga ei kasuta seda listi
- Küsimuste väljastamine ei ole perfektne: kasutaja võib valida mitme teema vahel, aga on võimalus, et ühe teema küsimusi ei kuvata, sest küsimusi võetakse suvaliselt teemade vahel, kuigi on mängu seadistuses küsitud konkreetselt, mitu küsimust soovib kasutaja saad iga teema kohta.
- Teha valmis Dark Mode.
