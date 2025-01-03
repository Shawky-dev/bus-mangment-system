package com.habbypanda.bus_mangment_system.bus;

import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.route.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository; // Add this field

    @Autowired
    public BusService(BusRepository busRepository, RouteRepository routeRepository, DriverRepository driverRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository; // Inject it here
    }

    public BusResponse createBus(BusCreateRequest request) {
        Optional<Route> route = routeRepository.findById(request.getRouteId());
        if (route.isEmpty()) {
            return BusResponse.builder()
                    .message("Route not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Optional<Driver> driverOptional = driverRepository.findById(request.getDriverId());
        if (driverOptional.isEmpty()) {
            throw new RuntimeException("Driver not found");
        }
        Driver driver = driverOptional.get(); // Initialize the driver

        Bus bus = Bus.builder()
                .plateNumber(request.getPlateNumber())
                .capacity(request.getCapacity())
                .route(route.get()) // Ensure the route is fetched correctly
                .driver(driver) // Set the Driver object
                .build();


        busRepository.save(bus);

        return BusResponse.builder()
                .message("Bus created successfully")
                .status(HttpStatus.CREATED)
                .bus(bus)
                .build();
    }

    public BusResponse getBusById(Integer id) {
        Optional<Bus> bus = busRepository.findById(id);
        if (bus.isEmpty()) {
            return BusResponse.builder()
                    .message("Bus not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return BusResponse.builder()
                .message("Bus found")
                .status(HttpStatus.OK)
                .bus(bus.get())
                .build();
    }

    public List<BusResponse> getAllBuses() {
        return busRepository.findAll().stream()
                .map(bus -> BusResponse.builder()
                        .message("Bus retrieved")
                        .status(HttpStatus.OK)
                        .bus(bus)
                        .build())
                .collect(Collectors.toList());
    }

    public BusResponse deleteBus(Integer id) {
        Optional<Bus> bus = busRepository.findById(id);
        if (bus.isEmpty()) {
            return BusResponse.builder()
                    .message("Bus not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        busRepository.delete(bus.get());

        return BusResponse.builder()
                .message("Bus deleted successfully")
                .status(HttpStatus.OK)
                .build();
    }
    public BusResponse reassignDriver(Integer busId, Integer newDriverId) {
        Optional<Bus> bus = busRepository.findById(busId);
        if (bus.isEmpty()) {
            return BusResponse.builder()
                    .message("Bus not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Optional<Driver> driver = driverRepository.findById(newDriverId);
        if (driver.isEmpty()) {
            return BusResponse.builder()
                    .message("Driver not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Bus existingBus = bus.get();
        existingBus.setDriver(driver.get());
        busRepository.save(existingBus);

        return BusResponse.builder()
                .message("Driver reassigned successfully")
                .status(HttpStatus.OK)
                .bus(existingBus)
                .build();
    }

}
