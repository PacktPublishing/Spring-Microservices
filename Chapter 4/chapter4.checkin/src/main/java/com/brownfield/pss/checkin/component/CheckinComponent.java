package com.brownfield.pss.checkin.component;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.brownfield.pss.checkin.entity.CheckInRecord;
import com.brownfield.pss.checkin.repository.CheckinRepository;

@Component
public class CheckinComponent {
	private static final Logger logger = LoggerFactory.getLogger(CheckinComponent.class);

	CheckinRepository checkinRepository;
	Sender sender;
	
	@Autowired
	CheckinComponent(CheckinRepository checkinRepository, Sender sender){
		this.checkinRepository = checkinRepository;
		this.sender = sender;
	}

	public long checkIn(CheckInRecord checkIn) {
		checkIn.setCheckInTime(new Date());
		logger.info("Saving checkin ");
		//save
		long id = checkinRepository.save(checkIn).getId();
		logger.info("Successfully saved checkin ");
		//send a message back to booking to update status
		logger.info("Sending booking id "+ id);
		sender.send(id);	
		return id;
		
	}
	
	public CheckInRecord getCheckInRecord(long id){
		return checkinRepository.findOne(id);
	}
	
}	
