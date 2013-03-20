package TabModels;

import Database.CNAME;
import Database.DatabaseDAO;
import com.intersys.objects.CacheException;
import java.util.ArrayList;
import java.util.List;

/**
 * Table Model for the Nicknames tab.
 * 
 * Model part of the Swing Table MVC architecture.
 *
 * @author rahulsingh
 */
public class NicknamesTableModel extends GenericTableModel {
    
    /**
     * Constructor for this Table Model. Sets table columns and rows.
     * Uses a NicknamesRow object to represent a row.
     * Table data is dependant on the domainName parameter.
     * 
     * @param dbDao
     * @param domainName 
     */
    public NicknamesTableModel(DatabaseDAO dbDao, String domainName){
        this.setDbDAO(dbDao);
        this.setDomainName(domainName);
        columnNames = new String[5]; // array of column names.
        columnNames[0] = "ID";
        columnNames[1] = "TTL";
        columnNames[2] = "Hostname";
        columnNames[3] = "Class";
        columnNames[4] = "Canonical Name";
        rows = setTableData();
    }

    /**
     * Sets up the data to be displayed in the table using all CNAME
     * records stored in the database. Only CNAME records that have a
     * matching domain name with that of the domainName attribute are shown
     * in the table.
     * @return List of tableRows of type List<NicknamesRow>. 
     */
    @Override
    public List setTableData() {
        List<NicknamesRow> tableRows = null;
        try {
            List<CNAME> cNames = this.getDbDAO().getCNAMEs(this.getDomainName());
            tableRows = new ArrayList<NicknamesRow>();
            NicknamesRow row = null;
            for (CNAME cName: cNames){
                row = new NicknamesRow(cName.getid(), cName.gettimeToLive(), 
                        cName.gethostName(), cName.getclassOfData(), 
                        cName.getnickname());
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
        NicknamesRow row = (NicknamesRow) this.getRows().get(r);
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
                return row.getNickname();
            default:
                return null;
        }
    }
}
