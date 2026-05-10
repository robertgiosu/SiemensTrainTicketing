package utils;

import model.Route;
import model.RouteStop;
import model.Station;
import model.Train;
import repository.RouteRepository;
import repository.RouteStopRepository;
import repository.StationRepository;
import repository.TrainRepository;

import java.time.LocalTime;

public class DemoDataLoader {
    private StationRepository stationRepository;
    private TrainRepository trainRepository;
    private RouteRepository routeRepository;
    private RouteStopRepository routeStopRepository;


    public DemoDataLoader(StationRepository stationRepository, TrainRepository trainRepository, RouteRepository routeRepository, RouteStopRepository routeStopRepository) {
        this.stationRepository = stationRepository;
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
        this.routeStopRepository = routeStopRepository;
    }

    public void loadDemoData() {
        stationRepository.save(new Station("Cluj"));
        stationRepository.save(new Station("Alba Iulia"));
        stationRepository.save(new Station("Sibiu"));
        stationRepository.save(new Station("Brasov"));
        stationRepository.save(new Station("Bucuresti"));

        trainRepository.save(new Train("IR1834", 120));
        trainRepository.save(new Train("RE1021", 80));

        routeRepository.save(new Route(1));
        routeRepository.save(new Route(2));

        // Ruta 1: Cluj -> Alba -> Sibiu -> Brasov
        routeStopRepository.save(new RouteStop(1, 1, 1, null, LocalTime.of(8, 0)));
        routeStopRepository.save(new RouteStop(1, 2, 2, LocalTime.of(9, 0), LocalTime.of(9, 10)));
        routeStopRepository.save(new RouteStop(1, 3, 3, LocalTime.of(10, 30), LocalTime.of(10, 40)));
        routeStopRepository.save(new RouteStop(1, 4, 4, LocalTime.of(12, 0), null));

        //Ruta 2: Sibiu -> Bucuresti
        routeStopRepository.save(new RouteStop(2, 3, 1, null, LocalTime.of(13, 0)));
        routeStopRepository.save(new RouteStop(2, 5, 2, LocalTime.of(17, 0), null));

        System.out.println("Demo data loaded successfully!");
    }
}