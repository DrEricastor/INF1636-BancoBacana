package model;

public abstract class Propriedade {
    protected String nome;
    protected Jogador dono;
    protected int preco;

    public Propriedade(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
        this.dono = null;
    }

    public Jogador getDono() { return dono; }
    public void setDono(Jogador j) { this.dono = j; }
    public int getPreco() { return preco; }
    public abstract int calcularAluguel();
}
