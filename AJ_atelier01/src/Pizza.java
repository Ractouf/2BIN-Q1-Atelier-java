import java.util.ArrayList;
import java.util.Iterator;

public abstract class Pizza implements Iterable<Ingredient> {
    public final static double PRIX_BASE = 5.0;
    private String titre, description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);

        this.ingredients = new ArrayList<>();

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

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
