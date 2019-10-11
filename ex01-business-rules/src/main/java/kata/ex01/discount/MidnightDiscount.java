package kata.ex01.discount;

import kata.ex01.model.HighwayDrive;

import java.time.LocalTime;

public class MidnightDiscount {
    public static boolean isDiscount(HighwayDrive drive) {
        var from = LocalTime.of(0, 0);
        var to = LocalTime.of(4, 0);

        return drive.isDriving(from, to);
    }

    public static int getRate() {
        return 30;
    }
}
