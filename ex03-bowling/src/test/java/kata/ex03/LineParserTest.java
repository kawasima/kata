package kata.ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

public class LineParserTest {
    private LineParser lineParser;

    @BeforeEach
    public void setup() {
        lineParser = new LineParser();
    }

    @Test
    public void strikeAll() {
        Line line = lineParser.readFromString("X X X X X X X X X X X X");
        assertThat(line.getScore()).isEqualTo(300);
        StringWriter sw = new StringWriter();
        line.print(sw);
        System.out.println(sw.toString());
    }

    @Test
    public void spareAll() {
        Line line = lineParser.readFromString("5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5 / 5");
        assertThat(line.getScore()).isEqualTo(150);
        StringWriter sw = new StringWriter();
        line.print(sw);
        System.out.println(sw.toString());
    }

    @Test
    public void gutterAll() {
        Line line = lineParser.readFromString("- - - - - - - - - - - - - - - - - - - -");
        assertThat(line.getScore()).isEqualTo(0);
        StringWriter sw = new StringWriter();
        line.print(sw);
        System.out.println(sw.toString());
    }
}
