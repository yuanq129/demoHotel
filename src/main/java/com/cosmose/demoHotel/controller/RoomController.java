package com.cosmose.demoHotel.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmose.demoHotel.json.SearchCriteria;
import com.cosmose.demoHotel.repository.ReservationRepository;
import com.cosmose.demoHotel.service.RoomService;
import com.google.gson.Gson;


@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	ReservationRepository reservationRepository;

	@RequestMapping("/search")
	public String searchForRooms(@RequestBody String jsonSearchCriteira) {
		SearchCriteria criteria = new Gson().fromJson(jsonSearchCriteira, SearchCriteria.class);
		return roomService.findRoomsByCriteria(criteria);
	}
	
}
