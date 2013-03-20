package Database;

import com.intersys.classes.RelationshipObject;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Accesses the database and contains all queries
 * made to the database from the GUI.
 * 
 * @author rahulsingh
 * 
 */
public class DatabaseDAO {

    private Connection dbConn; // database connection properties.
    private Database db; // database instance.

    /**
     * Constructor for DatabaseDAO.
     * 
     * @param dbConnection, db
     */
    public DatabaseDAO(Connection dbConnection, Database db) {
        this.dbConn = dbConnection;
        this.db = db;
    }

    /**
     * @return the dbConn
     */
    public Connection getDbConn() {
        return dbConn;
    }

    /**
     * @param dbConn the dbConn to set
     */
    public void setDbConn(Connection dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * @return the db
     */
    public Database getDb() {
        return db;
    }

    /**
     * @param db the db to set
     */
    public void setDb(Database db) {
        this.db = db;
    }
    
    /**
     * Helper method to perform a generic SQL query.
     * Each method in this class that needs to execute an SQL query can call
     * this class.
     * @param sql
     * @return ResultSet
     */
    private ResultSet sqlQuery(String sql){
        ResultSet rs = null;
        try {
            int scroll = ResultSet.TYPE_SCROLL_SENSITIVE;
            int update = ResultSet.CONCUR_UPDATABLE;

            // Execute query on the Cache Database.
            PreparedStatement pstmt = this.getDbConn().
                    prepareStatement(sql, scroll, update);
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    /**
     * 
     * @return List String of Domain Names.
     */
    public List<String> getAllDomainNames() {
        List<String> domainNames = null;
        try {
            domainNames = new ArrayList<String>();
            ResultSet rs = this.sqlQuery("SELECT ID FROM Database.\"Domain\"");
       
            // Iterate through all the Domains in the db and obtain domainName.
            while (rs.next()) {
                domainNames.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return domainNames;
    }
    
    /**
     * Retrieve a Domain object from db given a domain name. 
     * @param domainName
     * @return Domain.
     */
    public Domain getDomain(String domainName){
        Domain dom = null;
        try {
            dom = (Domain) Domain._open(db, new Id(domainName));
        } catch (CacheException ex) {
            System.err.append(ex.getMessage());
        }
        return dom;
    }
    
    /**
     * Get a particular DNSHosting object given the id.
     * If found, return DNSHosting object, else, return null.
     * @param id
     */
    public DNSHosting getDNSHosting(Integer id){
        DNSHosting dnsh = null;
        try {
            dnsh = (DNSHosting) DNSHosting._open(db, new Id(id));
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return dnsh;
    }
    
    /**
     * A NameServer can only have one role per zone. This method retrieves 
     * the DNSHosting object containing that information using the domain name
     * and the name of the name server as parameters for this query.
     * 
     * @param domainName
     * @param nameServer
     * @return DNSHosting object that matches domainName and nameServer.
     */
    public DNSHosting getDNSHosting(String domainName, String nameServer){
        DNSHosting dnsh = null;
        try {
            ResultSet rs = this.sqlQuery("SELECT id FROM "
                    + "Database.DNSHosting WHERE \"domain\" = "
                    + "'" + domainName + "' AND nameServer = '"
                    + nameServer + "'");
            
            rs.next();
            
            // Will only be one object.
            dnsh = (DNSHosting) DNSHosting._open(
                    this.getDb(), new Id(rs.getString(1)));
        
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return dnsh;
    }
    
    /**
     * Get all Name Servers within a zone defined by it's domain name.
     * @param domainName
     * @return List of NameServers in that domain.
     */
    public List<NameServer> getNameServers(String domainName) {
        List<NameServer> nameServers = null;
        try {
            nameServers = new ArrayList<NameServer>();
            ResultSet rs = this.sqlQuery("SELECT nameServer, id FROM "
                    + "Database.DNSHosting WHERE \"domain\" = "
                    + "'" + domainName + "'");
            
            NameServer ns = null;
            // Iterate through all rows and retrieve all NameServer objects.
            while (rs.next()) {
                ns = (NameServer) NameServer._open(
                        this.getDb(), new Id(rs.getString(1)));
                nameServers.add(ns);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return nameServers;
    }
    
    /**
     * Retrieves the master/primary Name Server for a particular zone.
     * As there can only be one Name Server per zone, this method returns 
     * a String of the Name Server's hostname. Query using domain name as 
     * parameter.
     * 
     * @param domainName
     * @return String
     */
    public String getMasterNameServer(String domainName){
        String nameServer = null;
        try{
            ResultSet rs = this.sqlQuery("SELECT nameServer FROM "
                    + "Database.DNSHosting WHERE \"domain\" = "
                    + "'" + domainName + "' AND type = 'master'");
            
            rs.next();
            nameServer = rs.getString(1);
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return nameServer;
    }
    
    /**
     * Given a hostname for a Name Server, retrieves a NameServer object.
     * @param hostname
     * @return NameServer
     */
    public NameServer getNameServer(String hostname){
        NameServer ns = null;
        try {
          ns = (NameServer) NameServer._open(db, new Id(hostname));  
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return ns;
    }
    
    /**
     * 
     * @param domainName
     * @return List of FandRMappings.
     */
    public List<FandRMapping> getFandRMappings(String domainName){
        List<FandRMapping> frMappings = null;
        try {
            frMappings = new ArrayList<FandRMapping>();
            ResultSet rs = this.sqlQuery("SELECT id FROM "
                    + "Database.FandRMapping");
            
            //Iterate all FandRMapping objects, check whether it's part of dom
            while (rs.next()){
                FandRMapping fr = (FandRMapping) FandRMapping._open(
                        this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = fr.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if FandRMapping instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The FandRMapping is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        frMappings.add(fr);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return frMappings;
    }
    
    /**
     * Retrieve a list of all Forward 'A' Mappings for a Forward Mapping file.
     * @param domainName
     * @return List
     */
    public List<FandRMapping> getForwardAMappings(String domainName){
        return this.getBINDIPMappings(domainName, "A");
    }
    
    /**
     * Retrieve a List of all Reverse 'PTR' Mappings for a Reverse Mapping file.
     * @param domainName
     * @return List
     */
    public List<FandRMapping> getReversePTRMappings(String domainName){
        return this.getBINDIPMappings(domainName, "PTR");
    }
    
    /**
     * Helper method which can retrieve either A, AAAA or PTR records stored
     * in the database. A List of FandRMapping objects that match the domain 
     * name and the type of record are returned.
     * @param domainName
     * @param type
     * @return List
     */
    private List<FandRMapping> getBINDIPMappings(String domainName, String type){
        List<FandRMapping> frMappings = null;
        try {
            frMappings = new ArrayList<FandRMapping>();
            ResultSet rs = this.sqlQuery("SELECT id FROM Database.FandRMapping"
                    + " WHERE type='" + type + "'");
            
            // Iterate all FandRMapping objects and check whether it's part of domain.
            while (rs.next()){
                FandRMapping fr = (FandRMapping) FandRMapping._open(
                        this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = fr.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if FandRMapping instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The FandRMapping is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        frMappings.add(fr);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return frMappings;
    }
    
    /**
     * Retrieve Map of all IP Addresses involved in a given FandRMapping 
     * object along with the primary key id for the IP Address.
     * @param fr
     * @return Map
     */
    public Map<Integer, String> getIPAddressesFandR(FandRMapping fr){
        Map<Integer, String> ipAddresses = null;
        try {
            ipAddresses = new HashMap<Integer, String>();
            RelationshipObject ipAssignments = fr.getipAssignments();
            Iterator iter = ipAssignments.keySet().iterator();
            
            while (iter.hasNext()){
                IPAssignment ipAssignment = (IPAssignment) ipAssignments.get(
                        iter.next());
                Integer key = ipAssignment.getipAddress().getid();
                String value = ipAssignment.getipAddress().getaddress();
                ipAddresses.put(key, value);
            } 
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return ipAddresses;
    }
    
    /**
     * Updates an IP Address given the Primary Key id and the new IP Address.
     * 
     * @param ipid
     * @param newIPAddress 
     */
    public void updateIPAddress(Integer ipid, String newIPAddress){
        try {
            IPAddress ip = (IPAddress) IPAddress._open(db, new Id(ipid));
            ip.setaddress(newIPAddress);
            ip._save();
            ip = null;
        } catch (CacheException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Given an IP Address string, return the subnet name that the IP Address
     * is in and the network name that the IP Address is in. Therefore, String
     * array is returned.
     * 
     * @param ipaddress
     * @return Subnet name and Network name as String array.
     */
    public String[] getSubnetAndNetworkName(String ipaddress){
        String[] strArr = new String[2];
        try {
            ResultSet rs = this.sqlQuery("SELECT subnet FROM Database.IPAddress"
                    + " WHERE address = '" + ipaddress + "'");
            
            rs.next(); // an ip address is in only one subnet, even though
                       // it can be reused in another private network.
            Subnet subnet = (Subnet) Subnet._open(
                    this.getDb(), new Id(rs.getString(1)));
            strArr[0] = subnet.getsubnetMask();
            strArr[1] = subnet.getnetwork().getnetname();
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return strArr;
    }
    
    /**
     * Retrieve all MX objects from a zone in the database. Zone defined
     * through it's domain name.
     * @param domainName
     * @return List of MX objects given domainName.
     */
    public List<MX> getMXs(String domainName){
        List<MX> mxRecords = null;
        try {
            mxRecords = new ArrayList<MX>();
            ResultSet rs = this.sqlQuery("SELECT id FROM Database.MX "
                    + "WHERE srv=0");
            
            // Iterate all MX objects and check whether it's part of domain.
            while (rs.next()){
                MX mx = (MX) MX._open(this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = mx.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if MX instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The MX is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        mxRecords.add(mx);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return mxRecords;
    }
    
    /**
     * Retrieve all SRV objects from a zone in the database. Zone defined by
     * it's domain name.
     * @param domainName
     * @return List of SRV objects given domainName.
     */
    public List<SRV> getSRVs(String domainName){
        List<SRV> srvRecords = null;
        try {
            srvRecords = new ArrayList<SRV>();
            ResultSet rs = this.sqlQuery("SELECT id FROM Database.SRV");
            
            // Iterate all MX objects and check whether it's part of domain.
            while (rs.next()){
                SRV srv = (SRV) SRV._open(
                        this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = srv.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if MX instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The MX is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        srvRecords.add(srv);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return srvRecords;
    }
    
    /**
     * Retrieve all CNAME objects from a zone in the database. Zone defined by
     * it's domain name.
     * @param domainName
     * @return List of CNAME objects given the domainName. 
     */
    public List<CNAME> getCNAMEs(String domainName){
        List<CNAME> cnameRecords = null;
        try {
            cnameRecords = new ArrayList<CNAME>();
            ResultSet rs = this.sqlQuery("SELECT id FROM Database.CNAME");
            
            // Iterate all CNAME objects and check whether it's part of domain.
            while (rs.next()){
                CNAME cname = (CNAME) CNAME._open(
                        this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = cname.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if CNAME instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The MX is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        cnameRecords.add(cname);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return cnameRecords;
    }
    
    /**
     * Retrieve all TXT objects from a zone in the database. Zone defined by
     * it's domain name.
     * @param domainName
     * @return List of TXT objects given the domainName. 
     */
    public List<TXT> getTXTs(String domainName){
        List<TXT> txtRecords = null;
        try {
            txtRecords = new ArrayList<TXT>();
            ResultSet rs = this.sqlQuery("SELECT id FROM Database.TXT");
            
            // Iterate all TXT objects and check whether it's part of domain.
            while (rs.next()){
                TXT txt = (TXT) TXT._open(
                        this.getDb(), new Id(rs.getString(1)));
                RelationshipObject domainDatas = txt.getdomainData();
                Iterator iter = domainDatas.keySet().iterator();
                // Check if TXT instance is part of the Domain 
                // specified by the domainName parameter.
                boolean inDomain = false;
                while ((iter.hasNext()) && !inDomain){
                    DomainData dd = (DomainData) domainDatas.get(iter.next());
                    // The MX is in the domain or not.
                    if(dd.getdomainName().getdomainName().equals(domainName)){
                        inDomain = true;
                        txtRecords.add(txt);
                    }
                }
            } 
        } catch (Exception ex){
            System.err.print(ex.getMessage());
        }
        return txtRecords;
    }
}
