package io;

import java.util.List;
import java.util.Set;

/**
 * Created by maxence on 12/05/17.
 *
 *
 * Représente le résultat du sondage, qui sera utilisé par le serveur
 *
 */
public class Resultat {

    private List<Byte> classement; //Représente le classement des candidats, chacun étant représenté par son numéro dans l'entrée Urne
    private String nomMethode; //Méthode de vote utilisée pour réaliser ce classement
    private Set<Set<Byte>> egalites; //Représente tous les groupes de candidats étant à égalité entre eux
    public boolean premierEgalite; //Permet de savoir si le premier du classement est issu d'une égalité

    public Resultat() {
        premierEgalite = false;
    }

    @Deprecated
    public Resultat(List<Byte> classement, String nomMethode) {
        this();
        this.classement = classement;
        this.nomMethode = nomMethode;
    }

    public Resultat(List<Byte> classement, Set<Set<Byte>> egalites) {
        this();
        this.classement = classement;
        this.nomMethode = nomMethode;
        this.egalites = egalites;
    }

    @Override
    public String toString() {

        String egalite = this.premierEgalite ? " (Egalité) " : " ";

        return "Numéro du vainqueur" + egalite + ": " + this.classement.get(0) + " avec méthode " + this.nomMethode;
    }

    public List<Byte> getClassement() {
        return classement;
    }

    public void setClassement(List<Byte> classement) {
        this.classement = classement;
    }

    public String getNomMethode() {
        return nomMethode;
    }

    public void setNomMethode(String nomMethode) {
        this.nomMethode = nomMethode;
    }

    public Set<Set<Byte>> getEgalites() {
        return egalites;
    }

    private void setEgalites(Set<Set<Byte>> egalites) {
        this.egalites = egalites;
    }
}
