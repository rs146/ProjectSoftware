package Database;

/**
 * Cache' Java Class Generated for class Database.MX on version Cache 
 * UNIX (Red Hat Enterprise Linux 5 for x86-32) 2012.1.1 (Build 602.0_SU)
 **/
public class MX extends Database.ResourceRecord {
    private static final long serialVersionUID = 1930;
    private static String CACHE_CLASS_NAME = "Database.MX";
    
   
    public MX (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    
    public MX (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    
    public static com.intersys.classes.RegisteredObject _open (com.intersys.objects.Database db, com.intersys.objects.Id id) throws com.intersys.objects.CacheException {
        return open(db, id);
    }
    
    public static com.intersys.classes.RegisteredObject open (com.intersys.objects.Database db, com.intersys.objects.Id id) throws com.intersys.objects.CacheException {
        com.intersys.cache.CacheObject cobj = (((com.intersys.cache.SysDatabase)db).openCacheObject(CACHE_CLASS_NAME, id.toString()));
        return (com.intersys.classes.RegisteredObject)(cobj.newJavaInstance());
    }
    
    public static void delete (com.intersys.objects.Database db, com.intersys.objects.Id id) throws com.intersys.objects.CacheException {
        ((com.intersys.cache.SysDatabase)db).deleteObject(CACHE_CLASS_NAME, id);
    }
    
    public java.lang.Integer getpreference()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("preference",false);
       return dh.getInteger();
    }

    public void setpreference(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("preference", dh);
        return;
    }
    
    public java.lang.Boolean getsrv()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("srv",false);
       return dh.getBoolean();
    }

   
    public void setsrv(java.lang.Boolean value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("srv", dh);
        return;
    }
}
