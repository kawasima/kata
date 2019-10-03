package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.model.VehicleFamily;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        /**
         * 平日朝夕割引
         */
        if (
                !HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate())
                        && !HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate())
                        && (
                                (
                                (drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(9, 0)))
                                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(6, 0))))
                                ||
                                (drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(9, 0)))
                                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(6, 0))))
                                )
                            ||
                                (
                                (drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(20, 0)))
                                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(17, 0))))
                                ||
                                (drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(20, 0)))
                                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(17, 0))))
                                )
                        )
                        && drive.getRouteType().equals(RouteType.RURAL)
        ) {
            if (5 <= drive.getDriver().getCountPerMonth() && drive.getDriver().getCountPerMonth() <= 9) {
                return 30;
            }

            if (10 <= drive.getDriver().getCountPerMonth()) {
                return 50;
            }
        }

        /**
         * 休日割引
         */
        if (drive.getVehicleFamily().isHolidayDiscountVehicle()
                && (HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate()) || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
                && drive.getRouteType().equals(RouteType.RURAL)) {
            return 30;
        }

        /**
         * 深夜割引
         */
        if ((drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(4, 0)))
                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getEnteredAt().toLocalDate(), LocalTime.of(0, 0))))
                || (drive.getEnteredAt().isBefore(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(4, 0)))
                && drive.getExitedAt().isAfter(LocalDateTime.of(drive.getExitedAt().toLocalDate(), LocalTime.of(0, 0))))
        ) {
            return 30;
        }

        return 0;
    }
}
