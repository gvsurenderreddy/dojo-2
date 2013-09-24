---
layout: post-alpha
title: "Procedimento de rebase com repositórios remotos distintos"
author: "Cristiane Iope Pelissari"
published: true
partof: git
num: 7
outof: 7
---

##Introdução

Neste procedimento falaremos apenas sobre o rebase realizado com repositórios remotos distintos. 
Para obter maiores informações sobre o processo geral de rebase consulte o artigo [Procedimento de rebase](http://dojo.objectos.com.br/caixa/git-06-procedimento-rebase.html/).

Quando utilizamos o git para realizar o controle de versões de nosso projeto várias pessoas podem 
colaborar através de repositórios remotos. Os repositórios remotos funcionam como versões do projeto 
que ficam hospedadas em redes ou na internet. Para integrar as várias versões devemos saber realizar 
o gerenciamento destes repositórios.

##Adicionando um repositório remoto
 
Vamos considerar que fizemos um `fork` do projeto em que estamos trabalhando e que nosso branch 
`branch_teste` foi criado a partir do branch chamado `gh-pages`.
  
Antes de realizar o rebase é necessário fazer alguns procedimentos, o primeiro deles consiste em 
aplicar o comando `git status` para verificar se há alguma pendência em nosso branch atual:

	$ git status
	  On branch branch_teste
	  nothing to commit (working directory clean)

Verificamos que não há pendências, agora vamos usar o comando abaixo para exibir os repositórios 
remotos:

      $ git remote show
      	origin
    
Quando realizamos o `fork` do projeto, automaticamente um repositório remoto chamado origin foi criado, 
agora iremos adicionar outro repositório remoto ao projeto. Para fazer isso clicamos no projeto no 
github e copiamos sua URL, que é exibida no campo `SSH clone URL`. Em nosso exemplo a URL será:
git@github.com:objectos/objectos-dojo.git.
Agora vamos adicionar esse repositório, para isso voltamos ao terminal e executamos o comando `git 
remote add` seguido de um nome que será associado a URL e colamos a URL:  
          
	$ git remote add obj git@github.com:objectos/objectos-dojo.git
     
Agora o novo repositório remoto foi adicionado e seu nome é obj.
Podemos notar que ao executar novamente o comando `git remote show` o novo repositório remoto é exibido:     
      
	$ git remote show
	  obj
	  origin
	  
Se quisermos verificar mais informações sobre um remoto específico utilizamos o comando `git remote 
show nomeremoto`, uma tela semelhante ao nosso exemplo será exibida:

	$ git remote show obj
	* remote obj
  	URL: git@github.com:objectos/objectos-dojo.git
  	Remote branches:
    gh-pages                  tracked
    master                    tracked
	  


##Atualizando e integrando atualizações

Antes de iniciar este processo iremos anotar o número do último _commit_ para que caso ocorra algum
problema durante o processo do rebase seja possível retornar ao estado deste _commit_. Para fazer
isso vamos utilizar o seguinte comando para visualizar o número do _commit_ e em seguida anotar:

	$ git log 

Agora vamos mudar para o branch gh-pages:

	$ git checkout gh-pages
	

Executamos o comando `git fetch` para trazer as atualizações do repositório remoto origin para nosso
repositório local:   

	$ git fetch origin 
	

Feito isso executamos o comando `git merge` para integrar as informações do repositório origin com 
gh-pages:    	

 	$ git merge origin/gh-pages
 
Através do comando `git fetch` vamos trazer as atualizações do repositório remoto obj: 

	$ git fetch obj
 

Em seguida realizar `merge` entre obj e gh-pages;

	$ git merge obj/gh-pages

Nosso repositório não segue necessariamente a mesma organização que o repositório remoto que adicionamos.
Podemos visualizar essas diferenças através do _github_, se compararmos o `gh-pages` de ambos veremos 
que a ordem de _commits_ e _merges_ não é mesma.

Para evitar conflitos, precisamos "apontar" nosso repositório para o mesmo _commit_ que está no repositório
remoto que adicionamos anteriormente.

Para isso, verificamos no _github_ o número do último _commit_ do repositório remoto e guardamos esta
informação.  

Agora utilizamos o comando `git reset --hard` e em seguida colamos o número do _commit_, para que desta
forma nosso repositório aponte para o mesmo _commit_ que o repositório remoto:

	$ git reset --hard numerodocommit
	

##Processo do Rebase

Agora vamos voltar para nosso branch:

	$ git checkout branch_teste


Vamos executar o rebase com gh-pages conforme o comando abaixo: 
		
	$ git rebase gh-pages -i
	

Forçaremos a sincronização entre os repositórios através do comando `git push` com o sinal de adição, 
conforme o exemplo abaixo: 	
	 	
	$ git push origin +branch_teste
	

Ao abrir o projeto no github podemos notar que agora o commit do rebase consta em `branch_teste`.

Por fim, realizaremos o `merge` de nosso branch com gh-pages:
Voltamos para o github, vamos até o repositório de nosso projeto e clicamos na aba _Pull Requests_, 
em seguida localizamos nosso branch e clicamos em `pull request`, ativamos a opção `delete branch` e 
agora clicamos em `Merge Pull Request`, uma mensagem indicando que o merge foi realizado será exibida.
Para finalizar basta apenas clicarmos no botão `Confirm Merge`.