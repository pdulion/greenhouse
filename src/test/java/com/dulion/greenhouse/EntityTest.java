package com.dulion.greenhouse;

import static org.junit.jupiter.api.Assertions.*;

import com.dulion.greenhouse.entity.Reading;
import com.dulion.greenhouse.entity.ReadingRepository;
import com.dulion.greenhouse.entity.Sensor;
import com.dulion.greenhouse.entity.SensorRepository;
import java.util.List;
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
  private TableLoader loader;

  @Test
  public void testList() {
    loader.insertData();
    List<Sensor> sensorList = sensors.findAll();
    List<Reading> readingList = readings.findAll();
    assertEquals(2, sensorList.size());
    assertEquals(6, readingList.size());
  }
}
