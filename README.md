# TrabalhoPOO4

Este documento apresenta um guia completo do projeto, incluindo objetivos, funcionamento, estrutura dos arquivos e instruções de execução.

# Objetivo do Projeto

Este projeto foi desenvolvido para a disciplina de Programação Orientada a Objetos.
O foco é demonstrar o uso de:

* Leitura e manipulação de arquivos XML.
* Integração do Java com bibliotecas externas (DLL) por meio do JNA.
* Execução de funções específicas voltadas para documentos fiscais.
* Estruturas de classes e métodos em Java aplicados em um cenário real.

# O que o Programa Faz

1. Lê diferentes tipos de arquivos XML (como NFe, NFCe, SAT e Cancelamento).
2. Carrega uma DLL externa usando JNA para acesso a funções nativas.
3. Processa os dados dos arquivos XML.
4. Exibe informações ou simula ações como impressão, validação ou leitura de campos específicos.
5. Demonstra integração entre Java e bibliotecas externas.

# Tecnologias Utilizadas

* Java
* JNA (Java Native Access)
* Arquivos XML
* DLL externa
* IDE de preferência (VS Code, Eclipse, NetBeans ou IntelliJ)

# Estrutura do Projeto

O projeto contém os seguintes arquivos:

* Main.java
  Contém o código principal do programa, incluindo leitura de XML e uso da DLL.

* libs/
  Pasta destinada para bibliotecas, dependências ou arquivos auxiliares utilizados pelo programa.

* E1_Impressora01.dll
  DLL externa usada para simular funções de impressora e integração nativa via JNA.

* Arquivos XML utilizados para teste:

  * NFe.xml
  * NFCe.xml
  * NFCeCancelamento.xml
  * CANC_SAT.xml
  * XMLSAT.xml

Esses arquivos servem como base para testar a leitura e interpretação feita pelo programa.

## Como Executar o Projeto 

1: Para rodar o programa, siga esses passos:
-Baixe o repositório para o seu computador ou faça o clone pelo GitHub.
-Abra a pasta do projeto em alguma IDE (VS Code, NetBeans, Eclipse ou IntelliJ).
2: Verifique se o arquivo E1_Impressora01.dll está na pasta certa.
-Esse arquivo é muito importante, porque é ele que o Java usa para “conversar” com funções nativas da impressora.
-Algumas IDEs precisam que esse arquivo esteja:
-na pasta raiz do projeto, ou configurado no caminho do sistema (variáveis de ambiente).
-Se não estiver, o programa pode dar erro ao tentar acessar a DLL.
3: Com tudo no lugar, execute o arquivo Main.java.
-O programa vai automaticamente procurar os arquivos XML que estão dentro da pasta do projeto.
4: Quando tudo estiver funcionando, as informações e resultados vão aparecer no console (a área onde aparecem as mensagens do programa).

## Como o Código Funciona

Primeiro, o Java precisa conseguir usar funções que não são dele.
Para isso existe o JNA, que permite que o Java chame funções que estão dentro da DLL.

A DLL é como se fosse um “arquivo especial” que já tem várias funções prontas, por exemplo:

funções que simulam impressão
validação de documentos
leitura de dados

Por isso ela precisa estar no lugar certo.

O Java cria uma interface (uma espécie de “tradutor”) explicando quais funções da DLL ele quer usar.
Essa interface é carregada automaticamente quando o programa roda.

Depois disso, o programa começa a abrir os arquivos XML.
Um XML é apenas um arquivo de texto organizado, geralmente usado para guardar informações de notas fiscais.

O código usa comandos como FileInputStream para abrir esses arquivos e ler os dados que estão dentro.
É como se o programa abrisse o arquivo e fosse “lendo linha por linha”.

Quando o programa lê esses arquivos:

ele pega informações importantes

interpreta o conteúdo

envia alguns dados para funções da DLL, se necessário

No final, o programa mostra tudo no console.
Pode aparecer mensagens como:

qual arquivo foi lido
informações extraídas
resultados de funções chamadas na DLL
erros, caso algo esteja errado

Em resumo:
O Java lê os arquivos → interpreta → usa funções da DLL quando precisa → mostra tudo na tela.

# Integrantes do Grupo (Ordem Alfabética)

* Gabriel Pegozzi
* Karollayne Gabrielly
* Letícia Nascimento
* Nicolas de Souza
* Vinicius Ferreira

# Disciplina

Programação Orientada a Objetos (POO)

# Ano

2025
