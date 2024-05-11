package org.example.oopprojekt2;

public class AvatudKüsimus {

    private final String kysimus;
    private final String õigeVastus;

    public AvatudKüsimus(String kysimus, String õigeVastus) {
        this.kysimus = kysimus;
        this.õigeVastus = õigeVastus;
    }

    public String getKysimus() {
        return kysimus;
    }

    public String getÕigeVastus() {
        return õigeVastus;
    }

    @Override
    public String toString() {
        return kysimus;
    }

}
