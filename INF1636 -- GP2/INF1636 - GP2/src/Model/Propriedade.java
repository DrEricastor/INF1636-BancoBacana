package Model;

import java.util.List;

/**
 * Classe abstrata que representa uma propriedade no jogo de Banco Imobiliário.
 * Uma propriedade pode ser comprada por um jogador e possui um preço definido.
 * Subclasses devem implementar o cálculo do aluguel.
 */
abstract class Propriedade extends Campo {
    protected String nome;//nome da propriedade no tabuleiro
    protected Jogador dono; // jogador dono da propriedade (se for null, a propriedade n foi comprada)
    protected int preco; // preço de compra 


    /**
     * Construtor da propriedade.
     * @param nome Nome da propriedade.
     * @param preco Preço de compra da propriedade.
     */
    public Propriedade(String nome, int preco) {
        super(TipoCampo.TERRENO);
        this.nome = nome;
        this.preco = preco;
        this.dono = null;
    }

    @Override
    public void efeitoCasa(Jogador j, Baralho_SorteReves baralho, List<Jogador> jogadores, int posPrisao, int deslocamento) {
    if (dono != null && dono != j) {
        calcularAluguel(deslocamento);
        int valorAluguel = calcularAluguel(deslocamento);
        int dinheiroRemovido = j.atualizarSaldo(-valorAluguel);
        dono.atualizarSaldo(dinheiroRemovido);
    }
}

    /**
     * Retorna o jogador que é dono da propriedade.
     * @return Jogador dono da propriedade, ou null se não houver dono.
     */
    public Jogador getDono() { 
        return this.dono; 
    }


    /**
     * Define o jogador como dono da propriedade.
     * @param j Jogador que comprou a propriedade.
     */
    public void setDono(Jogador j) { 
        this.dono = j; 
    }

    /**
     * Retorna o preço de compra da propriedade.
     * @return Preço da propriedade.
     */
    public int getPreco() {
        return this.preco; 
    }

    /**
     * Calcula o valor do aluguel a ser pago quando um jogador cai nesta propriedade.
     * @return Valor do aluguel.
     */
    public int calcularAluguel(int deslocamento) {
        //Definimos o  valor do aluguel de uma propriedade qualquer como 50 temporariamente, apenas para testes
        return -50;
    }



    /**
     * Retorna uma representação textual da propriedade,
     * incluindo nome, dono e preço.
     * @return String representando a propriedade.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Propriedade{");
        sb.append("nome=").append(nome);
        sb.append(", dono=").append(dono);
        sb.append(", preco=").append(preco);
        sb.append('}');
        return sb.toString();
    }
}