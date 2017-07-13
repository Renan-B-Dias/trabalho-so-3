/**
 * Classe que gerencia os espa�os da mem�ria principal de aloca��o BestFit.
 */
public class SegmentControll implements Comparable<SegmentControll> {
	/**
	 * Guarda a posi��o inicial do intervalo que o espa�o esta dispon�vel
	 */
	protected int begin;
	/**
	 * Guarda a posi��o final do intervalo que o espa�o est� dispon�vel
	 */
	protected int end;
	/**
	 * Guarda o tamanho deste intervalo de espa�os dispon�vel
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
     * Implementa��o da Interface do Java Comparable
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
