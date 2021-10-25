package com.dulion.greenhouse;

import static org.junit.jupiter.api.Assertions.*;

import com.dulion.greenhouse.entity.Initializer;
import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EntityTest {

  @Autowired
  private SensorRepository sensors;

  @Autowired
  private ReadingRepository readings;

  @Autowired
  private Initializer loader;

  @Test
  public void testList() {
    loader.insertData();

    List<Sensor> sensorList = new ArrayList<>();
    sensors.findAll().forEach(sensorList::add);

    List<Reading> readingList = new ArrayList<>();
    readings.findAll().forEach(readingList::add);

    assertEquals(2, sensorList.size());
    assertEquals(UUID.fromString("75D5F8E6-8A4D-4D66-8B8C-CFC2A7DB2421"), sensorList.get(0).getSensorId());
    assertEquals(UUID.fromString("E56E12D5-52CA-49A1-A7F6-4392325D960D"), sensorList.get(1).getSensorId());
    assertEquals(6, readingList.size());
  }
}
