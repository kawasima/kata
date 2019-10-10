package kata.ex01.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Threshold {

    private LocalDateTime rsToday;
    private LocalDateTime reToday;
    private LocalDateTime rsTomorrow;
    private LocalDateTime reTomorrow;

    public Threshold(LocalTime from, LocalTime to, LocalDate enteredAt, LocalDate exitedAt){
        this.rsToday = LocalDateTime.of(enteredAt, from);
        this.reToday =  LocalDateTime.of(enteredAt, to);
        this.rsTomorrow = LocalDateTime.of(exitedAt, from);
        this.reTomorrow = LocalDateTime.of(exitedAt, to);
    }

    public LocalDateTime getReToday() {
        return reToday;
    }

    public LocalDateTime getReTomorrow() {
        return reTomorrow;
    }

    public LocalDateTime getRsToday() {
        return rsToday;
    }

    public LocalDateTime getRsTomorrow() {
        return rsTomorrow;
    }
}
