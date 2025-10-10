package model;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
    private List<Propriedade> propriedades;

    public Tabuleiro() {
        propriedades = new ArrayList<>();
        // adicionando 5 terrenos de exemplo
        propriedades.add(new Terreno("Av. Brasil", 100, 50));
        propriedades.add(new Terreno("Rua XV", 120, 60));
        propriedades.add(new Terreno("Av. Paulista", 150, 75));
        propriedades.add(new Terreno("Rua das Flores", 80, 40));
        propriedades.add(new Terreno("Av. Central", 200, 100));
    }

    public Propriedade getPropriedade(int indice) {
        return propriedades.get(indice);
    }

    public List<Propriedade> getPropriedades() {
        return propriedades;
    }
}
