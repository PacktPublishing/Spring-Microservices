package com.brownfield.pss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.book.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByFlightNumberAndFlightDate(String flightNumber, String flightDate);
	
}
