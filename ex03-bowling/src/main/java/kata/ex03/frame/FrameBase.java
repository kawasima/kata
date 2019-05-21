package kata.ex03.frame;

import kata.ex03.Frame;

import java.util.Optional;

public abstract class FrameBase implements Frame {
    private Frame nextFrame;

    public void setNextFrame(Frame frame) {
        this.nextFrame = frame;
    }

    public Optional<Frame> getNextFrame() {
        return Optional.ofNullable(nextFrame);
    }
}
