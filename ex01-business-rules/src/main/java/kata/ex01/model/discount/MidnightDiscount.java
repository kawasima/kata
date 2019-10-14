package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;

import java.time.LocalDate;
import java.time.LocalTime;

public class MidnightDiscount extends Discount {
    public MidnightDiscount(HighwayDrive drive) {
        super(drive);
    }

    @Override
    protected int getRate() {
        if (!isDiscount()) {
            return 0;
        }

        return 30;
    }

    private boolean isDiscount() {
        var from = LocalTime.of(0, 0);
        var to = LocalTime.of(4, 0);

        for (LocalDate date : drive.getDriveDates()) {
            if (drive.isDriving(date, from, to)) {
                return true;
            }
        }

        return false;
    }
}
