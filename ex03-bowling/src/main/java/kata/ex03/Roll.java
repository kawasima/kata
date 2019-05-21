package kata.ex03;

import kata.ex03.roll.StrikeRoll;

public interface Roll {
    int getNumOfKnockedOutPins();
    String getPrintableChars();

    Roll STRIKE = new StrikeRoll();
}
