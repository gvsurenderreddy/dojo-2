---
layout: post-alpha
title: "Procedimento de rebase"
author: "Ricardo Murad"
published: true
partof: git
num: 6
---

##Introdução
 
No git temos basicamente duas formas de integrar dois ou mais commits ao projeto principal: o 
rebase e o merge.
Para facilitar o controle de versões e a iteração de nosso projeto, vamos seguir o seguinte padrão: 
dentro do repositório master vamos deixar sempre um commit de rebase próximo de seu respectivo merge. 
Para que isso seja possível, quando acabarmos de realizar o rebase devemos verificar no master se há 
alguma atualização remota efetuada após o commit do rebase que acabamos de fazer, caso isso ocorra 
devemos refazer todo o processo do rebase, testar novamente no jenkins e só então fazer o merge. 

Neste procedimento iremos aplicar os comandos merge e rebase em nosso projeto:

O comando rebase basicamente serve para reaplicar todas as modificações comitadas de um branch
para outro, por isso é chamado de rebase.
Nos exemplos abaixo assumiremos que o branch topic foi criado a partir do master:


Exemplo:
----------------------------------------------------------

                     A---B---C topic
                    /
               D---E---F---G master
----------------------------------------------------------               

O rebase verifica o ponto em comum entre o branch topic e o master, pega as alterações de cada
commit do branch topic que não existem no master, em seguida faz com que o branch master aponte 
para o mesmo commit do branch topic e só depois reaplica cada uma das alterações.     
Desta forma, o rebase garante um histórico ordenado de todos os commits facilitando a organização
de nosso projeto, como veremos a seguir o mesmo não ocorre quando utilizamos o comando merge.

                             A`--B`--C` topic
                            /
               D---E---F---G master



O merge resumidamente integra o conteúdo divergente entre dois branches com o ancestral comum deles
mais recente, criando um novo commit.     

Exemplo:

                     A---B---C topic
                    /
               D---E---F---G master
               

Se realizarmos um merge através do comando `git merge topic` todas as alterações de topic que 
estejam diferentes do master serão integradas em um novo commit, neste caso o H:    

                     A---B---C topic
                    /         \
               D---E---F---G---H master
               

##Estado do branch atual

Antes de iniciar o processo vamos verificar o estado do branch com o comando `git status`:

    $ git status

    On branch 
    nothing to commit (working directory clean)

Após verificarmos que não há nada pendente vamos utilizar o jenkins para verificar se há erros 
no branch:

    Tests run: 169, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.836 sec
    
    Results :
    
    Tests run: 169, Failures: 0, Errors: 0, Skipped: 0
    
    [JENKINS] Gravando resultados de teste# Deploying the attached artifact {0}


Neste caso não há erros, então vamos para o branch master para atualizar o repositório local:

    $ git checkout master

    Switched to branch `master`

É recomendável verificar o estado do master com o comando `git status` para verificar se há alguma 
pendência:

    $ git status

Para que tenhamos acesso as alterações que outros usuários realizaram na master remota  é 
necessário executar os seguintes comandos: 

    $ git pull origin master

Resultado:

	From github.com:objectos/projeto-teste
	 * branch            master     -> FETCH_HEAD
	Already up-to-date.


O próximo passo é voltar para nosso branch

    $ git checkout meubranch_rebase
	 
	Switched to branch `meubranch_rebase`


Vamos executar o próximo comando para verificar qual foi o último commit realizado:

    $ git log -1--pretty=oneline | cat

A seguinte tela irá aparecer: 
 
	commit 023229c695ce3dc7a35a4001590b85b2b33c6ae0
	Author: AutorDoCommit 
	Date:   Wed Sep 4 14:21:35 2013 -0300

    - Exemplo Commit

Vamos agora anotar a identificação do último commit para que posteriormente possamos verificar se 
este commit será o último a aparecer no rebase e para realizar backup caso seja necessário. 

Em seguida aplicamos o comando rebase:

    $ git rebase master -i
	
O comando `-i` é um modo interativo que exibe uma lista de commits referentes ao branch que está 
sendo utilizado e permite ao usuário a edição desta lista.    	

O editor vi será utilizado para que possamos selecionar os commits que desejamos utilizar no rebase.

Exemplo:

	pick e7c4b61 - Commit I
	pick 0996f15 - Commit II
	pick 5574fa7 - Commit III
	pick 5574fa8 - Commit IV

	 Rebase f346b81..df70f7c onto f346b81
	
	 Commands:
	  p, pick = use commit
	  r, reword = use commit, but edit the commit message
	  e, edit = use commit, but stop for amending
	  s, squash = use commit, but meld into previous commit
	  f, fixup = like "squash", but discard this commit`s log message
	  x, exec = run command (the rest of the line) using shell
	
	 These lines can be re-ordered; they are executed from top to bottom.
	
	 If you remove a line here THAT COMMIT WILL BE LOST.
	 However, if you remove everything, the rebase will be aborted.
	
	 Note that empty commits are commented out
	~                                                                               
	<objetos/projeto_teste/.git/rebase-merge/git-rebase-todo" 22L, 792C 2,1          
	

