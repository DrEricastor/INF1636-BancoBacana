package Model;

/**
 * Classe que representa um jogador no jogo de Banco Imobiliário.
 * O jogador possui nome, saldo, posição no tabuleiro e estado de prisão.
 */
class Jogador {
    private String nome; // nome do jogador
    private int saldo;// saldo atual do jogador
    private boolean preso; // indica se o jogador está preso no momento
    private int posicao; // posição atual do jogador no tabuleiro (0-39)
    private int qtd_passe_livre; // quantidade de passes livres do jogador
    /**
     * Construtor do jogador.
     * Inicializa o nome, saldo inicial, estado de prisão e posição inicial.
     * @param nome Nome do jogador.
     */
    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 4000;
        this.preso = false;
        this.posicao = 0;
        this.qtd_passe_livre = 0;
    }

    public int atualizarSaldo(int valor) {
        this.saldo += valor;
        return saldo;
    }
    
    
    public int getQtdPasseLivre() {
    	return this.qtd_passe_livre;
    }
    
    public void setQtdPasseLivre(int qtd) {
    	this.qtd_passe_livre = qtd;
    }
    
    /**
     * Retorna a posição atual do jogador no tabuleiro.
     * @return Posição do jogador.
     */
    public int getPosicao() { 
        return posicao; 
    }

    /**
     * Define a posição do jogador no tabuleiro.
     * @param posicao Nova posição do jogador.
     */
    public void setPosicao(int posicao) { 
        this.posicao = posicao; 
    }

    /**
     * Retorna o nome do jogador.
     * @return Nome do jogador.
     */
    public String getNome() { 
        return nome; 
    }

    /**
     * Retorna o saldo atual do jogador.
     * @return Saldo do jogador.
     */
    public int getSaldo() { 
        return saldo; 
    }

    /**
     * Define o saldo do jogador.
     * @param saldo Novo saldo do jogador.
     */
    public void setSaldo(int saldo) { 
        this.saldo = saldo; 
    }

    /**
     * Verifica se o jogador está preso.
     * @return true se estiver preso, false caso contrário.
     */
    public boolean estaPreso() { 
        return preso; 
    }

    /**
     * Define o estado de prisão do jogador.
     * @param preso true para preso, false para livre.
     */
    public void setPreso(boolean preso) { 
        this.preso = preso; 
    }
}