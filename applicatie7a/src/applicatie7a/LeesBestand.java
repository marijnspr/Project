package applicatie7a;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Michelle on 28-3-2017.
 */
public class LeesBestand {
    String sequentie = null;
    String header;

    /** 
     * functie: lezen van het bestand en plaatsen in textarea.
     * known bugs: niet aanwezig.
     * @param filepathway de pathway naar de locatie van de file.
     * @throws applicatie7a.geenFastaException exception die wordt opgeworpen wanneer het ingevoerde bestnd geen fasta is.
     */
    public void readFile(String filepathway)throws geenFastaException, geenDNAsequentie, sequentieTeLang {
        try {
            BufferedReader inFile = new BufferedReader(
                    new FileReader(filepathway));
            String line;
            header = inFile.readLine();
            if(!header.startsWith(">")){
            throw new geenFastaException();
            }
            String DNA = ".*[ATCGN].*";
            while ((line = inFile.readLine()) != null) {
                if(!line.toUpperCase().matches(DNA)){
                    throw new geenDNAsequentie();
                }
                if(sequentie == null){
                    sequentie = line;
                }
                else{
                sequentie = sequentie + line;
                if(sequentie.length() > 4000){
                    throw new sequentieTeLang();
                }
            }}
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "cant find file");
        }
    }

    /**
     * @return een String type met de nucleotidesequentie uit het bestand.
     */
    public String getSequentie() {
        return sequentie;
    }

    /**
     * @return een String type met de header van het fasta bestand.
     */
    public String getheader() {
        return header;
    }
}
class geenFastaException extends Exception{
    JFrame j1;
      public geenFastaException()
      {
       super();
       JOptionPane.showMessageDialog(j1,"Dit is geen fasta bestand","Fasta error",JOptionPane.ERROR_MESSAGE);
      }
 }
class geenDNAsequentie extends Exception{
    JFrame j1;
    public geenDNAsequentie(){
        super();
        JOptionPane.showMessageDialog(j1,"Dit is geen DNA sequentie", "DNA error", JOptionPane.ERROR_MESSAGE);
    }
}
class sequentieTeLang extends Exception{
    JFrame j1;
    public sequentieTeLang(){
        super();
        JOptionPane.showMessageDialog(j1,"Deze sequentie bevat meer dan 4000 nucleotiden" + "\n" + "probeer een kortere sequentie", "Lengte error", JOptionPane.ERROR_MESSAGE);
    }
}