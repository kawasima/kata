package kata.ex03.roll;

public class SpareRoll extends NumberedRoll {
    public SpareRoll(int numOfKnockedOutPins) {
        super(numOfKnockedOutPins);
    }

    @Override
    public String getPrintableChars() {
        return "/";
    }
}