Esta é a tela do rebase interativo.
Podemos ver que existem quatro commits. Vamos unir todos em um único commit com o comando `squash`.
Para fazer isso vamos digitar `x` em cima do comando pick da primeira linha para apagar a letra p
e em seguida utilizaremos o comando `%s/pick/squash/g` para trocar os comandos das demais linhas de 
pick para squash e então voltamos à primeira linha e usando o comando `I` de inserção digitamos a 
letra p novamente. 


Exemplo:

	pick e7c4b61   - Commit I
	squash 0996f15 - Commit II
	squash 5574fa7 - Commit III
	squash 5574fa8 - Commit IV
		
Agora utilizando a tecla `esc` e saimos do modo de inserção. 
Para salvar e sair simultaneamente utilizamos o comando `:wq` do VI. Caso acidentalmente tenhamos 
feito alguma alteração não desejada utilizamos o comando `:undo` para voltar para o estado anterior.

###Conflitos do Rebase
Neste momento poderão ocorrer conflitos para fazer o rebase, embora esta situação seja pouco comum é 
necessário realizar alguns procedimentos para resolvê-la.
Uma mensagem semelhante ao exemplo abaixo será exibida para indicar o conflito: 

	error: could not apply 123c... Commit I 

	When you have resolved this problem, run "git rebase --continue". 
	If you prefer to skip this patch, run "git rebase --skip" instead. 
	To check out the original branch and stop rebasing, run "git rebase --abort". 
	Could not apply 123c... Commit I 
	
Utilizando o comando `git status` o arquivo com conflito será listado.
Copiamos o caminho do arquivo e para que o arquivo seja aberto para edição digitamos o comando vi 
e colamos este caminho:
	
	$ vi Commit I.txt
	
	<<<<<<< HEAD
	A
	B
	C
	
	======
	T
	Z
	Q
	>>>>>>> meubranch_rebase
	

Neste momento os conteúdos com conflito são indicados e é possivel editar o arquivo e decidir se 
o conteúdo indicado na HEAD está correto e será mantido ou será apagado.
Se optarmos por apagar alguma informação devemos primeiramente sair do modo texto através da tecla
`esc` e em seguida pressionar as teclas `Shift + v` simultaneamente para que o editor passe para o 
modo de seleção de linhas, então utilizaremos as setas do teclado para selecionar as linhas desejadas 
e finalmente apagamos as linhas com a tecla `dd`. Em seguida uma mensagem será exibida indicando a
quantidade de linhas que foram removidas.
Caso alguma informação importante seja indevidamente apagada, para restaurá-la basta pressionar a 
tecla `esc` e em seguida utilizar o comando `:undo` desta forma a última ação será desfeita.
Se optarmos por manter todas as informações basta remover apenas os comentários gerados pelo conflito.
No exemplo abaixo vamos manter somente o conteúdo da HEAD:

	A
	B
	C

Após remover ou manter as informações é necessário salvar as alterações realizadas, para isso 
pressionamos `esc` para sair do modo texto e em seguida pressionar `:wq` para salvar e sair. 
Em seguida devemos incluir o arquivo com conflito no commit, para isso pressionamos seta 
para cima e será exibido o último comando executado que será vi e o caminho do arquivo, utilizando
a tecla home para ir para o início da linha substituimos o comando `vi` por `add .`.   

    $ git add Commit I.txt

Neste caso não será necessário realizar o _commit_ deste arquivo porque ele será incluso no _commit_
do rebase.

Agora devemos continuar o rebase através do comando `git rebase --continue` e passar para a próxima
etapa que é a finalização do rebase.

    $ git rebase --continue

<div class="alert alert-warning">
  Se após a resolução do conflito esquecermos de executar o comando `git rebase --continue`, o processo
  do rebase não terá sido concluído.  

</div>

