package kata.ex03.roll;

import kata.ex03.Roll;

public class StrikeRoll implements Roll {
    @Override
    public int getNumOfKnockedOutPins() {
        return 10;
    }

    @Override
    public String getPrintableChars() {
        return "X";
    }
}
