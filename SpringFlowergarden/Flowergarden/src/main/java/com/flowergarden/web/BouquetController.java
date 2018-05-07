package com.flowergarden.web;

import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.dao.MarriedBouquetDAO;
import com.flowergarden.model.Bouquet;
import com.flowergarden.model.Flower;
import com.flowergarden.model.GeneralFlower;
import com.flowergarden.model.MarriedBouquet;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/bouquet")
@RestController
public class BouquetController {

    private MarriedBouquetDAO marriedBouquetDAO;
    private FlowerDAO flowerDAO;

    @Autowired
    public BouquetController(MarriedBouquetDAO marriedBouquetDAO, FlowerDAO flowerDAO) {
        this.marriedBouquetDAO = marriedBouquetDAO;
        this.flowerDAO = flowerDAO;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getStatus() {
        return "success";
    }

    @RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
    public Bouquet getBouquetPrice(@PathVariable("id") int id) throws SQLException {
        return marriedBouquetDAO.getBouquetById(id);
    }

    @RequestMapping(value = "/{id}/freshness-reduce", method = {RequestMethod.GET, RequestMethod.POST})
    public void reduceFreshness(@PathVariable("id") int id) {
        try {
            MarriedBouquet bouquet = marriedBouquetDAO.getBouquetById(id);
            for (GeneralFlower flower : bouquet.getFlowers()) {
                flower.getFreshness().reduce();
                marriedBouquetDAO.updateBouquet(id, bouquet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/flower/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public Flower getFlower(@PathVariable("id") int id) throws SQLException {
        return flowerDAO.getFlowerById(id);
    }

    @RequestMapping(value = "/{id}/flowers", method = RequestMethod.GET)
    public Collection<GeneralFlower> getFlowers(@PathVariable("id") int id) throws SQLException {
        Bouquet<GeneralFlower> bouquet = marriedBouquetDAO.getBouquetById(id);
        return bouquet.getFlowers();
    }
}