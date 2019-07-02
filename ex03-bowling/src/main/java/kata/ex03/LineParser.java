package kata.ex03;

import kata.ex03.frame.*;
import kata.ex03.roll.NumberedRoll;
import kata.ex03.roll.SpareRoll;
import kata.ex03.roll.StrikeRoll;

import java.util.*;

public class LineParser {
    private Roll parseRoll(String pins, int remain) {
        if (pins.equalsIgnoreCase("X")) {
            return Roll.STRIKE;
        } else if (pins.equals("/")) {
            return new SpareRoll(10 - remain);
        } else if (pins.equals("-")) {
            return new NumberedRoll(0);
        } else if (pins.matches("\\d")) {
            return new NumberedRoll(Integer.parseInt(pins));
        } else {
            throw new IllegalArgumentException("Wrong roll: " + pins);
        }
    }

    public Line readFromString(String line) {
        String[] lines = line.split("\\s+");
        LinkedList<Frame> frames = new LinkedList<>();
        ArrayDeque<String> rollQueue = new ArrayDeque<>(Arrays.asList(lines));

        while(!rollQueue.isEmpty()) {
            String pins = rollQueue.pop();
            Roll first = parseRoll(pins, 0);
            Frame frame;
            if (first instanceof StrikeRoll) {
                frame = new SingleRollFrame(first);
            } else {
                Roll second = parseRoll(rollQueue.pop(), first.getNumOfKnockedOutPins());
                frame = new DoubleRollsFrame(first, second);
            }
            if (!frames.isEmpty()) {
                frames.getLast().setNextFrame(frame);
            }
            frames.add(frame);
            if (frames.size() == 9) break;
        }

        if (rollQueue.size() == 3) {
            Roll first = parseRoll(rollQueue.pop(), 0);
            Frame frame = new TripleRollsFrame(first,
                    parseRoll(rollQueue.pop(), first.getNumOfKnockedOutPins()),
                    parseRoll(rollQueue.pop(), 0));
            frames.getLast().setNextFrame(frame);
            frames.add(frame);
        } else if (rollQueue.size() == 2) {
            Roll first = parseRoll(rollQueue.pop(), 0);
            Frame frame = new DoubleRollsFrame(first, parseRoll(rollQueue.pop(), first.getNumOfKnockedOutPins()));
            frames.getLast().setNextFrame(frame);
            frames.add(frame);
        } else {
            throw new IllegalArgumentException("The last frame must contain 2 or 3 rolls");
        }
        return new Line(frames);
    }
}
