package com.brownfield.pss.client;

 

import com.fasterxml.jackson.annotation.JsonIgnore;

 
public class Passenger {

  	long id;
    
    String firstName;
    String lastName;
    String gender;
    
      @JsonIgnore
    private BookingRecord bookingRecord;
    
	public Passenger() {
	}    
    
	public Passenger(String firstName, String lastName, String gender,BookingRecord bookingRecord ) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.bookingRecord= bookingRecord;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public BookingRecord getBookingRecord() {
		return bookingRecord;
	}

	public void setBookingRecord(BookingRecord bookingRecord) {
		this.bookingRecord = bookingRecord;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender +"]";
	}
    
    
}
