package Model;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestBancoImobiliarioModel {

    private BancoImobiliarioModel jogo;

    @Before
    public void setUp() {
        jogo = new BancoImobiliarioModel();
        jogo.adicionarJogador("A");
        jogo.adicionarJogador("B");
    }
    
    
    /** Teste para verificar se o jogo pode começar com 2 jogadores.
     * Pela regra,  o  jogo pode começar com pelo menoss 2 jogadores.
     * */
    @Test
    public void testIniciarJogoComDoisJogadores() {
        jogo.iniciarJogo(); 
        List<Jogador> jogadoresAtivos = jogo.getJogadores(); 
        assertEquals(2, jogadoresAtivos.size());
    }

    
    
    /** Teste para verificar a exception gerada quando tentamos começar o jogo com
     *  apenas 1 jogador. Pelas regras, só é possível iniciar  um jogo com 2 ou 
     *  mais jogadores.A função iniciarJogo é responsável por lançar a IllegalStateException
     *  para quem a chamou.
     *  */
    @Test (expected = IllegalStateException.class)
    public void testErroIniciarComUmJogador() {
        BancoImobiliarioModel jogoSimples = new BancoImobiliarioModel();
        jogoSimples.adicionarJogador("A");
        jogoSimples.iniciarJogo(); // Deve lancar IllegalStateException
    }

    
    
    /** Teste para verificar o lançamento dos dados e ver se a soma está entre 2 e 
     * 12, incluindo os limites superior e inferior. O teste inicia um jogo, lança os dados,
     * calcula a soma dos dados e verifica se é >= 2 e menor ou igual a 12.
     * */
    @Test
    public void testLancamentoDadosEntre2e12() {
        jogo.iniciarJogo();
        int[] dados = jogo.lancarDados(); 
        int r  = dados[0] + dados[1];
        assertTrue("O valor do dado (" + r + ") deve ser maior ou igual a 2", r >= 2);
        assertTrue("O valor do dado (" + r + ") deve ser menor ou igual a 12", r <= 12);
    }
    
    
    
    /** Teste para verificar o deslocamento de Pião com o Lançamento dos dados.
     * O jogo é iniciado, pegamos o primeiro jogador, lançamos os dados com ele, e verificamos
     * se a posição que o jogador parou após o lançamento é a mesma que a soma dos dados 
     * (já que o jogador começa da casa 0).
     * */
    @Test
    public void testDeslocarPiaoLancandoDados() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        jogadorA.setPosicao(18);
        
        int[] dados = jogo.lancarDados();
        
        int posicao_antes = jogadorA.getPosicao();
        jogo.deslocarPiao(jogadorA, dados[0]+dados[1]);
        assertEquals((posicao_antes+dados[0]+dados[1])%jogo.getTabuleiro().getCampos().size(), jogadorA.getPosicao()); 
    }

    
    /**
     * Testa se o peão de um jogador dá a volta no tabuleiro corretamente ao passar do
     * final para o início.
     */
    @Test
    public void testDeslocarPiaoComVoltaNoTabuleiro() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        int tamanhoTabuleiro = jogo.getTabuleiro().getCampos().size(); // No seu codigo atual,  5

        // Coloca o jogador perto do fim do tabuleiro para forçar a volta
        jogadorA.setPosicao(tamanhoTabuleiro - 1); // Posicao 3 (de 0-4)
        
        jogo.deslocarPiao(jogadorA, 4); // Anda 4 casas: da 3 -> 4 -> 0 -> 1 -> 2
        
        // A nova posicao esperada: (3 + 4) % 5 = 7 % 5 = 2
        assertEquals(3, jogadorA.getPosicao());
    }
    
    
    /**
     * Testa o pagamento de aluguel em um terreno que possui uma casa construída.
     * Verifica se o saldo do jogador inquilino diminui após parar no terreno
     * e pagar o valor de aluguel majorado.
     */
    @Test
    public void testPagarAluguelComCasaConstruida() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0); // O inquilino
        Jogador jogadorB = jogo.getJogadores().get(1); // O proprietario

        int posicaoAlvo = 1; //Leblon
        Terreno terreno = (Terreno) jogo.getTabuleiro().getCampo(posicaoAlvo);
        terreno.setDono(jogadorB);


        int saldoInicialA = jogadorA.getSaldo();


        // --- Cenario: Construi uma casa e paga aluguel ---
        terreno.setNumCasas(1);
        jogadorA.setPosicao(0);
        jogo.deslocarPiao(jogadorA, 1);
     

        assertTrue("Esperado:" + (saldoInicialA - ((Propriedade) jogo.getTabuleiro().getCampo(1)).calcularAluguel(1)) + 
                "Real:" + jogadorA.getSaldo(), jogadorA.getSaldo() < saldoInicialA);
    }
    
    
    /**
     * Testa a funcionalidade de comprar uma propriedade. Verifica se o jogador se torna
     * o dono da propriedade em que está e se o seu saldo é debitado
     * corretamente com o preço da propriedade.
     */
    @Test
    public void testComprarPropriedadeNaPosicaoCorreta() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);

        int posicaoAlvo = 3;
        jogadorA.setPosicao(posicaoAlvo);

        Propriedade propriedadeAlvo = (Propriedade) jogo.getTabuleiro().getCampo(posicaoAlvo);
        int saldoInicial = jogadorA.getSaldo();

        // Jogador A tenta comprar a propriedade na posi��o em que est�
        jogo.comprarPropriedade(jogadorA);

        // Verifica se a compra foi bem-sucedida
        assertEquals("O dono da propriedade deve ser o jogador A", jogadorA, propriedadeAlvo.getDono());
        assertEquals("O saldo do jogador A deve ser descontado corretamente", saldoInicial - propriedadeAlvo.getPreco(), jogadorA.getSaldo());
    }

    
    /**
     * Testa a tentativa de compra de uma propriedade que já pertence a outro jogador.
     * Verifica se a propriedade do imóvel e o saldo do jogador que tentou a compra
     * permanecem inalterados.
     */
    @Test
    public void testNaoComprarPropriedadeQueJaTemDono() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        Jogador jogadorB = jogo.getJogadores().get(1);

        int posicaoAlvo = 1; // Posicao da "Rua XV"
        jogadorA.setPosicao(posicaoAlvo);

        Propriedade propriedadeAlvo = (Propriedade) jogo.getTabuleiro().getCampo(posicaoAlvo);
        propriedadeAlvo.setDono(jogadorB); // Jogador B ja é o dono

        int saldoInicialA = jogadorA.getSaldo();

        // Jogador A tenta comprar a propriedade que  tem dono
        jogo.comprarPropriedade(jogadorA);

        // Verifica se nada mudou para o jogador A
        assertEquals("O dono ainda deve ser o jogador B", jogadorB, propriedadeAlvo.getDono());
        assertEquals("O saldo do jogador A n�o deve mudar", saldoInicialA, jogadorA.getSaldo());
    }
    
    
    /**
     * Testa a construção de uma casa em um terreno que pertence ao próprio jogador.
     * Verifica se o número de casas no terreno aumenta em um e se o saldo do jogador
     * é debitado no valor do preço da casa.
     */
    @Test
    public void testConstruirCasaEmPropriedadePropria() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);

        int posicaoAlvo = 3; // Rua das Flores
        jogadorA.setPosicao(posicaoAlvo);

        Terreno terreno = (Terreno) jogo.getTabuleiro().getCampo(posicaoAlvo);
        terreno.setDono(jogadorA); // Jogador A � o dono

        int saldoInicial = jogadorA.getSaldo();
        int casasAntes = terreno.getNumCasas();

        // Jogador A constr�i uma casa no terreno onde est�
        jogo.construirCasa(jogadorA);

        assertEquals("O n�mero de casas deve aumentar em 1", casasAntes + 1, terreno.getNumCasas());
        assertEquals("O saldo do jogador deve diminuir o pre�o da casa", saldoInicial - terreno.getPrecoCasa(), jogadorA.getSaldo());
    }

    /**
     * Testa a tentativa de construir uma casa em um terreno que pertence a outro jogador.
     * Garante que o número de casas no terreno e o saldo do jogador que tentou a construção
     * permaneçam inalterados.
     */
    @Test
    public void testNaoConstruirCasaEmTerrenoDeOutro() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        Jogador jogadorB = jogo.getJogadores().get(1);

        int posicaoAlvo = 4; // Av. Central
        jogadorA.setPosicao(posicaoAlvo);

        Terreno terreno = (Terreno) jogo.getTabuleiro().getCampo(posicaoAlvo);
        terreno.setDono(jogadorB); // Jogador B  dono

        int saldoInicialA = jogadorA.getSaldo();
        int casasAntes = terreno.getNumCasas();

        // Jogador A tenta construir em terreno alheio
        jogo.construirCasa(jogadorA);

        assertEquals("O numero de casas nao deve mudar", casasAntes, terreno.getNumCasas());
        assertEquals("O saldo do jogador A n�o deve mudar", saldoInicialA, jogadorA.getSaldo());
    }
    
    
    /**
     * Testa o fluxo completo de um jogador ser enviado para a prisão e depois sair
     * pagando a fiança. Verifica o status de "preso" e a dedução correta do saldo.
     */
    @Test
    public void testEntrarESairDaPrisaoPagando() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        int saldoInicial = jogadorA.getSaldo();

        // Cen�rio 1: Entrar na prisão
        assertFalse("Jogador n�o deve come�ar preso", jogadorA.estaPreso());
        jogo.enviarParaPrisao(jogadorA);
        assertTrue("Jogador deve estar preso após ser enviado", jogadorA.estaPreso());

        // Cen�rio 2: Sair da prisao pagando a fianca de 50
        jogo.sairDaPrisaoPagando(jogadorA);
        assertFalse("Jogador deve estar livre ap�s pagar para sair", jogadorA.estaPreso());
        assertEquals("Saldo deve ser descontado em 50 para sair da pris�o", saldoInicial - 50, jogadorA.getSaldo());
    }

    
    /**
     * Testa a regra que impede um jogador de sair da prisão pagando fiança se
     * não tiver saldo suficiente. Verifica se o jogador continua preso e se seu
     * saldo permanece inalterado.
     */
    @Test
    public void testNaoSairDaPrisaoSemSaldoSuficiente() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0);
        jogadorA.setSaldo(40); // Saldo insuficiente para pagar a fian�a de 50

        jogo.enviarParaPrisao(jogadorA);
        assertTrue("Jogador deve estar preso", jogadorA.estaPreso());

        // Tenta sair da pris�o sem dinheiro
        jogo.sairDaPrisaoPagando(jogadorA);

        assertTrue("Jogador deve continuar preso por falta de saldo", jogadorA.estaPreso());
        assertEquals("Saldo n�o deve mudar se o pagamento falhar", 40, jogadorA.getSaldo());
    }
    
    
    /**
     * Testa a funcionalidade de sair da prisão utilizando uma carta de "Passe Livre".
     * Garante que o jogador seja libertado e que sua quantidade de passes livres
     * seja decrementada.
     */
    @Test
    public void testSairDaPrisaoUsandoPasseLivre() {
    	jogo.iniciarJogo();
    	Jogador jogadorA = jogo.getJogadores().get(0);
    	
    	jogadorA.setQtdPasseLivre(1);
    	jogo.enviarParaPrisao(jogadorA);
    	
    	jogo.sairUsandoPasseLivre(jogadorA);
    	assertFalse("Jogador deve sair da prisão por ter passe livre", jogadorA.estaPreso());
    	assertEquals("Jogador deve perder o passe livre apos usar", 0, jogadorA.getQtdPasseLivre());
    }
    
    
    /**
     * Testa a condição de sair da prisão ao tirar dados iguais. Verifica se o jogador
     * que está preso é libertado após um lançamento de dados com valores idênticos.
     */
    @Test
    public void testSairDaPrisaoDadosIguais() {
    	jogo.iniciarJogo();
    	Jogador jogadorA = jogo.getJogadores().get(0);
    	
    	jogo.enviarParaPrisao(jogadorA);
    	
    	int[] dados = new int[2];
    	dados[0] = 2;
    	dados[1] = 2;
    	jogo.tentarSairDaPrisao(jogadorA, dados);
    	
    	assertFalse("Jogador deve sair da prisão por ter tirado dados iguais", jogadorA.estaPreso());
    	
    	
    	
    }
   
    /**
     * Testa o cenário de falência de um jogador por não conseguir pagar o aluguel.
     * Verifica se o jogador falido é removido do jogo e se seu saldo restante
     * é transferido para o proprietário do terreno.
     */
    @Test
    public void testFalenciaAoNaoConseguirPagarAluguel() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0); // O que vai falir
        Jogador jogadorB = jogo.getJogadores().get(1); // O propriet�rio

        // Configura o cenario de falencia
        int posicaoAlvo = 1;
        jogadorA.setPosicao(0);
        jogadorA.setSaldo(1); // Saldo baixo e insuficiente (aluguel está mockado como 50)

        Terreno terreno = (Terreno) jogo.getTabuleiro().getCampo(posicaoAlvo);
        terreno.setDono(jogadorB);
        terreno.setNumCasas(4); // For�a um aluguel alto (no caso, 100 * (4+1) = 500)

        int saldoInicialB = jogadorB.getSaldo();
        int jogadoresAntes = jogo.getJogadores().size();

        // Açao: Pagar aluguel que leva a  falencia
        jogo.deslocarPiao(jogadorA, 1);
        
     
        System.out.println("Saldo Jogador A apos falencia: " + jogadorA.getSaldo());
        // Verificacoes
        assertEquals("Numero de jogadores deve diminuir em 1", jogadoresAntes - 1, jogo.getJogadores().size());
        assertFalse("Jogador falido nao deve mais estar na lista de jogadores", jogo.getJogadores().contains(jogadorA));
        assertEquals("Dono do terreno deve receber o saldo restante do jogador falido", saldoInicialB + 1, jogadorB.getSaldo());
    }

    @Test
    public void testPagarCompanhia() {
        jogo.iniciarJogo();
        Jogador jogadorA = jogo.getJogadores().get(0); // O que vai falir
        Jogador jogadorB = jogo.getJogadores().get(1); // O proprietário

        ((Companhia) (jogo.getTabuleiro().getCampo(5))).setDono(jogadorB); // da a companhia na pos 2 pro B

        int deslocamento = 5;
        jogadorA.setPosicao(0);
        jogo.deslocarPiao(jogadorA, deslocamento);

        assertEquals("Erro no saldo apos pagar companhia. ", 4000 - (deslocamento * 50), jogadorA.getSaldo());
    }
    
    
}
    