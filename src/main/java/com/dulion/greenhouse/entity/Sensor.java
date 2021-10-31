package com.dulion.greenhouse.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Sensor {

  @Id
  private final UUID sensorId;

  private String name;

  private double heatDegrees;

  private double coolDegrees;

  private double dryPercent;

  private int dryOnSpan;

  private int dryOffSpan;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

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

  public double getHeatDegrees() {
    return heatDegrees;
  }

  public void setHeatDegrees(double heatDegrees) {
    this.heatDegrees = heatDegrees;
  }

  public double getCoolDegrees() {
    return coolDegrees;
  }

  public void setCoolDegrees(double coolDegrees) {
    this.coolDegrees = coolDegrees;
  }

  public double getDryPercent() {
    return dryPercent;
  }

  public void setDryPercent(double dryPercent) {
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
