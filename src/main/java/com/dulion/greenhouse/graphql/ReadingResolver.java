package com.dulion.greenhouse.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadingResolver implements GraphQLResolver<Reading> {

  @Autowired
  private SensorRepository sensors;

  public Optional<Sensor> getSensor(@NotNull Reading reading) {
    return sensors.findById(reading.getSensorId());
  }
}
