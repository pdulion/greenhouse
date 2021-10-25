package com.dulion.greenhouse.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

  private static Logger LOG = LoggerFactory.getLogger(Initializer.class);

  @Autowired
  private JdbcTemplate template;

  private boolean initialized = false;

  public void insertData() {
    LOG.info("Storage Initialization: Checking database status...");
    template.query("SELECT count(*) FROM sensor;", rs -> {
      initialized = rs.getInt(1) > 0;
    });

    if (initialized) {
      LOG.info("Storage Initialization: Already initialized");
      return;
    }

    LOG.info("Storage Initialization: Adding initial sensors and readings...");

    template.execute(String.join(
        " ",
        "INSERT INTO Sensor",
        "(sensor_id, created_at, updated_at, name, heat_degrees, cool_degrees, dry_percent, dry_on_span, dry_off_span)",
        "VALUES",
        "('75D5F8E6-8A4D-4D66-8B8C-CFC2A7DB2421', NOW(), NOW(), 'Greenhouse', 70, 80, 80, 3600, 3600),",
        "('E56E12D5-52CA-49A1-A7F6-4392325D960D', NOW(), NOW(), 'Yard', 70, 80, 80, 3600, 3600)"
    ));

    template.execute(String.join(
        " ",
        "INSERT INTO Reading",
        "(reading_id, sensor_id, created_at, temperature, humidity)",
        "VALUES",
        "('2225C958-1931-4A9E-9447-E1229CCADC0F', '75D5F8E6-8A4D-4D66-8B8C-CFC2A7DB2421', NOW(), 70, 80),",
        "('F07A7B05-8D4A-4901-BE64-26DAFAEC0363', 'E56E12D5-52CA-49A1-A7F6-4392325D960D', NOW(), 65, 60),",
        "('27C9EF2E-D08E-4797-8973-58A9AE73A12D', '75D5F8E6-8A4D-4D66-8B8C-CFC2A7DB2421', NOW(), 71, 81),",
        "('613272A0-3A7B-48BD-8332-A06E4197C6C7', 'E56E12D5-52CA-49A1-A7F6-4392325D960D', NOW(), 66, 61),",
        "('E25F3D47-988D-4B12-92D9-EC994F91581E', '75D5F8E6-8A4D-4D66-8B8C-CFC2A7DB2421', NOW(), 72, 82),",
        "('0585A274-16EC-477B-B2A1-75832618B109', 'E56E12D5-52CA-49A1-A7F6-4392325D960D', NOW(), 67, 62)"
    ));
  }
}
