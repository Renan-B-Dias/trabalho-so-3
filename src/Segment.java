/**
 * Classe que representa um segmento
 */
public class Segment implements Comparable<Segment> {
	/**
	 * id único do segmento
	 */
    protected int segId;
    
    /**
     * tamanho do segmento 
     */
    protected int segSize;

    /**
     * Construtor da classe Segment
     * @param seg 
     * @param segSize
     */
    public Segment(int seg, int segSize) {
        this.segId = seg;
        this.segSize = segSize;
    }
    
    /**
     * Implementação da Interface do Java Comparable
     * @param seg 
     * @return
     */
    @Override
    public int compareTo(Segment seg) {
        if(this.segSize > seg.segSize)
            return 1;
        else if(this.segSize < seg.segSize)
            return -1;
        return 0;
    }
}
