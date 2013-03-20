package FileCreators;

import Database.DatabaseDAO;
import Database.Domain;
import Database.FandRMapping;
import Database.NameServer;
import com.intersys.objects.CacheException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the creation of the Reverse Mapping file
 * for a zone. This is required in a BIND DNS
 * implementation.
 * There is no need to amend the serial number in the Reverse file
 * as it was altered in  the creation of the Forward file. As
 * the Reverse Mapping file is created after the Forward mapping
 * file, the serial number will automatically be different.
 *
 * @author rahulsingh
 */
public class ReverseMappingCreator {
    private DatabaseDAO dbDao;
    private String dirPath;
    private String filename;
    private String domainName; // forward domain name; not reverse.
    private String revDomainName; // reverse domain name. Can be entered by 
                                  // manually through GUI or automatically.
    
    /**
     * Constructor for class.
     *
     * @param domainName
     * @param dbDao
     * @param dirPath
     * @param filename
     */
    public ReverseMappingCreator(String domainName, DatabaseDAO dbDao,
            String dirPath, String filename){
        this.domainName = domainName;
        this.dbDao = dbDao;
        this.dirPath = dirPath;
        this.filename = filename;
    }
    
    /**
     * Constructor for class.
     * @param domainName
     * @param dbDao
     * @param dirPath
     * @param filename
     * @param revDomainName 
     */
    public ReverseMappingCreator(String domainName, DatabaseDAO dbDao,
            String dirPath, String filename, String revDomainName){
        this.domainName = domainName;
        this.dbDao = dbDao;
        this.dirPath = dirPath;
        this.filename = filename;
        this.revDomainName = revDomainName;
    }

