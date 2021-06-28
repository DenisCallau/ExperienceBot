package main;

public class Exp {
    private int exp;
    private float expPercentage;

    public Exp(String string) {
        try {
//            String expNumber = string.substring(0, string.indexOf("["));
            String expPercent = string.substring(0, string.indexOf("%"));

            this.setExp(0);
            this.setExpPercentage(Float.parseFloat(expPercent));
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

    public Exp() {

    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public float getExpPercentage() {
        return expPercentage;
    }

    public void setExpPercentage(float expPercentage) {
        this.expPercentage = expPercentage;
    }
}
