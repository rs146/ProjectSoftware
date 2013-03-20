package TabModels;

/**
 * Represents an individual row in the table that is displayed when
 * the 'Name Servers' tab is selected in the DnsGui.
 *
 * @author rahulsingh
 */
public class NameServerRow extends GenericRow {
    private Integer id;
    private String ipAddress;
    private String classOfData;
    private String type;
    
    /**
     * Constructor for NameServerRow.
     * @param hostname
     * @param ipAddr
     * @param ttl
     * @param classOfData
     * @param type 
     */
    public NameServerRow(Integer id, String hostName, String ipAddr, 
            Integer ttl, String classOfData, String type){
        this.id = id;
        this.setHostname(hostName);
        this.ipAddress = ipAddr;
        this.setTtl(ttl);
        this.classOfData = classOfData;
        this.type = type;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the classOfData
     */
    public String getClassOfData() {
        return classOfData;
    }

    /**
     * @param classOfData the classOfData to set
     */
    public void setClassOfData(String classOfData) {
        this.classOfData = classOfData;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
