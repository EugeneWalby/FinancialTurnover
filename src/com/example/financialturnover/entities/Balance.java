package com.example.financialturnover.entities;

public class Balance {
	int id;
	double value;

	public Balance() {
		super();
	}
	
	public Balance(int id, double value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public Balance(double value) {
		super();
		this.id = 1;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}
}
