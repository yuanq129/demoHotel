package com.cosmose.demoHotel.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmose.demoHotel.model.Hotel;

@Transactional
public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
