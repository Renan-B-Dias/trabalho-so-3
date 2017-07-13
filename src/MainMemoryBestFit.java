import java.util.*;

/**
 * Classe que simula uma mem�ria principal com aloca��o Best-Fit
 */
public class MainMemoryBestFit {

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
     * Construtor da classe MainMemoryBestFit
     * @param mainBytes tamanho da memoria principal.
     * @param nSeg quantidade de segmentos que ir� receber.
     * @return
     */
	public MainMemoryBestFit(int mainBytes,  int nSeg) {
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
			// Verifica��o das Exce��es
			if (seg.segId < 0 || seg.segId > nSegments - 1) {
				throw new Exception ("Segmento " + seg.segId + " invalido!");
			}
			else if (inMemory(seg.segId)) {
				throw new Exception ("Segmento " + seg.segId + " ja esta na memoria!");
			} else {
				// Caso passe das Exce��es ir� inserir o segmento
				int count = 0;//contador de quantos bytes est�o dispin�vel na mem�ria principal
				ArrayList<SegmentControll> array = new ArrayList<>();// ir� guardar todos os espa�os poss�veis na memoria principal
				
				//La�o para contar os espa�os e guardar no array
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
				//Caso nao tenha espa�o disponivel na memoria principal o segmento � guardado na fila de pend�ncia
				if (array.isEmpty()) {
					pendingQueue.add(seg);
					return false;
				}else {//Como o array de espa�o est� organizado em ordem crescente, ir� resgatar a primeira posi��o pois � a menor poss�vel espa�o para assim inserir o segmente nele.
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
		for (Segment x : mainMemory)
			if (x == null)
				System.out.printf("%s ", "null");
			else
				System.out.printf("%d ", x.segId);
	}
	
	/**
     * M�todo que printa a fila de pend�ncia
     */
	public void printAllPending() {
		for (Segment x : pendingQueue)
			System.out.printf("%d ", x.segId);
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
