package kata.ex01;

import kata.ex01.model.HighwayDrive;

/**
 * @author kawasima
 */
public interface DiscountRule {
    boolean isApplicable(HighwayDrive drive);
    long discountPercentage(HighwayDrive drive);
}
