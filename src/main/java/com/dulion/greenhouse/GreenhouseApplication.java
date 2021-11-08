package com.dulion.greenhouse;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreenhouseApplication {

  public static void main(String[] args) {
    Class.class.getModule().addOpens(
        Class.class.getPackageName(),
        MethodAccess.class.getModule());
    SpringApplication.run(GreenhouseApplication.class, args);
  }
}
