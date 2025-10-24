package Model;
/**
 * Classe que representa um terreno no jogo de Banco Imobiliário.
 * Um terreno pode receber construções de casas, aumentando seu valor de aluguel.
 */
class Terreno extends Propriedade {
    private int numCasas; //numero de casas construidas
    private int precoCasa; //preço para construir uma casa



    /**
     * Construtor do terreno.
     * Inicializa o nome, preço do terreno e preço da casa.
     * O número de casas começa em zero.
     * @param nome Nome do terreno.
     * @param preco Preço de compra do terreno.
     * @param precoCasa Preço para construir uma casa.
     */
    public Terreno(String nome, int preco, int precoCasa) {
        super(nome, preco);
        this.numCasas = 0;
        this.precoCasa = precoCasa;
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
    public int calcularAluguel() {
        
        return 50;// usamos esse valor temporário como o cálculo do aluguel, se houver alguma casa construída.
    }

}