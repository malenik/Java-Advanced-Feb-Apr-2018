package com.flowergarden.bouquet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.json.AbstractJson;

import javax.xml.bind.annotation.XmlElement;

public class MarriedBouquet implements Bouquet<GeneralFlower>, AbstractJson {

	private float assemblePrice = 120;
	List<GeneralFlower> flowerList = new ArrayList<>();

    @XmlElement
    int bouquetId;

    public MarriedBouquet(float assemblePrice, List<GeneralFlower> flowerList) {
        this.assemblePrice = assemblePrice;
        this.flowerList = flowerList;
    }

    public MarriedBouquet(int bouquetId, float assemblePrice) {
        this(assemblePrice, new ArrayList<>());
        this.bouquetId = bouquetId;
    }

	public MarriedBouquet() {
	}

	public int getBouquetId() {
		return bouquetId;
	}

	public void setBouquetId(int bouquetId) {
		this.bouquetId = bouquetId;
	}

    @Override
	public float getPrice() {
		float price = assemblePrice;
		for (GeneralFlower flower : flowerList) {
			price += flower.getPrice();
		}
		return price;
	}

	@Override
	public void addFlower(GeneralFlower flower) {
			flowerList.add(flower);
	}

    @Override
    public void addFlowers(Collection<GeneralFlower> flowers) {
        flowerList.addAll(flowers);
    }

    @Override
	public Collection<GeneralFlower> searchFlowersByLength(int start, int end) {
		List<GeneralFlower> searchResult = new ArrayList<>();
		for (GeneralFlower flower : flowerList) {
			if (flower.getLength() >= start && flower.getLength() <= end) {
				searchResult.add(flower);
			}
		}
		return searchResult;
	}

	@Override
	public void sortByFreshness() {
		Collections.sort(flowerList);
	}

	@Override
	public Collection<GeneralFlower> getFlowers() {
		return flowerList;
	}

	public void setAssembledPrice(float price) {
		assemblePrice = price;
	}

	@Override
	public float getAssemblePrice() { return assemblePrice; }

	@Override
	public String toString() {
		return "MarriedBouquet{" +
				"assemblePrice=" + assemblePrice +
				", flowerList=" + flowerList +
				", bouquetId=" + bouquetId +
				'}';
	}
}
