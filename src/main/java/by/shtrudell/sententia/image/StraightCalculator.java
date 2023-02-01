package by.shtrudell.sententia.image;

public class StraightCalculator implements ResolutionCalculator{
    @Override
    public Resolution calculate(Resolution currentRes, Resolution targetRes) {
        return targetRes;
    }
}
