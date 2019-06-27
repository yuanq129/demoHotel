package com.cosmose.demoHotel.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmose.demoHotel.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User,Long>{

}
