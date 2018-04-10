package com.flowergarden.flowers;

import com.flowergarden.properties.FreshnessInteger;

public class Chamomile extends GeneralFlower {
	
	private int petals;
	
	public Chamomile(int petals, int lenght, float price, FreshnessInteger fresh){
		this.petals = petals;
		this.lenght = lenght;
		this.price = price;
		this.freshness = fresh;
	}

	public Chamomile(int id, int petals, int lenght, float price, FreshnessInteger fresh){
		this(petals, lenght, price, fresh);
		this.id = id;
	}
	
	public boolean getPetal(){
		if (petals <=0) return false;
		petals =-1;
		return true;
	}
	
	public int getPetals(){
		return petals;
	}

	@Override
	public String toString() {
		return "Chamomile{" +
				"petals=" + petals +
				", id=" + id +
				", freshness=" + freshness +
				", price=" + price +
				", lenght=" + lenght +
				'}';
	}
}
