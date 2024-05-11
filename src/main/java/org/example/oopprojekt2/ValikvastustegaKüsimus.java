package org.example.oopprojekt2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValikvastustegaKüsimus extends AvatudKüsimus {

    private String[] valikvastused;

    public ValikvastustegaKüsimus(String kysimus, String õigeVastus, String[] valikvastused) {
        super(kysimus, õigeVastus);
        this.valikvastused = valikvastused;
    }

    @Override
    public String toString() {
        return getKysimus() + " \nValikuvariandid: " + Arrays.toString(valikvastused);
    }

    public String[] getValikvastused() {
        return valikvastused;
    }


}
