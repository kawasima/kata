package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

public class HolidayDiscount {

    public static boolean isDiscount(HighwayDrive drive) {
        var vehicleFamily = drive.getVehicleFamily();
        var routeType = drive.getRouteType();
        var enteredAt = drive.getEnteredAt().toLocalDate();
        var exitedAt = drive.getExitedAt().toLocalDate();

        return vehicleFamily.isHolidayDiscountVehicle()
                && (HolidayUtils.isHoliday(enteredAt) || HolidayUtils.isHoliday(exitedAt))
                && routeType.equals(RouteType.RURAL);
    }

    public static int getRate() {
        return 30;
    }
}
