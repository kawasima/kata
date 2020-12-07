package kata.ex01.rule;

import kata.ex01.model.HighwayDrive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DiscountAtMidnightTest {
    DiscountAtMidnight rule;

    @BeforeEach
    void setup() {
        rule = new DiscountAtMidnight();
    }

    @Test
    void 深夜3時に入って4時30分に出る_これは割引対象() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 3, 31, 3, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 3, 31, 4, 30));
        assertTrue(rule.isApplicable(drive));
    }

    @Test
    void 深夜11時に入って0時30分に出る_これは割引対象() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 4, 1, 0, 30));
        assertTrue(rule.isApplicable(drive));
    }

    @Test
    void 深夜11時に入って11時30分に出る_これは割引対象じゃない() {
        HighwayDrive drive = new HighwayDrive();
        drive.setEnteredAt(LocalDateTime.of(2021, 3, 31, 23, 0));
        drive.setExitedAt(LocalDateTime.of(2021, 3, 31, 23, 30));
        assertFalse(rule.isApplicable(drive));
    }

    @Test
    void 深夜割引率は一律30パーセント() {
        HighwayDrive drive = new HighwayDrive();
        assertEquals(rule.discountPercentage(drive), 30);
    }
}