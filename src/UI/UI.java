package UI;

import Model.Grafo;
import Persistance.CustomOutputStream;
import Persistance.DataLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static Model.Grafo.NTRIPLE;
import static Model.Grafo.TURTTLE;
import static Model.Grafo.XML;

public class UI {
    public int selected = TURTTLE;

    private Grafo grafo;
    private JPanel contentPane;
    private JButton okButton;
    private JRadioButton turttleRadioButton;
    private JRadioButton XMLRadioButton;
    private JRadioButton nTripleRadioButton;
    private JTextField outputPath;
    private JPanel typePanel;
    private JPanel filePanel;
    private JPanel actionPanel;
    private JButton fileChooserButton;
    private JTextArea serialArea;
    private JScrollPane scrollPane;
    private JTextField outputFileName;

    public UI() {
        System.setProperty("javax.net.ssl.trustStore", "keystore.jks");
        grafo = DataLoader.cargaGrafo();
        outputPath.setText(System.getProperty("user.dir"));
        fileChooserButton.addActionListener(filechooserListener());
        turttleRadioButton.addActionListener(typeStateChange(turttleRadioButton, TURTTLE));
        XMLRadioButton.addActionListener(typeStateChange(XMLRadioButton, XML));
        nTripleRadioButton.addActionListener(typeStateChange(nTripleRadioButton, NTRIPLE));
        okButton.addActionListener(okActionListener());
    }

    private ActionListener okActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file = outputPath.getText() + "\\" + outputFileName.getText();
                serialArea.setText(null);
                try {
                    CustomOutputStream output = new CustomOutputStream(serialArea, file);
                    grafo.print(output, selected);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        };
    }

    private ActionListener typeStateChange(JRadioButton button, int state) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.isSelected()) selected = state;
            }
        };
    }

    private ActionListener filechooserListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int open = fc.showOpenDialog(contentPane);
                if (open == JFileChooser.APPROVE_OPTION) {
                    outputPath.setText(fc.getSelectedFile().getAbsolutePath());
                }
            }
        };
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tarea 1. Juan Francisco Pérez");
        frame.setContentPane(new UI().contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
