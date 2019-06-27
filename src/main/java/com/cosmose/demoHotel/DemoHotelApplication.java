package com.cosmose.demoHotel;

import javax.transaction.Transactional;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cosmose.demoHotel.model.Hotel;
import com.cosmose.demoHotel.model.HotelOwner;
import com.cosmose.demoHotel.model.Room;
import com.cosmose.demoHotel.repository.HotelOwnerRepository;
import com.cosmose.demoHotel.repository.HotelRepository;
import com.cosmose.demoHotel.repository.RoomRepository;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class DemoHotelApplication implements ApplicationRunner {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private HotelOwnerRepository hotelOwnerRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoHotelApplication.class, args);
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		initHotelRooms();
	}

	private void initHotelRooms() {
		//init hotel owner
		HotelOwner ho = HotelOwner.builder().name("Tony Leung").build();
		hotelOwnerRepository.save(ho);

		//init 2 hotels 
		Hotel h1 = Hotel.builder().name("Spring Hotel").city("Shanghai").address("West Nanjing Road").hotelOwner(ho)
				.build();
		hotelRepository.save(h1);

		Hotel h2 = Hotel.builder().name("Summer Hotel").city("Shanghai").address("East Nanjing Road").hotelOwner(ho)
				.build();
		hotelRepository.save(h2);
		
		//init rooms for Spring Hotel
		Room r = Room.builder().price(10000).bedNum(1).hotel(h1).isAvailable(true)
				.build();
		roomRepository.save(r);
		r = Room.builder().price(15000).bedNum(2).hotel(h1).isAvailable(true)
				.build();
		roomRepository.save(r);
		
		//init rooms for Summer Hotel
		r = Room.builder().price(30000).bedNum(2).hotel(h2).isAvailable(true)
				.build();
		roomRepository.save(r);
		r = Room.builder().price(45000).bedNum(2).hotel(h2).isAvailable(true)
				.build();
		roomRepository.save(r);
		
		r = Room.builder().price(30000).bedNum(1).hotel(h2).isAvailable(true)
				.build();
		roomRepository.save(r);
	}

}
