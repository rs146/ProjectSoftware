/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnitTests;

import java.util.ArrayList;
import java.util.Map;
import org.junit.Before;
import Database.DNSHosting;
import Database.TXT;
import Database.CNAME;
import Database.MX;
import Database.DatabaseDAO;
import Database.FandRMapping;
import Database.NameServer;
import Database.SRV;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.Database;
import com.intersys.jdbc.CacheDataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * JUnit tests for important methods in the DatabaseDAO class.
 *
 * @author rahulsingh
 */
public class DatabaseDAOTests {

    DatabaseDAO dbDao;
    Database db = null;

    public DatabaseDAOTests() {
    }

    @Before
    public void setUp() {
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
        } catch (InstantiationException ex) {
            System.out.print(ex.getMessage());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        CacheDataSource ds = new CacheDataSource();
        try {
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        java.sql.Connection dbconn = null;
        try {
            dbconn = ds.getConnection("_SYSTEM", "SYS");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseDAOTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            db = CacheDatabase.getDatabase(dbconn);
        } catch (CacheException ex) {
            System.out.print(ex.getMessage());
        }
        dbDao = new DatabaseDAO(dbconn, db);
    }

    @Test
    public void testDomName() {
        List<String> names = dbDao.getAllDomainNames();
        String name = names.get(1);
        assertEquals("bcs.com", name);
        System.out.println(name);

        try {
            db.close();
        } catch (CacheException ex) {
            Logger.getLogger(DatabaseDAOTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testNameServers() {
        try {
            List<NameServer> nameServers = dbDao.getNameServers("bcs.com");
            for (NameServer ns : nameServers) {
                assertEquals("maci01.bcs.com.", ns.gethostname());
                System.out.print(ns.gethostname());
            }
            db.close();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testFandRMappings() {
        try {
            List<FandRMapping> fandRMappings = dbDao.getFandRMappings("bcs.com");
            for (FandRMapping fr : fandRMappings) {
                assertNotNull(fr.gethostName());
                System.out.println(fr.gethostName());
                Map<Integer, String> iPAddressesFandR = dbDao.getIPAddressesFandR(fr);
                List<String> ips = new ArrayList<String>(iPAddressesFandR.values());
                for (String ipAddress : ips) {
                    assertNotNull(ipAddress);
                    System.out.println(ipAddress);
                }
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testMXs() {
        try {
            List<MX> mxRecords = dbDao.getMXs("bcs.com");
            for (MX mx : mxRecords) {
                assertNotNull(mx.gethostName());
                System.out.println(mx.gethostName());
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testSRVs() {
        try {
            List<SRV> SRVs = dbDao.getSRVs("bcs.com");
            for (SRV srv : SRVs) {
                assertNotNull(srv.gethostName());
                System.out.println(srv.gethostName());
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testCNAMEs() {
        try {
            List<CNAME> cnames = dbDao.getCNAMEs("bcs.com");
            for (CNAME cn : cnames) {
                assertNotNull(cn.getnickname());
                System.out.println(cn.getnickname());
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testTXTs() {
        try {
            List<TXT> txts = dbDao.getTXTs("bcs.com");
            for (TXT tx : txts) {
                assertNotNull(tx.gethostName());
                System.out.println(tx.getinformation());
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testDNSHostingss() {
        try {
            DNSHosting dnsh = dbDao.getDNSHosting("bcs.com", "maci01.bcs.com.");

            assertNotNull(dnsh.gettype());
            System.out.println(dnsh.gettype());

            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testSubnetAndNetworkName() {
        try {
            String[] strArr = dbDao.getSubnetAndNetworkName("192.168.1.101");

            assertNotNull(strArr);
            System.out.println(strArr[1]);

            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testMasterNameServer() {
        try {
            String ns = dbDao.getMasterNameServer("bcs.com");

            assertNotNull(ns);
            System.out.println(ns);

            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testgetForwardMappings() {
        try {
            List<FandRMapping> fMappings = dbDao.getForwardAMappings("bcs.com");
            for (FandRMapping f : fMappings) {
                Map<Integer, String> iPAddressesFandR = dbDao.getIPAddressesFandR(f);
                List<String> ipAddresses = new ArrayList<String>(iPAddressesFandR.values());
                for (String ipaddr : ipAddresses) {
                    System.out.println(ipaddr);
                    assertNotNull(ipAddresses);
                }
            }
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Test
    public void testUpdateIPAddress() {
        try {
            dbDao.updateIPAddress(1, "192.168.1.101");
            db.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
