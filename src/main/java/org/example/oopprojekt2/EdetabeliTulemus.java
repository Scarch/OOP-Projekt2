package org.example.oopprojekt2;

public class EdetabeliTulemus implements Comparable<EdetabeliTulemus> {

    private String nimi;
    private int punktid;

    public EdetabeliTulemus(String nimi, int punktid) {
        this.nimi = nimi;
        this.punktid = punktid;
    }

    public String getNimi() {
        return nimi;
    }

    public int getPunktid() {
        return punktid;
    }

    @Override
    public int compareTo(EdetabeliTulemus teineTulemus) {
        // Sorteerime nii, et suurem oleks ees
        return Integer.compare(teineTulemus.getPunktid(), punktid);
    }
}
