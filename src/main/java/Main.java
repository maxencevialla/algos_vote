/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) {
        ResultArray r = new ResultArray(3, 1000000);

        //r.afficheVotes();
        long start  = System.currentTimeMillis();
        Resultat result = UninomUnTour.getResult(r);
        System.out.println("Résultat complet en " + (System.currentTimeMillis() - start) + "ms.");

        for(Integer i : result.getClassement().keySet()) {
            System.out.println("Candidat n°" +  result.getClassement().get(i) + " : " + i + " voix.");
        }

        System.out.println();
        System.out.println("NbGagnant = " + result.getNbGagnant());
    }
}
