package kata.ex01.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author kawasima
 */
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;

    private Driver driver;

    public HighwayDrive() {
    }

    public boolean isDriving(LocalTime from, LocalTime to)
    {
        var enteredAt = this.getEnteredAt().toLocalDate();
        var exitedAt = this.getExitedAt().toLocalDate();
        var threshold = new Threshold(from, to, enteredAt, exitedAt);

        return ((this.getEnteredAt().isBefore(threshold.getReToday()) || this.getEnteredAt().isEqual(threshold.getReToday()))
                && (this.getExitedAt().isAfter(threshold.getRsToday()) || this.getExitedAt().isEqual(threshold.getRsToday())))
                || ((this.getEnteredAt().isBefore(threshold.getReTomorrow()) || this.getEnteredAt().isEqual(threshold.getReTomorrow()))
                && (this.getExitedAt().isAfter(threshold.getRsTomorrow()) || this.getExitedAt().isEqual(threshold.getRsTomorrow())));
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
