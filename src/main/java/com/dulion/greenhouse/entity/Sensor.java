package com.dulion.greenhouse.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Sensor {

  @Id
  private final UUID sensorId;

  private String name;

  private int heatDegrees;

  private int coolDegrees;

  private int dryPercent;

  private int dryOnSpan;

  private int dryOffSpan;

  private LocalDateTime created_at;

  private LocalDateTime updated_at;

  public Sensor() {
    this.sensorId = null;
  }

  @PersistenceConstructor
  public Sensor(UUID sensorId) {
    this.sensorId = sensorId;
  }

  public UUID getSensorId() {
    return sensorId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHeatDegrees() {
    return heatDegrees;
  }

  public void setHeatDegrees(int heatDegrees) {
    this.heatDegrees = heatDegrees;
  }

  public int getCoolDegrees() {
    return coolDegrees;
  }

  public void setCoolDegrees(int coolDegrees) {
    this.coolDegrees = coolDegrees;
  }

  public int getDryPercent() {
    return dryPercent;
  }

  public void setDryPercent(int dryPercent) {
    this.dryPercent = dryPercent;
  }

  public int getDryOnSpan() {
    return dryOnSpan;
  }

  public void setDryOnSpan(int dryOnSpan) {
    this.dryOnSpan = dryOnSpan;
  }

  public int getDryOffSpan() {
    return dryOffSpan;
  }

  public void setDryOffSpan(int dryOffSpan) {
    this.dryOffSpan = dryOffSpan;
  }

  public LocalDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }

  public LocalDateTime getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }
}
