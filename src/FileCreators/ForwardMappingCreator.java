package FileCreators;

import Database.CNAME;
import Database.DatabaseDAO;
import Database.Domain;
import Database.FandRMapping;
import Database.MX;
import Database.NameServer;
import Database.TXT;
import com.intersys.objects.CacheException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Manages the creation of the forward mapping file for
 * a zone in a BIND implementation or service.
 * 
 * @author rahulsingh
 */
public class ForwardMappingCreator {
    private String domainName;
    private DatabaseDAO dbDao;
    private String dirPath; // directory path set in FileCreatorGUI.
    private String fileName; // file name of the forward mapping file.
    
    /**
     * Constructor for this class.
     * @param domainName 
     */
    public ForwardMappingCreator(String domainName, DatabaseDAO dbDao,
            String dirPath, String fileName){
        this.domainName = domainName;
        this.dbDao = dbDao;
        this.dirPath = dirPath;
        this.fileName = fileName;
    }
    
    /**
     * Coordinator method that calls the relevant methods to create a Forward
     * Mapping file.
     */
    public void createFile() {
        // everytime a forward mapping file is created, the serial number
        // for the zone is updated.
        this.amendSerial();
        // check if directory entered by user already exists.
        File dir = new File(dirPath);
        if (!dir.isDirectory()){
            dir.mkdirs(); // make directories if doesn't exist.
        }
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(new File(dirPath + fileName)));
            writer.write(createSOAMapping());
            writer.newLine();
            writer.write(createNSMapping());
            writer.newLine();
            writer.write(createCNAMEMapping());
            writer.newLine();
            writer.write(createAMapping());
            writer.newLine();
            writer.write(createMXMapping());
            writer.newLine();
            writer.write(createTXTMapping());
            writer.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Amends the serial number of forward mapping file. This is critical 
     * in a BIND DNS implementation because it is only when the serial number
     * changes that the Name Server reloads the mapping files and registers
     * the changes.
     */
    private void amendSerial(){
        try {
            Domain domain = this.getDbDao().getDomain(domainName);
            Integer serial = domain.getserial();
            String serialStr = serial.toString();
            Integer year = Integer.parseInt(serialStr.substring(0, 4));
            Integer month = Integer.parseInt(serialStr.substring(4, 6));
            Integer day = Integer.parseInt(serialStr.substring(6, 8));
            Integer nn = Integer.parseInt(serialStr.substring(8));
            
            Calendar cal = Calendar.getInstance();
            int y = cal.get(Calendar.YEAR);
            int m = cal.get(Calendar.MONTH) + 1;
            int d = cal.get(Calendar.DAY_OF_MONTH);
            
            StringBuilder serialStrBuilder = new StringBuilder();
            
            if ((year.intValue() == y) && (month.intValue() == m) &&
                    (day.intValue() == d)){
                
                nn++; // increment no. of times changed in the day.
            } else {
                // a different date and therefore reset nn to 0.
                nn = 0;
            }
            serialStrBuilder.append(y);
            serialStrBuilder.append(checkTwoDigits(m));
            serialStrBuilder.append(checkTwoDigits(d));
            serialStrBuilder.append(checkTwoDigits(nn));
            
            serialStr = serialStrBuilder.toString();
            Integer newSerial = Integer.parseInt(serialStr);
            
            domain.setserial(newSerial);
            domain.save();
            domain = null;
        } catch (CacheException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * In the YYYYMMDDNN format for the serial number, some of the digits (0-9)
     * will have to be converted into two digit numbers for compliance with 
     * the serial number format.
     * @param number
     * @return String
     */
    private String checkTwoDigits(Integer number){
        StringBuilder sb = new StringBuilder();
        String numStr = String.valueOf(number);
        if (numStr.length() == 1){
            sb.append("0").append(numStr);
        } else {
            sb.append(numStr);
        }
        return sb.toString();
    }


    /**
     * Creates the string for the Start of Authority mapping.
     * @return String
     */
    private String createSOAMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append(checkDomainName()).append("        ");
            Domain domain = this.getDbDao().getDomain(domainName);
            sb.append(domain.get_class()).append("      ");
            sb.append("SOA").append("     ");
            sb.append(this.getDbDao().getMasterNameServer(
                    domainName)).append("  ");
            sb.append(domain.getadminContact()).append(" ").append("(");
            
            sb.append("\n").append("                        ");
            sb.append(domain.getserial()).append("      ").append(";serial");
            
            sb.append("\n").append("                        ");
            sb.append(domain.getrefresh()).append("           ")
                    .append(";refresh");
            
            sb.append("\n").append("                        ");
            sb.append(domain.getretry()).append("            ")
                    .append(";retry");
            
            sb.append("\n").append("                        ");
            sb.append(domain.getexpire()).append("         ")
                    .append(";expire");
            sb.append("\n").append("                        ");
            sb.append(domain.getttl()).append(")          ").append(";ttl");
        } catch (CacheException ex) {
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Checks the domain name. If it has a dot at the end, no change is made.
     * If it does not have a dot at the end, a dot is inserted.
     * @return String
     */
    private String checkDomainName(){
        StringBuilder sb = new StringBuilder();
        if (this.getDomainName().charAt(getDomainName().length()-1) != '.'){
            sb.append(this.getDomainName()).append(".");
        } else {
            sb.append(this.getDomainName());
        }
        return sb.toString();
    }
    
    /**
     * Creates a Name Server forward mapping for a zone. 
     * @return String
     */
    private String createNSMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n;Name Server");
            sb.append("\n").append(checkDomainName()).append("        ");
            // Retrieve Master Name Server.
            String masterNS = this.getDbDao().getMasterNameServer(domainName);
            NameServer nameServer = this.getDbDao().getNameServer(masterNS);
            sb.append(nameServer.get_class()).append("      ");
            sb.append("NS").append("      ");
            sb.append(masterNS);
        } catch (CacheException ex){
            System.err.println(ex.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a String for all CNAME mappings within a zone.
     * @return String
     */
    private String createCNAMEMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n; Aliases");
            List<CNAME> cnames = this.getDbDao().getCNAMEs(domainName);
            for (CNAME cname : cnames){
                // append nickname.
                sb.append("\n").append(
                        cname.getnickname()).append("        ");
                sb.append(checkNull(cname.gettimeToLive())).append("    ");
                // append class of data.
                sb.append(cname.getclassOfData()).append("      ");
                sb.append("CNAME").append("   ");
                sb.append(cname.gethostName());
            }
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a String for all Forward A Mappings within a zone. 
     * @return String
     */
    private String createAMapping(){
        StringBuilder sb = null;
        try{
            sb = new StringBuilder();
            sb.append("\n; IP Address Assignments");
            List<FandRMapping> forwardMappings = this.getDbDao()
                    .getForwardAMappings(domainName);
            for (FandRMapping fmapping : forwardMappings){
                sb.append("\n").append(
                        fmapping.gethostName()).append("       ");
                sb.append(checkNull(fmapping.gettimeToLive()))
                        .append("        ");
                sb.append(fmapping.getclassOfData()).append("      ");
                sb.append(fmapping.gettype()).append("  ");
                // a host could have multiple IP Addresses.
                Map<Integer, String> ipAddrMap = this.getDbDao()
                        .getIPAddressesFandR(fmapping);
                List<String> ipAddresses
                        = new ArrayList<String>(ipAddrMap.values());
                sb.append(ipAddresses.get(0)); // retrieves first IP address.
                // get all other IP Addresses for a host.
                for (int i = 1; i < ipAddresses.size(); i++){
                    sb.append("\n")
                            .append("                    ")
                            .append(checkNull(fmapping.gettimeToLive()))
                            .append("          ")
                            .append(fmapping.getclassOfData()).append("      ")
                            .append(fmapping.gettype()).append("  ")
                            .append(ipAddresses.get(i));
                }
            }
            
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a String for the MX records within a given zone.
     * @return String
     */
    private String createMXMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n; Mail Server allocation");
            List<MX> mailExchanges = this.getDbDao().getMXs(domainName);
            for (MX mailExchange : mailExchanges){
                sb.append("\n").append(this.checkDomainName())
                        .append("           ");
                sb.append(checkNull(mailExchange.gettimeToLive()))
                        .append("     ");
                sb.append(mailExchange.getclassOfData()).append("      ");
                sb.append("MX").append("      ");
                sb.append(mailExchange.getpreference()).append("      ");
                sb.append(mailExchange.gethostName());
            }
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return sb.toString();
    }
    
    /**
     * Creates a String representation of all TXT records in the database
     * for a given zone identified by the domain name.
     * @return String
     */
    private String createTXTMapping(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            sb.append("\n; Textual Information");
            List<TXT> txts = this.getDbDao().getTXTs(domainName);
            for (TXT txt : txts){
                sb.append("\n").append(txt.gethostName()).append("          ");
                sb.append(checkNull(txt.gettimeToLive())).append("        ");
                sb.append(txt.getclassOfData()).append("      ");
                sb.append("TXT").append("          ");
                sb.append(txt.getinformation());
            }
        } catch (CacheException ce){
            System.err.println(ce.getMessage());
        }
        return sb.toString();
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
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
