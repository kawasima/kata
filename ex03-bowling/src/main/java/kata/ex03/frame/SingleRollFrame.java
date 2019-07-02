package kata.ex03.frame;

import kata.ex03.Frame;
import kata.ex03.Roll;

import java.util.stream.Stream;

public class SingleRollFrame extends FrameBase {
    private final Roll roll;

    public SingleRollFrame(Roll roll) {
        this.roll = roll;
    }

    @Override
    public Stream<Roll> rollStream() {
        return Stream.concat(Stream.of(roll),
                getNextFrame()
                        .map(Frame::rollStream)
                        .orElse(Stream.empty()));
    }

    @Override
    public int getScore() {
        return 10 + getNextFrame().map(frame -> frame.rollStream()
                .limit(2)
                .mapToInt(Roll::getNumOfKnockedOutPins)
                .sum()).orElseThrow();
    }

    @Override
    public String toFormattedString() {
        return roll.getPrintableChars();
    }

}
