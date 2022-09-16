import java.util.ArrayList;

public abstract class Pizza {
    public final static double PRIX_BASE = 5.0;
    private String titre, description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);

        for (Ingredient ingredient : ingredients) {
            if (this.ingredients.contains(ingredient)) {
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza");
            }
            this.ingredients.add(ingredient);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient) {
        if (this.ingredients.contains(ingredient))
            return false;

        this.ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer(Ingredient ingredient) {
        return this.ingredients.remove(ingredient);
    }

    public double calculerPrix() {
        double prix = PRIX_BASE;
        for (Ingredient ingredient : this.ingredients) {
            prix += ingredient.getPrix();
        }
        return prix;
    }

}
