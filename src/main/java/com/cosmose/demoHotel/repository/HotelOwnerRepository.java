package com.cosmose.demoHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmose.demoHotel.model.HotelOwner;;

public interface HotelOwnerRepository  extends JpaRepository<HotelOwner, Long>{

}
