package kata.ex01.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
@Data
public class HighwayDrive implements Serializable {
    private LocalDateTime enteredAt;
    private LocalDateTime exitedAt;
    private VehicleFamily vehicleFamily;
    private RouteType routeType;

    private Driver driver;
}
