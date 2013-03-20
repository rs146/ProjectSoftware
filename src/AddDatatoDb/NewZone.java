package AddDatatoDb;

import Database.CNAME;
import Database.DNSHosting;
import Database.Domain;
import Database.DomainData;
import Database.FandRMapping;
import Database.IPAddress;
import Database.IPAssignment;
import Database.MX;
import Database.NameServer;
import Database.Network;
import Database.SRV;
import Database.Subnet;
import Database.TXT;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Id;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rahulsingh
 * 
 * Adds a new Zone to the database.
 */
public class NewZone {
    private Database db;
    
    public NewZone(Database db){
        this.db = db;
    }
    
    /**
     * 
     * @return Database instance containing db connection 
     * details.
     */
    public Database getDb(){
        return this.db;
    }
    
    /**
     * Adds a new Domain to the database. 
     */
    public void addDomain(String domainName, String adminContact, 
            String classOfData, Integer expire, Integer refresh,
            Integer retry, Integer serial, Integer ttl){
        try {
            Domain domain = new Domain(getDb());
            domain.setdomainName(domainName);
            domain.setadminContact(adminContact);
            domain.set_class(classOfData);
            domain.setexpire(expire);
            domain.setrefresh(refresh);
            domain.setretry(retry);
            domain.setserial(serial);
            domain.setttl(ttl);
            domain._save();
            this.getDb().closeObject(domain.getOref());
            domain = null;
            this.getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a new DNSHosting object to the database.
     * The new DNSHosting object must link to the Domain object in the db.
     */
    public void addDnsHosting(Integer id, String type, boolean query, 
            boolean transfer, boolean update, String domainName){
        try {
            DNSHosting dnsh = new DNSHosting(getDb());
            dnsh.setid(id);
            dnsh.settype(type);
            dnsh.setallowQuery(query);
            dnsh.setallowTransfer(transfer);
            dnsh.setallowUpdate(update);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domainName));
            dnsh.setdomain(dom);
            dnsh._save();
            dom.getdnsHostings().add(dnsh);
            dom._save();
            getDb().closeObject(dom.getOref());
            getDb().closeObject(dnsh.getOref());
            getDb().closeAllObjects();
            dnsh = null;
            dom = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a new NameServer object to the db. 
     * The NameServer object must be linked to the DNSHosting object.
     */
    public void addNameServer(String hostname, String classOfData, 
            String ipAddress, String id){
        try {
            NameServer ns = new NameServer(getDb());
            ns.sethostname(hostname);
            ns.set_class(classOfData);
            ns.setipAddress(ipAddress);
            DNSHosting dnsh = (DNSHosting) DNSHosting._open(getDb(), new Id(id));
            ns.getdnsHostings().add(dnsh);
            ns._save();
            dnsh.setnameServer(ns);
            dnsh._save();
            getDb().closeObject(ns.getOref());
            getDb().closeObject(dnsh.getOref());
            getDb().closeAllObjects();
            ns = null;
            dnsh = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add a new 'A' Resource Record to the db.
     * This will be of type 'FandRMapping'.
     * Here we use the benefits of subclasses of database classes.
     */
    public void addARecord(Integer id, String classOfData, String hostName,
            String type){
        try {
            FandRMapping fr = new FandRMapping(getDb());
            fr.setid(id);
            fr.setclassOfData(classOfData);
            fr.sethostName(hostName);
            fr.settype(type);
            fr._save();
            getDb().closeObject(fr.getOref());
            fr = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add 'DomainData' object to the database.
     * Link the 'Domain' and subclass of 'ResourceRecord' with this object.
     * Provides the link between the domain/zone and the resource record data
     * within the domain/zone.
     */
    public void addDomainData(Integer ddid, String domainName, String id){
        try {
            DomainData dd = new DomainData(getDb());
            dd.setid(ddid);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domainName));
            dd.setdomainName(dom);
            dom.getdomainData().add(dd);
            dom._save();
            
            FandRMapping fr = (FandRMapping) FandRMapping._open(getDb(), new Id(id));
            dd.setresourceRecord(fr);
            fr.getdomainData().add(dd);
            dd._save();
            fr._save();
            
            getDb().closeObject(dom.getOref());
            getDb().closeObject(fr.getOref());
            getDb().closeObject(dd.getOref());
            getDb().closeAllObjects();
            dom = null;
            fr = null;
            dd = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add a new 'IPAddress' object to the db.
     * This IPAddress object will later be used in a Forward and Reverse Mapping.
     */
    public void addIPAddress(Integer id, String ipaddress, String type, String version){
        try {
            IPAddress ip = new IPAddress(getDb());
            ip.setid(id);
            ip.setaddress(ipaddress);
            ip.settype(type);
            ip.setversion(version);
            ip._save();
            getDb().closeObject(ip.getOref());
            ip = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds an 'IPAssignemt' object to the database.
     * This forms the relationship between the Resource Record and the
     * IP Address added earlier. 'FandRMapping' object linked to the 'IPAddress'
     * object through this object.
     */
    public void addIPAssignemt(Integer id, String frid, String ipid){
        try {
            IPAssignment ipa = new IPAssignment(getDb());
            ipa.setid(id);
            
            FandRMapping fr = (FandRMapping) FandRMapping._open(getDb(), new Id(frid));
            fr.getipAssignments().add(ipa);
            ipa.setfandRMapping(fr);
            fr._save();
            
            IPAddress ip = (IPAddress) IPAddress._open(getDb(), new Id(ipid));
            ip.getipAssignments().add(ipa);
            ipa.setipAddress(ip);
            ip._save();
            ipa._save();
            
            getDb().closeAllObjects();
            fr = null;
            ip = null;
            ipa = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Adds a new 'Subnet' object to the database.
     * Must be linked with the 'IPAddress' object added earlier.
     */
    public void addSubnet(Integer id, String subname, String location,
            String submask, String subAddr, String ipid){
        try {
            Subnet sb = new Subnet(getDb());
            sb.setid(id);
            sb.setsubname(subname);
            sb.setlocation(location);
            sb.setsubnetMask(submask);
            sb.setsubnetAddr(subAddr);
            
            IPAddress ip = (IPAddress) IPAddress._open(getDb(), new Id(ipid));
            ip.setsubnet(sb);
            sb.getipAddresses().add(ip);
            ip._save();
            sb._save();
            
            getDb().closeAllObjects();
            sb = null;
            ip = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a new 'Network' object to db.
     * Must be linked with a Subnet.
     */
    public void addNetwork(Integer id, String nname, String netAddr, 
            String version, String subid){
        try {
            Network nt = new Network(getDb());
            nt.setid(id);
            nt.setnetname(nname);
            nt.setnetworkAddr(netAddr);
            nt.setversion(version);
            
            Subnet sb = (Subnet) Subnet._open(getDb(), new Id(subid));
            sb.setnetwork(nt);
            nt.getsubnets().add(sb);
            sb._save();
            nt._save();
            
            getDb().closeAllObjects();
            sb = null;
            nt = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    /**
     * Adds a CNAME object to the database.
     */
    public void addCname(Integer id, String classOfData, String hostName,
            String nickname){
        try {
            CNAME cn = new CNAME(getDb());
            cn.setid(2);
            cn.setclassOfData(classOfData);
            cn.sethostName(hostName);
            cn.setnickname(nickname);
            cn._save();
            
            getDb().closeAllObjects();
            cn = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add domaindata to manage relationship between Cname, DomainData and Domain.
     */
    public void addDomainDataCname(Integer id, String domName, String CNAMEid) {
        try {
            DomainData dd = new DomainData(getDb());
            dd.setid(id);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domName));
            dd.setdomainName(dom);
            dom.getdomainData().add(dd);
            dom._save();

            CNAME cn = (CNAME) CNAME._open(getDb(), new Id(CNAMEid));
            dd.setresourceRecord(cn);
            cn.getdomainData().add(dd);
            dd._save();
            cn._save();

            getDb().closeObject(dom.getOref());
            getDb().closeObject(cn.getOref());
            getDb().closeObject(dd.getOref());
            getDb().closeAllObjects();
            dom = null;
            cn = null;
            dd = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds MX object to the database.
     */
    public void addMX(Integer id, String classOfData, String hostName,
            Integer preference, boolean isSRV){
        try {
            MX mx = new MX(this.getDb());
            mx.setid(id);
            mx.setclassOfData(classOfData);
            mx.sethostName(hostName);
            mx.setpreference(preference);
            mx.setsrv(isSRV);
            mx._save();
            
            getDb().closeAllObjects();
            mx = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add domaindata for the MX record above method.
     */
    public void addDomainDataMX(Integer id, String domainName, String mXid){
        try {
            DomainData dd = new DomainData(getDb());
            dd.setid(id);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domainName));
            dd.setdomainName(dom);
            dom.getdomainData().add(dd);
            dom._save();

            MX mx = (MX) MX._open(getDb(), new Id(mXid));
            dd.setresourceRecord(mx);
            mx.getdomainData().add(dd);
            dd._save();
            mx._save();

            getDb().closeObject(dom.getOref());
            getDb().closeObject(mx.getOref());
            getDb().closeObject(dd.getOref());
            getDb().closeAllObjects();
            dom = null;
            mx = null;
            dd = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add SRV object to the database.
     */
    public void addSRV(Integer id, String service, String proto, String name,
            boolean isSRV, Integer pref, Integer weight, Integer port,
            String hostName){
        try {
            SRV srv = new SRV(this.getDb());
            srv.setid(id);
            srv.setservice(service);
            srv.setprotocol(proto);
            srv.setname(name);
            srv.setsrv(isSRV);
            srv.setpreference(pref);
            srv.setweight(weight);
            srv.setport(port);
            srv.sethostName(hostName);
            srv._save();
            
            getDb().closeAllObjects();
            srv = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add domaindata for the SRV record above method.
     */
    public void addDomainDataSRV(Integer id, String domainName, String srvid){
        try {
            DomainData dd = new DomainData(getDb());
            dd.setid(id);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domainName));
            dd.setdomainName(dom);
            dom.getdomainData().add(dd);
            dom._save();

            SRV srv = (SRV) SRV._open(getDb(), new Id(srvid));
            dd.setresourceRecord(srv);
            srv.getdomainData().add(dd);
            dd._save();
            srv._save();

            getDb().closeObject(dom.getOref());
            getDb().closeObject(srv.getOref());
            getDb().closeObject(dd.getOref());
            getDb().closeAllObjects();
            dom = null;
            srv = null;
            dd = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @param hostname
     * @param classOfData
     * @param id
     * @param information
     * @param ttl 
     * 
     * Adds a new TXT object to the database.
     */
    public void addTXT(String hostname, String classOfData, Integer id,
            String information, Integer ttl){
        try {
            TXT tx = new TXT(this.getDb());
            tx.setid(id);
            tx.sethostName(hostname);
            tx.setclassOfData(classOfData);
            tx.setinformation(information);
            tx.settimeToLive(ttl);
            tx._save();
            
            getDb().closeAllObjects();
            tx = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a domaindata object to db for the TXT record done above.
     */
    public void addDomainDataTXT(Integer id, String domainName, String txtid){
        try {
            DomainData dd = new DomainData(getDb());
            dd.setid(id);
            Domain dom = (Domain) Domain._open(getDb(), new Id(domainName));
            dd.setdomainName(dom);
            dom.getdomainData().add(dd);
            dom._save();

            TXT tx = (TXT) TXT._open(getDb(), new Id(txtid));
            dd.setresourceRecord(tx);
            tx.getdomainData().add(dd);
            dd._save();
            tx._save();

            getDb().closeObject(dom.getOref());
            getDb().closeObject(tx.getOref());
            getDb().closeObject(dd.getOref());
            getDb().closeAllObjects();
            dom = null;
            tx = null;
            dd = null;
            getDb().close();
        } catch (CacheException ex) {
            Logger.getLogger(NewZone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Makes the link between an IP Address and a Subnet by use of their
     * respective primary keys.
     * @param ipid
     * @param subid 
     */
    public void setUpIPAddressSubnet(String ipid, String subid) {
        try {
            IPAddress ip = (IPAddress) IPAddress._open(db, new Id(ipid));
            Subnet sub = (Subnet) Subnet._open(db, new Id(subid));
            ip.setsubnet(sub);
            sub.getipAddresses().add(ip);
            ip._save();
            sub._save();
            
            ip = null;
            sub = null;
            db.close();
        } catch (CacheException ex) {
            Logger.getLogger(CreateData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
