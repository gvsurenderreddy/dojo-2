---
layout: post-alpha
title: "Utilizando métodos genéricos"
author: "Carolene Bertoldi"
user: "CaroleneBertoldi"
date: "2013-10-01"
published: true
partof: procedimento-oo
outof: 0
num: 0
---

##Introdução 

Métodos genéricos são utilizados quando uma implementação pode ser utilizada por mais de um tipo de objeto.
A partir do exemplo abaixo, discutiremos quando utiliza-los, o porquê, e que o ele resolve de diferente das
metologias utilizadas anteriormente.

### Problema

Em um sistema de uma universidade um departamento precisa informar ao outro pedênias a serem resolvidas.
Por exemplo, a Secretária envia a Tesouraria uma lista dos alunos que precisam renovar o PROUNI, 
o Coordenação envia ao Departamento de Iniciação Científica lista dos horários dos professores de seu 
respectivo curso, para que a Inicialização Cientica encontre um horário, a Secretária solicita materiais
ao Almoxarifado e assim por diante.

#### Primeira solução

A necessidade de troca de pedidos surgiu a cada departamento de forma isolada, um a um, segundo 
sua necessidade. Assim, o ERP recebeu como requisitos, a elaboração de solicitações de cada módulo, 
segundo o interesse de cada módulo. Todos os pedidos deveriam ser impressos, protocolados e assinados
pelos responsáveis, um remetente enviava a solicitação que era recebida pelo destinatário e despachada
de volta com o resultado. Todas possuiam prazos de entrega a serem cumpridos.

Ào módulo Secretária chegou os pedidos da Tesouraria das listagens de alunos matriculados que possuiam 
bolsa do PROUNI, primeira solução encontrada, gerar um documento de solicitação para listar os alunos
com bolsas do PROUNI matriculados neste semestre.

Código de impressão do documento:






Estes relatórios possuem uma estrutura padrão, independente do conteúdo que demostra serem todos listagens
de pendências da universidade.
