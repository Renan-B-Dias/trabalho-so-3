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
        put(seg, true);
    }

    private Boolean put(Segment seg, boolean msg) {
    	try{
    		
    		if(seg.segId < 0 || seg.segId > nSegments-1) {
    			throw new Exception("Segmento " + seg.segId + " inv�lido!"); 
            }

            else if(inMemory(seg.segId)) {
            	throw new Exception("Segmento " + seg.segId + " j� est� na mem�ria!");
            }

            else {

                int count = 0;
                boolean hasSpace = false;

                int i = 0;
                for (; i < mainMemory.length && !hasSpace; i++) {
                    if (mainMemory[i] != null)
                        count = 0;
                    else
                        count++;


                    if (count == seg.segSize) {
                        hasSpace = true;
                        i--;
                    }
                }

                if (hasSpace) {
                    for (int size = seg.segSize; size > 0; size--, i--)
                        mainMemory[i] = seg;
                    if(msg)
                        System.out.println("Segmento " + seg.segId + " foi inserido na mem�ria!");
                    return true;
                }
                else {
                    pendingQueue.add(seg);  /*pendingQueue.remove();*/
                    return false;
                }
            }	
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    		return false;
    	}
        
    }

    public void delSeg(Segment seg) {
       try{
    	   if(seg.segId < 0 || seg.segId > nSegments)
    		   throw new Exception("N�mero de segmento " + seg.segId + " inv�lido!");
           else if(!inMemory(seg.segId))
        	   throw new Exception("Segmento " + seg.segId + " n�o est� na mem�ria");
           else
               remove(seg.segId);

               if(!pendingQueue.isEmpty()) {
                   Segment pended[] = new Segment[pendingQueue.size()];
                   pended = pendingQueue.toArray(pended);
                   pendingQueue = new PriorityQueue<>();
                   for(Segment x: pended) {
                       Boolean inserted = put(x, false);
                       if(inserted)
                           System.out.println("Segmento " + x.segId + " que estava na lista de pend�ncias foi inserido na mem�ria");
                   }
               }
               
       }catch(Exception e){
    	   System.out.println(e.getMessage());
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

    private void remove(int segId) {
        for(int i = 0; i < mainMemory.length; i++)
            if(mainMemory[i] != null)
                if(mainMemory[i].segId == segId)
                    mainMemory[i] = null;
        System.out.println("Segmento " + segId + " removido da mem�ria!");
    }

    private Boolean inMemory(int segId) {
        for(Segment x: mainMemory)
            if(x != null && x.segId == segId)
                return true;
        return false;
    }
}
