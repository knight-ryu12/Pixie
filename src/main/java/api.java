/**
 * Created by chroma on 16/01/19.
 */
import java.util.Random;

public class api {
    public int showRandomInteger(int aStart, int aEnd) {
        if (aStart > aEnd) throw new IllegalArgumentException("Start cannot exceed End.");
        Random aRandom = new Random();
        //get the range, casting to avoid overflow problems
        long range = (long) aEnd - (long) aStart + 1;
        //compute a fraction of the range , 0 <= frac <= range
        long fraction = (long) (range * aRandom.nextDouble());
        return (int) (fraction + aStart);
    }
}
