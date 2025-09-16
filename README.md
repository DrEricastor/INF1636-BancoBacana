# Regras do Jogo
O seu programa deve implementar as regras apresentadas no arquivo Regras-Banco Iimobiliario.pdf, que se encontra publicado na página de INF1636 no EAD. Entretanto, para adaptar as regras do jogo físico à simulação em computador, algumas modificações se mostram adequadas. São elas:
- COMPONENTES – as 32 casas plásticas, os 12 hotéis plásticos e as 380 notas não serão representadas por meio de imagens. O programa deve guardar o montante de dinheiro que cada jogador possui e as quantidades de casas e hotéis existentes em cada propriedade. Os dados, os títulos de propriedade, os piões e os cartões de sorte/revés serão representados por imagens, já publicadas na página da disciplina.
- JOGADORES – o número de jogadores (2 a 6) será definido no frame inicial. Cada jogador iniciará a partida com $4.000 unidades monetárias, e o banco com $200.000.
  - O papel de BANQUEIRO será exercido pelo próprio software que será desenvolvido.
- COMEÇO DO JOGO – não é necessário promover um sorteio para definir a ordem dos jogadores. Essa ordem pode ser fixa e associada às cores dos piões.
- PRISÃO – um jogador sairá da prisão caso obtenha dois números iguais ao lançar os dados, ou possua o cartão de saída livre da prisão. Neste caso, a saída deve ser automática e o cartão de saída deve ser devolvido ao deck de cartas. O pagamento de multa ao banqueiro não será considerado.
- TERRENO OU EMPRESA COM DONO – o pagamento de aluguel ou taxa dever ser feito automaticamente.
- TROCAS E VENDAS ENTRE JOGADORES – não serão implementadas.
- CONSTRUÇÕES – como trocas e vendas não serão implementadas, ficaria difícil realizar construções caso um jogador tivesse de possuir todo um grupo de propriedades de uma mesma cor para tal. Sendo assim, as regras para construções serão as seguintes:
  - Na 1ª vez em que um jogador cair em uma propriedade ele poderá comprá-la, caso esteja disponível.
  - Nas vezes subsequentes em que ele cair na mesma propriedade será possível construir um único imóvel por vez.
  - Para construir um hotel, a propriedade tem de possuir pelo menos uma casa.
  - Uma propriedade poderá ter até 4 casas e um único hotel.
- HIPOTECAS – não serão implementadas.
- PAGAMENTOS – devem ser feitos automaticamente (débito e crédito). Caso um jogador precise de dinheiro ele deverá vender uma propriedade ao banco, pela qual receberá 90% do valor da propriedade (valor do terreno, mais o valor das cassas e dos hotéis).
- FALÊNCIA – empréstimos e doações não serão implementados. Se um jogador falir ele sairá do jogo.
- TÉRMINO DO JOGO – o jogo terminará quando os jogadores decidirem. Nesse momento será apurado o capital acumulado por cada jogador e definida a posição de cada um deles.
Caso, no decorrer do desenvolvimento, sejam detectadas dificuldades excessivas para implementar uma ou outra regra, este documento será atualizado com a relação das regras que ficarão de fora do trabalho.
