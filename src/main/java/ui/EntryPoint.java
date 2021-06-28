package ui;

public class EntryPoint {

    public static void main(String[] args) {
        Thread t = new Thread(new Application());
        t.start();
    }
}

