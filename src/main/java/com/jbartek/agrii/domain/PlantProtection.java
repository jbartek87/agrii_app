package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlantProtection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "TEMPERATURE")
    private double temperature;

    @Column(name = "WIND_SPEED")
    private double windSpeed;

    @Column(name = "RAIN")
    private boolean rain;

    @Column(name = "WEATHER_STATUS")
    private boolean isWeatherOk;


}
