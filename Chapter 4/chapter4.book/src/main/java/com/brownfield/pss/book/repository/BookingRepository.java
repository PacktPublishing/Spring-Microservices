package com.brownfield.pss.book.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.brownfield.pss.book.entity.BookingRecord;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {
	
}
