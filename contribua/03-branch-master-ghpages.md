---
layout: post
title: "objectos-dojo :: Branch master e gh-pages"
---

## Trabalhando com branches no dojo

Você já deve saber que a branch _gh-pages_ é responsável por armazenar todos os arquivos que montam as 
páginas estáticas, ou seja, todos os arquivos de markdown, html, less, entre outros. 
Já a branch _master_ é responsável por armazenar todos os códigos JAVA, XML, SQL que foram inseridos 
nos exemplos das páginas.

_Importante: Esta branch é opcional, se em sua página não há códigos para linguagem de programação
não se preocupe com ela_.

## 1. Utilize a branch master (opcional)

Se sua página tem códigos, será necessário utilizar a branch _master_ do projeto para criar estes
códigos e guardá-los para futuras consultas.
### 1.1. Importe o projeto do GitHub

Primeiro faça um _fork_ do projeto _objectos-dojo_ contido no usuário _objectos_.<br> 
Agora execute os seguintes comandos:

	$ cd ~/kdo/projetos
	$ git clone git@github.com:username/objectos-dojo.git
	$ cd objectos-dojo

Na listagem acima substitua o _username_ para o nome correto de seu usuário GitHub

_Nota: Teremos dois projetos na máquina: objectos-dojo (branch master) e objectos-dojo-pages (branch gh-pages).
Com isso o mesmo projeto objectos-dojo terá suas branches em diretórios diferentes, isto é, serão
projetos diferentes, evitando assim, eventuais alterações de arquivos em branches erradas (isto pode
acontecer ao trabalhar em um único projeto com várias branches)._
_Veremos a branch gh-pages (projeto objectos-dojo-pages) no item 2._


### 1.2. Criando códigos na branch master

Com o projeto em sua máquina, crie uma _package_ nos seguintes diretórios:

> objectos-dojo-team/src/main/java/br/com/objectos/dojo/seu-login-de-rede-objectos<br>
> objectos-dojo-team/src/test/java/br/com/objectos/dojo/seu-login-de-rede-objectos

Os códigos JAVA devem ficar nestas _packages_.

_Nota: TODOS os códigos DEVEM possuir um cabeçalho padrão como este:_

    /*
     * Copyright 2011 Objectos, Fábrica de Software LTDA.
     *
     * Licensed under the Apache License, Version 2.0 (the "License"); you may not
     * use this file except in compliance with the License. You may obtain a copy of
     * the License at
     *
     * http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
     * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
     * License for the specific language governing permissions and limitations under
     * the License.
     */

_Veja o exemplo de um código completo com cabeçalho [aqui](https://github.com/objectos/objectos-dojo/blob/master/objectos-dojo-team/src/test/java/br/com/objectos/dojo/taguiar/string/Moeda.java)_. 

### 1.3. Atribua suas alterações ao GitHub
Após concluída as criações/alterações do códigos, devemos colocá-los no github através dos seguintes
comandos:

	$ git add <nome do arquivo>
	$ git commit -m "<mensagem do commit>"
	$ git push origin master

_Importante: Para visualizar o nome do arquivo corretamente, utilize o comando git status_.

Entre no github e faça um [Pull Request](http://help.github.com/send-pull-requests/) de sua 
branch _master_ para a branch _master_ da objectos.

Agora todos os códigos que serviram de exemplos nas páginas poderão ser acessados por completo 
através do github! Veja [aqui](https://github.com/objectos/objectos-dojo/tree/master/objectos-dojo-team).


## 2. Utilize a branch gh-pages

### 2.1. Importe o projeto do GitHub

Para importar o projeto veja [aqui](http://dojo.objectos.com.br/contribua/00-importar.html).

### 2.2. Atribua suas alterações ao GitHub

Após concluída as alterações das páginas devemos colocá-las no github através dos seguintes comandos:

	$ git add <nome do arquivo>
	$ git commit -m "mensagem do commit"
	$ git push origin gh-pages
	
_Importante: Para visualizar o nome do arquivo corretamente utilize o comando git status_.

Entre no github e faça um [Pull Request](http://help.github.com/send-pull-requests/).

Agora os arquivos do projeto poderão ser acessadas através do github! Veja [aqui](https://github.com/objectos/objectos-dojo/tree/gh-pages)

### 2.3. Utilize outra branch (opcional)

Com o projeto em sua máquina trabalhe em uma branch diferente de _gh-pages_. Para isso, execute o seguinte
comando:

	$ git checkout -b gh-pages-contribua
	
Onde _gh-pages-contribua_ é o nome de uma branch.

Trabalhe nela para evitar qualquer alteração na _gh-pages_. (O processo de push é o mesmo descrito anteriormente).