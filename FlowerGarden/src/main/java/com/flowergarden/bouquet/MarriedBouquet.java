package com.flowergarden.bouquet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MarriedBouquet implements Bouquet<GeneralFlower> {

	@XmlElement
	private float assemblePrice = 120;

	@XmlElement
	private List<GeneralFlower> flowerList = new ArrayList<>();

	@XmlElement
	private int id;

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
	public Collection<GeneralFlower> searchFlowersByLenght(int start, int end) {
		List<GeneralFlower> searchResult = new ArrayList<GeneralFlower>();
		for (GeneralFlower flower : flowerList) {
			if (flower.getLenght() >= start && flower.getLenght() <= end) {
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
	public int getId() {
		return id;
	}

	@Override
	public float getAssemblePrice() {
		return assemblePrice;
	}

	public int setId(int id) {
		return this.id = id;
	}

	@Override
	public String toString() {
		return "MarriedBouquet{" +
				"assemblePrice=" + assemblePrice +
				", flowerList=" + flowerList +
				", id=" + id +
				'}';
	}
}
