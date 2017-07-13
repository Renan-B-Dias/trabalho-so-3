import java.util.Random;

/**
 * 
 * 
 * @author Renan Bennati Dias
 * @author Jonathan Heidy Kinjo
 *
 */
public class Main {

	public static void main(String args[]) {

		/**
		 * Caso n�o haja argumentos suficiente uma mensagem de erro � mostrada
		 * para o usu�rio.
		 */
		if (args.length < 4) {
			System.out.println("Quantidade de argumentos faltando.");
			System.exit(1);
		}

		/**
		 * Pega o valor do seed.
		 */
		int seed = Integer.parseInt(args[0]);

		/**
		 * Pega o tamanho da main
		 */
		int main = Integer.parseInt(args[1]);

		/**
		 * Pega quantidade de solicita��es
		 */
		int nreq = Integer.parseInt(args[2]);

		/**
		 * Pega quantidade de segmentos
		 */
		int nseg = Integer.parseInt(args[3]);

		/**
		 * Array de Segment que receber� todos os Segmentos requisitado.
		 */
		Segment segmentReq[] = new Segment[nseg];

		/**
		 * Do-While que inst�ncia todos os segmentos
		 */
		int f = 0;
		int argsIndex = 4;
		do {
			int segId = Integer.parseInt(args[argsIndex++]);
			int segSize = Integer.parseInt(args[argsIndex++]);

			segmentReq[f++] = new Segment(segId, segSize);
		} while (argsIndex < args.length);

		
		/**
		 * Vari�vel que ir� gerar uma sequ�ncia aleat�ria a partir do seed fornecido
		 */
		Random rng = new Random(seed);
	
		/**
		 * Instancia��o da classe MainMemoryFirstFit, na qual ir� realizar a aloca��o first-fit
		 */
		MainMemoryFirstFit memFF = new MainMemoryFirstFit(main, segmentReq.length);
		
		/**
		 * C�digo que ir� gerar um boolean aleat�rio que definir� a opera��o na qual ser� realizada (true = inserir(put) ou false = remover(delSeg))
		 * Al�m de gerar um n�mero de segmento rand�mico que varia entre -1 <= n <= nseg, que define o segmento que ser� solicitado
		 */
		for (int i = 0; i < nreq; i++) {
			int aux = rng.nextInt(nseg+2)-1;//nseg aleat�rio
			if(rng.nextBoolean()){
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleat�rio for -1 ou >= nseg
					memFF.put(new Segment(aux, 0));
				}else{
					memFF.put(segmentReq[aux]);
				}
			}else{
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleat�rio for -1 ou >= nseg
					memFF.delSeg(new Segment(aux, 0));
				}else{
					memFF.delSeg(segmentReq[aux]);
				}
			}
		}
		
		/**
		 * M�todo que ir� printar a mem�ria principal
		 */
		memFF.printAllMain();
		
		/**
		 * M�todo que ir� printar os segmentos que est�o na fila de pen�ncia
		 */
		memFF.printAllPending();
		
		/**
		 * Vari�vel que ir� gerar uma sequ�ncia aleat�ria a partir do seed fornecido
		 */
		rng = new Random(seed);
		
		/**
		 * Instancia��o da classe MainMemoryBestFit, na qual ir� realizar a aloca��o best-fit
		 */
		MainMemoryBestFit memBF = new MainMemoryBestFit(main, segmentReq.length);
		
		/**
		 * C�digo que ir� gerar um boolean aleat�rio que definir� a opera��o na qual ser� realizada (true = inserir(put) ou false = remover(delSeg))
		 * Al�m de gerar um n�mero de segmento rand�mico que varia entre -1 <= n <= nseg, que define o segmento que ser� solicitado
		 */
		for (int i = 0; i < nreq; i++) {
			int aux = rng.nextInt(nseg+2)-1;//nseg aleat�rio
			if(rng.nextBoolean()){
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleat�rio for -1 ou >= nseg
					memBF.put(new Segment(aux, 0));
				}else{
					memBF.put(segmentReq[aux]);
				}
			}else{
				if(aux == -1 || aux >= nseg){
					memBF.delSeg(new Segment(aux, 0));
				}else{
					memBF.delSeg(segmentReq[aux]);
				}
			}
		}
		
		
		/**
		 * M�todo que ir� printar a mem�ria principal
		 */
		memBF.printAllMain();
		/**
		 * M�todo que ir� printar os segmentos que est�o na fila de pen�ncia
		 */
		memBF.printAllPending();
		

	}

}
