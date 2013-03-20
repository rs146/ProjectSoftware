/*
 * DnsGui.java
 * Created on Jan 1, 2013, 4:46:44 PM
 */
package Main;

import Database.DatabaseDAO;
import TabModels.IPAllocTableModel;
import TabModels.MailServicesTableModel;
import TabModels.NameServerTableModel;
import TabModels.NicknamesTableModel;
import TabModels.TXTTableModel;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Main entry point for the Project's software. GUI.
 * All generated code using the NetBeans GUI Swing Builder
 * is shown in the comments.
 *
 * @author rahulsingh
 */
public class DnsGui extends JFrame {

    private JTree tree; // tree of domains.
    private DatabaseDAO dbDao; // Access the DatabaseDao.
    private String domainName; // Keep a record of the domainName selected in Tree.
    private Connection connection; // Allows SQL queries to be made on db.
    private Database db; // Contains database connection properties.
    private List<JScrollPane> tabs = new ArrayList<JScrollPane>(); // All tabs.

    /**
     * GENERATED CODE.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        tabbedPanel = new javax.swing.JTabbedPane();
        createButton = new javax.swing.JButton();
        domainsArea = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setText("BIND DNS Data");

        createButton.setText("Create BIND files");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(350, 350, 350)
                        .add(title))
                    .add(layout.createSequentialGroup()
                        .add(37, 37, 37)
                        .add(domainsArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(240, 240, 240)
                                .add(createButton))
                            .add(layout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(tabbedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 561, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(title)
                .add(31, 31, 31)
                .add(tabbedPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 428, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(createButton)
                .addContainerGap(31, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .add(domainsArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 405, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Generated instance variables for skeleton GUI.
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createButton;
    private javax.swing.JScrollPane domainsArea;
    private javax.swing.JTabbedPane tabbedPanel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    // All code below is not generated code.
    
    /**
     * Creates new form DnsGui.
     *
     */
    public DnsGui() {
        initComponents();
        initButton(); // initialise button to listen for events.
        initCloseWindow(); // initialise event for closing window.
        setUpDatabaseConnection();
        this.dbDao = new DatabaseDAO(this.getConnection(), this.getDb());
        createDomTree();
    }
    
    
    /**
     * Initialize button to listen for event when it has been clicked.
     * When it has been clicked, it must launch the FileCreatorGUI.
     */
    private void initButton(){
        createButton.addActionListener(new ActionListener(){

            /**
             * Check if a domain name has been selected
             * before file creation.
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                // domain name has been selected.
                if (getDomainName() != null){
                    FileCreatorGUI fcGui = new FileCreatorGUI(getDomainName(),
                            getDbDao());
                    fcGui.setVisible(true);
                } else {
                    produceAlert(); // Show an alert that domain not selected.
                }
            }

        });
    }
    
    /**
     * Produces an alert when a user has not selected a domain name
     * in the list of domains shown in the tree.
     */
    public void produceAlert(){
        JOptionPane.showMessageDialog(this,
                "Please select a domain from the list on the left.");
    }

    /**
     * Executed when the JFrame window closes. The aim of this method is
     * to close all objects used in the database and close the database 
     * connection.
     */
    private void initCloseWindow() {
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing database connection.");
                try {
                    getDb().closeAllObjects();
                    getDb().close();
                    getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                } catch (CacheException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        });

    }

    /**
     * @param args
     * Runs the GUI.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new DnsGui().setVisible(true);
            }
        });
    }

    /**
     * Creates the top level of the domains tree.
     */
    private void createDomTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Domains");
        createNodes(top);
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            /**
             * When user clicks a domain name in the Domains tree.
             */
            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                        tree.getLastSelectedPathComponent();

                if (node == null)//Nothing is selected.
                {
                    return;
                }
                if (node.isLeaf()) {
                    System.out.println("You have set selected " + node);
                    setDomainName(node.toString());
                    System.out.println(getDomainName());
                    System.out.println(tabbedPanel.getTabCount());
                    removeTabs();
                    createTabs();
                    addTabsToPanel();
                }
            }
        });
        this.domainsArea.setViewportView(tree);
    }
    
    /**
     * Retrieves all domains from the database.
     * All domains are then added to the top level
     * of the tree.
     * @param top
     */
    private void createNodes(DefaultMutableTreeNode top) {
        List<String> domainNames = this.getDbDao().getAllDomainNames();
        DefaultMutableTreeNode child = null;
        for (String domName : domainNames) {
            child = new DefaultMutableTreeNode(domName);
            top.add(child);
        }
    }

    /**
     * Creates each individual tab using the individual models for each tab.
     * Then, these tabs are added to the list of tabs instance variable of
     * this class.
     */
    public void createTabs(){
        // Add Name Servers tab to GUI panel with its table model.
        NameServerTableModel nsmodel = new NameServerTableModel(getDbDao(), getDomainName());
        nsmodel.fireTableDataChanged();
        JTable table1 = new JTable(nsmodel);
        // handle mouse event for revealing hidden info about a Name Server.
        // MyMouseListener implements the MouseListener interface.
        table1.addMouseListener(new MyMouseListener(nsmodel, table1, getDbDao()));
        getTabs().add(new JScrollPane(table1));

        // Add IP Allocations tab to GUI panel with its table model.
        IPAllocTableModel ipAllmodel = new IPAllocTableModel(getDbDao(), getDomainName());
        ipAllmodel.fireTableDataChanged();
        JTable table2 = new JTable(ipAllmodel);
        getTabs().add(new JScrollPane(table2));
        // Needs to listen for even when user changes IP Address of host.
        table2.getModel().addTableModelListener(new TableModelListener(){

            /**
             * Called when table has been changed.
             */
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                IPAllocTableModel model = (IPAllocTableModel) e.getSource();
                String newIPAddress = (String) model.getValueAt(row, column);
                Integer ipid = (Integer) model.getValueAt(row, 4);
                getDbDao().updateIPAddress(ipid, newIPAddress);
            }
        });

        // Add Mail/Services tab to GUI panel with its table model.
        MailServicesTableModel msModel = new MailServicesTableModel(getDbDao(), 
                getDomainName());
        msModel.fireTableDataChanged();
        JTable table3 = new JTable(msModel);
        getTabs().add(new JScrollPane(table3));

        // Add Nicknames tab to GUI panel with its table model.
        NicknamesTableModel nkModel = new NicknamesTableModel(getDbDao(), getDomainName());
        nkModel.fireTableDataChanged();
        JTable table4 = new JTable(nkModel);
        getTabs().add(new JScrollPane(table4));

        // Add Textual Info tab to GUI panel with its table model.
        TXTTableModel txtModel = new TXTTableModel(getDbDao(), getDomainName());
        txtModel.fireTableDataChanged();
        JTable table5 = new JTable(txtModel);
        getTabs().add(new JScrollPane(table5));
    }

    /**
     * Adds all JScrollPanels (instance variable: tabs)
     * to the tabbedPanel instance variable.
     */
    public void addTabsToPanel(){
        tabbedPanel.addTab("Name Servers", getTabs().get(0));
        tabbedPanel.addTab("IP Allocations", getTabs().get(1));
        tabbedPanel.addTab("Mail/Services", getTabs().get(2));
        tabbedPanel.addTab("Nicknames", getTabs().get(3));
        tabbedPanel.addTab("Textual Info", getTabs().get(4));
    }

    /**
     * Removes all tabs in the GUI window.
     */
    public void removeTabs() {
        while (tabbedPanel.getTabCount() > 0){
            for (int i = 4; i >= 0; i--){
                getTabs().remove(i);
                tabbedPanel.remove(i);
            }
        }
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
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return the connection
     */
    public final Connection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * @return the db
     */
    public final Database getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(Database db) {
        this.db = db;
    }

    /**
     * @return the tabs
     */
    public List<JScrollPane> getTabs() {
        return tabs;
    }

    /**
     * @param tabs the tabs to set
     */
    public void setTabs(List<JScrollPane> tabs) {
        this.tabs = tabs;
    }

    /**
     * Sets up the Connection and database properties of this class.
     */
    private void setUpDatabaseConnection() {
        Connection dbconn = null;
        Database dbase = null;
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");

            dbconn = ds.getConnection("_SYSTEM", "SYS");
            dbase = CacheDatabase.getDatabase(dbconn);
        } catch (CacheException ex) {
            System.err.print(ex.getMessage());
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.print(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.print(ex.getMessage());
        }
        this.setDb(dbase);
        this.setConnection(dbconn);
    }
}
