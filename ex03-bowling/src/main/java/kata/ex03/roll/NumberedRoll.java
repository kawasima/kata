package kata.ex03.roll;

import kata.ex03.Roll;

public class NumberedRoll implements Roll {
    private int numOfKnockedOutPins;

    public NumberedRoll(int numOfKnockedOutPins) {
        this.numOfKnockedOutPins = numOfKnockedOutPins;
    }

    public int getNumOfKnockedOutPins() {
        return numOfKnockedOutPins;
    }

    @Override
    public String getPrintableChars() {
        return (numOfKnockedOutPins > 0) ? Integer.toString(numOfKnockedOutPins) : "-";
    }
}
