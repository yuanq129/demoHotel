package com.cosmose.demoHotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cosmose.demoHotel.json.ResultGen;
import com.cosmose.demoHotel.model.Reservation;
import com.cosmose.demoHotel.service.ReservationService;
import com.google.gson.Gson;


@RestController
@RequestMapping("/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("/add")
	public String makeReservation(@RequestBody String jsonReservation) {
		String ret = reservationService.makeReservation(new Gson().fromJson(jsonReservation, Reservation.class));
		return ResultGen.resultJsonGen(ret);
	}
	
	@RequestMapping(value="/check/{id}")
	public String checkReservationByUserId(@PathVariable(name="id") String userId) {
		return reservationService.checkReservationByUserId(Long.valueOf(userId));
	}
	
	@RequestMapping(value="/cancel/{id}")
	public String cancelReservation(@PathVariable(name="id") String reservationId) {
		String ret = reservationService.cancelReservation(Long.valueOf(reservationId));
		return ResultGen.resultJsonGen(ret);
	}
}
