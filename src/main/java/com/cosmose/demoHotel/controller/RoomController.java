package com.cosmose.demoHotel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmose.demoHotel.json.SearchCriteria;
import com.cosmose.demoHotel.repository.ReservationRepository;
import com.cosmose.demoHotel.repository.UserRepository;
import com.cosmose.demoHotel.service.RoomService;
import com.google.gson.Gson;


@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/search")
	public String searchForRooms(@RequestBody String jsonSearchCriteira) {
		SearchCriteria criteria = new Gson().fromJson(jsonSearchCriteira, SearchCriteria.class);
		return roomService.findRoomsByCriteria(criteria);
	}
	
//	@RequestMapping("/json")
//	public void gen() {
//		Gson gson = new Gson();
//		User user = ur.findById(1l).get();
//		log.info(user.toString());
//		
//		Room room = roomRepository.findById(1l).get();
//		List<Room> rooms = roomRepository.findByHotelId(1l);
////		log.info(rooms.toString());
//		
//		Reservation r = Reservation.builder().bookingState(BookingState.ACTIVE).checkInDate(new Date())
//				.checkOutDate(new Date()).residentName("Jake").user(user).build();
//
//		reservationRepository.save(r);
//		String res = gson.toJson(rooms,new TypeToken<List<Room>>(){}.getType());
//		log.info(res);
//	}
	
	
}
