package com.app.map.dto;

import java.util.List;

public class MapDTO {
    private double userLat;
    private double userLon;
    private List<MarkerDTO> markers;
    private RouteDTO route;

    public double getUserLat() { return userLat; }
    public void setUserLat(double userLat) { this.userLat = userLat; }
    public double getUserLon() { return userLon; }
    public void setUserLon(double userLon) { this.userLon = userLon; }
    public List<MarkerDTO> getMarkers() { return markers; }
    public void setMarkers(List<MarkerDTO> markers) { this.markers = markers; }
    public RouteDTO getRoute() { return route; }
    public void setRoute(RouteDTO route) { this.route = route; }
}



