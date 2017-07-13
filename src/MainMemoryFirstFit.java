import java.util.*;

/**
 * Classe que simula uma memória principal com alocação First-Fit
 * 
 */
public class MainMemoryFirstFit {
	
	/**
     * Memória principal
     */
    private Segment[] mainMemory;
    
    /**
     * Quantidade de segmentos que serão ser colocadas na memória principal
     */
    private int nSegments;
    
    /**
     * Fila dos segmentos que estão em  pendência.
     */
    private PriorityQueue<Segment> pendingQueue;

	/**
     * Construtor da classe MainMemoryFirstFit
     * @param mainBytes tamanho da memoria principal.
     * @param nSeg quantidade de segmentos que irá receber.
     * @return
     */
    public MainMemoryFirstFit(int mainBytes, int nSeg) {
        mainMemory = new Segment[mainBytes];
        this.nSegments = nSeg;
        this.pendingQueue = new PriorityQueue<>();
    }

	/**
     * Método public de inserção do segmento na memoria principal
     * @param seg Segmento.
     */
	public void put(Segment seg) {
		put(seg, true);
	}
	
	/**
     * Método private de  inserção do segmento na memoria principal
     * @param seg Segmento
     * @param msg Boolean que irá defenir se é uma inserção da lista pendente ou de uma nova solicitação
     * @return
     */
    private Boolean put(Segment seg, boolean msg) {
    	try{
    		
    		if(seg.segId < 0 || seg.segId > nSegments-1) {
    			throw new Exception("Segmento " + seg.segId + " inválido!"); 
            }

            else if(inMemory(seg.segId)) {
            	throw new Exception("Segmento " + seg.segId + " já está na memória!");
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
                        System.out.println("Segmento " + seg.segId + " foi inserido na memória!");
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

	/**
     * Método de deletar o segmento da memória principal
     * @param seg Segmento.
     */
    public void delSeg(Segment seg) {
        try{
        	// Verificação das Exceções
     	   if(seg.segId < 0 || seg.segId > nSegments)
     		   throw new Exception("Número de segmento " + seg.segId + " inválido!");
            else if(!inMemory(seg.segId))
         	   throw new Exception("Segmento " + seg.segId + " não está na memória");
            else
                remove(seg.segId); // Caso passe das Exceções irá remover o segmento
     	   		
     	   		// Após a remoção irá executar a inserção se possível dos segmentos que estão na fila de pendentes
                if(!pendingQueue.isEmpty()) {
                    Segment pended[] = new Segment[pendingQueue.size()];
                    pended = pendingQueue.toArray(pended);
                    pendingQueue = new PriorityQueue<>();
                    for(Segment x: pended) {
                        Boolean inserted = put(x, false);
                        if(inserted)
                            System.out.println("Segmento " + x.segId + " que estava na lista de pendências foi inserido na memória");
                    }
                }
                
        }catch(Exception e){
     	   System.out.println(e.getMessage());
        }
     }
    /**
     * Método que executa a remoção do segmento
     * @param segId  id único do segmento.
     */
	public void remove(int segId) {
		for (int i = 0; i < mainMemory.length; i++)
			if (mainMemory[i] != null)
				if (mainMemory[i].segId == segId)
					mainMemory[i] = null;
		System.out.println("Segmento " + segId + " removido da memoria!");
	}
    
    /**
     * Método que printa a memória principal
     */
	public void printAllMain() {
		System.out.println("Memória Principal:");
		for (Segment x : mainMemory)
			if (x == null)
				System.out.printf("%s ", "null");
			else
				System.out.printf("%d ", x.segId);
		System.out.println();
	}
	
	/**
     * Método que printa a fila de pendência
     */
	public void printAllPending() {
		System.out.println("Fila de Pendências:");
		for (Segment x : pendingQueue)
			System.out.printf("%d ", x.segId);
		System.out.println();
	}

	/**
     * Método de verifica se o segmento se encontra na memória principal
     * @param segId id úncido do segmento
     * @return
     */
	private Boolean inMemory(int segId) {
		for (Segment x : mainMemory)
			if (x != null && x.segId == segId)
				return true;
		return false;
	}
}
