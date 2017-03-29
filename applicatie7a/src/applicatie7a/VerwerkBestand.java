package applicatie7a;


import java.util.*;

/**
 * Created by Michelle on 27-3-2017.
 */
public class VerwerkBestand {

    HashSet<String> ORF1 = new HashSet<>(); //Alle ORF's
    HashSet<String> ORF2 = new HashSet<>(); // ""
    HashSet<String> ORF3 = new HashSet<>(); // ""
    ArrayList<ORF> alleForwardORFs = new ArrayList<>();

    public void orfVoorspeller(String seqDNA, int size, String header) {

        HashSet<String> stopcodon = new HashSet<>();
        stopcodon.add("TAG");
        stopcodon.add("TAA");
        stopcodon.add("TGA");
        String startcodon = "ATG";

        for (int i = 0; i < seqDNA.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "+1";
            String codon = null;
            codon = seqDNA.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i+1);
                String ORF = null;
                for (int y = i += 3; y < seqDNA.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                        stoppos = Integer.toString(y-2);
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                if(ORF.length()>size) {
                    alleForwardORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF1.add(ORF);
                }
            }
        }
        /**
         * Readingframs +2
         */
        for (int i = 1; i < seqDNA.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "+2";
            String codon = null;
            codon = seqDNA.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i+1);
                String ORF = null;
                for (int y = i += 3; y < seqDNA.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = Integer.toString(y-2);
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                if(ORF.length()>size){
                    alleForwardORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF2.add(ORF);
                    }
            }
        }
        /**
         * Readingframe +3
         */
        for (int i = 2; i < seqDNA.length() - 2; i += 3) {
            String startpos;
            String stoppos = null;
            String readingframe = "+3";
            String codon = null;
            codon = seqDNA.substring(i, i + 3);

            if (codon.equals(startcodon)) {
                startpos = Integer.toString(i+1);
                String ORF = null;
                for (int y = i += 3; y < seqDNA.length() - 2; y += 3) {
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y + 3);
                    if (stopcodon.contains(codon2)) {
                        stoppos = Integer.toString(y-2);
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                    } else {
                        if (ORF == null) {
                            ORF = codon + codon2;
                        } else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                if(ORF.length()>size) {
                    alleForwardORFs.add(new ORF(header, startpos, stoppos, readingframe, ORF));
                    ORF3.add(ORF);
                }
            }
        }
    }

    public int getORF1size() {
        return ORF1.size();
    }

    public int getORF2size() {
        return ORF2.size();
    }

    public int getORF3size() {
        return ORF3.size();
    }

}





