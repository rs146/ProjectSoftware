package JUnitExperiments;

import Database.IPAddress;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains all code to retrieve x number of IPAddress objects from
 * a Cache database. x is a variable that changes in each method as specified
 * in the Project Report. These experiments are data retrieval tests.
 * 
 * @author rahulsingh
 */
public class Experiment2Cache {

    Database db = null;

    public Experiment2Cache() {
    }

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
            Logger.getLogger(Experiment2Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void retrieval1() {
        try {
            IPAddress ip = (IPAddress) IPAddress._open(db, new Id("9"));
        } catch (CacheException ex) {
            Logger.getLogger(Experiment2Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void retrieval2() {
        for (int i = 9; i < 11; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval5() {
        for (int i = 9; i < 14; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval10() {
        for (int i = 9; i < 19; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval25() {
        for (int i = 9; i < 34; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval50() {
        for (int i = 9; i < 59; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval100() {
        for (int i = 9; i < 109; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval1000() {
        for (int i = 9; i < 1009; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval10000() {
        for (int i = 9; i < 10009; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieval100000() {
        for (int i = 9; i < 100009; i++) {
            List<IPAddress> ipAddresses = new ArrayList<IPAddress>();
            try {
                IPAddress ip = (IPAddress) IPAddress._open(db, new Id(i));
                ipAddresses.add(ip);
            } catch (CacheException ex) {
                Logger.getLogger(Experiment2Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
