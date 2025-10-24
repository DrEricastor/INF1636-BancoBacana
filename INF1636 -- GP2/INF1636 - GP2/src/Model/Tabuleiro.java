package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Campo.TipoCampo;

/**
 * Classe que representa o tabuleiro do jogo de Banco Imobiliário.
 * O tabuleiro contém uma lista de propriedades disponíveis para compra e interação dos jogadores.
 */
class Tabuleiro {
    private List<Campo> campos; // lista de propriedades no tabuleiro

    /**
     * Construtor do tabuleiro.
     * Inicializa a lista de propriedades e adiciona terrenos de exemplo para testes.
     */
    public Tabuleiro(List<Campo> campos) {
        this.campos = campos;
    }

    public static Tabuleiro gerarTabuleiroPadrao() {
        List<Campo> campos = new ArrayList<>();
        campos.add(new Campo(TipoCampo.PONTO_PARTIDA));
        campos.add(new Terreno("Leblon",100,50,
                new int[]{6,30,90,270,400,500}));
        campos.add(new Campo(TipoCampo.SORTE_REVES));
        campos.add(new Terreno("Av. Presidente Vargas",60,50,
                new int[]{2,10,30,90,160,250}));
        campos.add(new Terreno("Av. Nossa S. De Copacabana",60,50,
                new int[]{4,20,60,180,320,450}));
        campos.add(new Companhia("Companhia Ferroviária", 200, 50));
        campos.add(new Terreno("Av. Brig. Faria Lima",240,150,
                new int[]{20,100,300,750,925,1100}));
        campos.add(new Companhia("Companhia de Viação", 200, 50));
        campos.add(new Terreno("Av. Rebouças",220,150,
                new int[]{18,90,250,700,875,1050}));
        campos.add(new Terreno("Av. 9 de Julho",220,150,
                new int[]{18,90,250,700,875,1050}));

        campos.add(new Campo(TipoCampo.PRISAO));
        campos.add(new Terreno("Av. Europa",220,100,
                new int[]{18,90,250,700,875,1050}));
        campos.add(new Terreno("Rua Augusta",180,100,
                new int[]{14,70,200,550,750,950}));
        campos.add(new Terreno("Av. Pacaembú",180,150,
                new int[]{14,70,200,550,750,950}));
        campos.add(new Companhia("Companhia de Táxi", 150, 40));
        campos.add(new Terreno("Interlagos",350,200,
                new int[]{35,175,500,1100,1300,1500}));
        campos.add(new Campo(TipoCampo.RECEBER_DINHEIRO));
        campos.add(new Terreno("Morumbi",400,200,
                new int[]{50,200,600,1400,1700,2000}));
        
        campos.add(new Campo(TipoCampo.ESTACIONAMENTO));
        campos.add(new Terreno("Flamengo",120,50,
                new int[]{8,40,100,300,450,600}));
        campos.add(new Terreno("Botafogo",100,50,
                new int[]{6,30,90,270,400,500}));
        campos.add(new Campo(TipoCampo.PAGAR_IMPOSTO));
        campos.add(new Companhia("Companhia de Navegação", 150, 40));
        campos.add(new Terreno("Av. Brasil",160,100,
                new int[]{12,60,180,500,700,900}));
        campos.add(new Terreno("Av. Paulista",140,100,
                new int[]{10,50,150,450,625,750}));
        campos.add(new Terreno("Jardim Europa",140,100,
                new int[]{10,50,150,450,625,750}));

        campos.add(new Campo(TipoCampo.VAI_PARA_PRISAO));
        campos.add(new Terreno("Copacabana",260,150,
                new int[]{22,110,330,800,975,1150}));
        campos.add(new Companhia("Companhia de Aviação", 200, 50));
        campos.add(new Terreno("Av. Viera Souto",320,200,
                new int[]{28,150,450,1000,1200,1400}));
        campos.add(new Terreno("Av. Atlântica",300,200,
                new int[]{26,130,390,900,1100,1275}));
        campos.add(new Companhia("Companhia de Táxi Aéreo", 200, 50));

        campos.add(new Terreno("Ipanema",300,200,
                new int[]{26,130,390,900,1100,1275}));
        campos.add(new Terreno("Jardim Paulista",280,150,
                new int[]{24,120,360,850,1025,1200}));
        campos.add(new Terreno("Brooklin",260,150,
                new int[]{22,110,330,800,975,1150}));

        return new Tabuleiro(campos);
    }

    public int getPosicaoPrisao() {
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).tipo == TipoCampo.PRISAO) {
                return i;
            }
        }
        return -1; // Retorna -1 se a posição da prisão não for encontrada
    }

    /**
     * Retorna a propriedade do tabuleiro pelo índice informado.
     * @param indice Índice da propriedade na lista.
     * @return Propriedade correspondente ao índice.
     */
    public Campo getCampo(int indice) {
        return campos.get(indice);
    }

    /**
     * Retorna a lista de todas as propriedades do tabuleiro.
     * @return Lista de propriedades.
     */
    public List<Campo> getCampos() {
        return campos;
    }
}