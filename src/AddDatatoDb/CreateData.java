/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AddDatatoDb;

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is used to create all the dummy data in the Cache database.
 * This will then be used in a BIND implementation service.
 *
 * @author rahulsingh
 */
public class CreateData {
    
    /**
     * 
     * @return Database instance of all db connection parameters.
     * @throws CacheException 
     * Creates a db connection to the Cache db using jdbc.
     */
    public static Database createConnection() throws CacheException{
        String url = "jdbc:Cache://192.168.1.172:56773/USER";
        String username = "_system";
        String pwd = "SYS";
        Database db = CacheDatabase.getDatabase(url, username, pwd);
        return db;
    }
    
    /**
     * 
     * @param args 
     * Uses the db connection to create all data in db.
     */
    public static void main(String[] args) throws CacheException{
        addZone();
    }
    
    public static void addZone(){
        Database db = null;
        try {
            db = createConnection();
        } catch (CacheException ex) {
            Logger.getLogger(CreateData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        NewZone nz = new NewZone(db);

        nz.addDomain("bcs.com", "rahul.singh.mac01.bcs.com.", "IN", 1209600, 21600, 1800, 2012100601, 43200);
        nz.addDnsHosting(1, "master", false, false, false, "bcs.com");
        nz.addNameServer("maci01.bcs.com.", "IN", "192.168.1.101", "1");
        nz.addARecord(1, "IN", "maci01.bcs.com.", "A");
        nz.addDomainData(1, "bcs.com", "1");
        nz.addIPAddress(1, "192.168.1.101", "static", "IPv4");
        nz.addIPAssignemt(1, "1", "1");
        nz.addSubnet(1, "Home", "First Floor", "24", "192.168.1.1", "1");
        nz.addNetwork(1, "Building 1", "192.168.1.0", "IPv4", "1");
        nz.addCname(2, "IN", "ubws01.bcs.com.", "ubuntuServer01");
        nz.addDomainDataCname(2, "bcs.com", "2");
        nz.addMX(3, "IN", "mail.bcs.com.", 1, false);
        nz.addDomainDataMX(3, "bcs.com", "3");
        nz.addSRV(4, "_http", "_tcp", "www", true, 0, 0, 80, "www-server.bcs.com.");
        nz.addDomainDataSRV(4, "bcs.com", "4");
        nz.addTXT("maci01", "IN", 5, "Location: First Floor", null);
        nz.addDomainDataTXT(5, "bcs.com", "5");
        
        NewZone nz1 = new NewZone(db);
        nz1.addDomain("bbt.com", "mail.bbt.com", "IN", 24000, 150000, 2400, 2012110501, 1800);
        nz1.addDnsHosting(2, "master", false, false, false, "bbt.com");
        nz1.addNameServer("ubws01.bbt.com.", "IN", "192.168.1.103", "2");
        nz1.addARecord(6, "IN", "ubws02.bbt.com.", "A");
        nz1.addDomainData(6, "bbt.com", "6");
        nz1.addIPAddress(2, "192.168.1.156", "static", "IPv4");
        nz1.addIPAssignemt(2, "6", "2");
        nz1.addSubnet(2, "Work", "Second Floor", "24", "192.168.1.1", "2");
        nz1.addNetwork(2, "Building 9", "192.168.1.0", "IPv4", "2");
        nz.addARecord(7, "IN", "ubws01.bcs.com.", "A");
        nz.addDomainData(7, "bcs.com", "7");
        nz.addIPAddress(3, "192.168.1.103", "static", "IPv4");
        nz.addIPAssignemt(3, "7", "3");
        nz.setUpIPAddressSubnet("3", "1");
        nz.addARecord(8, "IN", "maci01", "PTR");
        nz.addDomainData(8, "bcs.com", "8");
        nz.addIPAddress(4, "101.1.168.192.in-addr.arpa.", "static", "IPv4");
        nz.addIPAssignemt(4, "8", "4");
        nz.setUpIPAddressSubnet("4", "1");
        nz.addARecord(9, "IN", "ubws01", "PTR");
        nz.addDomainData(9, "bcs.com", "9");
        nz.addIPAddress(5, "103.1.168.192.in-addr.arpa.", "static", "IPv4");
        nz.addIPAssignemt(5, "9", "5");
        nz.setUpIPAddressSubnet("5", "1");
        nz1.addARecord(10, "IN", "ubws02", "PTR");
        nz1.addDomainData(10, "bbt.com", "10");
        nz1.addIPAddress(6, "156.1.168.192.in-addr.arpa.", "static", "IPv4");
        nz1.addIPAssignemt(6, "10", "6");
        nz1.setUpIPAddressSubnet("6", "2");
        
        nz.addIPAddress(7, "192.168.1.105", "static", "IPv4"); 
        nz.addIPAssignemt(7, "1", "7");
        nz.setUpIPAddressSubnet("7", "1"); 
        nz.addIPAddress(8, "105.1.168.192.in-addr.arpa.", "static", "IPv4");
        nz.addIPAssignemt(8, "8", "8"); 
        nz.setUpIPAddressSubnet("8", "1");
    }

}