    /**
     * Coordinator method that calls the relevant methods
     * to create the Reverse Mapping file for a BIND DNS zone.
     */
    public void createFile(){
        // check if directory entered by user already exists.
        File dir = new File(dirPath);
        if (!dir.isDirectory()){
            dir.mkdirs(); // make directories if doesn't exist.
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(dirPath + filename)));
            writer.write(createSOAReverse());
            writer.newLine();
            writer.write(createNSMapping());
            writer.newLine();
            writer.write(createPTRMappings());
            writer.close();
        } catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Creates a String representation of the SOA Reverse
     * Mapping.
     *
     * @return String
     */
    private String createSOAReverse(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            // checks if reverse domain name instance var has been set.
            // If yes, user has specified the reverse domain name through the
            // GUI. If not, this class generates the reverse domain name.
            if (this.getRevDomainName() == null){
                sb.append(this.createReverseDomainName()).append("       ");
            } else {
                sb.append(this.getRevDomainName()).append("       ");
            }
            Domain domain = this.getDbDao().getDomain(domainName);
            sb.append(domain.get_class()).append("      SOA").append("    ");
            sb.append(this.getDbDao().getMasterNameServer(domainName))
                    .append(" ");
            sb.append(domain.getadminContact()).append("   (");
            sb.append("\n").append("                 ");
            sb.append(domain.getserial()).append("  ;serial").append("\n")
                    .append("                 ");
            sb.append(domain.getrefresh()).append("      ;refresh").append("\n")
                    .append("                 ");
            sb.append(domain.getretry()).append("        ;retry").append("\n")
                    .append("                 ");
            sb.append(domain.getexpire()).append("      ;expire").append("\n")
                    .append("                 ");
            sb.append(domain.getttl()).append(")       ;ttl");
        } catch (CacheException ex){
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }

    /**
     * Creates a String representation of a Reverse Domain name.
     * This is vastly different to that of the forward domain name for a zone.
     * This method is only called when a user DOES NOT type the name of the 
     * reverse zone in the GUI.
     *
     * @return String
     */
    private String createReverseDomainName(){
        StringBuilder sb = null;
        try {
            String masterNSName = this.getDbDao().getMasterNameServer(domainName);
            NameServer ns = this.getDbDao().getNameServer(masterNSName);

            // Use a StringBuilder object to manipulate the ip address.
            StringBuilder ipAddrSB = new StringBuilder(ns.getipAddress());
            // Break IP Address to get first three parts (number before dot).
            Integer[] ip = new Integer[3];

            for (int i = 0; i <= 2; i++){
                ip[i] = Integer.parseInt(
                    ipAddrSB.substring(0, ipAddrSB.indexOf(".")).toString());
                ipAddrSB.delete(0, ipAddrSB.indexOf(".") + 1);
            }
            sb = new StringBuilder();
            for (int i = 2; i >= 0; i--){
                sb.append(ip[i]).append(".");
            }
            sb.append("in-addr.arpa.");
        } catch (CacheException ex) {
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a simple reverse name server mapping for a Zone.
     * @return 
     */
    private String createNSMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n").append(";Name Server");
            sb.append("\n").append(this.createReverseDomainName())
                    .append("      ");
            
            NameServer ns = this.getDbDao().getNameServer(
                    this.getDbDao().getMasterNameServer(domainName));
            sb.append(ns.get_class()).append("     NS").append("       ");
            sb.append(ns.gethostname());
        } catch (CacheException ex){
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a String representation of all PTR records in the
     * Zone. These are the opposite of the A Records in the Forward
     * Mapping file for the zone.
     * 
     * @return String
     */
    private String createPTRMappings(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n").append(";PTR Records");
            List<FandRMapping> ptrMappings = 
                    this.getDbDao().getReversePTRMappings(domainName);
            // loop through all PTR records in db and extract corresponding
            // IP Address(es).
            for (FandRMapping ptr : ptrMappings){
                List<String> ipAddresses = new ArrayList<String>(
                        this.getDbDao().getIPAddressesFandR(ptr).values());
                // write reverse BIND DNS mapping
                for (String ipAddr : ipAddresses){
                    sb.append("\n").append(ipAddr).append("      ");
                    sb.append(checkNull(ptr.gettimeToLive())).append("   ");
                    sb.append(ptr.getclassOfData()).append("     ");
                    sb.append("PTR    ");
                    sb.append(checkHostname(ptr.gethostName()));
                }
            }
        } catch (CacheException ex){
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Checks if hostname has the forward domain name of the zone appended
     * to the hostname of the host used in the reverse mapping file. This 
     * was used to fix a bug.
     * @param hostname
     * @return String
     */
    private String checkHostname(String hostname){
        String domName = new String();
        if (domainName.endsWith(".")){
            domName = domainName;
        } else {
            domName = domainName + ".";
        }
        // if the hostname ends with the domain name, this is a good 
        // indicator that the hostname has been appended already.
        if (hostname.endsWith(domName)){
            return hostname;
        } else {
            StringBuilder result = new StringBuilder(hostname);
            result.append(".").append(domName);
            return result.toString();
        }
    }
    
    /**
     * Helper method.
     * Checks to see whether a part of the mapping has a null value in the 
     * database. Null values can't be used in a BIND DNS implementation. 
     * Therefore, if it is null, it should return an empty string.
     * @param text
     * @return Object either empty string or text itself.
     */
    private Object checkNull(Object text){
        if (text == null){
            return "";
        } else {
            return text;
        }
    }
    
    /**
     * @return the dbDao
     */
    public DatabaseDAO getDbDao() {
        return dbDao;
    }

    /**
     * @param dbDao the dbDao to set
     */
    public void setDbDao(DatabaseDAO dbDao) {
        this.dbDao = dbDao;
    }

    /**
     * @return the dirPath
     */
    public String getDirPath() {
        return dirPath;
    }

    /**
     * @param dirPath the dirPath to set
     */
    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param domainName the domainName to set
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * @return the revDomainName
     */
    public String getRevDomainName() {
        return revDomainName;
    }

    /**
     * @param revDomainName the revDomainName to set
     */
    public void setRevDomainName(String revDomainName) {
        this.revDomainName = revDomainName;
    }
}
