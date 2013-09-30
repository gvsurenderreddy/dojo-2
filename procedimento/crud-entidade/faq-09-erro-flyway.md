---
layout: post-alpha
title: "Erros flyway"
author: "Anderson Amorim Silva"
user: "asilva26"
date: 2013-09-30
published: true 
partof: faq-crud-entidade
num: 8
outof: 8
---

## PRINCIPAIS ERROS DO FLYWAY E SUAS POSSÍVEIS SOLUÇÕES

Trabalhando com flyway, comumente nos deparamos com alguns erros na criação ou alteração das tabelas, este artigo visa sanar as dúvidas  mais comuns encontradas.  

Siga o checklist abaixo:
<table class="table table-bordered">
 <tr>   
  <td class="tac col2em">
    <a id="topo_0_0"><input type="checkbox" /></a>
   </td>
  <td>
    Erro de sintaxe na criação de um script
   </td>
  <td>
    <a href="#0_0">help!</a>
   </td>
  </tr>
 <tr>
  <td class="tac col2em">
    <a id="topo_0_1"><input type="checkbox" /></a>
   </td>
  <td>
    Resolvendo problemas através do *schema_version*
   </td>
  <td>
    <a href="#0_1">help!</a>
   </td>
  </tr>
 <tr>
  <td class="tac col2em">
    <a id="topo_0_2"><input type="checkbox" /></a>
   </td>
  <td>
    Restaurando o backup de um banco de dados específico
   </td>
  <td>
    <a href="#0_2">help!</a>
   </td>
  </tr> 
 <tr>
  <td class="tac col2em">
    <a id="topo_0_3"><input type="checkbox" /></a>
   </td>
  <td>
    Restaurando o backup do projeto inteiro
   </td>
  <td>
    <a href="#0_3">help!</a>
   </td>
  </tr> 
 <tr>
  <td class="tac col2em">
    <a id="topo_0_4"><input type="checkbox" /></a>
   </td>
  <td>
    Conclusão
   </td>
  <td>
    <a href="#0_4">help!</a>
   </td>
 </tr> 
</table>

### <a id="0_0"> </a> Erro de sintaxe na criação de um *script*   

