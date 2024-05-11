package org.example.oopprojekt2;

import java.util.ArrayList;

public class KüsimusteTeema {

    private String teema;

    public KüsimusteTeema(String teema, ArrayList<AvatudKüsimus> avatudVastusegaKüsimused, ArrayList<AvatudKüsimus> valikvastusegaKüsimused) {
        this.teema = teema;
        this.avatudVastusegaKüsimused = avatudVastusegaKüsimused;
        this.valikvastusegaKüsimused = valikvastusegaKüsimused;
    }

    private ArrayList<AvatudKüsimus> avatudVastusegaKüsimused;
    private ArrayList<AvatudKüsimus> valikvastusegaKüsimused;

    public AvatudKüsimus tagastaSuvalineAvatudKüsimus() {

        // Tagastab suvalise avatud vastusega küsimuse ning eemaldab selle tagastavate küsimuste seast
        // Eemaldame, sest ei taha, et sama küsimust küsitaks mitu korda

        int küsimuseIndeks = (int) (avatudVastusegaKüsimused.size()*Math.random());
        AvatudKüsimus avatudKüsimus = avatudVastusegaKüsimused.get(küsimuseIndeks);
        avatudVastusegaKüsimused.remove(küsimuseIndeks);
        return avatudKüsimus;

    }

    public AvatudKüsimus tagastaSuvalineValikKüsimus() {

        // Tagastab suvalise valikvastusega küsimuse ning eemaldab selle tagastavate küsimuste seast
        // Eemaldame, sest ei taha, et sama küsimust küsitaks mitu korda

        int küsimuseIndeks = (int) (valikvastusegaKüsimused.size()*Math.random());
        AvatudKüsimus valikvastustegaKüsimus = valikvastusegaKüsimused.get(küsimuseIndeks);
        valikvastusegaKüsimused.remove(küsimuseIndeks);
        return valikvastustegaKüsimus;

    }

    public ArrayList<AvatudKüsimus> getAvatudVastusegaKüsimused() {
        return avatudVastusegaKüsimused;
    }

    public ArrayList<AvatudKüsimus> getValikvastusegaKüsimused() {
        return valikvastusegaKüsimused;
    }
}
