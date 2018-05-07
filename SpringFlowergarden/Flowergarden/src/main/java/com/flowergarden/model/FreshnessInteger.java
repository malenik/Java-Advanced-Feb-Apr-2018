package com.flowergarden.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FreshnessInteger implements Freshness<Integer>, Comparable<FreshnessInteger> {

	@XmlElement
	private Integer freshness;
	
	@Override
	public Integer getFreshness() {
		return freshness;
	}

	public FreshnessInteger(Integer freshness){
		this.freshness = freshness;
	}
	
	public FreshnessInteger(){
	}

	@Override
	public int compareTo(FreshnessInteger o) {
		if (freshness > o.getFreshness()) return 1;
		if (freshness < o.getFreshness()) return -1;
		return 0;
	}

	@Override
	public String toString() {
		return freshness.toString();
	}

	public void reduce() {
		if (freshness > 0) freshness = freshness - 1;
		else throw new UnsupportedOperationException("Сan not reduce the freshness 0");
	}
}
