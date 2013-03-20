package JUnitExperiments;

import Database.FandRMapping;
import Database.IPAssignment;
import Database.NameServer;
import com.intersys.classes.RelationshipObject;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Join query tests. Use of relationships will be examined.
 * @author rahulsingh
 */
public class Experiment6Cache {
    Connection dbConn;
    Database db;
    
    public Experiment6Cache() {}
    
    @Before
    public void setUp() {
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");
            dbConn = ds.getConnection("_SYSTEM", "SYS");
            db = CacheDatabase.getDatabase(dbConn);
        } catch (InstantiationException ex) {
            ex.printStackTrace(System.out);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @After
    public void tearDown() {
        try {
            db.close();
        } catch (CacheException ex) {
            ex.printFullTrace(System.out);
        }
    }
    
    /**
     * Get NameServer objects given a domain name. 
     * Then, print hostname of Name Server(s).
     */
    @Test
    public void getNameServers() {
        String domainName = "bcs.com";
        try {
            List<NameServer> nameServers = new ArrayList<NameServer>();
            String sql = "SELECT nameServer FROM Database.DNSHosting "
                    + "WHERE \"domain\" = '" + domainName + "'";
            PreparedStatement pstmt = dbConn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery(); 
            // Iterate through all rows and retrieve all NameServer objects.
            while (rs.next()) {
                NameServer ns = (NameServer) 
                        NameServer._open(db, new Id(rs.getString(1)));
                nameServers.add(ns);
            }
            for (NameServer nameServ : nameServers){
                System.out.println(nameServ.gethostname());
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    /**
     * Retrieve IPAddresses of a given FandRMapping object.
     * Then put it in a List and print IP Address(es) of FandRMapping object.
     */
    @Test
    public void getIPAddresses(){
        try {
            FandRMapping fr = (FandRMapping) FandRMapping.open(db, new Id("7"));
            List<String> ipAddresses = new ArrayList<String>();
            RelationshipObject ipAssignments = fr.getipAssignments();
            Iterator iter = ipAssignments.keySet().iterator();
            
            while (iter.hasNext()){
                IPAssignment ipAssignment = (IPAssignment) ipAssignments.get(
                        iter.next());
                ipAddresses.add(ipAssignment.getipAddress().getaddress());
            } 
            for (String ipaddr : ipAddresses){
                System.out.println(ipaddr);
            }
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
