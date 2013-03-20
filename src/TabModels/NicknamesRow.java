
package TabModels;

/**
 * Represents an individual row in the table that is displayed
 * when the Nicknames tab is selected in the DnsGui.
 * 
 * @author rahulsingh
 */
public class NicknamesRow extends GenericRow{
    private Integer id;
    private String classOfData;
    private String nickname;

    /**
     * Constructor for the NicknamesRow.
     * 
     * @param id
     * @param ttl
     * @param hostname
     * @param classOfData
     * @param nickname 
     */
    public NicknamesRow(Integer id, Integer ttl, String hostname,
            String classOfData, String nickname){
        this.id = id;
        this.setTtl(ttl);
        this.setHostname(hostname);
        this.classOfData = classOfData;
        this.nickname = nickname;
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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
