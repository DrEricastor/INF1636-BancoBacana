package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BancoImobiliarioModel {
    private List<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private int jogadorDaVez;

    public BancoImobiliarioModel() {
        jogadores = new ArrayList<>();
        tabuleiro = new Tabuleiro();
        jogadorDaVez = 0;
    }

    public void adicionarJogador(String nome) {
        if(jogadores.size() >= 6) return;
        jogadores.add(new Jogador(nome));
    }

    public void iniciarJogo() {
        if(jogadores.size() < 2) throw new IllegalStateException("Sao necessarios pelo menos dois jogadores para iniciar o jogo.");
        for(Jogador j : jogadores) j.setSaldo(1500); // saldo inicial equivalente �s regras
    }

    public int lancarDados() {
        Random rand = new Random();
        return rand.nextInt(6) + 1 + rand.nextInt(6) + 1; // 2 a 12
    }
    
    public void deslocarPiao(Jogador jogador, int casas) {
        int tamanhoTabuleiro = tabuleiro.getPropriedades().size();
        int novaPosicao = (jogador.getPosicao() + casas) % tamanhoTabuleiro;
        jogador.setPosicao(novaPosicao);
    }

    public void comprarPropriedade(Jogador jogador) {
        // 1. Descobre a propriedade usando a posi��o do jogador
        Propriedade prop = tabuleiro.getPropriedade(jogador.getPosicao());

        // 2. Verifica se a propriedade � compr�vel (existe, n�o � uma casa especial e n�o tem dono)
        if (prop != null && prop.getDono() == null) {

            // 3. Verifica se o jogador tem saldo suficiente
            if (jogador.getSaldo() >= prop.getPreco()) {

                // 4. Realiza a transa��o
                jogador.setSaldo(jogador.getSaldo() - prop.getPreco());
                prop.setDono(jogador);
            }
        }
    }

    public void construirCasa(Jogador jogador) {
        // 1. Pega a propriedade na posi��o do jogador
        Propriedade prop = tabuleiro.getPropriedade(jogador.getPosicao());

        // 2. Verifica se � um Terreno (e n�o uma Companhia, etc.)
        if (prop instanceof Terreno) {
            Terreno terreno = (Terreno) prop;

            // 3. Verifica se o terreno pertence ao jogador
            if (terreno.getDono() == jogador) {

                // 4. Verifica se o jogador tem saldo para a casa
                if (jogador.getSaldo() >= terreno.getPrecoCasa()) {

                    // 5. Realiza a constru��o e o d�bito
                    jogador.setSaldo(jogador.getSaldo() - terreno.getPrecoCasa());
                    terreno.construirCasa();
                }
            }
        }
    }

    public void pagarAluguel(Jogador pagador) {
        // 1. Encontra a propriedade usando a posi��o do jogador
        Propriedade prop = tabuleiro.getPropriedade(pagador.getPosicao());

        // 2. Verifica se a propriedade existe e tem um dono que n�o seja o pr�prio jogador
        if (prop != null && prop.getDono() != null && prop.getDono() != pagador) {
            int aluguel = prop.calcularAluguel();

            // 3. Verifica se h� aluguel a ser pago (respeitando a regra de "pelo menos 1 casa")
            if (aluguel > 0) {
                if (pagador.getSaldo() >= aluguel) {
                    // Pagamento normal
                    pagador.setSaldo(pagador.getSaldo() - aluguel);
                    prop.getDono().setSaldo(prop.getDono().getSaldo() + aluguel);
                } else {
                    // L�gica de Fal�ncia
                    prop.getDono().setSaldo(prop.getDono().getSaldo() + pagador.getSaldo()); // Paga o que resta
                    pagador.setSaldo(0);
                    jogadores.remove(pagador);
                }
            }
        }
    }

    public void enviarParaPrisao(Jogador j) {
        j.setPreso(true);
    }

    public void sairDaPrisao(Jogador j) {
        if(j.getSaldo() >= 50) {
            j.setSaldo(j.getSaldo() - 50);
            j.setPreso(false);
        }
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    
}
