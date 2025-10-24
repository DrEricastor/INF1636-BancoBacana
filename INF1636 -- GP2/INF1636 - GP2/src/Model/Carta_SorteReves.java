package Model;

import java.util.List;

class Carta_SorteReves {
    /*
     * A grande maioria das cartas de Sorte/Reves envolvem perder ou ganhar dinheiro, as poucas exceções sao:
     * 1. uma carta que te leva ao ponto de partida, e te faz ganhar 200. (movimentar naturalmente o jogador para ele receber os 200 do ponto de partida)
     * 2. uma carta que te leva para a cadeia. (setar a posicao do jogador pra cadeia pra ele nao passar pelo ponto de partida)
     * 3. carta de saída livre da cadeia. (aumentar o int registrado no Jogador pra isso)
     * 4. carta de aposta com os outros jogadores, todo mundo perde 50 e o jogador ganha o total.
     * 
     */
    private String texto;
    private int efeitoMonetario; //positivo se ganhar dinheiro, negativo se perder dinheiro
    private int cartaDiferente; //0 se for carta normal, 1 se for ir para cadeia, 2 se for ir para ponto de partida, 3 se for sair da cadeia, 4 se for aposta
    public Carta_SorteReves(String texto, int efeitoMonetario, int cartaDiferente) {
        this.texto = texto;
        this.efeitoMonetario = efeitoMonetario;
        this.cartaDiferente = cartaDiferente;
    }

    public void afetar(Jogador j, List<Jogador> jogadores) {
        switch (cartaDiferente) {
            case 0:
                j.setSaldo(j.getSaldo() + efeitoMonetario);
                break;
            case 1:
                j.setPosicao(10); //10 é um valor MOCK, posição da cadeia
                j.setPreso(true);
                break;
            case 2:
                j.setPosicao(0); //posição do ponto de partida
                j.setSaldo(j.getSaldo() + 200);; //ganha 200 ao passar pelo ponto de partida
                break;
            case 3:
                j.setQtdPasseLivre(j.getQtdPasseLivre()+1);
                break;
            case 4:
                int totalPerdido = 0;
                for (Jogador outroJogador : jogadores) {
                    if (outroJogador != j) {
                        outroJogador.atualizarSaldo(-50);
                        totalPerdido += 50;
                    }
                }
                j.atualizarSaldo(totalPerdido);
                break;
        }
    }



    
    
    
    //GETTERS E SETTERS
    public int getEfeitoMonetario() {
        return efeitoMonetario;
    }
    public String getTexto() {
        return texto;
    }
    public int getCartaDiferente() {
        return cartaDiferente;
    }

    public void setEfeitoMonetario(int efeitoMonetario) {
        this.efeitoMonetario = efeitoMonetario;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public void setCartaDiferente(int cartaDiferente) {
        this.cartaDiferente = cartaDiferente;
    }

}