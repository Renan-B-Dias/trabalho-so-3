import java.util.*;

/**
 * Created by renan on 10/07/2017.
 */
public class MainMemoryFirstFit {

    private Segment[] mainMemory;
    private int nSegments;

    private PriorityQueue<Segment> pendingQueue;

    public MainMemoryFirstFit(int mainBytes) {
        mainMemory = new Segment[mainBytes];
        this.nSegments = mainBytes;
        this.pendingQueue = new PriorityQueue<>();
    }

    public void put(Segment seg) {
        int count = 0;
        boolean hasSpace = false;

        int i = 0;
        for(; i < mainMemory.length && !hasSpace; i++) {
            if(mainMemory[i] != null)
                count = 0;
            else
                count++;


            if(count == seg.segSize) {
                hasSpace = true;
                i--;
            }
        }

        if(hasSpace)
            for(int size = seg.segSize; size > 0; size--, i--)
                mainMemory[i] = seg;

        else {
            System.out.println("No space");
        }
    }

    public void printAll() {
        for(Segment x: mainMemory)
            if(x == null)
                System.out.printf("%s ", "null");
            else
                System.out.printf("%d ", x.segId);
    }

//    public MainMemory(int mainBytes) {
//        this.mainMemory = new Segment[mainBytes];
//        this.nSegments = mainBytes;
//        this.pendingQueue = new PriorityQueue<>();
//    }
//
//    public void putSeg(Segment seg) {
//        if(seg.segId < 0 || seg.segId > nSegments-1) {
//            System.out.println("“Segmento " + seg.segId + " invalido!");
//        } else if(inMemory(seg)) {
//            System.out.println("“Segmento " + seg.segId + " ja esta na memoria!");
//        } else if(isFull()) { // see memory size
//            pendingQueue.add(seg);  /*pendingQueue.remove();*/
//        } else {
//
//        }
//    }
//
//    public void delSeg(Segment seg) {
//        if(seg.segId < 0 || seg.segId >nSegments-1)
//            System.out.print("Numero de segmento " + seg.segId + " invalido!");
//        else if()
//            System.out.print("Segmento seg nao esta na memoria!");
//        else {
//            pendingQueue.remove(seg);
//
//
//        }
//    }
//
//    private Boolean inMemory(Segment seg) {
//        return false;
//    }
//
//    private Boolean isFull() {
//        return false;
//    }

}
