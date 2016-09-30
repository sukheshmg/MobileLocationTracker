package com.sukhesh.mobilelocationtracker.imeitracker.model;

import javax.persistence.*;

/**
 * Created by sukhesh on 30/09/16.
 */
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "langitude", nullable = false, unique = false)
    private double langitude;

    @Basic
    @Column(name = "latitude", nullable = false, unique = false)
    private double latitude;

    @Basic
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Basic
    @Column(name = "range", nullable = false, unique = false)
    private int range;

    @Basic
    @Column(name = "message", nullable = false, unique = false)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLangitude() {
        return langitude;
    }

    public void setLangitude(double langitude) {
        this.langitude = langitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
