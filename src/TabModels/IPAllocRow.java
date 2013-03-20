package TabModels;

/**
 * Represents an individual row in the Table displayed
 * when the tab IP Allocations is chosen.
 *
 * @author rahulsingh
 */
public class IPAllocRow extends GenericRow {
    private Integer id;
    private String ipaddress;
    private Integer ipaddressId;
    private String submask;
    private String netname;
    
    /**
     * Constructor.
     * @param id
     * @param ttl
     * @param hostname
     * @param ipaddress
     * @param ipaddressId
     * @param submask
     * @param netname 
     */
    public IPAllocRow(Integer id, Integer ttl, String hostname,
            String ipaddress, Integer ipaddressId, String submask, 
            String netname){
        this.id = id;
        this.setTtl(ttl);
        this.setHostname(hostname);
        this.ipaddress = ipaddress;
        this.ipaddressId = ipaddressId;
        this.submask = submask;
        this.netname = netname;
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

    /**
     * @return the ipaddress
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * @param ipaddress the ipaddress to set
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * @return the submask
     */
    public String getSubmask() {
        return submask;
    }

    /**
     * @param submask the submask to set
     */
    public void setSubmask(String submask) {
        this.submask = submask;
    }

    /**
     * @return the netname
     */
    public String getNetname() {
        return netname;
    }

    /**
     * @param netname the netname to set
     */
    public void setNetname(String netname) {
        this.netname = netname;
    }

    /**
     * @return the ipaddressId
     */
    public Integer getIpaddressId() {
        return ipaddressId;
    }

    /**
     * @param ipaddressId the ipaddressId to set
     */
    public void setIpaddressId(Integer ipaddressId) {
        this.ipaddressId = ipaddressId;
    }
    
    
}
