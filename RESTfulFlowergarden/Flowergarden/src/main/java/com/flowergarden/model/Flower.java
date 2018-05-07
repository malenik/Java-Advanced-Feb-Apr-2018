package com.flowergarden.model;

public interface Flower<T> {

	Freshness<T> getFreshness();

	float getPrice();

	int getLength();

}