Muito comum acontecer quando se inicia na programação escrever o nome do _script flyway_ errado, para saber mais sobre a nomenclatura de _flyway_ [clique aqui](http://dojo.objectos.com.br/procedimento/crud-entidade/00.2-criando-testando-script-flyway.html).
Se eventualmente acontecer, será fácil de perceber pois ao rodar o comando `mvn compile flyway:migrate` irá aparecer a seguinte mensagem de erro:  

    [ERROR] Failed to execute goal com.googlecode.flyway:flyway-maven-plugin:2.2.1:migrate (default-cli) on project flyway: com.googlecode.flyway.core.api.FlywayException: Wrong migration name format: V2_teste.sql(It should look like this: V1_2__Description.sql)  

Neste exemplo o nome do _script_ foi escrito utilizando apenas um _underscore_, fugindo totalmente do padrão, resultando no não reconhecimento do *script* quando rodar algum comando como por exemplo `mvn compile flyway:migrate`.   
Para rodar de fato o script é necessário rodar o comando `mvn compile flyway:migrate`, este comando compila o código do projeto e migra a schema version para a versão mais recente do *script*, e se por acaso a *schema_version* não tiver sido criada, este comando a cria também.  

Outro problema ainda relacionado a criação de _scripts_ resulta em conflitos ao rodar o _branch_ no _Jenkins_ e/ou realizar um _merge_, que é dar continuidade em uma sequencia de _scripts_ baseando apenas na pasta `db/migration` do diretório local, sem consultar o _schema version_( através do *schema_version* é possível visualizar qual foi o último *flyway* criado, desta forma evita a criação de mais de um script com o mesmo nome e/ou versão).
Este procedimento deve ser realizado sempre pois evita muitos conflitos na hora de integrar um *branch* local com a master.

### <a id="0_1"> </a> Resolvendo problemas através do *schema_version*

Uma coisa que pode acontecer é alguém ir no `PhpMyAdmin` e alterar uma tabela qualquer, a questão inicial para esta situação é, o dá para reverter esta eventual situação?
A resposta para esta pergunta é sim, e esta resposta por si acaba gerando uma outra dúvida, como reverter este problema?
Através do comando `mvn flyway:migrate` é possível recriar os *scripts* alterados no `PhpMyAdmin`. 

    [ERROR] com.googlecode.flyway.core.exception.FlywayException: Cannot determine latest applied migration. Was the metadata table manually modified?

Para resolver este problema, deve-se ir ao `PhpMyAdmin`, ir até o banco que deseja restaurar, clicar em *schema_version* e modificar o valor da última linha da coluna `current_version` para 1, se rodar novamente o comando `mvn flyway:migrate`, o *script* alterado irá voltar.  
Mais um problema que pode acontecer é em relação a sintaxe, se por exemplo uma tabela teve seu nome modificado, e for tentar realizar a restauração em caso de deleção irá aparecer a seguinte mensagem:

    [ERROR] com.googlecode.flyway.core.migration.MigrationException: Migration to version V2__teste failed! Please restore backups and roll back database and code!
    
**Note que este erro é bem específico e indica que a tabela `V2__teste` está com erro de sintaxe e por isso não foi possível realizar a restauração da tabela alterada**.  
A restauração só irá ocorrer quando o erro for corrigido. O comando utilizado para esta restauração acontecer é `mvn flyway:migrate`.

### <a id="0_2"> </a> Restaurando o *backup* de um banco de dados  

A forma de resolver os erros de flyway é restaurar o backup, esta forma resolve o problema de fato, mas deve ser executada somente se foi tentado os procedimentos acima e mesmo assim o problema persiste. Por exemplo:  
Ao rodar um comando como `mvn flyway:migrate`, pode acontecer um erro ao construir um banco ou todos os bancos do projeto, mas o que fazer se isso acontecer?
Uma solução é substituir este banco por um de algum colega que esteja funcionando. O procedimento de como [realizar back up](http://dojo.objectos.com.br/caixa/sql-01-mysqldump.html).

    scp (nome do arquivo).sql (nome do usuário)@estacao(número da estação em que se deseja enviar):
Este comando envia o _backup_ para a estação que está com problema. Deve-se colocar os `:` no final do comando, pois senão o arquivo não será enviado, este `:` indica que o arquivo deve ser enviado para um local remoto, no caso a estação que apresentou o erro.  
__IMPORTANTE__: Estes dois comandos deve ser escrito da estação que contém o banco de dados funcional.

O procedimento para [restauração](http://dojo.objectos.com.br/caixa/sql-01-mysqldump.html).
Em ambos comandos após o `mysqld` está escrito _jetty_, este *jetty* indica que o banco utilizado é o remoto, se caso fosse o banco de dados local é só substituir para eclipse.  

### <a id="0_3"> </a> Restaurando o *backup* do projeto inteiro

Nos projetos utilizados na ***objectos*** há um *shell script* com o *backup* dos projetos trabalhados, que permite fazer a restauração do projeto inteiro. E são eles:  

    /.mysql-eclipse-restore
Utilizado para restaurar o servidor pertencente ao banco de dados do eclipse (onde rodam os testes).

    /.mysql-jetty-restore
Utilizado para restaurar o banco de dados de produção em si.

### <a id="0_4"> </a> Conclusão
Antes de resolver fazer o *backup* do projeto todo, deve-se sempre pensar se não existe uma maneira mais fácil de resolver o problema, principalmente porque não existe solução única. Os procedimentos de restauração ajudam e devem ser usados sempre como última alternativa, outra coisa que vale mencionar é justamente qual procedimento realizar, se apenas um banco apresentou problema, é melhor restaurar apenas ele, ao invés utilizar a segunda opção.
