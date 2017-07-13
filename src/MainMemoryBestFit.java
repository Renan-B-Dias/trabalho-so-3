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
		put(seg, true);
	}

	private Boolean put(Segment seg, boolean msg) {
		
		try{
			
			if (seg.segId < 0 || seg.segId > nSegments - 1) {
				throw new Exception ("Segmento " + seg.segId + " invalido!");
			}

			else if (inMemory(seg.segId)) {
				throw new Exception ("Segmento " + seg.segId + " ja esta na memoria!");
			} else {

				int count = 0;
				ArrayList<SegmentControll> array = new ArrayList<>();

				for (int i = 0; i < mainMemory.length; i++) {

					if (mainMemory[i] == null && i + 1 != mainMemory.length) {
						count++;
					} else {
						
						if (i + 1 == mainMemory.length && count + 1 >= seg.segSize) {
							array.add(new SegmentControll((i - count), i - 1));
							count = 0;
						}

						if (count >= seg.segSize) {
							array.add(new SegmentControll((i - count), i - 1));
							count = 0;
						}
					}
				}

				if (array.isEmpty()) {
					pendingQueue.add(seg);
					return false;
				}else {
					
					Collections.sort(array);
					SegmentControll least = array.get(0);

					int begin = least.begin;
					int siz = seg.segSize;

					for (int f = 0; f < siz; f++, begin++) {
						mainMemory[begin] = seg;
					}
					 if(msg)
		                    System.out.println("Segmento " + seg.segId + " foi inserido na mem�ria!");
		             return true;
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
		for (Segment x : mainMemory)
			if (x == null)
				System.out.printf("%s ", "null");
			else
				System.out.printf("%d ", x.segId);
	}

	public void printAllPending() {
		for (Segment x : pendingQueue)
			System.out.printf("%d ", x.segId);
	}

	public void remove(int segId) {
		for (int i = 0; i < mainMemory.length; i++)
			if (mainMemory[i] != null)
				if (mainMemory[i].segId == segId)
					mainMemory[i] = null;
		System.out.println("Segmento " + segId + " removido da memoria!");
	}

	private Boolean inMemory(int segId) {
		for (Segment x : mainMemory)
			if (x != null && x.segId == segId)
				return true;
		return false;
	}
}
