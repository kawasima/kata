package kata.ex01.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kawasima
 */
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;

    private Driver driver;

    public boolean isDriving(LocalTime from, LocalTime to) {
        for (LocalDate date : getDrivingDateRange()) {
            var rs = date.atTime(from);
            var re = date.atTime(to);

            if (!(getEnteredAt().isAfter(re) && getExitedAt().isBefore(rs))) {
                return true;
            }
        }

        return false;
    }

    private List<LocalDate> getDrivingDateRange() {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(getEnteredAt().toLocalDate());
        dates.add(getExitedAt().toLocalDate());

        return dates;
    }

    public LocalDateTime getEnteredAt() {
        return this.enteredAt;
    }

    public LocalDateTime getExitedAt() {
        return this.exitedAt;
    }

    public VehicleFamily getVehicleFamily() {
        return this.vehicleFamily;
    }

    public RouteType getRouteType() {
        return this.routeType;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setEnteredAt(LocalDateTime enteredAt) {
        this.enteredAt = enteredAt;
    }

    public void setExitedAt(LocalDateTime exitedAt) {
        this.exitedAt = exitedAt;
    }

    public void setVehicleFamily(VehicleFamily vehicleFamily) {
        this.vehicleFamily = vehicleFamily;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String toString() {
        return "HighwayDrive(enteredAt=" + this.getEnteredAt() + ", exitedAt=" + this.getExitedAt() + ", vehicleFamily=" + this.getVehicleFamily() + ", routeType=" + this.getRouteType() + ", driver=" + this.getDriver() + ")";
    }
}
