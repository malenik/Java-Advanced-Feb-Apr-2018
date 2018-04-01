package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.impl.FlowerJsonFileDAO;
import com.flowergarden.dao.impl.MarriedBouquetJsonFileDAO;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DAOSpringService {

    private FlowerDAO flowerDAO;

    private MarriedBouquetDAO marriedBouquetDAO;

    @Autowired
    public DAOSpringService(FlowerDAO flowerDAO, MarriedBouquetDAO marriedBouquetDAO) {
        this.flowerDAO = flowerDAO;
        this.marriedBouquetDAO = marriedBouquetDAO;
    }

    public DAOSpringService() {
    }

    public void setFlowerDAO(FlowerDAO flowerDAO) {
        this.flowerDAO = flowerDAO;
    }

    public void setMarriedBouquetDAO(MarriedBouquetDAO marriedBouquetDAO) {
        this.marriedBouquetDAO = marriedBouquetDAO;
    }

    public void run() throws Exception {

        System.out.println("******************************************");

        System.out.println("Call FlowerDAO: ");

        List<GeneralFlower> listFlowers = flowerDAO.getAllFlowers();
        for (GeneralFlower generalFlower : listFlowers) {
            System.out.println(generalFlower);
        }

        GeneralFlower someFlower = listFlowers.get(3);
        someFlower.setFreshness(new FreshnessInteger(333));
        flowerDAO.updateFlower(someFlower.getId(), (Rose) someFlower);
        System.out.println(flowerDAO.getFlowerById(someFlower.getId()));

        System.out.println("*****************************************");

        System.out.println("Call MarriedBouquetDAO: ");

        Bouquet bouquet1 = marriedBouquetDAO.getBouquetById(1);
        System.out.println(bouquet1);

        ((MarriedBouquet) bouquet1).setAssembledPrice(17);

        marriedBouquetDAO.updateBouquet(((MarriedBouquet) bouquet1).getBouquetId(), (MarriedBouquet) bouquet1);


        List<Bouquet> listBouquets = marriedBouquetDAO.getAllBouquets();
        for (Bouquet bouquet : listBouquets) {
            System.out.println(bouquet);
        }

        System.out.println("*****************************************");

        FlowerJsonFileDAO flowerJsonFileDAO = new FlowerJsonFileDAO();
        flowerJsonFileDAO.save(someFlower);
        GeneralFlower readFlower = flowerJsonFileDAO.read();
        System.out.println("Flower from json: " + readFlower);

        MarriedBouquetJsonFileDAO marriedBouquetJsonFileDAO = new MarriedBouquetJsonFileDAO();
        marriedBouquetJsonFileDAO.save((MarriedBouquet)bouquet1);
        MarriedBouquet readBouquet = marriedBouquetJsonFileDAO.read();
        System.out.println("Bouquet from json: " + readBouquet);


    }
}
