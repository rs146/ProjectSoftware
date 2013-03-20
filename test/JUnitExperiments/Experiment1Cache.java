package JUnitExperiments;

import com.intersys.objects.CacheException;
import com.intersys.objects.CacheDatabase;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.Database;
import Database.IPAddress;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This JUnit test conducts experiments for the insertion of IPAddress
 * objects into the Cache database. Each test is done individually and 
 * is completed in the following ordered format: insertion1(), insertion2(),
 * insertion5(), etc. as specified in the Project Report. Address of IPAddress
 * object remains constant 192.168.1.150 for accuracy. Insertions start at id 9
 * as 1-8 were used in the Project's software.
 * 
 * @author rahulsingh
 */
public class Experiment1Cache {
    Database db = null;

    public Experiment1Cache() {}

    @Before
    public void setUp() {
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");
            Connection dbconn = ds.getConnection("_SYSTEM", "SYS");
            db = CacheDatabase.getDatabase(dbconn);
        } catch (InstantiationException ex) {
            System.out.print(ex.getMessage());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    @After
    public void tearDown() {
        try {
            db.closeAllObjects();
            db.close();
        } catch (CacheException ex) {
            Logger.getLogger(Experiment1Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void insertion1() {
        try {
            IPAddress ip = new IPAddress(db);
            ip.setid(9);
            ip.setaddress("192.168.1.150");
            ip.settype("static");
            ip.setversion("IPv4");
            ip._save();
            db.closeObject(ip.getOref());
            ip = null;
        } catch (CacheException ex) {
            Logger.getLogger(Experiment1Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void insertion2() {
        for (int i = 10; i <= 11; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion5() {
        for (int i = 12; i <= 16; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion10() {
        for (int i = 17; i <= 26; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Test
    public void insertion25() {
        for (int i = 27; i <= 51; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion50() {
        for (int i = 52; i <= 101; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                db.closeObject(ip.getOref());
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion100() {
        for (int i = 102; i <= 201; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion1000() {
        for (int i = 202; i <= 1201; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion10000() {
        for (int i = 1202; i <= 11201; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                db.closeObject(ip.getOref());
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void insertion100000() {
        for (int i = 11202; i <= 111201; i++) {
            try {
                IPAddress ip = new IPAddress(db);
                ip.setid(i);
                ip.setaddress("192.168.1.150");
                ip.settype("static");
                ip.setversion("IPv4");
                ip._save();
                db.closeObject(ip.getOref());
                ip = null;
            } catch (CacheException ex) {
                Logger.getLogger(Experiment1Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