##Finalizar o rebase
O VI será reaberto para que possamos editar as mensagens de todos os commits que constam no rebase 
e criar uma mensagem única para o commit do rebase. 
Neste momento devemos apagar os comentários existentes, sendo que para apagar uma linha usamos o 
comando `dd` e para apagar três linhas seguidas é possível usar o comando `3dd`.     
O exemplo abaixo mostra como deverá ficar nosso commit, as linhas de comentário foram apagadas e 
o nome do commit do rebase será: Commits do Rebase.


	 This is a combination of 4 commits.
	 The first commit`s message is:
	Commits do Rebase
	- Commit I
	- Commit II
	- Commit III
	- Commit IV

	# Please enter the commit message for your changes. Lines starting
	# with `#` will be ignored, and an empty message aborts the commit.
	# Not currently on any branch.
	# You are currently editing a commit during a rebase.
	#
	# Changes to be committed:
	#   (use "git reset HEAD^1 <file>..." to unstage)
	#
	#       new file:   Commit I.txt
	#       new file:   Commit II
	#       new file:   Commit III
	#       new file:   Commit IV


Agora utilizamos a tecla `esc` para sair do VI mas ainda estamos dentro do processo de rebase. 
Podemos constatar isso pela mensagem no prompt:

    (meubranch_rebase|REBASE-i)

Feito isso vamos forçar a sincronização do branch local com o remoto por meio do comando abaixo: 

    $ git push origin +meubranch_rebase

Atenção: Não podemos esquecer de colocar `+` antes do nome do branch, pois desta forma estamos 
forçando este comando.  

Rodamos novamente os testes no jenkins e constatamos que não houve falhas

	Tests run: 169, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 12.836 sec
	
	Results :
	
	Tests run: 169, Failures: 0, Errors: 0, Skipped: 0
	
	[JENKINS] Gravando resultados de teste# Deploying the attached artifact {0}

Finalmente vamos fazer o merge de nosso branch com o master. 
Para realizar o merge utilizaremos o github ou stash, vamos até o repositório de nosso projeto e clicamos na 
aba _Pull Requests_, em seguida localizamos nosso branch e clicamos em pull request, ativamos a 
opção delete branch e agora clicamos em _Merge Pull Request_, a seguinte mensagem surgirá:

    Merge pull request #147 from projeto-teste/meubranch_rebase
    meubranch_rebase: habilitar artigo de rebase.
    
Se tudo estiver correto basta clicar no botão _Confirm Merge_.

##Resumo
Neste procedimento iremos integrar dois ou mais commits ao projeto principal utilizando os comandos
rebase e merge.
Antes de fazer o merge do projeto com o master é necessário verificar no repositório master se há 
alguma atualização remota efetuada após o commit do rebase que acabamos de fazer. Se isso ocorrer 
devemos refazer todo o processo do rebase, testar novamente no jenkins e só então fazer o merge. 
Desta forma o repositório master terá sempre um commit de rebase seguido de seu respectivo merge,
facilitando o controle de versões e a iteração de nosso projeto.  
Antes de iniciar o procedimento de rebase vamos verificar o estado do branch com o comando `git status`:
	
    $ git status
    
Após verificarmos que não há nada pendente vamos utilizar o jenkins para verificar se há erros 
no branch. Se não houver pendências vamos para o branch master para atualizar o repositório local:

    $ git checkout master

Agora vamos executar o seguinte comando para atualizar o master: 

    $ git pull origin master

O próximo passo é voltar para nosso branch

    $ git checkout meubranch_rebase

Em seguida aplicamos o comando rebase:
	
    $ git rebase master -i
	
O editor vi será utilizado para que possamos selecionar os commits que desejamos utilizar no rebase.
Vamos unir todos os commits em um único utilizando o comando `squash` e deixar somente a primeira 
linha com o comando `pick`.
Para salvar e sair simultaneamente utilizamos o comando `:wq` do VI. 
Embora seja pouco comum, um conflito pode ocorrer neste momento, neste caso podemos consultar no 
texto acima o item "Conflitos do Rebase" para resolvê-lo.

O VI será reaberto para que possamos editar as mensagens de todos os commits que constam no rebase 
e criar uma mensagem única. Feito isso, utilizamos a tecla `esc` para sair do VI e vamos forçar a 
sincronização do branch local com o remoto através do comando: 

    $ git push origin +meubranch_rebase

Rodamos novamente os testes no jenkins e constatamos que não houve falhas. Em seguida iremos 
realizar o merge: 

Para realizar o merge iremos utilizar o github ou stash, vamos até o repositório de nosso projeto e 
clicamos na aba _Pull Requests_, em seguida localizamos nosso branch e clicamos em `pull request`, 
ativamos a opção `delete branch` e agora clicamos em `Merge Pull Request`, uma mensagem indicando que
o merge foi realizado será exibida.

Para finalizar basta apenas clicarmos no botão `Confirm Merge`.