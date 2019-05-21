package kata.ex03;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一連のフレームを表すフレームライン.
 *
 * @author kawasima
 */
public class Line {
    private List<Frame> frames;
    protected Line(List<Frame> frames) {
        this.frames = frames;
    }

    public int getScore() {
        return frames.stream().mapToInt(Frame::getScore).sum();
    }

    public void print(Writer writer) throws UncheckedIOException {
        try {
            writer.write(frames.stream()
                    .map(Frame::toFormattedString)
                    .collect(Collectors.joining(" ")));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
