package JUnitTests;

import java.io.File;
import com.intersys.objects.CacheException;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.Database;
import java.sql.SQLException;
import com.intersys.jdbc.CacheDataSource;
import Database.DatabaseDAO;
import FileCreators.ReverseMappingCreator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rahulsingh
 */
public class ReverseMappingCreatorTest {
    private ReverseMappingCreator rvCreator;
    private DatabaseDAO dbDao;
    private Database db;
    
    public ReverseMappingCreatorTest() {
    }

    /**
     * Sets up database connection prior to the test.
     */
    @Before
    public void setUp() {
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
     * Tests the createFile() method in ReverseMappingCreator.
     */
    @Test
    public void testCreateFile(){
        this.rvCreator = new ReverseMappingCreator("bcs.com", this.dbDao, 
                "/Users/rahulsingh/mykonos", "/test.rev");
        this.rvCreator.createFile();
        File file = new File("/Users/rahulsingh/mykonos/test.rev");
        assertTrue(file.exists());
        try {
            this.db.closeAllObjects();
            this.db.close();
        } catch (CacheException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    
}
