package TabModels;

import Database.DatabaseDAO;
import Database.MX;
import Database.SRV;
import com.intersys.objects.CacheException;
import java.util.ArrayList;
import java.util.List;

/**
 * Table Model for the 'Mail/Services' tab.
 * 
 * Model part of the Swing Table MVC architecture.
 *
 * @author rahulsingh
 */
public class MailServicesTableModel extends GenericTableModel{
    
    /**
     * Constructor for this Table Model. Sets table columns and table data,
     * using MailServicesRow objects.
     * 
     * @param dbDao
     * @param domainName 
     */
    public MailServicesTableModel(DatabaseDAO dbDao, String domainName){
        this.setDbDAO(dbDao);
        this.setDomainName(domainName);
        columnNames = new String[10]; // Array of column names.
        columnNames[0] = "ID";
        columnNames[1] = "TTL";
        columnNames[2] = "Hostname";
        columnNames[3] = "Class";
        columnNames[4] = "Preference";
        columnNames[5] = "Service";
        columnNames[6] = "Protocol";
        columnNames[7] = "Weight";
        columnNames[8] = "Port";
        columnNames[9] = "SRV";
        rows = this.setTableData();
    }

    /**
     * Sets table data using DatabaseDAO. Must get all MX and SRV objects.
     * Pointer: SRV is a subclass of MX.
     * @return List of rows to be displayed in the table.
     */
    @Override
    public List setTableData() {
        List<MailServicesRow> dataRows = null;
        try {
            dataRows = new ArrayList<MailServicesRow>();
            List<MX> mXs = this.getDbDAO().getMXs(this.getDomainName());
            MailServicesRow row = null;
            for (MX mx : mXs){
                row = new MailServicesRow(mx.getid(), mx.gettimeToLive(),
                        mx.gethostName(), mx.getclassOfData(), 
                        mx.getpreference(), mx.getsrv());
                dataRows.add(row);
            }
            List<SRV> sRVs = this.getDbDAO().getSRVs(this.getDomainName());
            for (SRV srv : sRVs){
                row = new MailServicesRow(srv.getid(), srv.gettimeToLive(),
                        srv.gethostName(), srv.getclassOfData(), 
                        srv.getpreference(), srv.getservice(), srv.getprotocol(),
                        srv.getweight(), srv.getport(), srv.getsrv());
                dataRows.add(row);
            }
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return dataRows;
    }
    
    /**
     * 
     * @param r
     * @param c
     * @return Object at particular cell in the table.
     */
    @Override
    public Object getValueAt(int r, int c) {
        MailServicesRow row = (MailServicesRow) this.getRows().get(r);
        switch (c) {
            case 0:
                return String.valueOf(row.getId());
            case 1:
                return String.valueOf(row.getTtl());
            case 2:
                return row.getHostname();
            case 3:
                return row.getClassOfData();
            case 4:
                return String.valueOf(row.getPreference());
            case 5:
                return String.valueOf(row.getService());
            case 6:
                return row.getProtocol();
            case 7:
                return row.getWeight();
            case 8:
                return String.valueOf(row.getPort());
            case 9:
                return String.valueOf(row.isSrv());
            default:
                return null;
        }
    }
}
