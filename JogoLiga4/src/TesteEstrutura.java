import java.util.Scanner;

public class TesteEstrutura {

    private int contaPartidas = 1; //pra só pedir p jogar novamente dps de ter finalizado 1 partida
    private int contaRounds = 1; //pra só checar se ganhou dps do 4° round
    private Scanner sc = new Scanner(System.in);
    private char corUser;
    private char corMaquina;
    private char tabuleiro[][] = new char[6][7];


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

            char resposta;

            do{
                System.out.print("Gostaria de iniciar um novo jogo? (s/n): ");
                resposta = sc.next().toLowerCase().charAt(0);
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
        }

        return -1;

    }

    // retorna int pra fazer a checagem se finalizou partida (retorna 1) ou não (retorna 0)
    private int partida(){
        
        escolherCor();
        imprimirMatriz();
        round();

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

    private void atualizarMatriz(int coluna){

        /** pega a coluna da jogada, procura qual a primeira linha disponível e coloca a letra
            referente a cor do player da célula **/

    }

    private void imprimirMatriz(){

        for(int linha=0; linha<6; linha++){
            for(int coluna=0; coluna<7; coluna++){
                System.out.println(tabuleiro[linha][coluna]+" ");
            }
            System.out.println(" ");
        }

    }

    private void round(){
        //usuário
        System.out.println("- Rodada "+contaRounds+" -");

        consultarMatriz();
        
        System.out.println("Deseja jogar em qual coluna?");
        try{
            
        } catch(Exception ex){

        }

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
                break;
            } else if(Character.toString(corUser).equals("A")){
                corMaquina = 'V';
                break;
            } else{
                System.out.println("- Resposta inválida - \nTente novamente:");
            }

        }while(true);

    }


    public static void main(String[] args) {
        TesteEstrutura classe = new TesteEstrutura();        
    }

}
