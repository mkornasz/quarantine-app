package com.java.kurs.quarantineapp.repository;

import com.java.kurs.quarantineapp.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {

}
