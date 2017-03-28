import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Michelle on 27-3-2017.
 */
public class MainGUI extends JFrame implements ActionListener{

    public JPanel gegevensPanel;
    public JButton bladerButton, openButton, voorspelButton,
                    exportDatabaseButton;
    public JTextField filePathway;
    public JTextArea sequentieField;
    public JLabel fileLabel, sequentieLabel, gegevensLabel, rf1Label, rf2Label, rf3Label;
    VerwerkBestand bestand = new VerwerkBestand();
    OpenBestand openen = new OpenBestand();
    LeesBestand lezen = new LeesBestand();

    public static void main(String[] args){
        MainGUI frame = new MainGUI();
        frame.setSize(600,600);
        frame.GUI();
        frame.setVisible(true);
        frame.setTitle("ORF voorspeller");
        frame.show();
    }

    public void GUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        fileLabel = new JLabel("file: ");
        window.add(fileLabel);

        filePathway = new JTextField(30);
        window.add(filePathway);

        bladerButton = new JButton("blader");
        window.add(bladerButton);
        bladerButton.addActionListener(this);

        openButton = new JButton("open");
        window.add(openButton);
        openButton.addActionListener(this);

        sequentieLabel = new JLabel("DNA sequentie ");
        window.add(sequentieLabel);

        sequentieField = new JTextArea();
        sequentieField.setPreferredSize(new Dimension(450,150));
        sequentieField.setLineWrap(true
        );
        window.add(sequentieField);

        voorspelButton = new JButton("voorspel ORF's");
        window.add(voorspelButton);
        voorspelButton.addActionListener(this);

        gegevensLabel = new JLabel("gegevens ORF's ");
        gegevensLabel.setPreferredSize(new Dimension(500,20));
        window.add(gegevensLabel);

        gegevensPanel = new JPanel();
        gegevensPanel.setPreferredSize(new Dimension(500,100));
        gegevensPanel.setLayout(new BorderLayout());
        rf1Label = new JLabel();
        rf2Label = new JLabel();
        rf3Label = new JLabel();
        rf1Label.setText("Aantal ORF's readingframe +1: ");
        rf2Label.setText("Aantal ORF's readingframe +2: ");
        rf3Label.setText("Aantal ORF's readingframe +3: ");
        rf1Label.setBounds(0,0,400,25);
        rf2Label.setBounds(0,20,400,25);
        rf3Label.setBounds(0,50,400,25);
        gegevensPanel.add(rf1Label);
        gegevensPanel.add(rf2Label);
        gegevensPanel.add(rf3Label);
        setVisible(true);
        window.add(gegevensPanel);

        exportDatabaseButton = new JButton("export naar database");
        window.add(exportDatabaseButton);
        exportDatabaseButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == bladerButton) {
            openen.openFile();
            filePathway.setText(openen.getfilepathway());
            }

        if (event.getSource() == openButton) {
            lezen.readFile(filePathway.getText());
            sequentieField.append(lezen.getSequentie());
        }

        if (event.getSource() == voorspelButton){
            bestand.orfVoorspeller(sequentieField.getText());
            rf1Label.setText("Aantal ORF's readingframe +1: " + bestand.getORF1size());
            rf2Label.setText("Aantal ORF's readingframe +2: " + bestand.getORF2size());
            rf3Label.setText("Aantal ORF's readingframe +3: " + bestand.getORF3size());
            gegevensPanel.add(rf1Label);
            gegevensPanel.add(rf2Label);
            gegevensPanel.add(rf3Label);
            gegevensPanel.updateUI();
        }
    }
}
