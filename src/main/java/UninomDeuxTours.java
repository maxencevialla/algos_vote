import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by maxence on 12/05/17.
 */
public class UninomDeuxTours {

   public static Resultat getResult(ResultArray r) {
        Resultat res = UninomUnTour.getResult(r);

        //Récupération du classement du premier tour
        List<Integer> classementPremierTour = res.getClassement();

        int premier = classementPremierTour.get(0);
        int second = classementPremierTour.get(1);

        int nbVotesPourPremier = 0, nbVotesPourSecond = 0;

        //Comptage des votes du second tour
        for(int i = 0 ; i < r.getNbVotants() ; i++) {
           if(r.getVotes().get(i).get(premier) > r.getVotes().get(i).get(second)) {
               nbVotesPourPremier++;
           } else {
               nbVotesPourSecond++;
           }
       }

       List<Integer> classementSecondTour = classementPremierTour;
       System.out.println("Premier : " + nbVotesPourPremier + " voix.");
       System.out.println("Second : " + nbVotesPourSecond + " voix.");

       //On inverse le premier et le econd du classement du premier tour si besoin
       if(nbVotesPourPremier > nbVotesPourSecond) {
           classementSecondTour.set(0, second);
           classementSecondTour.set(1, premier);
       }

        Resultat resultat = new Resultat(classementSecondTour, UninomDeuxTours.class.getName());

        return resultat;
    }
}
