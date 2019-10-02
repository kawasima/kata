package kata.ex01.model;

/**
 * 車種.
 *
 * @author kawasima
 */
public enum VehicleFamily {
    /** 普通車 */
    STANDARD,
    /** 軽自動車 */
    MINI,
    /** 二輪車 */
    MOTORCYCLE,
    /** その他 */
    OTHER;

    public boolean isHolidayDiscountVehicle() {
        switch (this) {
            case STANDARD:
            case MINI:
            case MOTORCYCLE:
                return true;
        }

        return false;
    }
}
