package Model;

class Companhia extends Propriedade {
    /**
     * multiplicador: Fator que multiplica o valor do dado para calcular o aluguel
     * quando um jogador cai nesta companhia. Cada companhia pode ter um multiplicador diferente.
     */
    private int multiplicador;


    /**
     * Construtor da Companhia.
     * @param nome Nome da companhia no tabuleiro.
     * @param preco Preço de compra da companhia.
     * @param multiplicador Fator usado para calcular o aluguel.
     */
    public Companhia(String nome, int preco, int multiplicador) {
        super(nome, preco);
        this.multiplicador = multiplicador;
    }


    /**
     * Calcula o valor do aluguel quando um jogador cai nesta companhia.
     * O valor é determinado pelo resultado de um dado multiplicado pelo multiplicador da companhia.
     * @return Valor do aluguel a ser pago.
     */
    public int calcularAluguel(int[] dados) {
        int valorDado = dados[0] + dados[1];
        return valorDado * multiplicador;
    }
}