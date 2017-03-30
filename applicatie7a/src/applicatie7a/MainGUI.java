package applicatie7a;

import applicatie7a.VerwerkBestand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Michelle on 27-3-2017.
 */
public class MainGUI extends JFrame implements ActionListener{

    public JPanel gegevensPanel;
    public JButton bladerButton, openButton, voorspelButton,
                    exportDatabaseButton;
    public JTextField filePathway, orfSize;
    public JTextArea sequentieField;
    public JLabel fileLabel, sequentieLabel, gegevensLabel, rf1Label, rf2Label, rf3Label, rf_1Label, rf_2Label, rf_3Label;
    VerwerkBestand bestand = new VerwerkBestand();
    ReverseORF reverse = new ReverseORF();
    OpenBestand openen = new OpenBestand();
    LeesBestand lezen = new LeesBestand();
    DatabaseConnectie dbcon = new DatabaseConnectie();

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

        orfSize = new JTextField(10);
        orfSize.setText("0");
        window.add(orfSize);

        voorspelButton = new JButton("voorspel ORF's");
        window.add(voorspelButton);
        voorspelButton.addActionListener(this);

        gegevensLabel = new JLabel("gegevens ORF's ");
        gegevensLabel.setPreferredSize(new Dimension(500,20));
        window.add(gegevensLabel);

        gegevensPanel = new JPanel();
        gegevensPanel.setPreferredSize(new Dimension(500,220));
        gegevensPanel.setLayout(new BorderLayout());
        rf1Label = new JLabel();
        rf2Label = new JLabel();
        rf3Label = new JLabel();
        rf_1Label = new JLabel();
        rf_2Label = new JLabel();
        rf_3Label = new JLabel();
        rf1Label.setText("Aantal ORF's readingframe +1: ");
        rf2Label.setText("Aantal ORF's readingframe +2: ");
        rf3Label.setText("Aantal ORF's readingframe +3: ");
        rf_1Label.setText("Aantal ORF's readingframe -1: ");
        rf_2Label.setText("Aantal ORF's readingframe -2: ");
        rf_3Label.setText("Aantal ORF's readingframe -3: ");
        rf1Label.setBounds(0,0,400,25);
        rf2Label.setBounds(0,20,400,25);
        rf3Label.setBounds(0,40,400,25);
        rf_1Label.setBounds(0,60,400,25);
        rf_2Label.setBounds(0,80,400,25);
        rf_3Label.setBounds(0,100,400,25);
        gegevensPanel.add(rf1Label);
        gegevensPanel.add(rf2Label);
        gegevensPanel.add(rf3Label);
        gegevensPanel.add(rf_1Label);
        gegevensPanel.add(rf_2Label);
        gegevensPanel.add(rf_3Label);
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
            gegevensPanel.removeAll();
            bestand.orfVoorspeller(sequentieField.getText().toUpperCase(), Integer.parseInt(orfSize.getText()), lezen.getheader());
            reverse.leesBestand(sequentieField.getText().toUpperCase(), Integer.parseInt(orfSize.getText()), lezen.getheader());
            rf1Label.setText("Aantal ORF's readingframe +1: " + bestand.getORF1size());
            rf2Label.setText("Aantal ORF's readingframe +2: " + bestand.getORF2size());
            rf3Label.setText("Aantal ORF's readingframe +3: " + bestand.getORF3size());
            rf_1Label.setText("Aantal ORF's readingframe -1: " +reverse.getORF_1size());
            rf_2Label.setText("Aantal ORF's readingframe -2: " + reverse.getORF_2size());
            rf_3Label.setText("Aantal ORF's readingframe -3: " + reverse.getORF_3size());
            gegevensPanel.add(rf1Label);
            gegevensPanel.add(rf2Label);
            gegevensPanel.add(rf3Label);
            gegevensPanel.add(rf_1Label);
            gegevensPanel.add(rf_2Label);
            gegevensPanel.add(rf_3Label);
            gegevensPanel.updateUI();
        }
        if(event.getSource() == exportDatabaseButton){
            try {
                dbcon.connectie(lezen.getheader(),bestand.getORFforward(), reverse.getORFreverse(),lezen.getSequentie());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
