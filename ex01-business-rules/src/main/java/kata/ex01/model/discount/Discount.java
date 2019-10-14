package kata.ex01.model.discount;

import kata.ex01.model.HighwayDrive;

public abstract class Discount {
    private Discount next;
    HighwayDrive drive;

    Discount(HighwayDrive drive) {
        this.drive = drive;
    }

    public Discount setNext(Discount next) {
        this.next = next;

        return next;
    }

    public final int calc() {
        int rate = 0;

        if (next != null) {
            rate = next.calc();
        }

        return Math.max(rate, getRate());
    }

    protected abstract int getRate();
}
