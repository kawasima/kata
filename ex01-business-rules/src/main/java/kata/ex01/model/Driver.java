package kata.ex01.model;

import java.io.Serializable;

/**
 * @author kawasima
 */
public class Driver implements Serializable {
    private int countPerMonth;

    public int getCountPerMonth() {
        return this.countPerMonth;
    }

    public void setCountPerMonth(int countPerMonth) {
        this.countPerMonth = countPerMonth;
    }

    public String toString() {
        return "Driver(countPerMonth=" + this.getCountPerMonth() + ")";
    }
}
