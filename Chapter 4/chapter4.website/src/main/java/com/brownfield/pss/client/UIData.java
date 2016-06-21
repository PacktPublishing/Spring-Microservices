package com.brownfield.pss.client;

import java.util.List;

public class UIData {
	
	private SearchQuery searchQuery;
	private List<Flight> flights;
	private Flight selectedFlight;
	private Passenger passenger;

	private String bookingid;
	
	
	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public SearchQuery getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(SearchQuery searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
}