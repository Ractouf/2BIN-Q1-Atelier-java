public class LigneDeCommande {
    private Pizza pizza;
    private int quantite;
    private double prixUnitaire;

    public LigneDeCommande(Pizza pizza, int quantite) {
        if (pizza == null || quantite < 1)
            throw new IllegalArgumentException();

        this.pizza = pizza;
        this.quantite = quantite;

        prixUnitaire = pizza.calculerPrix();
    }

    public Pizza getPizza() {
        return pizza;
    }
    public int getQuantite() {
        return quantite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double calculerPrixTotal() {
        return prixUnitaire * quantite;
    }

    @Override
    public String toString() {
        return quantite + " " + pizza.getTitre() + " à " + prixUnitaire + "\n";
    }
}
