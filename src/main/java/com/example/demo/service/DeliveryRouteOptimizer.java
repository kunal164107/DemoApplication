package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeliveryRouteOptimizer {

    private static final double EARTH_RADIUS = 6371;
    private static final double AVERAGE_SPEED_KMH = 20;

    // Haversine formula to calculate the distance between two points given their lat/lon
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    // Calculate travel time between two points
    public double travelTime(double lat1, double lon1, double lat2, double lon2) {
        double distance = haversine(lat1, lon1, lat2, lon2);
        return distance / AVERAGE_SPEED_KMH;
    }

}
