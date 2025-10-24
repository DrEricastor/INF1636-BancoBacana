package Model;

import java.util.LinkedList;

public class Baralho_SorteReves {
    private LinkedList<Carta_SorteReves> cartas;

    public Baralho_SorteReves(LinkedList<Carta_SorteReves> cartas) {
        this.cartas = cartas;
    }

    public Carta_SorteReves puxarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.removeFirst();
    }
}
