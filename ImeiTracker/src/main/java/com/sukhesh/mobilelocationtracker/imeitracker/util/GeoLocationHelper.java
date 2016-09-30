package com.sukhesh.mobilelocationtracker.imeitracker.util;

import com.sukhesh.mobilelocationtracker.imeitracker.model.Event;
import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;

/**
 * Created by sukhesh on 30/09/16.
 */
public class GeoLocationHelper {
    public static boolean isWithinRange(Event event, ImeiLocation location, int range) {
        double distance = distance(event.getLangitude(), event.getLatitude(), location.getLangitude(), location.getLatitude());

        if(distance <= range) {
            return true;
        }

        return false;
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        // in kilometers
        dist = dist * 1.609344;

        // in meters
        dist = dist * 1000;

        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
