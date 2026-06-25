import java.util.Scanner;

public class TesteEstrutura {

    private int contaRounds = 1;
    private Scanner sc = new Scanner(System.in);
    private char corUser;
    private char corMaquina;
    private char tabuleiro[][] = new char[6][7];
    private int coluna;
    private int ultimaLinha, ultimaColuna;


    public TesteEstrutura() {
    
        do{
            gerarMatriz();
            partida();

            int continuar = gerenciaPartidas();
            if(continuar ==0){
                break;
            }
            contaRounds = 1;

        } while(true);

    }

    //retorna int pra saber se o usuário quer continuar (retorna 1) ou não (retorna 0)
    private int gerenciaPartidas(){

        int terminou = partida();

        if(terminou==1){

            char resposta=' ';

            do{
                System.out.print("Gostaria de iniciar um novo jogo? (s/n): ");
                try{
                    resposta = sc.next().toLowerCase().charAt(0);
                } catch(Exception ex){
                    System.out.println("- Resposta inválida - \nTente novamente:");
                    ex.getMessage();
                }
                
                if(Character.toString(resposta).equals("s")){
                    System.out.println("- Iniciando nova partida -");
                    return 1;
                    
                } else if(Character.toString(resposta).equals("n")){
                    System.out.println("- Obrigada por jogar! - \nMerecemos um 10 né ;)");
                    return 0;
                } else{
                    System.out.println("- Resposta inválida - \nTente novamente:");
                }
            } while(true);
            
        }
        return -1;
    }

    // retorna int pra fazer a checagem se finalizou partida (retorna 1)
    private int partida(){

        escolherCor();
        imprimirMatriz();
        int venceu = 0;

        do{
            
            System.out.println("##### Rodada "+contaRounds+" #####");
            
            jogadaUsuario();
            if(checaVencedor(ultimaLinha, ultimaColuna, corUser)){
                System.out.println("##### Fim do jogo #####");
                System.out.println("- Parabéns! Você venceu -");
                venceu =1;
                break;
            }
            jogadaMaquina();
            if(checaVencedor(ultimaLinha,ultimaColuna,corMaquina)){
                System.out.println("##### Fim do jogo #####");
                System.out.println("- Você perdeu -");
                venceu = 1;
                break;
            }

            if(contaRounds == 21){
                System.out.println("##### Fim do jogo #####");
                System.out.println("- Empate -");
                break;
            }

            contaRounds++;
        }while(venceu==0);
        
        return 1;
    }

    private boolean checaVencedor(int linha, int coluna, char cor){

    int x = linha;
    int y = coluna;
    //contador de peças da mesma cor juntas
    int cont = 1;

    for(int i = 1; i <= 4; i++){
        switch(i){
            case 1:
                // horizontal
                //esquerda
                // checa se ta na beirada e compara a próxima celula com a atual
                while(y-1 >= 0 && tabuleiro[x][y-1] == cor){
                    cont++;
                    y--;
                }
                if(cont >= 4){
                    return true;
                }
                //volta pra posição inicial
                x = linha;
                y = coluna;

                //direita
                while(y+1 <= 6 && tabuleiro[x][y+1] == cor){
                    cont++;
                    y++;
                }
                if(cont >= 4){
                     return true;
                }
                x = linha;
                y = coluna;
                //reseta o contador pq já foi dos 2 lados da horizontal
                cont = 1;
                break;

            case 2:
                // vertical
                //cima
                while(x-1 >= 0 && tabuleiro[x-1][y] == cor){
                    cont++;
                    x--;
                }
                if(cont >= 4){
                    return true;
                }
                x = linha;
                y = coluna;

                // baixo
                while(x+1 <= 5 && tabuleiro[x+1][y] == cor){
                    cont++;
                    x++;
                }
                if(cont >= 4){
                    return true;
                }

                x = linha;
                y = coluna;
                cont = 1;
                break;

            case 3:
                // diagonal /
                //fundo
                while(x-1 >= 0 && y+1 <= 6 && tabuleiro[x-1][y+1] == cor){
                    cont++;
                    x--;
                    y++;
                }
                if(cont >= 4){
                     return true;
                }
                x = linha;
                y = coluna;

                // diagonal /
                //topo
                while(x+1 <= 5 && y-1 >= 0 && tabuleiro[x+1][y-1] == cor){
                    cont++;
                    x++;
                    y--;
                }
                if(cont >= 4){
                    return true;
                }
                x = linha;
                y = coluna;
                cont = 1;
                break;

            case 4:
                // diagonal \
                //topo
                while(x-1 >= 0 && y-1 >= 0 && tabuleiro[x-1][y-1] == cor){
                    cont++;
                    x--;
                    y--;
                }
                if(cont >= 4){
                    return true;
                }
                x = linha;
                y = coluna;

                // diagonal \
                //fundo
                while(x+1 <= 5 && y+1 <= 6 && tabuleiro[x+1][y+1] == cor){
                    cont++;
                    x++;
                    y++;
                }
                if(cont >= 4){
                    return true;
                }
                x = linha;
                y= coluna;
                break;

            default:
                break;
        }
    }

    return false;
}

