package com.dulion.greenhouse.entity;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long> {

  List<Reading> findAll();
}
