package domaine;

import java.time.Duration;
import java.util.*;

public class Plat implements Iterable<Instruction> {
    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette;
    private Set<IngredientQuantifie> ingredients;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);

        recette = new ArrayList<>();
        ingredients = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }
    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }
    public Cout getCout() {
        return cout;
    }
    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction) {
        if (position < 0 || position > recette.size())
            throw new IllegalArgumentException();

        recette.set(position, instruction);
    }
    public void ajouterInstruction (Instruction instruction) {
        recette.add(instruction);
    }
    public Instruction remplacerInstruction (int position, Instruction instruction) {
        return recette.set(position, instruction);
    }
    public Instruction supprimerInstruction (int position) {
        return recette.remove(position);
    }

    @Override
    public Iterator<Instruction> iterator() {
        return recette.iterator();
    }
    public Iterator<Instruction> instructions() {
        //fournit un itérateur d’instructions ne permettant pas de supprimer des
        //instructions (la méthode remove de l’itérateur renvoyé doit lancer une UnsupportedOperationException)
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite) {
        IngredientQuantifie ingredientQuantifie = trouverIngredientQuantifie(ingredient);

        if (ingredientQuantifie == null) {
            return false;
        }

        return ingredients.add(new IngredientQuantifie(ingredient, quantite, unite));
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite) {
        return ajouterIngredient(ingredient, quantite, null);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite) {
        IngredientQuantifie ingredientQuantifie = trouverIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) {
            return false;
        }

        ingredientQuantifie.setQuantite(quantite);
        ingredientQuantifie.setUnite(unite);
        return true;
    }

    public boolean supprimerIngredient(Ingredient ingredient) {
        IngredientQuantifie ingredientQuantifie = trouverIngredientQuantifie(ingredient);
        if (ingredientQuantifie == null) {
            return false;
        }

        return ingredients.remove(ingredientQuantifie);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient) {
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient() == ingredient) {
                return ingredientQuantifie;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }
    public enum Difficulte {
        X,XX,XXX,XXXX,XXXXX;

        @Override
        public String toString() {
            return "Difficulté : " + super.toString().replace("X", "*");
        }
    }
    public enum Cout {
        $,$$,$$$,$$$$,$$$$$;

        @Override
        public String toString() {
            return "Cout : " + super.toString().replace("$", "€");
        }
    }
}
