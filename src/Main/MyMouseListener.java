package Main;

import Database.DNSHosting;
import Database.DatabaseDAO;
import TabModels.NameServerTableModel;
import com.intersys.objects.CacheException;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * Listens for event when a row in the list of name servers table is clicked.
 * Then, it creates a JFrane GUI window to show hidden information about the
 * Name Server.
 * 
 * @author rahulsingh
 */
public class MyMouseListener implements MouseListener {
    // represents the TableModel on which mouse was clicked.
    private NameServerTableModel nsmodel;
    private JTable table; // represents the table on which mouse was clicked.
    private DatabaseDAO dbDao; // required for retrieving NameServer object.
    private JFrame frame; // The frame displayed when the mouse is clicked.

    /**
     * Constructor for MyMouseListener. Passes the Table as a
     * parameter. Can then be used to determine the row that was
     * clicked.
     * @param nsmodel 
     * @param table
     */
    public MyMouseListener(NameServerTableModel nsmodel, JTable table, 
            DatabaseDAO dbDao) {
        this.nsmodel = nsmodel;
        this.table = table;
        this.dbDao = dbDao;
    }

    /**
     * Called when the mouse is clicked in the GUI table for the list
     * of Name Servers.
     * @param e 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        System.out.println("Row: " + selectedRow);
        Integer id = (Integer) nsmodel.getValueAt(selectedRow, 0);
        DNSHosting dnsh = dbDao.getDNSHosting(id);
        createFrame(e, dnsh); // Create the JFrame window.
    }

    /**
     * 
     * @param e
     * @param dnsh 
     */
    private void createFrame(MouseEvent e, DNSHosting dnsh) {
        try {
            frame = new JFrame("Name Server Details");
            frame.setSize(200, 100);
            frame.setLayout(new FlowLayout());
            frame.add(new JLabel("Allow-Update: "));
            frame.add(new JLabel(dnsh.getallowUpdate().toString()));
            frame.add(new JLabel("Allow-Query: "));
            frame.add(new JLabel(dnsh.getallowQuery().toString()));
            frame.add(new JLabel("Allow-Transfer: "));
            frame.add(new JLabel(dnsh.getallowTransfer().toString()));
            frame.setLocation(e.getXOnScreen(), e.getYOnScreen());
            frame.setVisible(true);
        } catch (CacheException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    // All methods below unused.
    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
