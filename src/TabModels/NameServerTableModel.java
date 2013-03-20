package TabModels;

import Database.DNSHosting;
import Database.DatabaseDAO;
import Database.NameServer;
import com.intersys.objects.CacheException;
import java.util.ArrayList;
import java.util.List;

/**
 * Allow java.util.List objects to be used instead of java.util.Vector.
 * Follows the MVC pattern. This becomes the Model. 
 * Model is responsible for the provision of data.
 *
 * @author rahulsingh
 */
public class NameServerTableModel extends GenericTableModel {
    
   /**
     * 
     * @param dbDAO
     * @param domName 
     * Sets up a Table Model to be displayed. Table Model is determined
     * by the data in the database given the parameter domain name.
     */
    public NameServerTableModel(DatabaseDAO dbDAO, String domName){
        this.setDbDAO(dbDAO);
        this.setDomainName(domName);
        columnNames = new String[6]; // Array of column names.
        columnNames[0] = "ID";
        columnNames[1] = "Hostname";
        columnNames[2] = "IP Address";
        columnNames[3] = "TTL";
        columnNames[4] = "Class";
        columnNames[5] = "Type";
        rows = setTableData();
    }
    
    /**
     * Sets up the Data to be displayed in the Table.
     * 
     * @return List of Rows for the Table. 
     */
    @Override
    public List<NameServerRow> setTableData() {
        List<NameServer> nameServers = getDbDAO().getNameServers(getDomainName());
        List<NameServerRow> rowsOfData = new ArrayList<NameServerRow>();
        NameServerRow row = null;
        for (NameServer ns : nameServers){
            DNSHosting dnsh = null;
            try {
                dnsh = this.getDbDAO().getDNSHosting(getDomainName(), ns.gethostname());
                row = new NameServerRow(dnsh.getid(), ns.gethostname(),
                        ns.getipAddress(), ns.getttl(), ns.get_class(), dnsh.gettype());
                rowsOfData.add(row);
            } catch (CacheException ex){
                System.out.println(ex.getMessage());
            } 
        }
        return rowsOfData;
    }  
    
    /**
     * 
     * @param r
     * @param c
     * @return Object of a table cell. 
     */
    @Override
    public Object getValueAt(int r, int c) {
        NameServerRow row = (NameServerRow) this.getRows().get(r);
        switch (c) {
            case 0:
                return row.getId();
            case 1:
                return row.getHostname();   
            case 2:
                return row.getIpAddress();
            case 3:
                return String.valueOf(row.getTtl());
            case 4:
                return row.getClassOfData();
            case 5:
                return row.getType();
            default:
                return null;
        } 
        
    }
}
