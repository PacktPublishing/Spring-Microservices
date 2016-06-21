package com.brownfield.pss.client;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class BrownFieldSiteController {
	private static final Logger logger = LoggerFactory.getLogger(BrownFieldSiteController.class);

	@LoadBalanced
	@Autowired
	RestTemplate searchClient;

	@LoadBalanced
	@Autowired
	RestTemplate bookingClient;

	@LoadBalanced
	@Autowired
	RestTemplate checkInClient;
	
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String greetingForm(Model model) {
    	SearchQuery query = new  SearchQuery("NYC","SFO","22-JAN-16");
    	UIData uiData = new UIData();
    	uiData.setSearchQuery(query);
        model.addAttribute("uidata",uiData );
        return "search";
    }   

   @RequestMapping(value="/search", method=RequestMethod.POST)
   public String greetingSubmit(@ModelAttribute UIData uiData, Model model) {
		Flight[] flights = searchClient.postForObject("http://search-apigateway/api/search/get", uiData.getSearchQuery(), Flight[].class); 
		uiData.setFlights(Arrays.asList(flights));
		model.addAttribute("uidata", uiData);
       return "result";
   }
   
   @RequestMapping(value="/book/{flightNumber}/{origin}/{destination}/{flightDate}/{fare}", method=RequestMethod.GET)
   public String bookQuery(@PathVariable String flightNumber, 
		   @PathVariable String origin, 
		   @PathVariable String destination, 
		   @PathVariable String flightDate, 
		   @PathVariable String fare, 
		   Model model) {
   		UIData uiData = new UIData();
   		Flight flight = new Flight(flightNumber, origin,destination,flightDate,new Fares(fare,"AED"));
   		uiData.setSelectedFlight(flight);
   		uiData.setPassenger(new Passenger());
	   model.addAttribute("uidata",uiData);
       return "book"; 
   }
   @RequestMapping(value="/confirm", method=RequestMethod.POST)
   public String ConfirmBooking(@ModelAttribute UIData uiData, Model model) {
	   	Flight flight= uiData.getSelectedFlight();
		BookingRecord booking = new BookingRecord(flight.getFlightNumber(),flight.getOrigin(),
				  flight.getDestination(), flight.getFlightDate(),null,
				  flight.getFares().getFare());
		Set<Passenger> passengers = new HashSet<Passenger>();
		Passenger pax = uiData.getPassenger();
		pax.setBookingRecord(booking);
		passengers.add(uiData.getPassenger());
	 		booking.setPassengers(passengers);
		long bookingId =0;
		try { 
			//long bookingId = bookingClient.postForObject("http://book-service/booking/create", booking, long.class); 
			 bookingId = bookingClient.postForObject("http://book-apigateway/api/booking/create", booking, long.class); 
			logger.info("Booking created "+ bookingId);
		}catch (Exception e){
			logger.error("BOOKING SERVICE NOT AVAILABLE...!!!");
		}
		model.addAttribute("message", "Your Booking is confirmed. Reference Number is "+ bookingId);
		return "confirm";
   }
   @RequestMapping(value="/search-booking", method=RequestMethod.GET)
   public String searchBookingForm(Model model) {
   		UIData uiData = new UIData();
   		uiData.setBookingid("5");
   		model.addAttribute("uidata",uiData );
   		return "bookingsearch";
   }   

	@RequestMapping(value="/search-booking-get", method=RequestMethod.POST)
	public String searchBookingSubmit(@ModelAttribute UIData uiData, Model model) {
		Long id = new Long(uiData.getBookingid());
 		BookingRecord booking = bookingClient.getForObject("http://book-apigateway/api/booking/get/"+id, BookingRecord.class);
		Flight flight = new Flight(booking.getFlightNumber(), booking.getOrigin(),booking.getDestination()
				,booking.getFlightDate(),new Fares(booking.getFare(),"AED"));
		Passenger pax = booking.getPassengers().iterator().next();
		Passenger paxUI = new Passenger(pax.getFirstName(),pax.getLastName(),pax.getGender(),null);
		uiData.setPassenger(paxUI);
		uiData.setSelectedFlight(flight);
		uiData.setBookingid(id.toString()); 
		model.addAttribute("uidata", uiData);
	   return "bookingsearch";
	}
	
	@RequestMapping(value="/checkin/{flightNumber}/{origin}/{destination}/{flightDate}/{fare}/{firstName}/{lastName}/{gender}/{bookingid}", method=RequestMethod.GET)
	public String bookQuery(@PathVariable String flightNumber, 
			   @PathVariable String origin, 
			   @PathVariable String destination, 
			   @PathVariable String flightDate, 
			   @PathVariable String fare, 
			   @PathVariable String firstName, 
			   @PathVariable String lastName, 
			   @PathVariable String gender, 
			   @PathVariable String bookingid, 
			   Model model) {
		
 
			CheckInRecord checkIn = new CheckInRecord(firstName, lastName, "28C", null,
					  									flightDate,flightDate, new Long(bookingid).longValue());

			long checkinId = checkInClient.postForObject("http://checkin-apigateway/api/checkin/create", checkIn, long.class); 
	   		model.addAttribute("message","Checked In, Seat Number is 28c , checkin id is "+ checkinId);
	       return "checkinconfirm"; 
	}	
}