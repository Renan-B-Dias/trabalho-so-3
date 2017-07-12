	/**
 * Created by yellow-umbrella on 12/07/17.
 */
public class SegmentControll implements Comparable<SegmentControll> {

    protected int begin;
    protected int end;

    protected int size;

    public SegmentControll(int begin, int end) {
        this.begin = begin;
        this.end = end;
        size = end - begin;
    }

    @Override
    public int compareTo(SegmentControll seg) {
        if(this.size > seg.size)
            return 1;
        else if(this.size < seg.size)
            return -1;
        return 0;
    }

}
