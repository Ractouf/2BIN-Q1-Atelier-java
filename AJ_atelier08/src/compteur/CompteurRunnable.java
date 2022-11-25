package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    private static AtomicInteger podium = new AtomicInteger(1);

    @Override
    public void run() {
        for (int i = 1; i <= max; i++) {
            System.out.println(nom + " : " + i);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(nom + " a fini de compter en position " + podium.getAndIncrement());
    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}