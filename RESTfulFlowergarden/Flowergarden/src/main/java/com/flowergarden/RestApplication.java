package com.flowergarden;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.dao.MarriedBouquetDAO;
import com.flowergarden.web.BouquetRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@Component
public class RestApplication extends Application {

    @Autowired
    private MarriedBouquetDAO marriedBouquetDAO;

    @Autowired
    private FlowerDAO flowerDAO;

    private static final Set<Object> singletons = new HashSet<>();

    public RestApplication() {
    }

    @PostConstruct
    public void postConstruct() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        BouquetRest bouquetRest = new BouquetRest(marriedBouquetDAO, flowerDAO, new ObjectMapper());
        singletons.add(bouquetRest);
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
