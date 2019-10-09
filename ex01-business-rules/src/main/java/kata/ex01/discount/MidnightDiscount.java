package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MidnightDiscount {
    private static LocalTime from = LocalTime.of(0,0);
    private static LocalTime to = LocalTime.of(4,0);

    public static boolean isDiscount(HighwayDrive drive) {
        var enteredAt = drive.getEnteredAt().toLocalDate();
        var exitedAt = drive.getExitedAt().toLocalDate();
        var rsToday = LocalDateTime.of(enteredAt, from);
        var reToday = LocalDateTime.of(enteredAt, to);
        var rsTomorrow = LocalDateTime.of(exitedAt, from);
        var reTomorrow = LocalDateTime.of(exitedAt, to);

        return drive.isDriving(rsToday, reToday) || drive.isDriving(rsTomorrow, reTomorrow);
    }

    public static int getRate() {
        return 30;
    }
}
