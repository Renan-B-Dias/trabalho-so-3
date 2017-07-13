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
		 * Caso não haja argumentos suficiente uma mensagem de erro é mostrada
		 * para o usuário.
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
		 * Pega quantidade de solicitações
		 */
		int nreq = Integer.parseInt(args[2]);

		/**
		 * Pega quantidade de segmentos
		 */
		int nseg = Integer.parseInt(args[3]);

		/**
		 * Array de Segment que receberá todos os Segmentos requisitado.
		 */
		Segment segmentReq[] = new Segment[nseg];

		/**
		 * Do-While que instância todos os segmentos
		 */
		int f = 0;
		int argsIndex = 4;
		do {
			int segId = Integer.parseInt(args[argsIndex++]);
			int segSize = Integer.parseInt(args[argsIndex++]);

			segmentReq[f++] = new Segment(segId, segSize);
		} while (argsIndex < args.length);

		
		/**
		 * Variável que irá gerar uma sequência aleatória a partir do seed fornecido
		 */
		Random rng = new Random(seed);
	
		/**
		 * Instanciação da classe MainMemoryFirstFit, na qual irá realizar a alocação first-fit
		 */
		MainMemoryFirstFit memFF = new MainMemoryFirstFit(main, segmentReq.length);
		
		/**
		 * Código que irá gerar um boolean aleatório que definirá a operação na qual será realizada (true = inserir(put) ou false = remover(delSeg))
		 * Além de gerar um número de segmento randômico que varia entre -1 <= n <= nseg, que define o segmento que será solicitado
		 */
		for (int i = 0; i < nreq; i++) {
			int aux = rng.nextInt(nseg+2)-1;//nseg aleatório
			if(rng.nextBoolean()){
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleatório for -1 ou >= nseg
					memFF.put(new Segment(aux, 0));
				}else{
					memFF.put(segmentReq[aux]);
				}
			}else{
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleatório for -1 ou >= nseg
					memFF.delSeg(new Segment(aux, 0));
				}else{
					memFF.delSeg(segmentReq[aux]);
				}
			}
		}
		
		/**
		 * Método que irá printar a memória principal
		 */
		memFF.printAllMain();
		
		/**
		 * Método que irá printar os segmentos que estão na fila de penência
		 */
		memFF.printAllPending();
		
		/**
		 * Variável que irá gerar uma sequência aleatória a partir do seed fornecido
		 */
		rng = new Random(seed);
		
		/**
		 * Instanciação da classe MainMemoryBestFit, na qual irá realizar a alocação best-fit
		 */
		MainMemoryBestFit memBF = new MainMemoryBestFit(main, segmentReq.length);
		
		/**
		 * Código que irá gerar um boolean aleatório que definirá a operação na qual será realizada (true = inserir(put) ou false = remover(delSeg))
		 * Além de gerar um número de segmento randômico que varia entre -1 <= n <= nseg, que define o segmento que será solicitado
		 */
		for (int i = 0; i < nreq; i++) {
			int aux = rng.nextInt(nseg+2)-1;//nseg aleatório
			if(rng.nextBoolean()){
				if(aux == -1 || aux >= nseg){//condicional que cuida do caso o nseg aleatório for -1 ou >= nseg
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
		 * Método que irá printar a memória principal
		 */
		memBF.printAllMain();
		/**
		 * Método que irá printar os segmentos que estão na fila de penência
		 */
		memBF.printAllPending();
		

	}

}
