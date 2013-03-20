package TabModels;

/**
 * Abstract superclass representing a generic row that
 * is displayed in a table.
 *
 * @author rahulsingh
 */
public abstract class GenericRow {
    private Integer ttl;
    private String hostname;

    /**
     * @return the ttl
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * @param ttl the ttl to set
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
