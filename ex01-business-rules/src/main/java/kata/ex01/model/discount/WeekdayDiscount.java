package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeekdayDiscount extends Discount {
    public WeekdayDiscount(HighwayDrive drive) {
        super(drive);
    }

    @Override
    protected int getRate() {
        if (!isDiscount()) {
            return 0;
        }

        if (isMiddleUser()) {
            return 30;
        }

        if (isHeavyUser()) {
            return 50;
        }

        return 0;
    }

    private boolean isDiscount() {
        var routeType = drive.getRouteType();
        if (!routeType.equals(RouteType.RURAL)) {
            return false;
        }

        for (LocalDate date : drive.getDriveDates()) {
            if (isWeekday(date) && isDiscountTime(date, drive)) {
                return true;
            }
        }

        return false;
    }

    private boolean isWeekday(LocalDate date) {
        return !HolidayUtils.isHoliday(date) && !HolidayUtils.isHoliday(date);
    }

    private boolean isDiscountTime(LocalDate date, HighwayDrive drive) {
        var morningFrom = LocalTime.of(6, 0);
        var morningTo = LocalTime.of(9, 0);
        var eveningFrom = LocalTime.of(17, 0);
        var eveningTo = LocalTime.of(20, 0);

        return drive.isDriving(date, morningFrom, morningTo)
                || drive.isDriving(date, eveningFrom, eveningTo);
    }

    private boolean isMiddleUser() {
        var count = drive.getDriver().getCountPerMonth();

        return 5 <= count && count <= 9;
    }

    private boolean isHeavyUser() {
        var count = drive.getDriver().getCountPerMonth();

        return 10 <= count;
    }
}
