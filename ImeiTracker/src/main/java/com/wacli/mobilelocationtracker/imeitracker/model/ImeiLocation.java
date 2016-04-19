package com.wacli.mobilelocationtracker.imeitracker.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sukhesh on 16/04/16.
 */
@Entity
@Table(name = "imeilocation")
public class ImeiLocation {

    @Version
    private Long version;

    @Id
    @Column(name = "imei", nullable = false, unique = true)
    private String imei;

    @Basic
    @Column(name = "langitude", nullable = false, unique = false)
    private double langitude;

    @Basic
    @Column(name = "latitude", nullable = false, unique = false)
    private double latitude;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="seenTime",nullable = false)
    private Date seenTime;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(Date seenTime) {
        this.seenTime = seenTime;
    }
}
