package kata.ex01;

import kata.ex01.model.HighwayDrive;
import kata.ex01.rule.DiscountAtMidnight;
import kata.ex01.rule.DiscountInMorningOrEvening;
import kata.ex01.rule.DiscountOnHoliday;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author kawasima
 */
public class DiscountServiceImpl implements DiscountService {
    List<DiscountRule> discountRules;

    public DiscountServiceImpl() {
        discountRules = Arrays.asList(
                new DiscountInMorningOrEvening(),
                new DiscountOnHoliday(),
                new DiscountAtMidnight()
        );
    }

    @Override
    public long calc(HighwayDrive drive) {
        return discountRules.stream()
                .filter(rule -> rule.isApplicable(drive))
                .map(rule -> rule.discountPercentage(drive))
                .max(Comparator.naturalOrder())
                .orElse(0l);
    }

}
