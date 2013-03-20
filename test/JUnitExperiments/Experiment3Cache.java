package JUnitExperiments;

import com.intersys.jdbc.CacheDataSource;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This experiment retrievals data from the database as literals, rather than
 * objects. It follows the numbered list as set out in the Project Report.
 * try-catch is excluded from all Lines Of Code measures.
 * @author rahulsingh
 */
public class Experiment3Cache {
    Database db;
    Connection dbcon;
    
    public Experiment3Cache() {}
    
    @Before
    public void setUp() {
        try {
            Class.forName("com.intersys.jdbc.CacheDriver").newInstance();
            CacheDataSource ds = new CacheDataSource();
            ds.setURL("jdbc:Cache://192.168.1.172:56773/USER");
            dbcon = ds.getConnection("_SYSTEM", "SYS");
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
            Logger.getLogger(Experiment3Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void retrieve1() {
        try {
            String sql = "SELECT address FROM Database.IPAddress WHERE id = 9";
            PreparedStatement pstmt = dbcon.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String ipAddr = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(Experiment3Cache.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
  
    @Test
    public void retrieve2() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 11; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
 
    @Test
    public void retrieve5() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 14; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    @Test
    public void retrieve10() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 19; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void retrieve25() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 34; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieve50() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 59; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieve100() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 109; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Test
    public void retrieve1000() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 1009; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
 
    @Test
    public void retrieve10000() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 10009; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void retrieve100000() {
        List<String> ipAddresses = new ArrayList<String>();
        for (int i = 9; i < 100009; i++) {
            try {
                String sql = "SELECT address FROM Database.IPAddress WHERE id"
                        + " = " + i;
                PreparedStatement pstmt = dbcon.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                ipAddresses.add(rs.getString(1));
            } catch (SQLException ex) {
                Logger.getLogger(Experiment3Cache.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }
}
