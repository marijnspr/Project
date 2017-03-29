import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Michelle on 29-3-2017.
 */
public class ReverseORF {
    HashSet<String> ORF_1 = new HashSet<>(); //Alle ORF's
    HashSet<String> ORF_2 = new HashSet<>(); // ""
    HashSet<String> ORF_3 = new HashSet<>(); // ""
    HashMap<String, HashSet<String>> alleORF_1 = new HashMap<>(); // Alle ORF's gelinkt aan hun readingframe
    HashMap<String, HashSet<String>> alleORF_2 = new HashMap<>(); // ""
    HashMap<String, HashSet<String>> alleORF_3 = new HashMap<>(); // ""

    public void leesBestand(String sequentie, int size, String header) {
        String revseq = new StringBuilder(sequentie).reverse().toString();
        HashSet<String> stopcodon = new HashSet<>();
        stopcodon.add("TAG");
        stopcodon.add("TAA");
        stopcodon.add("TGA");
        String startcodon = "ATG";

        for (int i = 0; i < revseq.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "-1";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i+1);
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = Integer.toString(y-2);
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
                    new ORF(header, startpos, stoppos, readingframe, ORF);
                    ORF_1.add(ORF);
                    alleORF_1.put(readingframe, ORF_1);
                }
            }
        }
        /**
         * Readingframs +2
         */
        for (int i = 1; i < revseq.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "-2";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i);
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);

                    if (stopcodon.contains(codon2)) {
                        stoppos = Integer.toString(y-2);
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
                    new ORF(header, startpos, stoppos, readingframe, ORF);
                    ORF_2.add(ORF);
                    alleORF_2.put(readingframe, ORF_2);
                }
            }
        }
        /**
         * Readingframe +3
         */
        for (int i = 2; i < revseq.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "-3";
            String codon = null;
            codon = revseq.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i);
                String ORF = null;
                for (int y = i += 3; y < revseq.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = revseq.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = Integer.toString(y-2);
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
                    new ORF(header, startpos, stoppos, readingframe, ORF);
                    ORF_3.add(ORF);
                    alleORF_3.put(readingframe, ORF_3);
                }
            }
        }
    }

    public int getORF_1size() {
        return ORF_1.size();
    }

    public int getORF_2size() {
        return ORF_2.size();
    }

    public int getORF_3size() {
        return ORF_3.size();
    }

    public HashMap<String, HashSet<String>> getORF_1() {
        return alleORF_1;
    }

    public HashMap<String, HashSet<String>> getORF_2() {
        return alleORF_2;
    }

    public HashMap<String, HashSet<String>> getORF_3() {
        return alleORF_3;
    }
}

