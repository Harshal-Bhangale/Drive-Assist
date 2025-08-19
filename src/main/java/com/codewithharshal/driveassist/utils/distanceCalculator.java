package com.codewithharshal.driveassist.utils;

// Haversine formula/ helper methods
public class distanceCalculator {

    // Returns distance in kilometers between two lat/lon points
    public static  double distance(double lat1, double lon1, double lat2, double lon2){

        final int R = 6371; // Radius of the Earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}

/*
Usage -
    You can call this method in the service layer to filter services by distance:
    double distance = DistanceCalculator.distance(userLat, userLon, service.getLatitude(), service.getLongitude());
    if (distance <= radiusKm) {
        nearbyServices.add(service);
    }


 */
