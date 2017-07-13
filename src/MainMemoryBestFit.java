import java.util.*;

/**
 * Classe que simula uma memória principal com alocação Best-Fit
 */
public class MainMemoryBestFit {

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
     * Construtor da classe MainMemoryBestFit
     * @param mainBytes tamanho da memoria principal.
     * @param nSeg quantidade de segmentos que irá receber.
     * @return
     */
	public MainMemoryBestFit(int mainBytes,  int nSeg) {
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
			// Verificação das Exceções
			if (seg.segId < 0 || seg.segId > nSegments - 1) {
				throw new Exception ("Segmento " + seg.segId + " invalido!");
			}
			else if (inMemory(seg.segId)) {
				throw new Exception ("Segmento " + seg.segId + " ja esta na memoria!");
			} else {
				// Caso passe das Exceções irá inserir o segmento
				int count = 0;//contador de quantos bytes estão dispinível na memória principal
				ArrayList<SegmentControll> array = new ArrayList<>();// irá guardar todos os espaços possíveis na memoria principal
				
				//Laço para contar os espaços e guardar no array
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
				//Caso nao tenha espaço disponivel na memoria principal o segmento é guardado na fila de pendência
				if (array.isEmpty()) {
					pendingQueue.add(seg);
					return false;
				}else {//Como o array de espaço está organizado em ordem crescente, irá resgatar a primeira posição pois é a menor possível espaço para assim inserir o segmente nele.
					Collections.sort(array);
					SegmentControll least = array.get(0);
					int begin = least.begin;
					int siz = seg.segSize;
					for (int f = 0; f < siz; f++, begin++) {
						mainMemory[begin] = seg;
					}
					 if(msg)
		                    System.out.println("Segmento " + seg.segId + " foi inserido na memória!");
		             return true;
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
		for (Segment x : mainMemory)
			if (x == null)
				System.out.printf("%s ", "null");
			else
				System.out.printf("%d ", x.segId);
	}
	
	/**
     * Método que printa a fila de pendência
     */
	public void printAllPending() {
		for (Segment x : pendingQueue)
			System.out.printf("%d ", x.segId);
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
