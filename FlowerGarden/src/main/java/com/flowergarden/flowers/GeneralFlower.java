package com.flowergarden.flowers;

import javax.xml.bind.annotation.XmlElement;

import com.flowergarden.properties.FreshnessInteger;

public class GeneralFlower implements Flower<Integer>, Comparable<GeneralFlower> {

	int id;

	FreshnessInteger freshness;
	
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

	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public float getPrice() {
		return price;
	}

	@Override
	public int getLenght() {
		return lenght;
	}

	@Override
	public int compareTo(GeneralFlower compareFlower) {
		int compareFresh = compareFlower.getFreshness().getFreshness();		
		return this.getFreshness().getFreshness() - compareFresh;
	}

}
