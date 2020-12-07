package kata.ex01;

import kata.ex01.util.HolidayUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kawasima
 */
public class HolidayUtilsTest {
    @Test
    public void test元日は休日() {
        assertThat(HolidayUtils.isHoliday(LocalDate.of(2021, 1, 1))).isTrue();
        assertThat(HolidayUtils.isHoliday(LocalDate.of(2021, 1, 2))).isTrue();
        assertThat(HolidayUtils.isHoliday(LocalDate.of(2021, 1, 3))).isTrue();
        assertThat(HolidayUtils.isHoliday(LocalDate.of(2021, 1, 4))).isFalse();
    }
}
