package compteur;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;

    //Cette variable de classe permet de retenir quel CompteurThread
    //a fini de compter le premier.
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= max; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(nom + " : " + i);
        }

        System.out.println(nom + " a fini de compter");

        synchronized (this.getClass()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (gagnant == null) {
                System.out.println(nom + " a gagné");
                gagnant = this;
            }
        }



    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
