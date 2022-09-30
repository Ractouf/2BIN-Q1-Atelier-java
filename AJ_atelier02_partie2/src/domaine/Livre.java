package domaine;

import util.Util;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Livre {
    private HashMap<Plat.Type, SortedSet<Plat>> plats;

    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {
        Util.checkObject(plat);

        if (plats.containsKey(plat.getType())) {
            plats.get(plat.getType()).add(plat);
        } else {
            SortedSet<Plat> temp = new TreeSet<>();
            temp.add(plat);
            plats.put(plat.getType(), temp);
        }
        return true;
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
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Plat.Type type : plats.keySet()) {
            sb.append(type).append("\n");
            for (Plat plat : plats.get(type)) {
                sb.append("\t").append(plat).append("\n");
            }
        }

        return sb.toString();
    }
}
