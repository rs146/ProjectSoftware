package JUnitExperiments;

import Database.IPAddress;
import com.intersys.objects.CacheDatabase;
import java.sql.Connection;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Deletion tests.
 * @author rahulsingh
 */
public class Experiment5Cache {
    Database db;
    
    public Experiment5Cache() {}
    
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
            Logger.getLogger(Experiment5Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
   
    @Test
    public void delete1() {
        try {
            IPAddress.delete(db, new Id("132003"));
        } catch (CacheException ex) {
            ex.printFullTrace(System.out);
        }
    }
    
    @Test
    public void delete2() {
        for (int i = 132007; i > 132005; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }

    @Test
    public void delete5() {
        for (int i = 132017; i > 132012; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }
    
    @Test
    public void delete10() {
        for (int i = 132037; i > 132027; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }

    @Test
    public void delete25() {
        for (int i = 132087; i > 132062; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }
    
    @Test
    public void delete50() {
        for (int i = 132187; i > 132137; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }

    @Test
    public void delete100() {
        for (int i = 132387; i > 132287; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }

    @Test
    public void delete1000() {
        for (int i = 301201; i > 300201; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }
    
    @Test
    public void delete10000() {
        for (int i = 110012; i > 100012; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }

    @Test
    public void delete100000() {
        for (int i = 100010; i > 10; i--) {
            try {
                IPAddress.delete(db, new Id(i));
            } catch (CacheException ex) {
                ex.printFullTrace(System.out);
            }
        }
    }   
}
