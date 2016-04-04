package kata.ex01.rule;

import kata.ex01.DiscountRule;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.util.EnumSet;

import static kata.ex01.model.RouteType.RURAL;
import static kata.ex01.model.VehicleFamily.*;

/**
 * @author kawasima
 */
public class DiscountOnHoliday implements DiscountRule {
    private static EnumSet<VehicleFamily> targetVehicleFamilies =
            EnumSet.of(STANDARD, MOTORCYCLE, MINI);

    @Override
    public boolean isApplicable(HighwayDrive drive) {
        return (HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate())
                  || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
                && targetVehicleFamilies.contains(drive.getVehicleFamily())
                && drive.getRouteType() == RURAL;
    }

    @Override
    public long discountPercentage(HighwayDrive drive) {
        return 30;
    }
}
