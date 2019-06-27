package com.cosmose.demoHotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmose.demoHotel.json.ResultType;
import com.cosmose.demoHotel.model.BookingState;
import com.cosmose.demoHotel.model.Reservation;
import com.cosmose.demoHotel.repository.ReservationRepository;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	public String makeReservation(Reservation reservation) {
		Object u = reservationRepository.save(reservation);
		if(u == null) {
			return ResultType.RESULT_FAILURE;
		}
		else {
			return ResultType.RESULT_SUCCESS;
		}
	}
	
	public String cancelReservation(Long reservationId) {
		Reservation booking = reservationRepository.getOne(reservationId);
		booking.setBookingState(BookingState.CANCELLED);
		Object u = reservationRepository.save(booking);
		if(u == null) {
			return ResultType.RESULT_FAILURE;
		}
		else {
			return ResultType.RESULT_SUCCESS;
		}
	}
	
	public String checkReservationByUserId(Long userId) {
		List<Reservation> reservations = reservationRepository.findByUserId(userId);

		Gson m = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getName().contains("rooms");
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}

		}).create();

		String ret = m.toJson(reservations, new TypeToken<List<Reservation>>() {}.getType());
		return ret;
	}
}
