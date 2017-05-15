package io;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by maxence on 12/05/17.
 */
public class ResultArray {

    private ArrayList<ArrayList<Integer>> votes;
    private int nbCandidats;
    private int nbVotants;

    public ResultArray() {
        votes = new ArrayList<ArrayList<Integer>>();
    }

    /*
    Créé un io.ResultArray avec un tableau de votes aléatoires pour nbCandidats et nbVotants
     */
    public ResultArray(int nbCandidats, int nbVotants) {
        this();
        this.nbCandidats = nbCandidats;
        this.nbVotants = nbVotants;

        Random rand = new Random();
        int myRand;
        ArrayList<Integer> line = null;

        for(int i = 0 ; i < this.nbVotants ; i++) {
            line = new ArrayList<Integer>();
            for(int j = 0 ; j < this.nbCandidats ; j++) {
                myRand = rand.nextInt(this.nbCandidats) + 1;
                while(line.contains(myRand)) {
                    myRand = rand.nextInt(this.nbCandidats) + 1;
                }
                //System.out.println("Add " + myRand +" to this array : " + line.toString());
                line.add(myRand);
            }
            this.votes.add(line);
        }
    }

    /*
    Créé un io.ResultArray à partir d'un tableau de votes
     */
    public ResultArray(ArrayList<ArrayList<Integer>> votes) {
        this();
        this.votes = votes;

        this.nbVotants = votes.size();
        this.nbCandidats = votes.get(0).size();
    }

    public void afficheVotes() {
        for(int j = 0 ; j < this.nbCandidats ; j++) {
            for(int i = 0 ; i < this.nbVotants ; i++) {
                System.out.print(this.votes.get(i).get(j) + " ");
            }
            System.out.println("\n");
        }
    }

    public ArrayList<ArrayList<Integer>> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<ArrayList<Integer>> votes) {
        this.votes = votes;
    }

    public int getNbCandidats() {
        return nbCandidats;
    }

    public void setNbCandidats(int nbCandidats) {
        this.nbCandidats = nbCandidats;
    }

    public int getNbVotants() {
        return nbVotants;
    }

    public void setNbVotants(int nbVotants) {
        this.nbVotants = nbVotants;
    }
}
