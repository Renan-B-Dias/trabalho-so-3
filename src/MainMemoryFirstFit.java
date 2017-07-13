import java.util.*;

/**
 * Classe que simula uma mem�ria principal com aloca��o First-Fit
 * 
 */
public class MainMemoryFirstFit {
	
	/**
     * Mem�ria principal
     */
    private Segment[] mainMemory;
    
    /**
     * Quantidade de segmentos que ser�o ser colocadas na mem�ria principal
     */
    private int nSegments;
    
    /**
     * Fila dos segmentos que est�o em  pend�ncia.
     */
    private PriorityQueue<Segment> pendingQueue;

	/**
     * Construtor da classe MainMemoryFirstFit
     * @param mainBytes tamanho da memoria principal.
     * @param nSeg quantidade de segmentos que ir� receber.
     * @return
     */
    public MainMemoryFirstFit(int mainBytes, int nSeg) {
        mainMemory = new Segment[mainBytes];
        this.nSegments = nSeg;
        this.pendingQueue = new PriorityQueue<>();
    }

	/**
     * M�todo public de inser��o do segmento na memoria principal
     * @param seg Segmento.
     */
	public void put(Segment seg) {
		put(seg, true);
	}
	
	/**
     * M�todo private de  inser��o do segmento na memoria principal
     * @param seg Segmento
     * @param msg Boolean que ir� defenir se � uma inser��o da lista pendente ou de uma nova solicita��o
     * @return
     */
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

	/**
     * M�todo de deletar o segmento da mem�ria principal
     * @param seg Segmento.
     */
    public void delSeg(Segment seg) {
        try{
        	// Verifica��o das Exce��es
     	   if(seg.segId < 0 || seg.segId > nSegments)
     		   throw new Exception("N�mero de segmento " + seg.segId + " inv�lido!");
            else if(!inMemory(seg.segId))
         	   throw new Exception("Segmento " + seg.segId + " n�o est� na mem�ria");
            else
                remove(seg.segId); // Caso passe das Exce��es ir� remover o segmento
     	   		
     	   		// Ap�s a remo��o ir� executar a inser��o se poss�vel dos segmentos que est�o na fila de pendentes
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
    /**
     * M�todo que executa a remo��o do segmento
     * @param segId  id �nico do segmento.
     */
	public void remove(int segId) {
		for (int i = 0; i < mainMemory.length; i++)
			if (mainMemory[i] != null)
				if (mainMemory[i].segId == segId)
					mainMemory[i] = null;
		System.out.println("Segmento " + segId + " removido da memoria!");
	}
    
    /**
     * M�todo que printa a mem�ria principal
     */
	public void printAllMain() {
		System.out.println("Mem�ria Principal:");
		for (Segment x : mainMemory)
			if (x == null)
				System.out.printf("%s ", "null");
			else
				System.out.printf("%d ", x.segId);
		System.out.println();
	}
	
	/**
     * M�todo que printa a fila de pend�ncia
     */
	public void printAllPending() {
		System.out.println("Fila de Pend�ncias:");
		for (Segment x : pendingQueue)
			System.out.printf("%d ", x.segId);
		System.out.println();
	}

	/**
     * M�todo de verifica se o segmento se encontra na mem�ria principal
     * @param segId id �ncido do segmento
     * @return
     */
	private Boolean inMemory(int segId) {
		for (Segment x : mainMemory)
			if (x != null && x.segId == segId)
				return true;
		return false;
	}
}
