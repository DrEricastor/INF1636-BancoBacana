package Model;

import java.util.Collections;
import java.util.LinkedList;

import Model.Carta_SorteReves.TiposCarta;

public class Baralho_SorteReves {
    private LinkedList<Carta_SorteReves> cartas;

    public Baralho_SorteReves(LinkedList<Carta_SorteReves> cartas) {
        this.cartas = cartas;
    }

    public static Baralho_SorteReves gerarBaralhoPadrao() {
        LinkedList<Carta_SorteReves> baralho = new LinkedList<Carta_SorteReves>();
        baralho.add(Carta_SorteReves.criarCarta(
                        "--SORTE--\r\n" + //
                        "A prefeitura mandou abrir uma nova avenida, para o que desapropriou vários prédios. Em consequência seu terreno valorizou.\r\n" + //
                        "Receba 25", 25, TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(
                        "--SORTE--\r\n" + //
                        "Houve um assalto à sua loja, mas você estava segurado. \r\n" + //
                        "Receba 150", 150, TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(
                        "--SORTE--\r\n" + //
                        "Um amigo tinha he pedido um empréstimo e se esqueceu de devolver. Ele acaba de se lembrar. \r\n" + //
                        "Receba 80", 80, TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(
                        "--SORTE--\r\n" + //
                        "Você está com sorte. Suas açoes na Bolsa de Valores estão em alta. \r\n" + //
                        "Receba 200", 200, TiposCarta.NORMAL));

        baralho.add(Carta_SorteReves.criarCarta(null,50,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,50,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,0,TiposCarta.SAIR_CADEIA));
        baralho.add(Carta_SorteReves.criarCarta(null,0,TiposCarta.IR_PARA_PARTIDA));
        baralho.add(Carta_SorteReves.criarCarta(null,0,TiposCarta.APOSTA));
        baralho.add(Carta_SorteReves.criarCarta(null,45,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,20,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -15,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -25,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -45,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -30,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -100,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -40,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null,0,TiposCarta.IR_PARA_CADEIA));
        baralho.add(Carta_SorteReves.criarCarta(null, -30,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -50,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -25,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -30,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -45,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -50,TiposCarta.NORMAL));
        baralho.add(Carta_SorteReves.criarCarta(null, -50,TiposCarta.NORMAL));
                



        Collections.shuffle(baralho);
        return new Baralho_SorteReves(baralho);
    }

    public Carta_SorteReves puxarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.removeFirst();
    }
}
