# OptimalRouteFinder

Got it! Here is the updated `README.md` file, which includes instructions for using Gradle to build and run the application.

```markdown
# Delivery Route Optimizer

This project aims to help a delivery executive determine the optimal route to deliver two orders in the shortest possible time. The solution involves calculating travel times between various locations using the Haversine formula and considering meal preparation times at restaurants.

## Table of Contents

- [Introduction](#introduction)
- [Setup](#setup)
- [Usage](#usage)
- [API Endpoint](#api-endpoint)
- [Technologies Used](#technologies-used)
- [Haversine Formula](#haversine-formula)

## Introduction

The application helps a delivery executive named Aman figure out the best way to finish a batch of two orders in the shortest possible time. Given the geo-locations of Aman, two restaurants, and two customers, the application calculates the optimal route.

## Setup

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/your-username/delivery-route-optimizer.git
   cd delivery-route-optimizer
   ```

2. **Build and Run the Application:**

   Ensure you have Java and Gradle installed. Then, build and run the application:

   ```bash
   ./gradlew build
   ./gradlew bootRun
   ```

## Usage

To find the optimal route, you can use the following curl command:

```bash
curl --location 'http://localhost:8080/application/find-route' \
--header 'Content-Type: application/json' \
--data '{
    "user": {
        "lat": 12.15899,
        "lon": 14.599 
    },
    "restaurants": [
        {
            "lat": 13.15899,
            "lon": 15.599 
        },
        {
            "lat": 14.15899,
            "lon": 16.599 
        }
    ],
    "customers": [
        {
            "lat": 15.15899,
            "lon": 17.599 
        },
        {
            "lat": 16.15899,
            "lon": 18.599 
        }
    ]
}'
```

This request will return the optimal route and the total time required to complete the deliveries.

## API Endpoint

### POST /application/find-route

#### Request

- **Headers:**
  - `Content-Type: application/json`

- **Body:**
  ```json
  {
      "user": {
          "lat": 12.15899,
          "lon": 14.599 
      },
      "restaurants": [
          {
              "lat": 13.15899,
              "lon": 15.599 
          },
          {
              "lat": 14.15899,
              "lon": 16.599 
          }
      ],
      "customers": [
          {
              "lat": 15.15899,
              "lon": 17.599 
          },
          {
              "lat": 16.15899,
              "lon": 18.599 
          }
      ]
  }
  ```

#### Response

- **Body:**
  ```json
  {
      "optimalRoute": "A -> R1 -> C1 -> R2 -> C2",
      "totalTime": 2.5
  }
  ```

## Technologies Used

- Java
- Spring Boot
- Gradle

## Haversine Formula

The Haversine formula is used to calculate the great-circle distance between two points on the Earth given their latitude and longitude. It is essential for calculating travel times between the locations in this application.

```java
public static double haversine(double lat1, double lon1, double lat2, double lon2) {
    final double EARTH_RADIUS = 6371; // Radius of the earth in km
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    return EARTH_RADIUS * c;
}
```

By integrating this formula, we can calculate the travel time between any two geo-locations based on an average speed of 20 km/h.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
```

This `README.md` file provides an overview of the project, instructions for setting up and running the application using Gradle, details on the API endpoint, and a brief explanation of the Haversine formula used for distance calculation. Make sure to replace `https://github.com/your-username/delivery-route-optimizer.git` with the actual URL of your repository if you have one.
