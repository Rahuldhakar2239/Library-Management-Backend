package com.library.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.management.entities.MemberShip;

public interface MemberShipRepo extends JpaRepository<MemberShip, Integer> {

}
