package io;

import exceptions.mauvaisesUrnesException.ClassementInvalideException;
import exceptions.mauvaisesUrnesException.LongueurDeLigneIncorrecteException;
import exceptions.mauvaisesUrnesException.MauvaiseUrneException;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by maxence on 12/05/17.
 *
 * Objet fourni en entrée de chacune des méthodes de vote
 * Il contient la liste des classements des candidats par chacun des votants
 *
 */
public class Urne {

    private ArrayList<ArrayList<Byte>> votes;
    private int nbCandidats;
    private int nbVotants;
    private int numCandidatPref;

    private Urne() {
        this.votes = new ArrayList<ArrayList<Byte>>();
        this.numCandidatPref = -1;
    }

    /**
     * Créé un tableau de résultats aléatoires
     *
     * @param nbCandidats
     * @param nbVotants
     */
    public Urne(int nbCandidats, int nbVotants) {
        this();
        this.nbCandidats = nbCandidats;
        this.nbVotants = nbVotants;

        Random rand = new Random();
        Integer myRand;
        ArrayList<Byte> line;

        for(int i = 0 ; i < this.nbVotants ; i++) {
            line = new ArrayList<Byte>();
            for(int j = 0 ; j < this.nbCandidats ; j++) {
                myRand = rand.nextInt(this.nbCandidats) + 1;
                while(line.contains(myRand.byteValue())) {
                    myRand = rand.nextInt(this.nbCandidats) + 1;
                }
                //System.out.println("Add " + myRand +" to this array : " + line.toString());
                line.add(myRand.byteValue());
            }
            //System.out.println("Add " + line.toString() + " to this array : " + this.votes.toString());
            this.votes.add(line);
        }
    }

    /**
     * Force le candidat en première place à être gagnant sur la moitié des bulletins dans l'urne courante
     */
    public void forceOne() {
        for(int i = 0 ; i < this.nbVotants ; i+=2) {
            int toSwitch = this.getVotes().get(i).indexOf((byte)1);
            this.getVotes().get(i).set(toSwitch, this.getVotes().get(i).get(0));
            this.getVotes().get(i).set(0, (byte)1);
        }
    }

    /**
     *
     * @param votes : contient tous les bulletins à traiter
     *              Chaque arraylist "interne" contient l'ordre des choix d'un votant
     * @throws MauvaiseUrneException : renvoit une exception si la stucture de l'array votes n'est pas celle attendue
     */
    @Deprecated
    /**
     * Utiliser le constructeur Urne(ArrayList<ArrayList<Byte>> votes, int numCandidatPref) à la place afin d'indiquer le candidat préféré
     */
    public Urne(ArrayList<ArrayList<Byte>> votes) throws MauvaiseUrneException {
        this();
        this.votes = votes;

        this.nbVotants = votes.size();
        this.nbCandidats = votes.get(0).size();

        this.checkStructure();
    }

    /**
     *
     * @param votes : contient tous les bulletins à traiter
     *              Chaque arraylist "interne" contient l'ordre des choix d'un votant
     * @throws MauvaiseUrneException : renvoit une exception si la stucture de l'array votes n'est pas celle attendue
     */
    public Urne(ArrayList<ArrayList<Byte>> votes, int numCandidatPref) throws MauvaiseUrneException {
        this();
        this.votes = votes;
        this.numCandidatPref = numCandidatPref;

        this.nbVotants = votes.size();
        this.nbCandidats = votes.get(0).size();

        this.checkStructure();
    }

    public void afficheVotes() {
        for(int j = 0 ; j < this.nbCandidats ; j++) {
            for(int i = 0 ; i < this.nbVotants ; i++) {
                System.out.print(this.votes.get(i).get(j) + " ");
            }
            System.out.println("\n");
        }
    }

    public void checkStructure() throws MauvaiseUrneException {

        for(int i = 0 ; i < nbVotants ; i++){
            if(this.getVotes().get(i).size() != nbCandidats) {
                throw new LongueurDeLigneIncorrecteException("Longueur de ligne " + i + " non valide.");
            }
            for(int j = 0 ; j < nbCandidats ; j++) {
                if(this.getVotes().get(i).indexOf(j+1) == -1) {
                    throw new ClassementInvalideException("Classement de la ligne " + i + " non valide.");
                }
            }
        }
    }

    public ArrayList<ArrayList<Byte>> getVotes() {
        return votes;
    }

    private void setVotes(ArrayList<ArrayList<Byte>> votes) {
        this.votes = votes;
    }

    public int getNbCandidats() {
        return nbCandidats;
    }

    private void setNbCandidats(int nbCandidats) {
        this.nbCandidats = nbCandidats;
    }

    public int getNbVotants() {
        return nbVotants;
    }

    private void setNbVotants(int nbVotants) {
        this.nbVotants = nbVotants;
    }

    public int getNumCandidatPref() { return numCandidatPref; }

    private void setNumCandidatPref(int numCandidatPref) { this.numCandidatPref = numCandidatPref; }
}
