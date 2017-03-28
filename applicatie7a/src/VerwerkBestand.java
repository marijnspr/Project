
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Michelle on 27-3-2017.
 */
public class VerwerkBestand {

    HashSet<String> ORF1 = new HashSet<>(); //Alle ORF's
    HashSet<String> ORF2 = new HashSet<>(); // ""
    HashSet<String> ORF3 = new HashSet<>(); // ""
    HashMap<String, HashSet<String>> alleORF1 = new HashMap<>(); // Alle ORF's gelinkt aan hun readingframe
    HashMap<String, HashSet<String>> alleORF2 = new HashMap<>(); // ""
    HashMap<String, HashSet<String>> alleORF3 = new HashMap<>(); // ""

    public void orfVoorspeller(String seqDNA) {
        System.out.println(seqDNA);
        HashSet<String> stopcodon = new HashSet<>();
        stopcodon.add("TAC");
        stopcodon.add("TGA");
        stopcodon.add("TAA");
        String startcodon = "ATG";

        for(int i = 0; i<seqDNA.length()-2;i+=3){
            String startpos;
            String stoppos;
            String readingframe = "+1";
            String codon = null;
            codon = seqDNA.substring(i, i+3);
     //       System.out.println("c1 " +codon);
            if(codon.equals(startcodon)){
                startpos = Integer.toString(i);
                String ORF = null;
                for(int y = i+=3; y<seqDNA.length()-2; y+=3){
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y+3);
            //        System.out.println("c2 " +codon2);
                    if (stopcodon.contains(codon2)){
                        System.out.println("wel stop");
                        Integer.toString(y);
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                    }
                    else {
            //            System.out.println("geen stop");
                        if(ORF == null){
                            ORF = codon + codon2;
                        }
                        else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                new ORF(startpos, startpos, readingframe, ORF);
                ORF1.add(ORF);
                alleORF1.put(readingframe, ORF1);
           //     System.out.println(ORF);
            }

        }
        /**
         * Readingframs +2
         */
        for(int i = 1; i<seqDNA.length()-2;i+=3){
            String startpos;
            String stoppos;
            String readingframe = "+2";
            String codon = null;
            codon = seqDNA.substring(i, i+3);
    //        System.out.println("c1 " +codon);
            if(codon.equals(startcodon)){
                startpos = Integer.toString(i);
                String ORF = null;
                for(int y = i+=3; y<seqDNA.length()-2; y+=3){
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y+3);
             //       System.out.println("c2 " +codon2);
                    if (stopcodon.contains(codon2)){
                        Integer.toString(y);
                        System.out.println("wel stop");
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                    }
                    else {
             //           System.out.println("geen stop");
                        if(ORF == null){
                            ORF = codon + codon2;
                        }
                        else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                new ORF(startpos, startpos, readingframe, ORF);
                ORF2.add(ORF);
                alleORF2.put(readingframe, ORF2);
            //    System.out.println(ORF);
            }
        }
        /**
         * Readingframe +3
         */
        for(int i = 2; i<seqDNA.length()-2;i+=3){
            String startpos;
            String stoppos;
            String readingframe = "+3";
            String codon = null;
            codon = seqDNA.substring(i, i+3);
     //       System.out.println("c1 " +codon);
            if(codon.equals(startcodon)){
                startpos = Integer.toString(i);
                String ORF = null;
                for(int y = i+=3; y<seqDNA.length()-2; y+=3){
                    String codon2 = null;
                    codon2 = seqDNA.substring(y, y+3);
                //    System.out.println("c2 " +codon2);
                    if (stopcodon.contains(codon2)){
                        Integer.toString(y);
                        System.out.println("wel stop");
                        ORF = ORF + codon2;
                        y = seqDNA.length();
                    }
                    else {
                //        System.out.println("geen stop");
                        if(ORF == null){
                            ORF = codon + codon2;
                        }
                        else {
                            ORF = ORF + codon2;
                        }
                    }
                }
                new ORF(startpos, startpos, readingframe, ORF);
                ORF3.add(ORF);
                alleORF3.put(readingframe, ORF3);
              //  System.out.println(ORF);
            }
        }
        System.out.println("ORF 1: " +ORF1);
        System.out.println("ORF 2: " +ORF2);
        System.out.println("ORF 3: " +ORF3);
    }
    public int getORF1size() {return ORF1.size();}
    public int getORF2size() {return ORF2.size();}
    public int getORF3size() {return ORF3.size();}

    public HashMap<String, HashSet<String>> getORF1() {return alleORF1;}
    public HashMap<String, HashSet<String>> getORF2() {return alleORF2;}
    public HashMap<String, HashSet<String>> getORF3() {return alleORF3;}
}




