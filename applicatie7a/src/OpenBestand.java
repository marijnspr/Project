import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Michelle on 28-3-2017.
 */
public class OpenBestand extends Component {
    JFileChooser fileChooser;
    File selectedFile;
    int reply;

    public void openFile() {
        fileChooser = new JFileChooser();
        reply = fileChooser.showOpenDialog(this);
        if (reply == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
    }
    public String getfilepathway() {return selectedFile.getAbsolutePath();}
}

