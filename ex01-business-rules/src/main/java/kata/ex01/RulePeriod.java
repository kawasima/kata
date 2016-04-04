package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.util.HolidayUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author kawasima
 */
public class RulePeriod {
    private LocalTime startTime;
    private LocalTime endTime;

    public RulePeriod(int startHour, int endHour) {
        startTime = LocalTime.of(startHour, 0);
        endTime   = LocalTime.of(endHour, 0);
    }

    public boolean isIn(HighwayDrive drive) {
        int offset = drive.getEnteredAt().toLocalTime().isBefore(endTime) ? 0 : 1;
        LocalDateTime start = LocalDateTime.of(
                LocalDate.from(drive.getEnteredAt().plusDays(offset)),
                startTime);
        LocalDateTime end = LocalDateTime.of(
                LocalDate.from(drive.getExitedAt().plusDays(offset)),
                endTime);
        return drive.getEnteredAt().isBefore(end) && drive.getExitedAt().isAfter(start);
    }

    public boolean isHoliday(HighwayDrive drive) {
        int offset = drive.getEnteredAt().toLocalTime().isBefore(endTime) ? 0 : 1;
        return HolidayUtils.isHoliday(LocalDate.from(drive.getEnteredAt().plusDays(offset)));
    }
}
