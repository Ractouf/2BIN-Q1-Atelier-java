package domaine;

import util.Util;

import java.util.*;

public class Livre {
    private HashMap<Plat.Type, SortedSet<Plat>> plats = new HashMap<>();

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);

        SortedSet<Plat> set = this.plats.get(plat.getType());
        if (set == null) {
            set = new TreeSet<>(new Comparator<Plat>() {
                @Override
                public int compare(Plat o1, Plat o2) {
                    int compare = Integer.compare(o1.getNiveauDeDifficulte().ordinal(), o2.getNiveauDeDifficulte().ordinal());

                    if (compare != 0)
                        return compare;

                    return o1.getNom().compareToIgnoreCase(o2.getNom());
                }
            });
            this.plats.put(plat.getType(), set);
        }

        return set.add(plat);
    }

    /**
     * Supprime un plat du livre, s'il est dedans.
     4
     * Si le plat supprimé est le dernier de ce type de plat, il faut supprimer
     ce type de
     * plat de la Map.
     * @param plat le plat à supprimer
     * @return true si le plat a été supprimé, false sinon.
     */
    public boolean supprimerPlat (Plat plat) {
        Util.checkObject(plat);

        if (plats.containsKey(plat.getType())) {
            plats.get(plat.getType()).remove(plat);
            if (plats.get(plat.getType()).size() == 0) {
                plats.remove(plat.getType());
            }
            return true;
        }
        return false;
    }

    /**
     * Renvoie un ensemble contenant tous les plats d'un certain type.
     * L'ensemble n'est pas modifable.
     * @param type le type de plats souhaité
     * @return l'ensemble des plats
     */
    public SortedSet<Plat> getPlatsParType(Plat.Type type) {
        SortedSet<Plat> platParType = new TreeSet<>();

        for (Plat plat : plats.get(type)) {
            platParType.add(plat);
        }

        return (SortedSet<Plat>) Collections.unmodifiableSet(platParType);
    }

    /**
     * Renvoie true si le livre contient le plat passé en paramètre. False
     sinon.
     * Pour cette recherche, un plat est identique à un autre si son type, son
     niveau de
     * difficulté et son nom sont identiques.
     * @param plat le plat à rechercher
     * @return true si le livre contient le plat, false sinon.
     */
    public boolean contient(Plat plat) {

        for (Plat plats : this.plats.get(plat.getType())) {
            if (plats.getNiveauDeDifficulte() == plat.getNiveauDeDifficulte() && plats.getNom().equals(plat.getNom())) {
                return true;
            }
        }

        // Ne pas utiliser 2 fois la méthode get() de la map, et ne pas déclarer de variable locale !
        return false;
    }

    /**
     * Renvoie un ensemble contenant tous les plats du livre. Ils ne doivent pas être triés.
     * @return l'ensemble de tous les plats du livre.
     */
    public Set<Plat> tousLesPlats() {
        // Ne pas utiliser la méthode keySet() ou entrySet() ici !
        return null;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Plat.Type type : plats.keySet()) {
            sb.append(type.getNom()).append("\n");
            for (Plat plat : plats.get(type)) {
                sb.append("\t").append(plat).append("\n");
            }
        }
        return sb.toString();
    }
}
