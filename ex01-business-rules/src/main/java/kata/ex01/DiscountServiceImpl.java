package kata.ex01;

import kata.ex01.model.discount.HolidayDiscount;
import kata.ex01.model.discount.MidnightDiscount;
import kata.ex01.model.discount.WeekdayDiscount;
import kata.ex01.model.HighwayDrive;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        var weekdayDiscount = new WeekdayDiscount(drive);
        var holidayDiscount = new HolidayDiscount(drive);
        var midnightDiscount = new MidnightDiscount(drive);

        weekdayDiscount.setNext(holidayDiscount)
                .setNext(midnightDiscount);

        return weekdayDiscount.calc();
    }
}
