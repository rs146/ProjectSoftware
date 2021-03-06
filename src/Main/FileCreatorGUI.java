/*
 * FileCreatorGUI.java
 *
 * Created on Jan 9, 2013, 3:49:49 PM
 */
package Main;

import Database.DatabaseDAO;
import FileCreators.ForwardMappingCreator;
import FileCreators.ReverseMappingCreator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The GUI that allows the user to create the BIND files for a zone.
 * @author rahulsingh
 */
public class FileCreatorGUI extends JFrame {
    private String domainName; // domainName selected from DnsGui is passed to this var.
    private DatabaseDAO dbDao; // dbDao to be passed.

    /** Creates new form FileCreatorGUI */
    public FileCreatorGUI(String domainName, DatabaseDAO dbDao) {
        initComponents();
        this.domainName = domainName;
        this.dbDao = dbDao;
        createButtonListener(); // regeister button for event when clicked.
    }
    
    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName
     * Sets the domainName.
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    
    /**
     * @return the dbDao
     */
    public DatabaseDAO getDbDao() {
        return dbDao;
    }

    /**
     * @param dbDao the dbDao to set
     */
    public void setDbDao(DatabaseDAO dbDao) {
        this.dbDao = dbDao;
    }
    
    /**
     * Handles the event when the 'Create' Button is pressed.
     * Uses ActionListener interface and overrides actionPerformed().
     */
    private void createButtonListener(){
        create.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ae) {
                if ("".equals(directory.getText())){
                    genericAlert("Please enter a directory");
                } else if ("".equals(forwardFileName.getText())){
                    genericAlert("Please enter a file name for the Forward "
                            + "File");
                } else if ("".equals(reverseFileName.getText())){
                    genericAlert("Please enter a file name for the Reverse "
                            + "File");
                } else {
                    // Create the Forward Mapping BIND file for the zone,
                    // identified by it's domain name.
                    ForwardMappingCreator fmc = 
                            new ForwardMappingCreator(domainName, dbDao, 
                                    directory.getText(), 
                                    forwardFileName.getText());
                    fmc.createFile();
                    // Create Reverse Mapping BIND file for the zone.
                    ReverseMappingCreator rmc = null;
                    if ("".equals(reverseDomName.getText())){
                        genericAlert("A reverse domain name has automatically "
                                + "been generated");
                        rmc = new ReverseMappingCreator(domainName, dbDao, 
                                directory.getText(), reverseFileName.getText());
                    } else {
                        rmc = new ReverseMappingCreator(domainName, dbDao,
                                directory.getText(), reverseFileName.getText(),
                                reverseDomName.getText());
                    }
                    rmc.createFile();
                    genericAlert("The files have been created in the "
                            + "directory: " + directory.getText());
                    setVisible(false);
                }
            }
        });
    }
    
    /**
     * Produces an alert when required.
     * The message passed to the parameter is the message that
     * will be displayed to the user.
     * @param message
     */
    public void genericAlert(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    /** 
     * GENERATED CODE.
     * This method is called from within the constructor to
     * initialize the form. 
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        labelChooseDir = new javax.swing.JLabel();
        directory = new javax.swing.JTextField();
        labelFMapping = new javax.swing.JLabel();
        forwardFileName = new javax.swing.JTextField();
        labelRMapping = new javax.swing.JLabel();
        reverseFileName = new javax.swing.JTextField();
        create = new javax.swing.JButton();
        rZoneLabel = new javax.swing.JLabel();
        reverseDomName = new javax.swing.JTextField();

        title.setText("Create BIND Files");

        labelChooseDir.setText("Choose Directory:");

        labelFMapping.setText("Name of the Forward Mapping File:");

        labelRMapping.setText("Name of the Reverse Mapping File:");

        create.setText("Create");

        rZoneLabel.setText("Name of the Reverse Zone:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(287, 287, 287)
                .add(title)
                .addContainerGap(218, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(49, 49, 49)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(create)
                            .add(rZoneLabel)
                            .add(labelChooseDir)
                            .add(labelFMapping))
                        .add(13, 13, 13))
                    .add(layout.createSequentialGroup()
                        .add(labelRMapping)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(forwardFileName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .add(directory, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .add(reverseDomName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .add(reverseFileName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                .add(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(title)
                .add(36, 36, 36)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(directory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelChooseDir))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(reverseDomName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(rZoneLabel))
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelFMapping)
                    .add(forwardFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(reverseFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(labelRMapping, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(34, 34, 34)
                .add(create)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create;
    private javax.swing.JTextField directory;
    private javax.swing.JTextField forwardFileName;
    private javax.swing.JLabel labelChooseDir;
    private javax.swing.JLabel labelFMapping;
    private javax.swing.JLabel labelRMapping;
    private javax.swing.JLabel rZoneLabel;
    private javax.swing.JTextField reverseDomName;
    private javax.swing.JTextField reverseFileName;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

}
