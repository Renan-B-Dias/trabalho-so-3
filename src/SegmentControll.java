/**
 * Classe que gerencia os espaços da memória principal de alocação BestFit.
 */
public class SegmentControll implements Comparable<SegmentControll> {
	/**
	 * Guarda a posição inicial do intervalo que o espaço esta disponível
	 */
	protected int begin;
	/**
	 * Guarda a posição final do intervalo que o espaço está disponível
	 */
	protected int end;
	/**
	 * Guarda o tamanho deste intervalo de espaços disponível
	 */
	protected int size;

	/**
	 * Construtor da classe
	 * @param begin 
	 * @param end
	 */
	public SegmentControll(int begin, int end) {
		this.begin = begin;
		this.end = end;
		size = end - begin;
	}
	
	 /**
     * Implementação da Interface do Java Comparable
     * @param seg 
     * @return
     */
	@Override
	public int compareTo(SegmentControll seg) {
		if (this.size > seg.size)
			return 1;
		else if (this.size < seg.size)
			return -1;
		return 0;
	}

}
