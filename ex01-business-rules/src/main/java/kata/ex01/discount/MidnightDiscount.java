package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;
import kata.ex01.model.Threshold;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MidnightDiscount {
    private static LocalTime from = LocalTime.of(0,0);
    private static LocalTime to = LocalTime.of(4,0);

    public static boolean isDiscount(HighwayDrive drive) {
        var enteredAt = drive.getEnteredAt().toLocalDate();
        var exitedAt = drive.getExitedAt().toLocalDate();

        var threshold = new Threshold(from, to, enteredAt, exitedAt);

        return drive.isDriving(threshold.getRsToday(), threshold.getReToday()) || drive.isDriving(threshold.getRsTomorrow(), threshold.getReTomorrow());
    }

    public static int getRate() {
        return 30;
    }
}
