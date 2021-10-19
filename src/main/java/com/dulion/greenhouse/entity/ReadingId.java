package com.dulion.greenhouse.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ReadingId implements Serializable {

  private UUID sensor;

  private long readTime;

  public ReadingId(long readTime, UUID sensor) {
    this.readTime = readTime;
    this.sensor = sensor;
  }

  public UUID getSensor() {
    return sensor;
  }

  public long getReadTime() {
    return readTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReadingId readingId = (ReadingId) o;
    return readTime == readingId.readTime && sensor.equals(readingId.sensor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sensor, readTime);
  }
}
