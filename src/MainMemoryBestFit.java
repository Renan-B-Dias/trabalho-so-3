import java.util.*;

/**
 * Created by renan on 11/07/2017.
 */
public class MainMemoryBestFit {

    private Segment[] mainMemory;
    private int nSegments;

    private PriorityQueue<Segment> pendingQueue;

    public MainMemoryBestFit(int mainBytes) {
        mainMemory = new Segment[mainBytes];
        this.nSegments = mainBytes;
        this.pendingQueue = new PriorityQueue<>();
    }

    public void put(Segment seg) {
        put(seg, false);
    }

    private Boolean put(Segment seg, boolean msg) {
        if(seg.segId < 0 || seg.segId > nSegments-1) {
            System.out.println("Segmento " + seg.segId + " invalido!");
            return false;
        }

        else if(inMemory(seg.segId)) {
            System.out.println("Segmento " + seg.segId + " ja esta na memoria!");
            return false;
        }
        else {

            int count = 0;
            ArrayList<SegmentControll> array = new ArrayList<>();

            int i = 0;
            for(; i < mainMemory.length; i++) {
                count = 0;

                while(mainMemory[i++] == null) {
                    count++;
                    if(i == 13)
                        break;
                }

                if(count > seg.segSize)
                    array.add(new SegmentControll((i - count), i-1));

            }

            if(array.isEmpty()) {
                pendingQueue.add(seg);
                return false;
            }

            else {

                Collections.sort(array);

                SegmentControll least = array.get(0);

                int begin = least.begin;
                int siz = seg.segSize;

                for(int f = 0; f < siz ; f++, begin++) {
                    mainMemory[begin] = seg;
                }
                return true;
            }
        }
    }

    public void printAllMain() {
        for(Segment x: mainMemory)
            if(x == null)
                System.out.printf("%s ", "null");
            else
                System.out.printf("%d ", x.segId);
    }

    public void printAllPending() {
        for(Segment x: pendingQueue)
            System.out.printf("%d ", x.segId);
    }

    public void remove(int segId) {
        for(int i = 0; i < mainMemory.length; i++)
            if(mainMemory[i] != null)
                if(mainMemory[i].segId == segId)
                    mainMemory[i] = null;
        System.out.println("Segmento " + segId + " removido da memoria!");
    }

    private Boolean inMemory(int segId) {
        for(Segment x: mainMemory)
            if(x != null && x.segId == segId)
                return true;
        return false;
    }
}
