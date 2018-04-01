package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlElement;

import com.flowergarden.json.AbstractJson;
import com.flowergarden.properties.FreshnessInteger;

import java.util.Comparator;

public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower>, AbstractJson {

	public GeneralFlower(FreshnessInteger freshness, float price, int lenght) {
        this.freshness = freshness;
        this.price = price;
        this.lenght = lenght;
    }

	public GeneralFlower() {
	}

	@XmlElement
    FreshnessInteger freshness;

    @XmlElement
    int id;

	@XmlElement
	float price;
	
	@XmlElement
	int lenght;
	
	public void setFreshness(FreshnessInteger fr){
		freshness = fr;
	}
	
	@Override
	public FreshnessInteger getFreshness() {
		return freshness;
	}

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

	@Override
	public float getPrice() {
		return price;
	}

	@Override
	public int getLenght() {
		return lenght;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	@Override
	public int compareTo(GeneralFlower compareFlower) {
		int compareFresh = compareFlower.getFreshness().getFreshness();
		return this.getFreshness().getFreshness() - compareFresh;
	}

	@Override
	public String toString() {
		return "GeneralFlower{" +
                "id=" + id +
				"freshness=" + freshness +
				", price=" + price +
				", lenght=" + lenght +
				'}';
	}
}
