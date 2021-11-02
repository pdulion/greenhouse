package com.dulion.greenhouse.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Reading {

  @Id
  private final UUID readingId;

  private UUID sensorId;

  private LocalDateTime createdAt;

  private double temperature;

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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

}
