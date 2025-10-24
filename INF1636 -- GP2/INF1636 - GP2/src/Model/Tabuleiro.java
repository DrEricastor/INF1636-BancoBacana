package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o tabuleiro do jogo de Banco Imobiliário.
 * O tabuleiro contém uma lista de propriedades disponíveis para compra e interação dos jogadores.
 */
class Tabuleiro {
    private List<Propriedade> propriedades; // lista de propriedades no tabuleiro

    /**
     * Construtor do tabuleiro.
     * Inicializa a lista de propriedades e adiciona terrenos de exemplo para testes.
     */
    public Tabuleiro() {
        propriedades = new ArrayList<>();
        // adicionando 5 terrenos de exemplo (temporários, apenas para testes)
        propriedades.add(new Terreno("Av. Brasil", 100, 50));
        propriedades.add(new Terreno("Rua XV", 120, 60));
        propriedades.add(new Terreno("Av. Paulista", 150, 75));
        propriedades.add(new Terreno("Rua das Flores", 80, 40));
        propriedades.add(new Terreno("Av. Central", 200, 100));
    }

    /**
     * Retorna a propriedade do tabuleiro pelo índice informado.
     * @param indice Índice da propriedade na lista.
     * @return Propriedade correspondente ao índice.
     */
    public Propriedade getPropriedade(int indice) {
        return propriedades.get(indice);
    }

    /**
     * Retorna a lista de todas as propriedades do tabuleiro.
     * @return Lista de propriedades.
     */
    public List<Propriedade> getPropriedades() {
        return propriedades;
    }
}