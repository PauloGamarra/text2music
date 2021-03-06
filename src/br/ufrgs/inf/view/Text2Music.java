package br.ufrgs.inf.view;

import br.ufrgs.inf.controller.Controller;
import br.ufrgs.inf.model.MyFile;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfugue.midi.MidiDictionary;

public class Text2Music extends javax.swing.JFrame {

    public Text2Music() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        this.status = Text2Music.STATUS_CONVERT;
        this.predefinedTimbre = new HashMap<String, Integer>();
        definedTimbre();
        insertComBoxTimbre();
        jSpinnerBPM.setValue(INITIAL_BPM);

    }

    private void definedTimbre() {

        MyFile file = new MyFile("utilfiles//instruments.txt");

        if (file.openFile(file)) {
            String contentFile = file.getContent();

            String contentFileInArray[];
            contentFileInArray = contentFile.split("\n");

            LinkedList<String> predefinedTimbreName;
            predefinedTimbreName = new LinkedList<>(Arrays.asList(contentFileInArray));

            for (String ptn : predefinedTimbreName) {
                this.predefinedTimbre.put(convertInitialLetterUpperCase(ptn
                        .substring(ptn.indexOf(" ")).replaceAll("_", " ").trim()),
                        Integer.parseInt(ptn.substring(0, ptn.indexOf(" "))));
            }

        } else {
            JOptionPane.showMessageDialog(this, "> instruments.txt file not found!");
            System.exit(0);
        }
    }

    private String convertInitialLetterUpperCase(String string) {

        String newString = "";

        for (String sNome : string.toLowerCase().split(" ")) {
            if (!"".equals(sNome)) {
                if (!"".equals(newString)) {
                    newString += " ";
                }

                if (sNome.length() > 2) {
                    newString += sNome.substring(0, 1).toUpperCase() + sNome.substring(1);
                } else {
                    newString += sNome;
                }
            }
        }
        return newString;
    }

    private void insertComBoxTimbre() {
        List<String> instrumentsNamesOrdened = new LinkedList<>();
        for (Map.Entry<String, Integer> pt : this.predefinedTimbre.entrySet()) {
            instrumentsNamesOrdened.add(pt.getKey());
        }

        Collections.sort(instrumentsNamesOrdened);

        for (String i : instrumentsNamesOrdened) {
            this.jComboBoxTimbre.addItem(i);
        }
    }

    public void setStatus(String status) {
        jLabelStatus.setText("Status: " + status);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinnerBPM = new javax.swing.JSpinner();
        jSliderVolume = new javax.swing.JSlider();
        jComboBoxTimbre = new javax.swing.JComboBox<String>();
        jLabelBPM = new javax.swing.JLabel();
        jLabelTimbre = new javax.swing.JLabel();
        jScrollPaneTextAreaMusicContent = new javax.swing.JScrollPane();
        jTextAreaMusicContent = new javax.swing.JTextArea();
        jButtonConvertPlay = new javax.swing.JButton();
        jLabelMusic = new javax.swing.JLabel();
        jLabelVolume = new javax.swing.JLabel();
        jSeparator = new javax.swing.JSeparator();
        jLabelStatus = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpenFile = new javax.swing.JMenuItem();
        jMenuExport = new javax.swing.JMenu();
        jMenuItemExportExportFile = new javax.swing.JMenuItem();
        jMenuItemExportExportMIDI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text2Music");

        jSpinnerBPM.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jSpinnerBPM.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 10));
        jSpinnerBPM.setToolTipText("");
        jSpinnerBPM.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerBPM, ""));
        jSpinnerBPM.setName(""); // NOI18N
        jSpinnerBPM.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                none(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jSpinnerBPM.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerBPMStateChanged(evt);
            }
        });
        jSpinnerBPM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jSpinnerBPMKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jSpinnerBPMKeyTyped(evt);
            }
        });

        jSliderVolume.setMaximum(127);

        jComboBoxTimbre.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        jComboBoxTimbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTimbreActionPerformed(evt);
            }
        });

        jLabelBPM.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelBPM.setText("BPM:");

        jLabelTimbre.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelTimbre.setText("Timbre:");

        jScrollPaneTextAreaMusicContent.setHorizontalScrollBar(null);

        jTextAreaMusicContent.setColumns(20);
        jTextAreaMusicContent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jTextAreaMusicContent.setLineWrap(true);
        jTextAreaMusicContent.setRows(5);
        jScrollPaneTextAreaMusicContent.setViewportView(jTextAreaMusicContent);

        jButtonConvertPlay.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jButtonConvertPlay.setText("Convert");
        jButtonConvertPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertPlayActionPerformed(evt);
            }
        });
        jButtonConvertPlay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonConvertPlayKeyPressed(evt);
            }
        });

        jLabelMusic.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        jLabelMusic.setText("Music:");

        jLabelVolume.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabelVolume.setText("Volume:");

        jLabelStatus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabelStatus.setText("Status: ");

        jMenuFile.setText("File");

        jMenuItemOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpenFile.setText("Open File");
        jMenuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpenFile);

        jMenuExport.setText("Export");

        jMenuItemExportExportFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemExportExportFile.setText("Export File");
        jMenuItemExportExportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExportExportFileActionPerformed(evt);
            }
        });
        jMenuExport.add(jMenuItemExportExportFile);

        jMenuItemExportExportMIDI.setText("Export MIDI");
        jMenuItemExportExportMIDI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExportExportMIDIActionPerformed(evt);
            }
        });
        jMenuExport.add(jMenuItemExportExportMIDI);

        jMenuFile.add(jMenuExport);

        jMenuBar.add(jMenuFile);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTextAreaMusicContent, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jSeparator)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTimbre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTimbre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabelBPM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerBPM))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonConvertPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelVolume)
                                    .addComponent(jSliderVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelStatus)
                            .addComponent(jLabelMusic))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinnerBPM, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBPM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTimbre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTimbre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabelMusic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTextAreaMusicContent, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelVolume)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSliderVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonConvertPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatus)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenFileActionPerformed
        // TODO add your handling code here:
        controller.openFile();
    }//GEN-LAST:event_jMenuItemOpenFileActionPerformed

    private void jMenuItemExportExportMIDIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExportExportMIDIActionPerformed
        try {
            // TODO add your handling code here:
            controller.saveMidi();
        } catch (IOException ex) {
            Logger.getLogger(Text2Music.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemExportExportMIDIActionPerformed

    private void jComboBoxTimbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTimbreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTimbreActionPerformed

    private void jSpinnerBPMStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerBPMStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jSpinnerBPMStateChanged

    private void jSpinnerBPMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinnerBPMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerBPMKeyPressed

    private void jSpinnerBPMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinnerBPMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerBPMKeyTyped

    private void jButtonConvertPlayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonConvertPlayKeyPressed

    }//GEN-LAST:event_jButtonConvertPlayKeyPressed

    private void convertMusic() {
        this.jButtonConvertPlay.setText("Loading...");

        this.setStatus("Converting... Please, Wait!");
        this.jLabelStatus.setForeground(Color.black);

        //  - Souts só para testar:
        System.out.println("--------------------------------------------");
        System.out.println("> BPM: " + jSpinnerBPM.getValue().toString());
        System.out.println("> Timbre: " + jComboBoxTimbre.getSelectedItem().toString());
        System.out.println("> Music: " + jTextAreaMusicContent.getText());
        System.out.println("> Volume: " + jSliderVolume.getValue());

        // -> Aqui chama função de conversão
        controller.convertSong();

        this.jButtonConvertPlay.setText("Play");

        this.setStatus("Converted with Success, Press Play!");
        this.jLabelStatus.setForeground(Color.green);
    }

    private void disableComponentesOfInput(boolean disable) {
        jSpinnerBPM.setEnabled(disable);
        jComboBoxTimbre.setEnabled(disable);
        jTextAreaMusicContent.setEnabled(disable);
        jButtonConvertPlay.setEnabled(disable);
        jSliderVolume.setEnabled(disable);
    }

    private void playMusic() {
        this.setStatus("Playing Music...");
        this.jLabelStatus.setForeground(Color.black);

        disableComponentesOfInput(false);

        // -> Aqui função de tocar a musica
        controller.playSong();

        this.jButtonConvertPlay.setText("Convert");
        this.setStatus("Success!");
        this.jLabelStatus.setForeground(Color.black);

        disableComponentesOfInput(true);
    }

    private void jButtonConvertPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertPlayActionPerformed

        if (!this.jTextAreaMusicContent.getText().isEmpty()) {
            switch (this.status) {
                case Text2Music.STATUS_CONVERT:
                    convertMusic();
                    this.status = Text2Music.STATUS_PLAY;
                    break;
                case Text2Music.STATUS_PLAY:
                    playMusic();
                    this.status = Text2Music.STATUS_CONVERT;
                    break;
            }
        } else {
            this.setStatus("Text Music is Empty!");
            this.jLabelStatus.setForeground(Color.red);
        }
    }//GEN-LAST:event_jButtonConvertPlayActionPerformed

    private void jMenuItemExportExportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExportExportFileActionPerformed
        // TODO add your handling code here:
        controller.saveFile();
    }//GEN-LAST:event_jMenuItemExportExportFileActionPerformed

    private void none(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_none
        // TODO add your handling code here:
    }//GEN-LAST:event_none

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Text2Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Text2Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Text2Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Text2Music.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Text2Music().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConvertPlay;
    private javax.swing.JComboBox<String> jComboBoxTimbre;
    private javax.swing.JLabel jLabelBPM;
    private javax.swing.JLabel jLabelMusic;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTimbre;
    private javax.swing.JLabel jLabelVolume;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuExport;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemExportExportFile;
    private javax.swing.JMenuItem jMenuItemExportExportMIDI;
    private javax.swing.JMenuItem jMenuItemOpenFile;
    private javax.swing.JScrollPane jScrollPaneTextAreaMusicContent;
    private javax.swing.JSeparator jSeparator;
    private javax.swing.JSlider jSliderVolume;
    private javax.swing.JSpinner jSpinnerBPM;
    private javax.swing.JTextArea jTextAreaMusicContent;
    // End of variables declaration//GEN-END:variables

    private int status;
    private final Map<String, Integer> predefinedTimbre;
    private Controller controller;

    private static final int STATUS_CONVERT = 0;
    private static final int INITIAL_OCTAVE = 5;
    private static final int STATUS_PLAY = 1;
    private static final int INITIAL_BPM = 250;

    public void setText(String content) {
        jTextAreaMusicContent.setText(content);
    }

    public String getText() {
        return jTextAreaMusicContent.getText();
    }

    public int getBPM() {
        return Integer.parseInt(jSpinnerBPM.getValue().toString());
    }

    public int getInstrument() {
        return MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(jComboBoxTimbre.getSelectedItem().toString().toUpperCase().replace(" ", "_"));
    }

    public int getVolume() {
        return jSliderVolume.getValue();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public int getInitOctave() {
        return INITIAL_OCTAVE;
    }

}
