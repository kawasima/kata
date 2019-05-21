package kata.ex03;

import java.util.Optional;
import java.util.stream.Stream;

public interface Frame {
    int getScore();
    Stream<Roll> rollStream();
    Optional<Frame> getNextFrame();
    void setNextFrame(Frame frame);
    default String toFormattedString() {
        return "?";
    }
}
