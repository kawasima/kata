package kata.ex01;

import kata.ex01.model.discount.HolidayDiscount;
import kata.ex01.model.discount.MidnightDiscount;
import kata.ex01.model.discount.WeekdayDiscount;
import kata.ex01.model.HighwayDrive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    @Override
    public long calc(HighwayDrive drive) {
        List<Integer> discountRates = new ArrayList<>();

        /**
         * 平日朝夕割引
         */
        if (WeekdayDiscount.isDiscount(drive)) {
            discountRates.add(WeekdayDiscount.getRate(drive));
        }

        /**
         * 休日割引
         */
        if (HolidayDiscount.isDiscount(drive)) {
            discountRates.add(HolidayDiscount.getRate());
        }

        /**
         * 深夜割引
         */
        if (MidnightDiscount.isDiscount(drive)) {
            discountRates.add(MidnightDiscount.getRate());
        }

        return discountRates.stream()
                .max(Comparator.naturalOrder())
                .orElse(0);
    }
}
