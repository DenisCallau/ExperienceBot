package main;

public class CalculatorMapleStoryImpl extends Calculator {

    public Exp expPerHour(Hunt hunt, Exp currentExp) {
        expPerHour = new Exp();
        now = System.currentTimeMillis();

        expGained = currentExp.getExp() - hunt.getInitialExp().getExp();
        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds  = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour.setExp( (int) (expGained / hoursDiff));
            return expPerHour;
        } catch (ArithmeticException e) {
            System.out.println("Invalid data for calculations");
            expPerHour.setExp(0);
            return expPerHour;
        }
    }


    public float timeToNextLevel(Exp currentExp, Exp expPerHour) {
        int totalExpToNextLevel;
        totalExpToNextLevel = (int) ((currentExp.getExp() * 100) / currentExp.getExpPercentage());
        int expToNextLevel = totalExpToNextLevel - currentExp.getExp();

        if(expPerHour.getExp() > 0) {
            timeToNextLevel = (float) expToNextLevel / expPerHour.getExp();
            return timeToNextLevel;
        } else {
            return 0.0f;
        }
    }

}