    //vai pegar o vetor matriz do tabuleiro e colocar tudo B
    private void gerarMatriz(){

        for(int linha=0; linha<6; linha++){
            for(int coluna=0; coluna<7; coluna++){
                tabuleiro[linha][coluna] = 'B';
            }
        }

    }

    private boolean atualizarMatriz(int colunaJogada, char corJogada){
        int col = colunaJogada - 1;

        for(int linha = 5; linha >= 0; linha--){
            if(tabuleiro[linha][col] == 'B'){
                tabuleiro[linha][col] = corJogada;
                ultimaLinha = linha;
                ultimaColuna = col;

                // se na jogadaMaquina o Math.random encontrar uma coluna válida mostrar essa msg
                if(Character.toString(corJogada).equalsIgnoreCase(Character.toString(corMaquina))){
                    System.out.println("Bot jogará peça na coluna: "+colunaJogada);
                }
                
                imprimirMatriz();
                return true;
            }
        }

        //só trazer a msg de aviso caso for o user, pq pra máquina n precisa
        if(Character.toString(corJogada).equalsIgnoreCase(Character.toString(corUser))){
            System.out.println("- Coluna cheia -\nTente novamente: ");
        }
        return false;
    }

    private void imprimirMatriz(){

        for(int linha=0; linha<6; linha++){
            for(int coluna=0; coluna<7; coluna++){
                System.out.print(tabuleiro[linha][coluna]+" ");
            }
            System.out.println(" ");
        }

        System.out.println();

    }

    private void jogadaUsuario(){

        System.out.println("- Sua vez! -");
        consultarMatriz();
        boolean jogadaValida = false;

        do{
        
            do{
                System.out.print("Jogar peça na coluna: ");
                try{
                    coluna = sc.nextInt();        
                } catch(Exception ex){
                    System.out.println();
                    System.out.println("- Resposta inválida - \nTente novamente:");
                    ex.getMessage();
                }

                if(coluna<=7 && coluna>=1){
                    break;
                } else{
                    System.out.println();
                    System.out.println("- Resposta inválida - \nTente novamente (Escolha um número de 1 a 7):");
                }
            } while(true);
        
            jogadaValida = atualizarMatriz(coluna,corUser);


        } while(!jogadaValida);
    }

    private void jogadaMaquina(){
        
        System.out.println("- Vez do Bot -");
        consultarMatriz();

        //pesquisei como usar Math.random com um intervalo e encontrei a fórmula 
        // int numero = (int) (Math.random() * (max - min + 1) + min);
        boolean jogadaValida = false;

        do{
            
            int coluna = (int) (Math.random() * (7 - 1 + 1)+1);
            jogadaValida = atualizarMatriz(coluna,corMaquina);


        } while(!jogadaValida);

         
    }

    //só p não ter q fazer validação toda vez
    private void consultarMatriz(){

        //pra imprimir o tabuleiro de novo antes de jogar
        char resposta;
        do{
            System.out.print("Deseja consultar o tabuleiro? (s/n): ");
            resposta = sc.next().toLowerCase().charAt(0);
            if(Character.toString(resposta).equals("s")){
                imprimirMatriz();
                break;
            } else if(Character.toString(resposta).equals("n")){
                break;
            } else{
                System.out.println();
                System.out.println("- Resposta inválida - \nTente novamente:");
            }
        } while(true);
        System.out.println(" ");

    }

    private void escolherCor(){
        //pedir p escolher entre V (vermelho) ou A (azul), a q não for escolhida pertence a maquina
        do{
            System.out.println("Qual cor você escolhe? \n- V (vermelho);\n- A (azul)");
            corUser = sc.next().toUpperCase().charAt(0);
            
            if(Character.toString(corUser).equals("V")){
                corMaquina = 'A';
                System.out.println("");
                break;

            } else if(Character.toString(corUser).equals("A")){
                corMaquina = 'V';
                System.out.println("");
                break;
                
            } else{
                System.out.println();
                System.out.println("- Resposta inválida - \nTente novamente:");
            }

        }while(true);

    }


    public static void main(String[] args) {
        TesteEstrutura classe = new TesteEstrutura();        
    }

}
