package kata.ex03.frame;

import kata.ex03.Frame;
import kata.ex03.Roll;
import kata.ex03.roll.SpareRoll;
import kata.ex03.roll.StrikeRoll;

import java.util.stream.Stream;

/**
 * A last frame contains spare or strike.
 */
public class TripleRollsFrame extends FrameBase {
    private final Roll first;
    private final Roll second;
    private final Roll third;

    public TripleRollsFrame(Roll first, Roll second, Roll third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public Stream<Roll> rollStream() {
        return Stream.concat(Stream.of(first, second, third),
                getNextFrame()
                        .map(Frame::rollStream)
                        .orElse(Stream.empty()));
    }

    @Override
    public int getScore() {
        if (first instanceof StrikeRoll) {
            return 10 + second.getNumOfKnockedOutPins() + third.getNumOfKnockedOutPins();
        } else if (second instanceof SpareRoll) {
            return 10 + third.getNumOfKnockedOutPins();
        } else {
            throw new IllegalStateException("First roll must be 'strike' or second roll must be 'spare'");
        }
    }

    @Override
    public String toFormattedString() {
        return first.getPrintableChars() + " " + second.getPrintableChars() + " " + third.getPrintableChars();
    }
}
