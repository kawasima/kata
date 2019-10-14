package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;

public class HolidayDiscount extends Discount {
    public HolidayDiscount(HighwayDrive drive) {
        super(drive);
    }

    @Override
    protected int getRate() {
        if (!isDiscount()) {
            return 0;
        }

        return 30;
    }

    private boolean isDiscount() {
        var vehicleFamily = drive.getVehicleFamily();
        var routeType = drive.getRouteType();

        for (LocalDate date : drive.getDriveDates()) {
            if (vehicleFamily.isHolidayDiscountVehicle()
                    && HolidayUtils.isHoliday(date) && routeType.equals(RouteType.RURAL)) {
                return true;
            }
        }

        return false;
    }
}
