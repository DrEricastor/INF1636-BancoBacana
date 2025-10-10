package model;

public class Terreno extends Propriedade {
    private int numCasas;
    private int precoCasa;

    public Terreno(String nome, int preco, int precoCasa) {
        super(nome, preco);
        this.numCasas = 0;
        this.precoCasa = precoCasa;
    }
    

    public void construirCasa() { numCasas++; }
    
    public int getNumCasas() { return numCasas; }
    public void setNumCasas(int numCasas) {
        if (numCasas >= 0 && numCasas <= 4) {
            this.numCasas = numCasas;
        }
    }
    
    public int getPrecoCasa() { return precoCasa; }

    @Override
    public int calcularAluguel() {
        if (this.numCasas == 0) {
            // Conforme o requisito 5, não há aluguel se não houver pelo menos uma casa.
            return 0;
        }
        // Se houver casas, usamos essa lógica temporária para implementar um preço
        return this.preco * (this.numCasas + 1);
    }

}
