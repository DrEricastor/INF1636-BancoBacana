package Model;

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
}
