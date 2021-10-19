package com.dulion.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableLoader {

  @Autowired
  private JdbcTemplate template;

  public void insertData() {
    template.execute(String.join(
        " ",
        "INSERT INTO Sensor",
        "(sensor, name, heat_degrees, cool_degrees, dry_percent, dry_on_span, dry_off_span)",
        "VALUES",
        "(0, 'Greenhouse', 70, 80, 80, 3600, 3600),",
        "(1, 'Yard', 70, 80, 80, 3600, 3600)"
    ));
    template.execute(String.join(
        " ",
        "INSERT INTO Reading",
        "(sensor, read_time, temperature, humidity)",
        "VALUES",
        "(0, 1634400969759, 70, 80),",
        "(1, 1634400976411, 65, 60),",
        "(0, 1634400983709, 71, 81),",
        "(1, 1634400990719, 66, 61),",
        "(0, 1634400997263, 72, 82),",
        "(1, 1634401038087, 67, 62)"
    ));
  }
}
