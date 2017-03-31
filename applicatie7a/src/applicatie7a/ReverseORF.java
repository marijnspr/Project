package applicatie7a;

import java.util.*;

/**
 * Created by Michelle on 29-3-2017.
 */
public class ReverseORF {
    HashSet<String> ORF_1 = new HashSet<>(); //Alle ORF's
    HashSet<String> ORF_2 = new HashSet<>(); // ""
    HashSet<String> ORF_3 = new HashSet<>(); // ""
    ArrayList<ORF> alleReverseORFs = new ArrayList<>();

    /**
     *
     * @param sequentie
     * @param size
     * @param header
     */
    public void leesBestand(String sequentie, int size, String header) {
        String revseq = new StringBuilder(sequentie).reverse().toString();
        HashSet<String> stopcodon = new HashSet<>();
        stopcodon.add("TAG");
        stopcodon.add("TAA");
        stopcodon.add("TGA");
        String startcodon = "ATG";

        for (int i = 0; i < revseq.length() - 2; i += 3) {
            int startpos;
            int stoppos = 0;
            String readingframe = "-1";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = i+1;
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = y-2;
                        ORF = ORF + codon2;
                        y = revseq.length();
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }

                if (ORF.length() > size) {
                    alleReverseORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF_1.add(ORF);
                }
            }
        }
        /**
         * Readingframs +2
         */
        for (int i = 1; i < revseq.length() - 2; i += 3) {
            int startpos;
            int stoppos = 0;
            String readingframe = "-2";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = i;
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);

                    if (stopcodon.contains(codon2)) {
                        stoppos = y-2;
                        ORF = ORF + codon2;
                        y = revseq.length();
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }

                if (ORF.length() > size) {
                    alleReverseORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF_2.add(ORF);
                }
            }
        }
        /**
         * Readingframe +3
         */
        for (int i = 2; i < revseq.length() - 2; i += 3) {
            int startpos;
            int stoppos = 0;
            String readingframe = "-3";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = i;
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = y-2;
                        ORF = ORF + codon2;
                        y = revseq.length();
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }

                if (ORF.length() > size) {
                    alleReverseORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF_3.add(ORF);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public int getORF_1size() {
        return ORF_1.size();
    }

    /**
     *
     * @return
     */
    public int getORF_2size() {
        return ORF_2.size();
    }

    /**
     *
     * @return
     */
    public int getORF_3size() {
        return ORF_3.size();
    }

    /**
     *
     * @return
     */
    public ArrayList<ORF> getORFreverse(){
    return alleReverseORFs;
    }
}

