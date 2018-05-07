package com.flowergarden.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower> {

	public GeneralFlower(FreshnessInteger freshness, float price, int length) {
        this.freshness = freshness;
        this.price = price;
        this.length = length;
    }

	public GeneralFlower() {
	}

	@XmlElement(name = "freshness")
    FreshnessInteger freshness;

    @XmlElement (name = "id")
    int id;

	@XmlElement(name = "price")
	float price;

    @XmlElement(name = "length")
	int length;
	
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
	public int getLength() {
		return length;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setLength(int length) {
		this.length = length;
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
				", length=" + length +
				'}';
	}
}
