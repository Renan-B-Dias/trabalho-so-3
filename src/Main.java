/**
 * Created by renan on 10/07/2017.
 */
public class Main {

    public static void main(String args[]) {

        MainMemoryFirstFit mem = new MainMemoryFirstFit(13);

        mem.put(new Segment(0, 5));
        mem.put(new Segment(1, 2));
        mem.put(new Segment(2, 3));

        mem.printAll();


    }

}
