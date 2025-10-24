package Model;
/**
 * Classe que representa um terreno no jogo de Banco Imobiliário.
 * Um terreno pode receber construções de casas, aumentando seu valor de aluguel.
 */
class Terreno extends Propriedade {
    private int numCasas; //numero de casas construidas
    private int precoCasa; //preço para construir uma casa
    private int[] alugueis; //array contendo os diferentes valores do aluguel de um terreno, dependendo do número de casas construídas




    /**
     * Construtor do terreno.
     * Inicializa o nome, preço do terreno e preço da casa.
     * O número de casas começa em zero.
     * @param nome Nome do terreno.
     * @param preco Preço de compra do terreno.
     * @param precoCasa Preço para construir uma casa.
     * @param alugueis os 6 valores de aluguel do terreno, dependendo do número de casas construídas (0 a 4) e hotel.
     */
    public Terreno(String nome, int preco, int precoCasa, int[] alugueis) {
        super(nome, preco);
        this.numCasas = 0;
        this.precoCasa = precoCasa;
        this.alugueis = alugueis;
    }
    
    /**
     * Constrói uma casa no terreno, aumentando o número de casas em 1.
     */
    public void construirCasa() { 
        this.numCasas++; 
    }


    /**
     * Retorna o número de casas construídas neste terreno.
     * @return Número de casas.
     */
    public int getNumCasas() { 
        return this.numCasas;
    }

    /**
     * Define o número de casas construídas no terreno.
     * Só permite valores entre 0 e 4.
     * @param numCasas Novo número de casas.
     */
    public void setNumCasas(int numCasas) {
        if (numCasas >= 0 && numCasas <= 4) {
            this.numCasas = numCasas;
        }
    }
    
    /**
     * Retorna o preço para construir uma casa neste terreno.
     * @return Preço da casa.
     */
    public int getPrecoCasa() { 
        return this.precoCasa; 
    }

    
    /**
     * Calcula o valor do aluguel do terreno.
     * Atualmente retorna um valor fixo (implementação temporária).
     * @return Valor do aluguel.
     */
    @Override
    public int calcularAluguel(int deslocamento) {
        return alugueis[numCasas];
    }

}