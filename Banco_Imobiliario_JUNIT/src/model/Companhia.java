package model;

class Companhia extends Propriedade {
    private int multiplicador;

    public Companhia(String nome, int preco, int multiplicador) {
        super(nome, preco);
        this.multiplicador = multiplicador;
    }

    @Override
    public int calcularAluguel() {
        int valorDado = new java.util.Random().nextInt(6) + 1;
        return valorDado * multiplicador;
    }
}