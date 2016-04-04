package kata.ex01.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kawasima
 */
@Data
public class Driver implements Serializable {
    private int countPerMonth;
}
