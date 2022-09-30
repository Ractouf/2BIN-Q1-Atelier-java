package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat implements Iterable<Instruction> {
    private final String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes = Duration.ofMinutes(0);
    private List<Instruction> recette;
    private Set<IngredientQuantifie> ingredients;
    private Type type;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.type = type;

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
    public Type getType() {
        return type;
    }

    public void insererInstruction(int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);

        if (position > recette.size() + 1)
            throw new IllegalArgumentException();

        recette.add(position - 1,instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }
    public void ajouterInstruction (Instruction instruction) {
        Util.checkObject(instruction);

        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }
    public Instruction remplacerInstruction (int position, Instruction instruction) {
        Util.checkStrictlyPositive(position);
        Util.checkObject(instruction);

        if (position > recette.size())
            throw new IllegalArgumentException();

        Instruction instructionRemplacee = recette.set(position - 1,instruction);
        dureeEnMinutes = dureeEnMinutes.minus(instructionRemplacee.getDureeEnMinutes());
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());

        return instructionRemplacee;
    }
    public Instruction supprimerInstruction (int position) {
        Util.checkStrictlyPositive(position);

        if (position > recette.size() )
            throw new IllegalArgumentException();

        Instruction instructionSupprimee = recette.remove(position - 1);
        dureeEnMinutes = dureeEnMinutes.minus(instructionSupprimee.getDureeEnMinutes());

        return instructionSupprimee;
    }

    @Override
    public Iterator<Instruction> iterator() {
        return Collections.unmodifiableList(recette).iterator();
    }

    private IngredientQuantifie getIngredientQuantifie(Ingredient ingredient) {
        Util.checkObject(ingredient);

        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            if (ingredientQuantifie.getIngredient().equals(ingredient))
                return ingredientQuantifie;
        }

        return null;
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);

        if (getIngredientQuantifie(ingredient) != null)
            return false;

        ingredients.add(new IngredientQuantifie(ingredient,quantite,unite));

        return true;
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        return ajouterIngredient(ingredient,quantite,Unite.NEANT);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        Util.checkObject(unite);
        Util.checkStrictlyPositive(quantite);

        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);

        if (ingredientQuantifie == null)
            return false;

        ingredientQuantifie.setQuantite(quantite);
        ingredientQuantifie.setUnite(unite);

        return true;
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);

        if (ingredientQuantifie == null)
            return false;

        return ingredients.remove(ingredientQuantifie);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        IngredientQuantifie ingredientQuantifie = getIngredientQuantifie(ingredient);

        if (ingredientQuantifie == null)
            return null;

        return ingredientQuantifie;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutesPart());
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
    public enum Type {
        ENTREE, PLAT, DESSERT
    }
}
