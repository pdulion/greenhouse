package com.dulion.greenhouse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Reading {

  @Id
  private final UUID readingId;

  private UUID sensorId;

  private LocalDateTime created_at;

  private int temperature;

  private double humidity;

  public Reading() {
    this.readingId = null;
  }

  @PersistenceConstructor
  public Reading(UUID readingId) {
    this.readingId = readingId;
  }

  public UUID getReadingId() {
    return readingId;
  }

  public UUID getSensorId() {
    return sensorId;
  }

  public void setSensorId(UUID sensorId) {
    this.sensorId = sensorId;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public int getTemperature() {
    return temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

}
