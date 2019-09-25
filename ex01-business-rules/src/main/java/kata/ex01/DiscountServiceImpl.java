package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.Period;
import kata.ex01.model.RouteType;
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

        LocalDateTime morningStart, morningEnd;

        if (drive.getEnteredAt().getHour() >= 9) {
            morningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(6, 0));
            morningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(9, 0));
        } else {
            morningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(6, 0));
            morningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(9, 0));
        }

        LocalDateTime eveningStart, eveningEnd;
        if (drive.getEnteredAt().getHour() >= 17) {
            eveningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(17, 0));
            eveningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(20, 0));
        } else {
            eveningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(17, 0));
            eveningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(20, 0));
        }


        var morning = new Period(morningStart, morningEnd);
        var evening = new Period(eveningStart, eveningEnd);

        var enteredAt = drive.getEnteredAt();
        var exitedAt = drive.getExitedAt();
        if (!HolidayUtils.isHoliday(enteredAt.toLocalDate()) &&
                !HolidayUtils.isHoliday(exitedAt.toLocalDate()) &&
                (morning.over(enteredAt, exitedAt) || evening.over(enteredAt, exitedAt)) &&
                drive.getRouteType() == RouteType.RURAL) {
            if (drive.getDriver().getCountPerMonth() >= 10) {
                return 50;
            } else {
                return 30;
            }

        }

        return 30;
    }
}