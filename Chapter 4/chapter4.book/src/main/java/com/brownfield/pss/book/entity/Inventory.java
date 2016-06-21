package com.brownfield.pss.book.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
    
    String flightNumber;
    String flightDate;
    int available;
    
    public Inventory(String flightNumber, String flightDate, int available) {
		super();
		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
		this.available = available;
	}


	public Inventory(){}
    
    
   public boolean isAvailable(int count){
    	return ((available-count) >5);
    }

   public int getBookableInventory(){
	   return available - 5;
   }

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}


	public String getFlightDate() {
		return flightDate;
	}


	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}


	public int getAvailable() {
		return available;
	}


	public void setAvailable(int available) {
		this.available = available;
	}


	@Override
	public String toString() {
		return "Inventory [id=" + id + ", flightNumber=" + flightNumber + ", flightDate=" + flightDate + ", available="
				+ available + "]";
	}
    
    
}
