package com.flowergarden.spring;

import com.flowergarden.dao.DAOSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FlowerShopAnnotationApp {

    @Autowired
    DAOSpringService daoSpringService;

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.flowergarden");
        DAOSpringService daoSpringService = applicationContext.getBean(DAOSpringService.class);
        daoSpringService.run();
    }
}
