package JUnitExperiments;

import Database.IPAddress;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Update tests.
 * @author rahulsingh
 */
public class Experiment4Cache {
    Database db;
    
    public Experiment4Cache() {}
    
    @Before
    public void setUp() {
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");
            Connection dbcon = ds.getConnection("_SYSTEM", "SYS");
            db = CacheDatabase.getDatabase(dbcon);
        } catch (InstantiationException ex) {
            System.out.print(ex.getMessage());
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        try {
            db.close();
        } catch (CacheException ex) {
            Logger.getLogger(Experiment4Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void udpate1() {
        try {
            IPAddress ip  = (IPAddress) IPAddress.open(db, new Id("9"));
            ip.setaddress("192.168.1.152");
            ip._save();
        } catch (CacheException ex) {
            Logger.getLogger(Experiment4Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
  
    @Test
    public void udpate2() {
        for (int i = 9; i < 11; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void udpate5() {
        for (int i = 11; i < 16; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void udpate10() {
        for (int i = 16; i < 26; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void udpate25() {
        for (int i = 26; i < 51; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void udpate50() {
        for (int i = 51; i < 101; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void udpate100() {
        for (int i = 101; i < 201; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void udpate1000() {
        for (int i = 201; i < 1201; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void udpate10000() {
        for (int i = 1201; i < 11201; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.152");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void udpate100000() {
        for (int i = 9; i < 100009; i++) {
            try {
                IPAddress ip = (IPAddress) IPAddress.open(db, new Id(i));
                ip.setaddress("192.168.1.151");
                ip._save();
            } catch (CacheException ex) {
                Logger.getLogger(Experiment4Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }  
}
