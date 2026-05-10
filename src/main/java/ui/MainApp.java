package ui;

import model.Station;
import repository.*;
import service.RouteService;
import utils.DemoDataLoader;

import java.util.Properties;

public class MainApp {
    public static void main(String[] args) {
        try {
            Properties dbProps = new Properties();
            dbProps.load(MainApp.class.getResourceAsStream("/database.properties"));
            DatabaseManager dbManager = new DatabaseManager(dbProps);
            dbManager.initializeDatabase();
            StationRepository stationRepository = new StationRepository(dbManager);
            TrainRepository trainRepository = new TrainRepository(dbManager);
            RouteRepository routeRepository = new RouteRepository(dbManager);
            RouteStopRepository routeStopRepository = new RouteStopRepository(dbManager);

            RouteService routeService = new RouteService(routeRepository, routeStopRepository, stationRepository);

            System.out.println(routeService.findRoutes("Cluj", "Brasov"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
