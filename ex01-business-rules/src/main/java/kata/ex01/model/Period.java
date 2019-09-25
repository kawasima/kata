package kata.ex01.model;

import java.time.LocalDateTime;

public class Period {
    private LocalDateTime start;
    private LocalDateTime end;

    public Period(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public Boolean over(LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(this.end) && end.isAfter(this.start)) {
            return true;
        }
        return false;
    }
}


