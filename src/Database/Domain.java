package Database;

/**
 * Cache' Java Class Generated for class Database.Domain on version Cache for 
 * UNIX (Red Hat Enterprise Linux 5 for x86-32) 2012.1.1 (Build 602.0_SU)
 **/
public class Domain extends com.intersys.classes.Persistent {
    private static final long serialVersionUID = 7314;
    private static String CACHE_CLASS_NAME = "Database.Domain";
   
    public Domain (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    
    public Domain (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }

    public static com.intersys.classes.RegisteredObject _open (com.intersys.objects.Database db, com.intersys.objects.Id id) throws com.intersys.objects.CacheException {
        return open(db, id);
    }
   
    public static com.intersys.classes.RegisteredObject open (com.intersys.objects.Database db, com.intersys.objects.Id id) throws com.intersys.objects.CacheException {
        com.intersys.cache.CacheObject cobj = (((com.intersys.cache.SysDatabase)db).openCacheObject(CACHE_CLASS_NAME, id.toString()));
        return (com.intersys.classes.RegisteredObject)(cobj.newJavaInstance());
    }

    public java.lang.String getadminContact()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("adminContact",false);
       return dh.getString();
    }

    public void setadminContact(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("adminContact", dh);
        return;
    }

    public java.lang.String get_class()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("class",false);
       return dh.getString();
    }

    public void set_class(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("class", dh);
        return;
    }

    public com.intersys.classes.RelationshipObject getdnsHostings()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("dnsHostings",true);
        com.intersys.cache.CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (com.intersys.classes.RelationshipObject)(cobj.newJavaInstance());
    }

    public com.intersys.classes.RelationshipObject getdomainData()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("domainData",true);
        com.intersys.cache.CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (com.intersys.classes.RelationshipObject)(cobj.newJavaInstance());
    }

    public java.lang.String getdomainName()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("domainName",false);
       return dh.getString();
    }

    public void setdomainName(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("domainName", dh);
        return;
    }
    
    public java.lang.Integer getexpire()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("expire",false);
       return dh.getInteger();
    }

    public void setexpire(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("expire", dh);
        return;
    }
    
    public java.lang.Integer getrefresh()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("refresh",false);
       return dh.getInteger();
    }
  
    public void setrefresh(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("refresh", dh);
        return;
    }

    public java.lang.Integer getretry()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("retry",false);
       return dh.getInteger();
    }

    public void setretry(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("retry", dh);
        return;
    }

    public java.lang.Integer getserial()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("serial",false);
       return dh.getInteger();
    }

    public void setserial(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("serial", dh);
        return;
    }

    public java.lang.Integer getttl()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("ttl",false);
       return dh.getInteger();
    }

    public void setttl(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("ttl", dh);
        return;
    }
}
