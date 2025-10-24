package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe principal que representa o modelo do jogo de Banco Imobiliário.
 * Gerencia jogadores, tabuleiro, regras de movimentação, compra, aluguel e construção.
 */
class BancoImobiliarioModel {
    /**
     * Lista de jogadores participantes da partida.
     */
    private List<Jogador> jogadores;

    /**
     * Tabuleiro do jogo, contendo todas as propriedades.
     */
    private Tabuleiro tabuleiro;

    private Baralho_SorteReves baralho;



    /**
     * Construtor do modelo do jogo.
     * Inicializa a lista de jogadores, o tabuleiro e define o primeiro jogador da vez.
     */
    public BancoImobiliarioModel() {
        jogadores = new ArrayList<>();
        tabuleiro = Tabuleiro.gerarTabuleiroPadrao();
        //baralho = new Baralho_SorteReves(new LinkedList<Carta_SorteReves>());
        
    }

    /**
     * Adiciona um novo jogador à partida.
     * Limite máximo de 6 jogadores.
     * @param nome Nome do jogador.
     */
    public void adicionarJogador(String nome) {
        if(jogadores.size() >= 6) 
            return;
        jogadores.add(new Jogador(nome));
    }

    /**
     * Inicia o jogo, atribuindo saldo inicial aos jogadores.
     * Exige pelo menos dois jogadores.
     */
    public void iniciarJogo() {
        if(jogadores.size() < 2) throw new IllegalStateException("Sao necessarios pelo menos dois jogadores para iniciar o jogo.");
        for(Jogador j : jogadores) j.setSaldo(4000); // saldo inicial equivalente às regras
    }

    /**
     * Simula o lançamento de dois dados de seis faces.
     * @return Soma dos valores dos dois dados (2 a 12).
     */
    public int[] lancarDados() {
        Random rand = new Random();
        int[] dados = new int[2];
        dados[0] = rand.nextInt(6)+1;
        dados[1] = rand.nextInt(6) + 1;
        return dados;
 
    }
    
    /**
     * Move o peão do jogador pelo tabuleiro.
     * @param jogador Jogador a ser movimentado.
     * @param casas Número de casas a avançar.
     */
    public void deslocarPiao(Jogador jogador, int casas) {
        int tamanhoTabuleiro = tabuleiro.getCampos().size();
        int novaPosicao = (jogador.getPosicao() + casas) % tamanhoTabuleiro;
        jogador.setPosicao(novaPosicao);
        Campo campoAtual = tabuleiro.getCampo(novaPosicao);

        campoAtual.efeitoCasa(jogador, baralho, jogadores, tabuleiro.getPosicaoPrisao(), casas);

        confereFalencias();
    }

    /**
     * Permite ao jogador comprar a propriedade onde está posicionado, se possível.
     * Verifica saldo, existência e se não há dono.
     * @param jogador Jogador que deseja comprar a propriedade.
     */
    public void comprarPropriedade(Jogador jogador) {
        Campo campoAtual = tabuleiro.getCampo(jogador.getPosicao());
        if (campoAtual instanceof Propriedade) {
            Propriedade prop = (Propriedade) campoAtual;
            if (prop != null && prop.getDono() == null) {
                if (jogador.getSaldo() >= prop.getPreco()) {
                    jogador.setSaldo(jogador.getSaldo() - prop.getPreco());
                    prop.setDono(jogador);
                }
            }
        }
    }

    /**
     * Permite ao jogador construir uma casa em um terreno que possua e tenha saldo suficiente.
     * @param jogador Jogador que deseja construir a casa.
     */
    public void construirCasa(Jogador jogador) {
        Campo campoAtual = tabuleiro.getCampo(jogador.getPosicao());
        if (campoAtual instanceof Terreno) {
            Terreno terreno = (Terreno) campoAtual;
            if (terreno.getDono() == jogador) {
                if (jogador.getSaldo() >= terreno.getPrecoCasa()) {
                    jogador.setSaldo(jogador.getSaldo() - terreno.getPrecoCasa());
                    terreno.construirCasa();
                }
            }
        }
    }

    public void confereFalencias() {
        for (Jogador j: jogadores) {
            if (j.getSaldo() < 0) {
                // Transferir propriedades para o banco
                for (Campo c : tabuleiro.getCampos()) {
                    if (c instanceof Propriedade) {
                        Propriedade p = (Propriedade) c;
                        if (p.getDono() == j) {
                            p.setDono(null); // Propriedade volta a não ter dono
                        }
                    }
                }
                jogadores.remove(j);
            }
        }
    }

    /**
     * Envia o jogador para a prisão.
     * @param j Jogador a ser preso.
     */
    public void enviarParaPrisao(Jogador j) {
        j.setPreso(true);
    }

    /**
     * Permite ao jogador sair da prisão pagando uma taxa.
     * @param j Jogador que deseja sair da prisão.
     */
    public void sairDaPrisaoPagando(Jogador j) {

        if(j.getSaldo() >= 50) {
            j.setSaldo(j.getSaldo() - 50);
            j.setPreso(false);
        }
    }

    public void tentarSairDaPrisao(Jogador j, int[] dados) {
    	if (dados[0] == dados[1]) {
    		j.setPreso(false);
    		deslocarPiao(j, dados[0]+dados[1]);
    	}
    	
    }
    
    public void sairUsandoPasseLivre(Jogador j) {
    	if (j.getQtdPasseLivre()> 0) {
    		int[] dados = lancarDados();
    		j.setQtdPasseLivre(j.getQtdPasseLivre()-1);
    		j.setPreso(false);
    		deslocarPiao(j, dados[0]+dados[1]);
    	}
    }
    /**
     * Retorna a lista de jogadores da partida.
     * @return Lista de jogadores.
     */
    public List<Jogador> getJogadores() {
        return jogadores;
    }

    /**
     * Retorna o tabuleiro do jogo.
     * @return Tabuleiro.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}