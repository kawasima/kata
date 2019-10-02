package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        /**
         * 休日料金
         */
        if (drive.getVehicleFamily().isHolidayDiscountVehicle()
        && (HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate()) || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
        && drive.getRouteType().equals(RouteType.RURAL)) {
            return 30;
        }

        /**
         * 深夜料金
         */
//        if(drive.getEnteredAt().getHour() <= 4 || drive.getExitedAt().getHour() <= 4 || () ) {
//               return 30;
//        }
//            if (drive.getEnteredAt().getHour() >= 4){
//
//            }

        return 0;
    }
}
