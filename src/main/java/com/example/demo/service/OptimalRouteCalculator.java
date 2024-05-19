package com.example.demo.service;

import com.example.demo.apimodels.RouteCalculatorRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OptimalRouteCalculator {

    @Autowired
    private DeliveryRouteOptimizer deliveryRouteOptimizer;

    public String calculateRoute(RouteCalculatorRequest request) {

        // Coordinates for Aman location
        double[] A = {request.getUser().getLat(), request.getUser().getLon()};

        // Coordinates for Restaurant location
        double[] R1 = {request.getRestaurants().get(0).getLon(), request.getRestaurants().get(0).getLon()};
        double[] R2 = {request.getRestaurants().get(1).getLon(), request.getRestaurants().get(1).getLon()};

        // Coordinates for Customer location
        double[] C1 = {request.getCustomers().get(0).getLon(), request.getCustomers().get(0).getLon()};
        double[] C2 = {request.getCustomers().get(1).getLon(), request.getCustomers().get(1).getLon()};

        // Assuming Average Preparation times at restaurants in hours
        double pt1 = 0.25;
        double pt2 = 0.25;

        // Calculate travel times between all locations
        Map<String, Double> times = new HashMap<>();
        times.put("A->R1", deliveryRouteOptimizer.travelTime(A[0], A[1], R1[0], R1[1]));
        times.put("A->R2", deliveryRouteOptimizer.travelTime(A[0], A[1], R2[0], R2[1]));

        // Possible routes from R1
        times.put("R1->C1", deliveryRouteOptimizer.travelTime(R1[0], R1[1], C1[0], C1[1]));
        times.put("R1->C2", deliveryRouteOptimizer.travelTime(R1[0], R1[1], C2[0], C2[1]));
        times.put("R1->R2", deliveryRouteOptimizer.travelTime(R1[0], R1[1], R2[0], R2[1]));

        // Possible routes from R2
        times.put("R2->C2", deliveryRouteOptimizer.travelTime(R2[0], R2[1], C2[0], C2[1]));
        times.put("R2->C1", deliveryRouteOptimizer.travelTime(R2[0], R2[1], C1[0], C1[1]));
        times.put("R2->R1", deliveryRouteOptimizer.travelTime(R2[0], R2[1], R1[0], R1[1]));

        // Possible routes from C1
        times.put("C1->C2", deliveryRouteOptimizer.travelTime(C1[0], C1[1], C2[0], C2[1]));
        times.put("C1->R2", deliveryRouteOptimizer.travelTime(C1[0], C1[1], R2[0], R2[1]));

        // Possible routes from C2
        times.put("C2->C1", deliveryRouteOptimizer.travelTime(C2[0], C2[1], C1[0], C1[1]));
        times.put("C2->R1", deliveryRouteOptimizer.travelTime(C2[0], C2[1], R1[0], R2[1]));

        // Possible routes and their total time calculations
        Map<String, Double> routes = new HashMap<>();
        routes.put("A -> R1 -> C1 -> R2 -> C2", times.get("A->R1") + pt1 + times.get("R1->C1") + times.get("C1->R2") + pt2 + times.get("R2->C2"));
        routes.put("A -> R1 -> R2 -> C1 -> C2", times.get("A->R1") + pt1 + times.get("R1->R2") + pt2 + times.get("R2->C1") + times.get("C1->C2"));
        routes.put("A -> R1 -> R2 -> C2 -> C1", times.get("A->R1") + pt1 + times.get("R1->R2") + pt2 + times.get("R2->C2") + times.get("C2->C1"));

        routes.put("A -> R2 -> C2 -> R1 -> C1", times.get("A->R2") + pt2 + times.get("R2->C2") + times.get("C2->R1") + pt1 + times.get("R1->C1"));
        routes.put("A -> R2 -> R1 -> C2 -> C1", times.get("A->R2") + pt2 + times.get("R2->R1") + pt1 + times.get("R1->C2") + times.get("C2->C1"));
        routes.put("A -> R2 -> R1 -> C1 -> C2", times.get("A->R2") + pt2 + times.get("R2->R1") + pt1 + times.get("R1->C1") + times.get("C1->C2"));

        // Find the route with the minimum time
        String optimalRoute = null;
        double optimalTime = Double.MAX_VALUE;
        for (Map.Entry<String, Double> entry : routes.entrySet()) {
            if (entry.getValue() < optimalTime) {
                optimalTime = entry.getValue();
                optimalRoute = entry.getKey();
            }
        }
        log.info("Optimal route: {}", optimalRoute);
        log.info("Total time: {} hours", optimalTime);
        return optimalRoute;
    }
}
