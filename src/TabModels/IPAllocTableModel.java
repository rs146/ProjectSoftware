package TabModels;

import Database.DatabaseDAO;
import Database.FandRMapping;
import com.intersys.objects.CacheException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Table Model for the IP Allocations tab.
 * 
 * Forms the model part of the Swing Table MVC 
 * architecture.
 *
 * @author rahulsingh
 */
public class IPAllocTableModel extends GenericTableModel{
    
    /**
     * Constructor for this Table Model. Sets up table columns and 
     * table data.
     * 
     * @param dbDao
     * @param domainName 
     */
    public IPAllocTableModel(DatabaseDAO dbDao, String domainName){
        this.setDbDAO(dbDao);
        this.setDomainName(domainName);
        columnNames = new String[7]; // Array of column names.
        columnNames[0] = "ID";
        columnNames[1] = "TTL";
        columnNames[2] = "Hostname";
        columnNames[3] = "IP Address";
        columnNames[4] = "IP Address id";
        columnNames[5] = "Subnet Mask";
        columnNames[6] = "Network Name";
        rows = setTableData();
    }
    
    /**
     * Returns a list of rows for the table.
     * @return List.
     */
    @Override
    public List setTableData() {
        List<IPAllocRow> rowsOfData = null;
        try {
            List<FandRMapping> frmappings = 
                this.getDbDAO().getFandRMappings(this.getDomainName());
            rowsOfData = new ArrayList<IPAllocRow>();
            if (!frmappings.isEmpty()){
                IPAllocRow row = null;
                for (FandRMapping fr : frmappings){
                    Map<Integer, String> ipaddrs = this.getDbDAO().getIPAddressesFandR(fr);
                    Iterator iterator = ipaddrs.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry pairs = (Map.Entry)iterator.next();
                        String ipAddr = (String) pairs.getValue();
                        Integer ipAddrId = (Integer) pairs.getKey();
                        String[] strArr = this.getDbDAO().getSubnetAndNetworkName(ipAddr);
                        row = new IPAllocRow(fr.getid(), fr.gettimeToLive(),
                            fr.gethostName(), ipAddr, ipAddrId, strArr[0], strArr[1]);
                        rowsOfData.add(row);
                    }
                }
            }
        } catch (CacheException ex){
            System.err.print(ex.getMessage());
        }
        return rowsOfData;
    }
    
    /**
     * 
     * @param r
     * @param c
     * @return Object at particular cell in the table.
     */
    @Override
    public Object getValueAt(int r, int c) {
        IPAllocRow row = (IPAllocRow) this.getRows().get(r);
        switch (c) {
            case 0:
                return String.valueOf(row.getId());
            case 1:
                return String.valueOf(row.getTtl());
            case 2:
                return row.getHostname();
            case 3:
                return row.getIpaddress();
            case 4:
                return row.getIpaddressId();
            case 5:
                return row.getSubmask();
            case 6:
                return row.getNetname();
            default:
                return null;
        }
    }
    
    /**
     * Allows user to edit IP Addresses. These are rows below 0 that
     * are in column 3 (4th column).
     * @param row
     * @param col
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int col){
        if ((col == 3) && (row > 0)){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Sets value of the table row in accord with the change made by a user.
     * @param value
     * @param row
     * @param col 
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
        IPAllocRow aRow = (IPAllocRow) this.getRows().get(row);
        String newIpAddress = (String) value;
        aRow.setIpaddress(newIpAddress);
        fireTableCellUpdated(row, col);
    }
}
