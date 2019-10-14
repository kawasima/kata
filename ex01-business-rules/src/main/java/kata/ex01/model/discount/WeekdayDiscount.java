package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalTime;

public class WeekdayDiscount {
    public static boolean isDiscount(HighwayDrive drive) {
        var enteredAt = drive.getEnteredAt().toLocalDate();
        var exitedAt = drive.getExitedAt().toLocalDate();
        var routeType = drive.getRouteType();

        return !HolidayUtils.isHoliday(enteredAt) && !HolidayUtils.isHoliday(exitedAt)
                && (isMorning(drive) || isEvening(drive))
                && (isMiddleUser(drive) || isHeavyUser(drive))
                && routeType.equals(RouteType.RURAL);
    }

    private static boolean isMorning(HighwayDrive drive) {
        var from = LocalTime.of(6, 0);
        var to = LocalTime.of(9, 0);

        return drive.isDriving(from, to);
    }

    private static boolean isEvening(HighwayDrive drive) {
        var from = LocalTime.of(17, 0);
        var to = LocalTime.of(20, 0);

        return drive.isDriving(from, to);
    }

    private static boolean isMiddleUser(HighwayDrive drive) {
        return 5 <= drive.getDriver().getCountPerMonth()
                && drive.getDriver().getCountPerMonth() <= 9;
    }

    private static boolean isHeavyUser(HighwayDrive drive) {
        return 10 <= drive.getDriver().getCountPerMonth();
    }

    public static int getRate(HighwayDrive drive) {
        if (isMiddleUser(drive)) {
            return 30;
        }

        if (isHeavyUser(drive)) {
            return 50;
        }

        return 0;
    }
}
