package com.flowergarden.spring;

import com.flowergarden.dao.DAOSpringService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FlowerShopXMLApp {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("application-context.xml");
        DAOSpringService daoSpringService = applicationContext.getBean(DAOSpringService.class);
        daoSpringService.run();
    }
}
