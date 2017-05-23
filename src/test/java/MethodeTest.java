import methodes.UninomUnTour;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by maxence on 23/05/17.
 */
public class MethodeTest {

    @Test
    public void testClassement() {
        Double[] scores = {3.0, 2.5, 4.7, 5.3};
        ArrayList<Byte> al = new ArrayList<>();
        al.add((byte)3);
        al.add((byte)2);
        al.add((byte)0);
        al.add((byte)1);

        assertEquals("Classement non valide", al, UninomUnTour.getInstance().classeParScore(scores));
    }

    @Test
    public void testClassementEgalite() {
        Double[] scores = {3.0, 2.5, 4.7, 5.3, 5.3};
        List<Byte> al = new ArrayList<>();
        al.add((byte)3);
        al.add((byte)4);
        al.add((byte)2);
        al.add((byte)0);
        al.add((byte)1);

        List<Byte> al2 = new ArrayList<>();
        al2.add((byte)4);
        al2.add((byte)3);
        al2.add((byte)2);
        al2.add((byte)0);
        al2.add((byte)1);

        List<Byte> res = UninomUnTour.getInstance().classeParScore(scores);

        assertTrue(res.equals(al2) || res.equals(al));
    }
}
