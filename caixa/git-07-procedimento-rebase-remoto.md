---
layout: post-alpha
title: "Procedimento de rebase com repositórios remotos distintos"
author: "Cristiane Iope Pelissari"
published: true
partof: git
num: 7
outof: 7
---

###Introdução

Neste procedimento falaremos apenas sobre o rebase realizado com repositórios remotos distintos. 
Para obter maiores informações sobre o processo geral de rebase consulte o artigo [Procedimento de rebase](http://dojo.objectos.com.br/caixa/git-06-procedimento-rebase.html/).

Quando utilizamos o git para realizar o controle de versões de nosso projeto várias pessoas podem 
colaborar através de repositórios remotos. Os repositórios remotos funcionam como versões do projeto 
que ficam hospedadas em redes ou na internet. Para integrar as várias versões devemos saber realizar 
o gerenciamento destes repositórios.
  
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


Agora vamos voltar para nosso branch:

	$ git checkout branch_teste
	

Vamos executar o rebase com gh-pages conforme o comando abaixo: 
		
	$ git rebase gh-pages -i
	

Forçaremos a sincronização entre os repositórios através do comando `git push` com o sinal de adição, 
conforme o exemplo abaixo: 	
	 	
	$ git push origin +branch_teste
	

Ao abrir o projeto no github podemos notar que agora o commit do rebase consta em `branch_teste`.