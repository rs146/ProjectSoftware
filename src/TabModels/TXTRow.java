package TabModels;

/**
 * Represents an individual row in the table that is displayed when 
 * the Textual Info tab is selected.
 *
 * @author rahulsingh
 */
public class TXTRow extends GenericRow{
    private Integer id;
    private String classOfData;
    private String information;
    
    /**
     * Constructor for TXTRow.
     * 
     * @param id
     * @param ttl
     * @param hostname
     * @param classOfData
     * @param information 
     */
    public TXTRow(Integer id, Integer ttl, String hostname, String classOfData,
            String information){
        this.id = id;
        this.setTtl(ttl);
        this.setHostname(hostname);
        this.classOfData = classOfData;
        this.information = information;
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
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information the information to set
     */
    public void setInformation(String information) {
        this.information = information;
    }
}
