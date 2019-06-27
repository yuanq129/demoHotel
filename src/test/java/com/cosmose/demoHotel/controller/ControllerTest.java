package com.cosmose.demoHotel.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cosmose.demoHotel.BaseTest;
import com.cosmose.demoHotel.model.BookingState;
import com.cosmose.demoHotel.model.Reservation;
import com.cosmose.demoHotel.model.Room;
import com.cosmose.demoHotel.repository.ReservationRepository;
import com.cosmose.demoHotel.tools.Tool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ControllerTest extends BaseTest {

	@Autowired
	ReservationRepository reservationRepository;

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	/**
	 * test register customer
	 */
	public void test01() {
		String jsonUserInfo = "{\"name\":\"yuanq\",\"phone\":\"18901238882\",\"address\":\"Pudong district, Shanghai\"}";
		ResponseEntity<String> response = testRestTemplate.postForEntity(base.toString() + "/user/add", jsonUserInfo,
				String.class);
		TestCase.assertEquals("{\"result\":\"success\"}", response.getBody());
	}

	@Test
	/**
	 * test make a reservation
	 */
	public void test02() {
		String jsonReservation = Tool.readJsonData("src/test/java/com/cosmose/demoHotel/jsonTemplate/reservation.json");
		ResponseEntity<String> response = testRestTemplate.postForEntity(base.toString() + "/reservation/add",
				jsonReservation, String.class);
		TestCase.assertEquals("{\"result\":\"success\"}", response.getBody());
	}

	@Test
	/**
	 * test search rooms by criteria
	 */
	public void test03() {
		String criteria = "{\"checkInDate\":\"Jun 22, 2019 10:34:45 AM\",\"checkOutDate\":\"Jun 23, 2019 10:34:45 AM\",\"city\":\"Shanghai\",\"priceMin\":9000,\"priceMax\":19000}";
		
		ResponseEntity<String> response = testRestTemplate.postForEntity(base.toString() + "/room/search",
				criteria, String.class);
		String jsonRet = response.getBody();
		log.info(jsonRet);
		List<Room> rooms = new Gson().fromJson(jsonRet, new TypeToken<List<Reservation>>() {}.getType());
		
		TestCase.assertEquals(2, rooms.size());
	}

	@Test
	/**
	 * test check reservations for user
	 */
	public void test04() {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString() + "reservation/check/1",
				String.class);
		String jsonRet = response.getBody();
		log.info(jsonRet);
		List<Reservation> r = new Gson().fromJson(jsonRet, new TypeToken<List<Reservation>>() {
		}.getType());
		TestCase.assertEquals(1, r.size());
	}

	/**
	 * test cancel a reservation
	 */
	@Test
	public void test05() {
		restTemplate.getForEntity(base.toString() + "reservation/cancel/1",
				String.class);
		Reservation r = reservationRepository.findById(1l).get();
		TestCase.assertEquals(BookingState.valueOf("CANCELLED"), r.getBookingState());
	}
}
