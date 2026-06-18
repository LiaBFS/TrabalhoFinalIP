import java.util.Scanner;

public class TesteEstrutura {

    private int contaPartidas = 1; //pra só pedir p jogar novamente dps de ter finalizado 1 partida
    private int contaRounds = 1;
    private Scanner sc = new Scanner(System.in);
    private char corUser;
    private char corMaquina;
    private char tabuleiro[][] = new char[6][7];
    private int coluna;


    public TesteEstrutura() {
        int continuar = 1;

        do{
            if(contaPartidas>1){
                continuar = gerenciaPartidas();
                if(continuar==0){
                    break;
                }
            }
            
            gerarMatriz();
            partida();

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
                    contaPartidas++;
                    return 1;
                    
                } else if(Character.toString(resposta).equals("n")){
                    System.out.println("- Obrigada por jogar! - \nMerecemos um 10 né ;)");
                    return 0;
                } else{
                    System.out.println("- Resposta inválida - \nTente novamente:");
                }
            } while(true);
            
        } else{
            //ver oq fazer se não retornar 1 (ou seja, não dizer q acabou)
            //acho q só diz q vai pro próximo round (chama round(); talvez?)
        }

        return -1;

    }

    // retorna int pra fazer a checagem se finalizou partida (retorna 1) ou não (retorna 0)
    private int partida(){

        //deixar mais pra frente
        
        
        escolherCor();
        imprimirMatriz();
        int venceu = 0;

        do{
            System.out.println("- Rodada "+contaRounds+" -");

            jogadaUsuario();

            //fazer um if caso o atualizar matriz responder que já está cheio, para fazer o usuário jogar de novo
            checaVencedor();
            imprimirMatriz();
            jogadaMaquina();
            checaVencedor();
            imprimirMatriz();

            contaRounds++;
        }while(venceu==0);
        

        return 0;
    }

    //1 caso teve vencedor 0 caso n teve
    private int checaVencedor(){

        /** */
        return 0;
    }

    //vai pegar o vetor matriz do tabuleiro e colocar tudo B
    private void gerarMatriz(){

        for(int linha=0; linha<6; linha++){
            for(int coluna=0; coluna<7; coluna++){
                tabuleiro[linha][coluna] = 'B';
            }
        }

    }

    private void atualizarMatriz(int colunaJogada, char corJogada){

        /** pega a coluna da jogada, procura qual a primeira linha disponível e coloca a letra
            referente a cor do player na célula **/

        for(int linha=6; linha<=0;linha--){
            if(tabuleiro[linha][colunaJogada]=='B'){
                tabuleiro[linha][colunaJogada] = corJogada;
                break;
            } //vai ter que devolver que já está cheio
        }
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

        consultarMatriz();

        //vez do usuario
        do{
            System.out.print("Jogar peça na coluna: ");
            try{
                coluna = sc.nextInt();        
            } catch(Exception ex){
                System.out.println();
                System.out.println("- Resposta inválida - \nTente novamente:");
                ex.getMessage();
            }

            if(coluna<=7 || coluna>=1){
                break;
            } else{
                System.out.println();
                System.out.println("- Resposta inválida - \nTente novamente (Escolha um número de 1 a 7):");
            }
        } while(true);
        
        atualizarMatriz(coluna,corUser);

    }

    private void jogadaMaquina(){
        //MARIA

        atualizarMatriz(coluna, corMaquina);
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
