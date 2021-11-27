package com.dulion.greenhouse.entity;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, UUID> {
  Iterable<Reading> findBySensorId(UUID sensorId);
}
