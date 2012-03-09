/*
 * BuscarFuncionario.java criado em 08/03/2012
 * 
 * Propriedade de Objectos Fábrica de Software LTDA.
 * Reprodução parcial ou total proibida.
 */
package main;

/**
 * @author caio.petreanu@objectos.com.br (Caio Petreanu)
 */
public interface BuscarFuncionario {

  Funcionario porId(int id);

  Funcionario porSuperior(Superior superior);

}
