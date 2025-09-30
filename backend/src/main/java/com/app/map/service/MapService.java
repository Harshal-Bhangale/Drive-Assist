package com.app.map.service;

import com.app.api.ExternalAPIClient;
import com.app.fuelstation.model.FuelStation;
import com.app.fuelstation.repository.FuelStationRepository;
import com.app.garage.model.Garage;
import com.app.garage.repository.GarageRepository;
import com.app.map.dto.MapDTO;
import com.app.map.dto.MarkerDTO;
import com.app.map.dto.RouteDTO;
import com.app.rental.model.CarRental;
import com.app.rental.repository.CarRentalRepository;
import com.app.util.GeoUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MapService {
    private final FuelStationRepository fuelStationRepository;
    private final GarageRepository garageRepository;
    private final CarRentalRepository carRentalRepository;
    private final ExternalAPIClient externalAPIClient;

    public MapService(FuelStationRepository fuelStationRepository,
                      GarageRepository garageRepository,
                      CarRentalRepository carRentalRepository,
                      ExternalAPIClient externalAPIClient) {
        this.fuelStationRepository = fuelStationRepository;
        this.garageRepository = garageRepository;
        this.carRentalRepository = carRentalRepository;
        this.externalAPIClient = externalAPIClient;
    }

    public MapDTO nearby(double lat, double lon, double radiusKm) {
        List<MarkerDTO> markers = new ArrayList<>();

        for (FuelStation fs : fuelStationRepository.findAll()) {
            double d = GeoUtils.calculateDistanceKm(lat, lon, fs.getLatitude(), fs.getLongitude());
            if (d <= radiusKm) {
                MarkerDTO m = new MarkerDTO();
                m.setType("Fuel");
                m.setId(fs.getId());
                m.setName(fs.getName());
                m.setLat(fs.getLatitude());
                m.setLon(fs.getLongitude());
                m.setRating(fs.getRating());
                m.setDistanceKm(d);
                markers.add(m);
            }
        }
        for (Garage g : garageRepository.findAll()) {
            double d = GeoUtils.calculateDistanceKm(lat, lon, g.getLatitude(), g.getLongitude());
            if (d <= radiusKm) {
                MarkerDTO m = new MarkerDTO();
                m.setType("Garage");
                m.setId(g.getId());
                m.setName(g.getName());
                m.setLat(g.getLatitude());
                m.setLon(g.getLongitude());
                m.setRating(g.getRating());
                m.setDistanceKm(d);
                markers.add(m);
            }
        }
        for (CarRental c : carRentalRepository.findByAvailabilityTrue()) {
            double d = GeoUtils.calculateDistanceKm(lat, lon, c.getPickupLat(), c.getPickupLon());
            if (d <= radiusKm) {
                MarkerDTO m = new MarkerDTO();
                m.setType("Rental");
                m.setId(c.getId());
                m.setName(c.getModel());
                m.setLat(c.getPickupLat());
                m.setLon(c.getPickupLon());
                m.setRating(c.getRating());
                m.setDistanceKm(d);
                markers.add(m);
            }
        }

        markers.sort(Comparator.comparingDouble(MarkerDTO::getDistanceKm));

        MapDTO map = new MapDTO();
        map.setUserLat(lat);
        map.setUserLon(lon);
        map.setMarkers(markers);
        return map;
    }

    public RouteDTO route(double startLat, double startLon, double endLat, double endLon) {
        ExternalAPIClient.DirectionsDTO d = externalAPIClient.getDirections(startLat, startLon, endLat, endLon);
        RouteDTO route = new RouteDTO();
        route.setDistanceKm(d.distanceKm());
        route.setDurationMinutes(d.durationMinutes());
        route.setPolyline(d.polyline());
        route.setInstructions(d.instructions());
        return route;
    }
}



