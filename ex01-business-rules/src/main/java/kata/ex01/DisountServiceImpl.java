package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.EnumSet;

import static kata.ex01.model.VehicleFamily.*;

/**
 * @author kawasima
 */
public class DisountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        // 入場と出場の時刻が逆転していればエラーとする
        if (drive.getExitedAt().isBefore(drive.getEnteredAt())) {
            throw new IllegalArgumentException("おかしい");
        }
        if (drive.getExitedAt().toEpochSecond(ZoneOffset.UTC) - drive.getEnteredAt().toEpochSecond(ZoneOffset.UTC) > 24 * 60 * 60) {
            throw new IllegalArgumentException("1日以上は運転しすぎです");
        }

        LocalDateTime morningEnd, morningStart;
        if (drive.getEnteredAt().getHour() >= 9) {
            morningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(6, 0));
            morningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt().plusDays(1)),
                    LocalTime.of(9, 0));
        } else {
            morningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(6, 0));
            morningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt()),
                    LocalTime.of(9, 0));
        }

        LocalDateTime eveningStart, eveningEnd;
        if (drive.getEnteredAt().getHour() >= 17) {
            eveningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(17, 0));
            eveningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt().plusDays(1)),
                    LocalTime.of(20, 0));
        } else {
            eveningStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(17, 0));
            eveningEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt()),
                    LocalTime.of(20, 0));
        }

        LocalDateTime midnightStart, midnightEnd;
        if (drive.getEnteredAt().getHour() >= 0) {
            midnightStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt().plusDays(1)),
                    LocalTime.of(0, 0));
            midnightEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt().plusDays(1)),
                    LocalTime.of(4, 0));
        } else {
            midnightStart = LocalDateTime.of(
                    LocalDate.from(drive.getEnteredAt()),
                    LocalTime.of(0, 0));
            midnightEnd = LocalDateTime.of(
                    LocalDate.from(drive.getExitedAt()),
                    LocalTime.of(4, 0));
        }


        // 平日朝夕割引
        if (!HolidayUtils.isHoliday(morningStart.toLocalDate())
                && !HolidayUtils.isHoliday(eveningStart.toLocalDate())
                && ((drive.getEnteredAt().isBefore(morningEnd) && drive.getExitedAt().isAfter(morningStart))
                || (drive.getEnteredAt().isBefore(eveningEnd) && drive.getExitedAt().isAfter(eveningStart)))
                && drive.getRouteType() == RouteType.RURAL) {
            if (drive.getDriver().getCountPerMonth() >= 10) {
                return 50;
            }

            if (drive.getDriver().getCountPerMonth() >= 5) {
                return 30;
            }
        } else {
            // 休日割引
            if ((HolidayUtils.isHoliday(drive.getEnteredAt().toLocalDate())
                    || HolidayUtils.isHoliday(drive.getExitedAt().toLocalDate()))
                    && EnumSet.of(STANDARD, MINI, MOTORCYCLE).contains(drive.getVehicleFamily())
                    && drive.getRouteType() == RouteType.RURAL) {
                return 30;
            } else {
                // 深夜割引
                if (drive.getEnteredAt().isBefore(midnightEnd) && drive.getEnteredAt().isAfter(midnightStart)) {
                    return 30;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
}
