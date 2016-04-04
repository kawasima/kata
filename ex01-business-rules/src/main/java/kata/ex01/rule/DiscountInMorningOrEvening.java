package kata.ex01.rule;

import kata.ex01.DiscountRule;
import kata.ex01.RulePeriod;
import kata.ex01.model.HighwayDrive;
import kata.ex01.model.RouteType;

/**
 * @author kawasima
 */
public class DiscountInMorningOrEvening implements DiscountRule {
    private RulePeriod morning = new RulePeriod(6, 9);
    private RulePeriod evening = new RulePeriod(17, 20);

    @Override
    public boolean isApplicable(HighwayDrive drive) {
        return ((!morning.isHoliday(drive) && morning.isIn(drive))
                || (!evening.isHoliday(drive) && evening.isIn(drive)))
                && drive.getRouteType() == RouteType.RURAL;
    }

    @Override
    public long discountPercentage(HighwayDrive drive) {
        int count = drive.getDriver().getCountPerMonth();
        if (count >= 10) {
            return 50;
        } else if (count >= 5){
            return 30;
        } else {
            return 0;
        }
    }
}
