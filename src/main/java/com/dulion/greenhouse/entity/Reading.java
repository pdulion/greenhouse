package com.dulion.greenhouse.entity;

import org.springframework.data.annotation.Id;

public class Reading {

  @Id
  private long readTime;

  private long sensor;

  private int temperature;

  private double humidity;

  public long getReadTime() {
    return readTime;
  }

  public void setReadTime(long readTime) {
    this.readTime = readTime;
  }

  public long getSensor() {
    return sensor;
  }

  public void setSensor(long sensor) {
    this.sensor = sensor;
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
