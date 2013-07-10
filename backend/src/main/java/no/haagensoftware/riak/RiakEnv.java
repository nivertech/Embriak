package no.haagensoftware.riak;

import no.haagensoftware.riak.dao.RiakBucketDao;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.raw.pbc.PBClientConfig;
import com.basho.riak.client.raw.pbc.PBClusterConfig;

public class RiakEnv {
	private IRiakClient riakClient;
	private RiakBucketDao riakBucketDao;
	
	public RiakEnv() {
		
	}
	
	public void setup()  throws RiakException {
		// Riak Protocol Buffers client with supplied IP and Port
        PBClusterConfig riakClusterConfig = new PBClusterConfig(20);
        // See above examples for client config options
        PBClientConfig riakClientConfig = PBClientConfig.defaults();
        //riakClusterConfig.addHosts(riakClientConfig, "192.168.1.102", "192.168.1.104", "192.168.1.105");
        riakClusterConfig.addHosts(riakClientConfig, "127.0.0.1");
        riakClient = RiakFactory.newClient(riakClusterConfig);
        
        riakBucketDao = new RiakBucketDao(riakClient);
	}
	
	public void teardown() {
		riakClient.shutdown();
	}
	
	public RiakBucketDao getRiakBucketDao() {
		return riakBucketDao;
	}
	
	public void setRiakBucketDao(RiakBucketDao riakBucketDao) {
		this.riakBucketDao = riakBucketDao;
	}
}
