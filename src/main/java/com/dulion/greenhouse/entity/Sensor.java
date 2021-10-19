package com.dulion.greenhouse.entity;

import org.springframework.data.annotation.Id;

public class Sensor {

  @Id
  private long sensor;

  private String name;

  private int heatDegrees;

  private int coolDegrees;

  private int dryPercent;

  private int dryOnSpan;

  private int dryOffSpan;

  public long getSensor() {
    return sensor;
  }

  public void setSensor(long sensor) {
    this.sensor = sensor;
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
}
