/*
 * Racional.java criado em 27/07/2011
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */

package br.com.objectos.dojo.hescarate;

/**
 * @author hellen.escarate@objectos.com.br (Hellen Escarate)
 */
public class Racional {

  Integer numerador;
  Integer denominador;

  public Racional(int numerador, int denominador) {

    if (denominador == 0) {

      throw new IllegalArgumentException();

    } else {

      this.numerador = numerador;
      this.denominador = denominador;

    }

  }

  public Racional add(Racional numero) {

    int numerador = numero.numerador;
    int denominador = numero.denominador;

    int somaNumerador = this.numerador * denominador + numerador * this.denominador;
    int somaDenominador = this.denominador * denominador;
    return new Racional(somaNumerador, somaDenominador);

  }

  @Override
  public String toString() {
    return numerador.toString() + "/" + denominador.toString();

  }

}

// > supporting addiction
// > public add method que pega outro Racional como um parâmetro.
// > para pegar o Racional, o add method não deve adicionar
// o número racional passado para ele mesmo
// > Isso deve criar e retornar um novo Racional que guarda a
// soma
// > Apesar de que os parametos da classe numerador e denominador estão no
// escopo do seu método adicional, você pode somente acessar esse valor no
// objeto no qual add foi invocado
// > adicionado dois campos chamados numer demon e incializa com os valores
// dos
// parametros da classe

