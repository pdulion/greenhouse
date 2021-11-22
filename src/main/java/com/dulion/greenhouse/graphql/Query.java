package com.dulion.greenhouse.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  @Autowired
  private SensorRepository sensors;

  @Autowired
  private ReadingRepository readings;

  public Iterable<Sensor> sensors() {
    return sensors.findAll();
  }

  public Iterable<Reading> readings(int interval, int page, int size) {
    if (size > 0) {
      return readings.findAll(PageRequest.of(page, size));
    }
    return readings.findAll();
  }

  public Optional<Sensor> sensorById(String sensorId) {
    return sensors.findById(UUID.fromString(sensorId));
  }

  public Optional<Reading> readingById(String readingId) {
    return readings.findById(UUID.fromString(readingId));
  }
}
