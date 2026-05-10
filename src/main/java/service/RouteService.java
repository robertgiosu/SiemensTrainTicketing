package service;

import model.*;
import repository.RouteRepository;
import repository.RouteStopRepository;
import repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteStopRepository routeStopRepository;
    private final StationRepository stationRepository;

    public RouteService(RouteRepository routeRepository, RouteStopRepository routeStopRepository, StationRepository stationRepository) {
        this.routeRepository = routeRepository;
        this.routeStopRepository = routeStopRepository;
        this.stationRepository = stationRepository;
    }

    public List<RouteResult> findRoutes(String fromStation, String toStation) {
        List<RouteResult> results = new ArrayList<>();
        List<Route> routes = routeRepository.findAll();

        // direct routes
        for (Route route : routes) {
            List<RouteStop> stops = routeStopRepository.findByRouteId(route.getId());
            RouteStop fromStop = null;
            RouteStop toStop = null;

            for (RouteStop stop : stops) {
                Station station = stationRepository.findById(stop.getStationId());
                if (station.getName().equalsIgnoreCase(fromStation)) {
                    fromStop = stop;
                }
                if (station.getName().equalsIgnoreCase(toStation)) {
                    toStop = stop;
                }
            }

            if (fromStop != null && toStop != null && fromStop.getStopOrder() < toStop.getStopOrder()) {
                String message =
                        "DIRECT ROUTE FOUND | Route ID: "
                                + route.getId()
                                + " | Departure: "
                                + fromStop.getDepartureTime()
                                + " | Arrival: "
                                + toStop.getArrivalTime();
                results.add(new RouteResult(message));
            }
        }

        // one transfer routes
        for (Route firstRoute : routes) {
            List<RouteStop> firstStops = routeStopRepository.findByRouteId(firstRoute.getId());

            for (Route secondRoute : routes) {
                if (firstRoute.getId().equals(secondRoute.getId())) {
                    continue;
                }

                List<RouteStop> secondStops = routeStopRepository.findByRouteId(secondRoute.getId());

                for (RouteStop firstStop : firstStops) {
                    Station transferStation = stationRepository.findById(firstStop.getStationId());
                    boolean firstHasFrom = false;
                    boolean secondHasTo = false;

                    for (RouteStop fs : firstStops) {
                        Station s = stationRepository.findById(fs.getStationId());
                        if (s.getName().equalsIgnoreCase(fromStation) && fs.getStopOrder() < firstStop.getStopOrder()) {
                            firstHasFrom = true;
                        }
                    }

                    for (RouteStop ss : secondStops) {
                        Station s = stationRepository.findById(ss.getStationId());
                        if (s.getName().equalsIgnoreCase(toStation) && ss.getStopOrder() > 1) {
                            secondHasTo = true;
                        }

                        if (s.getName().equalsIgnoreCase(transferStation.getName())) {
                            if (secondHasTo && firstHasFrom) {
                                String message =
                                        "TRANSFER ROUTE FOUND | "
                                                + "Transfer at: "
                                                + transferStation.getName()
                                                + " | First Route ID: "
                                                + firstRoute.getId()
                                                + " | Second Route ID: "
                                                + secondRoute.getId();
                                results.add(new RouteResult(message));
                            }
                        }
                    }
                }
            }
        }

        return results;
    }
}