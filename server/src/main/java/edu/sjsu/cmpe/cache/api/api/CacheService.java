package edu.sjsu.cmpe.cache.api.api;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import edu.sjsu.cmpe.cache.api.api.api.resources.CacheResource;
import edu.sjsu.cmpe.cache.api.api.config.CacheServiceConfiguration;
import edu.sjsu.cmpe.cache.api.api.domain.Entry;
import edu.sjsu.cmpe.cache.api.api.repository.CacheInterface;
import edu.sjsu.cmpe.cache.api.api.repository.InMemoryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;



public class CacheService extends Service<CacheServiceConfiguration> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) throws Exception {
        new CacheService().run(args);
    }

    @Override
    public void initialize(Bootstrap<CacheServiceConfiguration> bootstrap) {
        bootstrap.setName("cache-server");
    }

    @Override
    public void run(CacheServiceConfiguration configuration,
            Environment environment) throws Exception {
        /** Cache APIs */
        ConcurrentHashMap<Long, Entry> map = new ConcurrentHashMap<Long, Entry>();
        CacheInterface cache = new InMemoryCache(map);
        environment.addResource(new CacheResource(cache));
        log.info("Loaded resources");

    }
}