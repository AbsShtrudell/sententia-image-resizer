package by.shtrudell.sententia.image;

public class PreserveRatioCalculator implements ResolutionCalculator{
    @Override
    public Resolution calculate(Resolution currentRes, Resolution targetRes) {
        if(currentRes.getWidth() > currentRes.getHeight())
            return new Resolution(targetRes.getWidth(), currentRes.getHeight() * targetRes.getWidth() / currentRes.getWidth());
        else
            return new Resolution(currentRes.getWidth() * targetRes.getHeight() / currentRes.getHeight(), targetRes.getHeight());
    }
}
