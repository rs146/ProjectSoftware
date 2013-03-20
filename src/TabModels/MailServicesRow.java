package TabModels;

/**
 * Represents a single row in the table shown when the 'Mail/Services' tab
 * is selected in DnsGui.java.
 *
 * @author rahulsingh
 */
public class MailServicesRow extends GenericRow {
    private Integer id;
    private String classOfData;
    private Integer preference;
    private String service;
    private String protocol;
    private Integer weight;
    private Integer port;
    private boolean srv;

    /**
     * Constructor for MailServicesRow. Used only when MX data is retrieved
     * from the database.
     * 
     * @param id
     * @param ttl
     * @param hostname
     * @param classOfData
     * @param srv
     * @param preference 
     */
    public MailServicesRow(Integer id, Integer ttl, String hostname,
            String classOfData, Integer preference, boolean srv){
        this.id = id;
        this.setTtl(ttl);
        this.setHostname(hostname);
        this.classOfData = classOfData;
        this.preference = preference;
        this.srv = srv;
    }
    
    /**
     * Constructor for MailServicesRow. Used for when SRV data is retrieved 
     * from the database.
     * 
     * @param id
     * @param ttl
     * @param hostname
     * @param classOfData
     * @param srv
     * @param preference
     * @param service
     * @param protocol
     * @param weight
     * @param port 
     */
    public MailServicesRow(Integer id, Integer ttl, String hostname,
            String classOfData, Integer preference, String service,
            String protocol, Integer weight, Integer port, boolean srv){
        this.id = id;
        this.setTtl(ttl);
        this.setHostname(hostname);
        this.classOfData = classOfData;
        this.preference = preference;
        this.service = service;
        this.protocol = protocol;
        this.weight = weight;
        this.port = port;
        this.srv = srv;
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
     * @return the preference
     */
    public Integer getPreference() {
        return preference;
    }

    /**
     * @param preference the preference to set
     */
    public void setPreference(Integer preference) {
        this.preference = preference;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the srv
     */
    public boolean isSrv() {
        return srv;
    }

    /**
     * @param srv the srv to set
     */
    public void setSrv(boolean srv) {
        this.srv = srv;
    }
}
