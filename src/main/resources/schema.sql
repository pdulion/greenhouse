CREATE TABLE IF NOT EXISTS sensor (
  sensor_id UUID PRIMARY KEY,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  name VARCHAR(50) NOT NULL,
  heat_degrees DOUBLE,
  cool_degrees DOUBLE,
  dry_percent DOUBLE,
  dry_on_span INTEGER,
  dry_off_span INTEGER);

CREATE TABLE IF NOT EXISTS reading (
  reading_id UUID PRIMARY KEY,
  sensor_id UUID NOT NULL,
  created_at TIMESTAMP NOT NULL,
  temperature INTEGER,
  humidity INTEGER);

CREATE INDEX IF NOT EXISTS sensor_readings
  ON reading (sensor_id, created_at);
