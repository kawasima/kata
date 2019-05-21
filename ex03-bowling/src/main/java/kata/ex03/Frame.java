package kata.ex03;

import java.util.Optional;
import java.util.stream.Stream;

public interface Frame {
    /**
     * このフレームのスコアを計算し返します.
     *
     * @return フレームのスコア
     */
    int getScore();

    /**
     * このフレームに含まれる投球(roll)と以降のフレームに含まれる投球のストリームを返します.
     *
     * @return 投球のストリーム
     */
    Stream<Roll> rollStream();

    /**
     * このフレームの次のフレームを返します.
     *
     * @return 次のフレーム(Optional)
     */
    Optional<Frame> getNextFrame();
    void setNextFrame(Frame frame);
    default String toFormattedString() {
        return "?";
    }
}
