package com.raven.springbootonetomanybi.repository;

import com.raven.springbootonetomanybi.entity.Owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
