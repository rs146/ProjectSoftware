package JUnitTests;

import org.junit.Before;
import Database.DatabaseDAO;
import java.sql.SQLException;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheDatabase;
import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.Database;
import java.io.File;
import FileCreators.ForwardMappingCreator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rahulsingh
 */
public class FowardMappingCreatorTest {
    private ForwardMappingCreator fmCreator;
    private Database db;
    private DatabaseDAO dbDao;
    
    public FowardMappingCreatorTest() {    
    }
    
    /**
     * Sets up database connection prior to testing.
     */
    @Before
    public void setUp(){
        try {
            Class.forName ("com.intersys.jdbc.CacheDriver").newInstance();
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
            dbconn = ds.getConnection("_SYSTEM","SYS");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        this.db = null;
        try {
            this.db = CacheDatabase.getDatabase(dbconn);
        } catch (CacheException ex) {
            System.out.print(ex.getMessage());
        }
        this.dbDao = new DatabaseDAO(dbconn, this.db);
    }
    
    /**
     * Tests the createFile() in ForwardMappingCreator.
     */
    @Test
    public void testCreateFile(){
        
        
        fmCreator = new ForwardMappingCreator("bcs.com", dbDao,
                "/Users/rahulsingh/mykonos", "/test.txt");
        fmCreator.createFile();
        File file = new File("/Users/rahulsingh/mykonos/test.txt");
        assertTrue(file.exists());
        try {
            db.closeAllObjects();
            db.close();
        } catch (CacheException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
