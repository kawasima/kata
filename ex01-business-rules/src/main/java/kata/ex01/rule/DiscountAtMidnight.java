package kata.ex01.rule;

import kata.ex01.DiscountRule;
import kata.ex01.RulePeriod;
import kata.ex01.model.HighwayDrive;

/**
 * @author kawasima
 */
public class DiscountAtMidnight implements DiscountRule {
    private RulePeriod midnight = new RulePeriod(0, 4);
    @Override
    public boolean isApplicable(HighwayDrive drive) {
        return midnight.isIn(drive);
    }

    @Override
    public long discountPercentage(HighwayDrive drive) {
        return 30;
    }
}
