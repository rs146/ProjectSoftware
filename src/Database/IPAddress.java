package Database;

/**
 * Cache' Java Class Generated for class Database.IPAddress on version Cache 
 * UNIX (Red Hat Enterprise Linux 5 for x86-32) 2012.1.1 (Build 602.0_SU)
 **/
public class IPAddress extends com.intersys.classes.Persistent {
    private static final long serialVersionUID = 1977;
    private static String CACHE_CLASS_NAME = "Database.IPAddress";
   
    public IPAddress (com.intersys.cache.CacheObject ref) throws com.intersys.objects.CacheException {
        super (ref);
    }
    
    public IPAddress (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        super (((com.intersys.cache.SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
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
    
    public java.lang.String getaddress()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("address",false);
       return dh.getString();
    }

    public void setaddress(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("address", dh);
        return;
    }

    public java.lang.Integer getid()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("id",false);
       return dh.getInteger();
    }
    
    public void setid(java.lang.Integer value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("id", dh);
        return;
    }
   
    public com.intersys.classes.RelationshipObject getipAssignments()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("ipAssignments",true);
        com.intersys.cache.CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (com.intersys.classes.RelationshipObject)(cobj.newJavaInstance());
    }
   
    public Database.Subnet getsubnet()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("subnet",true);
        com.intersys.cache.CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (Database.Subnet)(cobj.newJavaInstance());
    }

    public void setsubnet(Database.Subnet value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("subnet", dh);
        return;
    }

    public static void checktypeValid (com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
    }
   
    public java.lang.String gettype()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("type",false);
       return dh.getString();
    }

   
    public void settype(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("type", dh);
        return;
    }

   
    public java.lang.String getversion()  throws com.intersys.objects.CacheException {
       com.intersys.cache.Dataholder dh = mInternal.getProperty("version",false);
       return dh.getString();
    }

    public void setversion(java.lang.String value)  throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder dh = new com.intersys.cache.Dataholder (value);
        mInternal.setProperty("version", dh);
        return;
    }

   
    public com.intersys.objects.Oid ipAssignmentsGetObject () throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("ipAssignmentsGetObject",args,com.intersys.objects.Database.RET_PRIM);
        return res.getOid();
    }
   
    public com.intersys.objects.Oid ipAssignmentsGetObject (java.lang.Integer force) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(force);
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("ipAssignmentsGetObject",args,com.intersys.objects.Database.RET_PRIM);
        return res.getOid();
    }
   
    public java.lang.String ipAssignmentsGetObjectId () throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("ipAssignmentsGetObjectId",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
    
    public java.lang.String ipAssignmentsGetObjectId (java.lang.Integer force) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(force);
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("ipAssignmentsGetObjectId",args,com.intersys.objects.Database.RET_PRIM);
        return res.getString();
    }
   
    public com.intersys.objects.Oid subnetGetObject () throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[0];
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("subnetGetObject",args,com.intersys.objects.Database.RET_PRIM);
        return res.getOid();
    }
    
   
    public com.intersys.objects.Oid subnetGetObject (java.lang.Integer force) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(force);
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("subnetGetObject",args,com.intersys.objects.Database.RET_PRIM);
        return res.getOid();
    }
    
    public void subnetSetObject (com.intersys.objects.Oid newvalue) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(newvalue);
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("subnetSetObject",args,com.intersys.objects.Database.RET_PRIM);
        getDatabase().parseStatus(res);
        return;
    }
    
    public void subnetSetObjectId (java.lang.String newid) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(newid);
        com.intersys.cache.Dataholder res=mInternal.runInstanceMethod("subnetSetObjectId",args,com.intersys.objects.Database.RET_PRIM);
        getDatabase().parseStatus(res);
        return;
    } 
}
