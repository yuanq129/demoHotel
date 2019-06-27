package com.cosmose.demoHotel.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cosmose.demoHotel.model.Room;

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("select r.id from Reservation re left join re.rooms r left join r.hotel h "
			+ "where r.price between :priceMin and :priceMax and h.city=:city "
			+ "and re.bookingState=0 \r\n" + "and re.checkInDate not between :beginDate and :endDate \r\n"
			+ "and re.checkOutDate not between :beginDate and :endDate")
	List<Long> findRoomsByCriteria(@Param("priceMin") int priceMin, @Param("priceMax") int priceMax,
			@Param("city") String city, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

}
