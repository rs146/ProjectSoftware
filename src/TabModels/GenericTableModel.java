package TabModels;

import Database.DatabaseDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Abstract Table Model.
 *
 * @author rahulsingh
 */
public abstract class GenericTableModel extends AbstractTableModel{
    private DatabaseDAO dbDAO;
    private String domainName;
    protected List rows = new ArrayList();
    protected String[] columnNames;
    
    /**
     * 
     * @return List of rows to be put in a Table format. 
     */
    public abstract List setTableData();
    
    @Override
    public int getRowCount() {
        return getRows().size();
    }

    @Override
    public int getColumnCount() {
        return getColumnNames().length;
    }

    
    @Override
    public String getColumnName(int col){
        return getColumnNames()[col];
    }

    /**
     * @return the dbDAO
     */
    public DatabaseDAO getDbDAO() {
        return dbDAO;
    }

    /**
     * @param dbDAO the dbDAO to set
     */
    public void setDbDAO(DatabaseDAO dbDAO) {
        this.dbDAO = dbDAO;
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
     * @return the rows
     */
    public List getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List rows) {
        this.rows = rows;
    }

    /**
     * @return the columnNames
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * @param columnNames the columnNames to set
     */
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}
