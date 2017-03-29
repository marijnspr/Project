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

    public void readFile(String filepathway) {
        try {
            BufferedReader inFile = new BufferedReader(
                    new FileReader(filepathway));
            String line;
            header = inFile.readLine();
            while ((line = inFile.readLine()) != null) {
                sequentie = sequentie + line;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "cant find file");
        }
    }

    public String getSequentie() {
        return sequentie;
    }

    public String getheader() {
        return header;
    }
}
