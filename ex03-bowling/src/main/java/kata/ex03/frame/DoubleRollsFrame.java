package kata.ex03.frame;

import kata.ex03.Frame;
import kata.ex03.Roll;

import java.util.stream.Stream;

public class DoubleRollsFrame extends FrameBase {
    private Roll first;
    private Roll second;

    public DoubleRollsFrame(Roll first, Roll second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Stream<Roll> rollStream() {
        return Stream.concat(Stream.of(first, second),
                getNextFrame()
                        .map(Frame::rollStream)
                        .orElse(Stream.empty()));
    }

    @Override
    public int getScore() {
        int knockedPins = first.getNumOfKnockedOutPins() + second.getNumOfKnockedOutPins();

        if (knockedPins == 10) {
            knockedPins += getNextFrame().map(frame -> frame.rollStream()
                    .limit(1)
                    .mapToInt(Roll::getNumOfKnockedOutPins)
                    .sum()).orElseThrow();
        }
        return knockedPins;
    }

    @Override
    public String toFormattedString() {
        return first.getPrintableChars() + " " + second.getPrintableChars();
    }
}
