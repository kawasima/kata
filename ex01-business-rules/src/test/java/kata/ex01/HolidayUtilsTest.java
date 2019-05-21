package kata.ex01;

import kata.ex01.util.HolidayUtils;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author kawasima
 */
public class HolidayUtilsTest {
    @Test
    public void test元日は休日() {
        assertTrue(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 1)));
        assertFalse(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 2)));
        assertFalse(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 3)));
        assertTrue(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 5)));
    }
}
