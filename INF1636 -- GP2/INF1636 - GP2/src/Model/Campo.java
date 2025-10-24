package Model;

import java.util.List;

class Campo {
    public enum TipoCampo {
        TERRENO,            // 0: propriedade ou companhia
        PONTO_PARTIDA,      // 1: ponto de partida
        PRISAO,             // 2: prisao
        VAI_PARA_PRISAO,    // 3: vai pra prisao
        ESTACIONAMENTO,     // 4: estacionamento gratuito
        SORTE_REVES,        // 5: sorte/reves
        PAGAR_IMPOSTO,      // 6: pagar imposto
        RECEBER_DINHEIRO    // 7: receber dinheiro
    }

    TipoCampo tipo; 

    public Campo(TipoCampo tipo) {
        this.tipo = tipo;
    }

    public void efeitoCasa(Jogador j, Baralho_SorteReves baralho, List<Jogador> jogadores, int posPrisao, int deslocamento) {
        switch(tipo) {
            case PONTO_PARTIDA:
                j.atualizarSaldo(200); // jogador recebe 200 ao passar pelo ponto de partida
                break;
            case VAI_PARA_PRISAO:
                j.setPosicao(posPrisao);
                j.setPreso(true);
                break;
            case SORTE_REVES:
                baralho.puxarCarta().afetar(j, jogadores);
                break;
            case PAGAR_IMPOSTO:
                j.atualizarSaldo(-200);
                break;
            case RECEBER_DINHEIRO:
                j.atualizarSaldo(200);
                break;
            default:
                // terrenos ou companhias, prisao, e estacionamento nao tem efeito direto aqui
                break;
        }

    }
}
