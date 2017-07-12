/**
 * Created by renan on 10/07/2017.
 */
public class Main {

    public static void main(String args[]) {

        MainMemoryFirstFit mem = new MainMemoryFirstFit(13);

        mem.put(new Segment(0, 5));
        mem.put(new Segment(1, 2));
        mem.put(new Segment(2, 3));
        mem.put(new Segment(2, 3));
        mem.put(new Segment(9, 2000));

        mem.printAllMain();
        System.out.println();
        mem.printAllPending();
        System.out.println();

        mem.put(new Segment(8, 4));
        mem.delSeg(new Segment(0, 5));

        mem.printAllMain();
        System.out.println();
        mem.printAllPending();
        System.out.println();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Best Fit Test!!!!!!!!!!");
        MainMemoryBestFit bestFit = new MainMemoryBestFit(13);
        bestFit.put(new Segment(1, 2));
        bestFit.put(new Segment(2, 3));
        bestFit.remove(1);
        bestFit.put(new Segment(3, 2));

        bestFit.printAllMain();
        System.out.println();
        bestFit.printAllPending();
        System.out.println();

    }

}
