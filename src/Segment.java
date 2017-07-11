/**
 * Created by renan on 10/07/2017.
 */
public class Segment implements Comparable<Segment> {

    protected int segId;
    protected int segSize;

    public Segment(int seg, int segSize) {
        this.segId = seg;
        this.segSize = segSize;
    }

    @Override
    public int compareTo(Segment seg) {
        if(this.segSize > seg.segSize)
            return 1;
        else if(this.segSize < seg.segSize)
            return -1;
        return 0;
    }
}
