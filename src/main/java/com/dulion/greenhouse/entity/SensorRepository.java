package com.dulion.greenhouse.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, String> {

  List<Sensor> findAll();

}
