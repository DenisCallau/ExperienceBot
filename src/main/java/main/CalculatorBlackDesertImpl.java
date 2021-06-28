package main;

public class CalculatorBlackDesertImpl extends Calculator {

    public Exp expPerHour(Hunt hunt, Exp currentExp) {
        expPerHour = new Exp();
        now = System.currentTimeMillis();

        expGainedPercentage = currentExp.getExpPercentage() - hunt.getInitialExp().getExpPercentage();
        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds  = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour.setExpPercentage(expGainedPercentage / hoursDiff);
            return expPerHour;
        } catch (ArithmeticException e) {
            System.out.println("Invalid data for calculations");
            expPerHour.setExpPercentage(0);
            return expPerHour;
        }
    }


    public float timeToNextLevel(Exp currentExp, Exp expPerHour) {
        float totalExpToNextLevel = 100;
        float expToNextLevel = totalExpToNextLevel - currentExp.getExpPercentage();

        if(expPerHour.getExpPercentage() > 0) {
            timeToNextLevel = expToNextLevel / expPerHour.getExpPercentage();
            return timeToNextLevel;
        } else {
            return 0.0f;
        }
    }

}
