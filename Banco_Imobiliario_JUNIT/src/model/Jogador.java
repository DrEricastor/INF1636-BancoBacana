package model;

public class Jogador {
    private String nome;
    private int saldo;
    private boolean preso;
    private int posicao;

    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = 0;
        this.preso = false;
        this.posicao = 0;
    }
    
    public int getPosicao() { return posicao; }
    public void setPosicao(int posicao) { this.posicao = posicao; }

    public String getNome() { return nome; }
    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
    public boolean estaPreso() { return preso; }
    public void setPreso(boolean preso) { this.preso = preso; }
}
