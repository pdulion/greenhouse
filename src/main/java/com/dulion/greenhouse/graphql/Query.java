package com.dulion.greenhouse.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  private static final Logger logger = getLogger(Query.class);

  @Autowired
  private SensorRepository sensors;

  @Autowired
  private ReadingRepository readings;

  public Iterable<Sensor> sensors() {
    return sensors.findAll();
  }

  public Iterable<Reading> readings(String datetime, int interval) {
    logger.info("Datetime: {}, Interval: {}", datetime, interval);
    return readings.findAll();
  }

  public Optional<Sensor> sensorById(String sensorId) {
    return sensors.findById(UUID.fromString(sensorId));
  }

  public Optional<Reading> readingById(String readingId) {
    return readings.findById(UUID.fromString(readingId));
  }
}
