package com.dulion.greenhouse.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SensorResolver implements GraphQLResolver<Sensor> {

  @Autowired
  private ReadingRepository readings;

  public Iterable<Reading> getReadings(@NotNull Sensor sensor) {
    return readings.findBySensorId(sensor.getSensorId());
  }
}
