package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.impl.ConnectionPoolImpl;
import com.flowergarden.dao.impl.FlowerDAOImpl;
import com.flowergarden.dao.impl.MarriedBouquetDAOImpl;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;

import java.util.List;

public class DAOLauncher {
    public static void main(String[] args) throws Exception {

        try (ConnectionPool connectionPool = new ConnectionPoolImpl()) {


            System.out.println("******************************************");

            System.out.println("Call FlowerDAO: ");

            FlowerDAOImpl flowerDAO = new FlowerDAOImpl(connectionPool);
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

            MarriedBouquetDAOImpl marriedBouquetDAO = new MarriedBouquetDAOImpl(connectionPool);
            Bouquet bouquet1 = marriedBouquetDAO.getBouquetById(1);
            System.out.println(bouquet1);

            ((MarriedBouquet) bouquet1).setAssembledPrice(17);

            marriedBouquetDAO.updateBouquet(((MarriedBouquet) bouquet1).getBouquetId(), (MarriedBouquet) bouquet1);


            List<Bouquet> listBouquets = marriedBouquetDAO.getAllBouquets();
            for (Bouquet bouquet : listBouquets) {
                System.out.println(bouquet);
            }
        }
    }
}