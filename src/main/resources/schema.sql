CREATE TABLE IF NOT EXISTS Sensor (
  sensor BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL,
  heat_degrees INTEGER,
  cool_degrees INTEGER,
  dry_percent INTEGER,
  dry_on_span INTEGER,
  dry_off_span INTEGER,
  PRIMARY KEY (sensor));
CREATE TABLE IF NOT EXISTS Reading (
  sensor BIGINT NOT NULL,
  read_time BIGINT NOT NULL,
  temperature INTEGER,
  humidity INTEGER,
  PRIMARY KEY (sensor, read_time));
