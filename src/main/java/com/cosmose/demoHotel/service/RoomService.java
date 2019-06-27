package com.cosmose.demoHotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmose.demoHotel.json.SearchCriteria;
import com.cosmose.demoHotel.model.Room;
import com.cosmose.demoHotel.repository.RoomRepository;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public String findRoomsByCriteria(SearchCriteria criteria) {

		List<Long> ids = roomRepository.findRoomsByCriteria(
				criteria.getPriceMin(),
				criteria.getPriceMax(),
				criteria.getCity(),
				criteria.getCheckInDate(),
				criteria.getCheckOutDate());

		List<Room> rooms = roomRepository.findAllById(ids);
		
		Gson m = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {

			@Override
			public boolean shouldSkipField(FieldAttributes f) {
				return f.getName().contains("hotel");
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}

		}).create();

		return m.toJson(rooms, new TypeToken<List<Room>>() {}.getType());
	}

}
