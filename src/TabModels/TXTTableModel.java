/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TabModels;

import Database.DatabaseDAO;
import Database.TXT;
import com.intersys.objects.CacheException;
import java.util.ArrayList;
import java.util.List;

/**
 * Table Model for the Textual Info tab.
 * 
 * Model part of the Swing Table MVC architecture.
 *
 * @author rahulsingh
 */
public class TXTTableModel extends GenericTableModel{
    
    /**
     * Constructor for TXTTableModel. Sets the table columns and rows.
     * Uses a TXTRow object to represent a row in the table. Table data
     * shown in the table depends on the String value of the domainName 
     * parameter.
     * 
     * @param dbDao
     * @param domainName 
     */
    public TXTTableModel(DatabaseDAO dbDao, String domainName){
        this.setDbDAO(dbDao);
        this.setDomainName(domainName);
        columnNames = new String[5]; //array of column names.
        columnNames[0] = "ID";
        columnNames[1] = "TTL";
        columnNames[2] = "Hostname";
        columnNames[3] = "Class";
        columnNames[4] = "Information";
        rows = setTableData();
    }

    /**
     * Sets up the Table data by retrieving the TXT records from the 
     * Cache database. The TXT records chosen to be displayed in the table
     * are determined by the domain name String value. 
     * 
     * @return List of table rows to be displayed.
     */
    @Override
    public List setTableData() {
        List<TXTRow> tableRows = null;
        try {
            tableRows = new ArrayList<TXTRow>();
            List<TXT> txts = this.getDbDAO().getTXTs(this.getDomainName());
            TXTRow row = null;
            for (TXT txt : txts){
                row = new TXTRow(txt.getid(), txt.gettimeToLive(), 
                        txt.gethostName(), txt.getclassOfData(), 
                        txt.getinformation());
                tableRows.add(row);
            }
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return tableRows;
    }

    /**
     * 
     * @param r
     * @param c
     * @return Object at particular cell.
     */
    @Override
    public Object getValueAt(int r, int c) {
        TXTRow row = (TXTRow) this.getRows().get(r);
        switch (c){
            case 0:
                return row.getId();
            case 1:
                return row.getTtl();
            case 2:
                return row.getHostname();
            case 3:
                return row.getClassOfData();
            case 4:
                return row.getInformation();
            default:
                return null;
        }
    }
}
