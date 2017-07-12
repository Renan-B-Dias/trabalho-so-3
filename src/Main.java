

/**
 * Created by renan on 10/07/2017.
 */
public class Main {

    public static void main(String args[]) {
    	
    	//Caso não haja argumentos suficiente uma mensagem de erro é mostrada para o usuário.
        if(args.length < 4) {
            System.out.println("Quantidade de argumentos faltando.");
            System.exit(1);
        }
        
        // Pega o valor do seed.
        int seed = Integer.parseInt(args[0]);
        
        // Pega o tamanho da main
        int main = Integer.parseInt(args[1]);
        
        // Pega quantidade de solicitações
        int nreq = Integer.parseInt(args[2]);

        //Pega quantidade de segmentos
        int nseg = Integer.parseInt(args[3]);
        
        //Array de Segment que receberá todos os Segmentos requisitado.
        Segment segmentReq[] = new Segment[nseg];
        
        //Do-While que instância todos os segmentos
        int f = 0;
        int argsIndex = 4;
        do {
            int segId = Integer.parseInt(args[argsIndex++]);
            int segSize = Integer.parseInt(args[argsIndex++]);

            segmentReq[f++] = new Segment(segId, segSize);
        } while(argsIndex < args.length);

        MainMemoryFirstFit mem = new MainMemoryFirstFit(main);

        mem.put(segmentReq[0]);
        mem.put(segmentReq[1]);
        mem.put(segmentReq[2]);
        mem.put(segmentReq[3]);
        mem.put(segmentReq[2]);
        mem.put(segmentReq[4]);
        mem.put(new Segment(-1, 4));
        
        mem.printAllMain();
        System.out.println();
        mem.printAllPending();
        System.out.println();

        mem.delSeg(segmentReq[1]);
        mem.delSeg(segmentReq[2]);

        mem.printAllMain();
        System.out.println();
        mem.printAllPending();
        System.out.println();

    }

}
