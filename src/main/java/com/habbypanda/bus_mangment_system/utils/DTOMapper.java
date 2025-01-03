package com.habbypanda.bus_mangment_system.utils;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaDTO;
import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.route.RouteDTO;
import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.admin.Admin;
import com.habbypanda.bus_mangment_system.user.admin.AdminDTO;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverDTO;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOMapper {

    // Convert a single Student to StudentDTO
    public static StudentDTO toStudentDTO(Student student) {
        if (student == null) {
            return null;
        }

        return StudentDTO.builder().id(student.getId()).name(student.getName()).email(student.getEmail()).role(student.getRole()).parentId(student.getParent() != null ? student.getParent().getId() : null).areaId(student.getArea() != null ? student.getArea().getId() : null).routeId(student.getRoute() != null ? student.getRoute().getId() : null).stopId(student.getStop() != null ? student.getStop().getId() : null).build();
    }

    // Convert a list of Students to a list of StudentDTOs
    public static List<StudentDTO> toStudentDTOList(List<Student> students) {
        if (students == null) {
            return null;
        }

        return students.stream() // Convert the list to a Stream
                .map(DTOMapper::toStudentDTO) // Map each Student to StudentDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    public static ParentDTO toParentDTO(Parent parent) {
        if (parent == null) {
            return null;
        }

        return ParentDTO.builder().id(parent.getId()).name(parent.getName()).email(parent.getEmail()).role(parent.getRole()).studentId(parent.getStudent() != null ? parent.getStudent().getId() : null).build();
    }

    // Convert a list of Parents to a list of ParentDTOs
    public static List<ParentDTO> toParentDTOList(List<Parent> parents) {
        if (parents == null) {
            return null;
        }

        return parents.stream() // Convert the list to a Stream
                .map(DTOMapper::toParentDTO) // Map each Parent to ParentDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    // Convert Driver to DriverDTO
    public static DriverDTO toDriverDTO(Driver driver) {
        if (driver == null) {
            return null;
        }
        return DriverDTO.builder().id(driver.getId()).name(driver.getName()).email(driver.getEmail()).role(driver.getRole()).driverLicense(driver.getDriverLicense()).build();
    }

    // Convert a list of Drivers to a list of DriverDTOs
    public static List<DriverDTO> toDriverDTOList(List<Driver> drivers) {
        if (drivers == null) {
            return null;
        }
        return drivers.stream() // Convert the list to a Stream
                .map(DTOMapper::toDriverDTO) // Map each Driver to DriverDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    // Convert Admin to AdminDTO
    public static AdminDTO toAdminDTO(Admin admin) {
        if (admin == null) {
            return null;
        }
        return AdminDTO.builder().id(admin.getId()).name(admin.getName()).role(admin.getRole()).email(admin.getEmail()).build();
    }

    // Convert a list of Admins to a list of AdminDTOs
    public static List<AdminDTO> toAdminDTOList(List<Admin> admins) {
        if (admins == null) {
            return null;
        }
        return admins.stream() // Convert the list to a Stream
                .map(DTOMapper::toAdminDTO) // Map each Admin to AdminDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    public static StopDTO toStopDTO(Stop stop) {
        if (stop == null) {
            return null;
        }

        return StopDTO.builder().id(stop.getId()).name(stop.getName()).priority(stop.getPriority()).AreaId(stop.getArea() != null ? stop.getArea().getId() : null).build();
    }

    // Convert a list of Stops to a list of StopDTOs
    public static List<StopDTO> toStopDTOList(List<Stop> stops) {
        if (stops == null) {
            return null;
        }

        return stops.stream() // Convert the list to a Stream
                .map(DTOMapper::toStopDTO) // Map each Stop to StopDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    public static RouteDTO toRouteDTO(Route route) {
        if (route == null) {
            return null;
        }

        return RouteDTO.builder().id(route.getId()).date(route.getDate()).timeSlot(route.getTimeSlot()).type(route.getType()).status(route.getStatus()).areaId(route.getArea() != null ? route.getArea().getId() : null).students(route.getStudents() != null ? route.getStudents().stream().map(DTOMapper::toStudentDTO).collect(Collectors.toList()) : null).stops(route.getStops() != null ? route.getStops().stream().map(DTOMapper::toStopDTO).collect(Collectors.toList()) : null).build();
    }

    // Convert a list of Routes to a list of RouteDTOs
    public static List<RouteDTO> toRouteDTOList(List<Route> routes) {
        if (routes == null) {
            return null;
        }

        return routes.stream() // Convert the list to a Stream
                .map(DTOMapper::toRouteDTO) // Map each Route to RouteDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

    public static AreaDTO toAreaDTO(Area area) {
        if (area == null) {
            return null;
        }

        return AreaDTO.builder().id(area.getId()).name(area.getName()).students(area.getStudents() != null ? area.getStudents().stream().map(DTOMapper::toStudentDTO).collect(Collectors.toList()) : null).routes(area.getRoutes() != null ? area.getRoutes().stream().map(DTOMapper::toRouteDTO).collect(Collectors.toList()) : null).stops(area.getStops() != null ? area.getStops().stream().map(DTOMapper::toStopDTO).collect(Collectors.toList()) : null).build();
    }

    // Convert a list of Areas to a list of AreaDTOs
    public static List<AreaDTO> toAreaDTOList(List<Area> areas) {
        if (areas == null) {
            return null;
        }

        return areas.stream() // Convert the list to a Stream
                .map(DTOMapper::toAreaDTO) // Map each Area to AreaDTO
                .collect(Collectors.toList()); // Collect the results into a List
    }

}