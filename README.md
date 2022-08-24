## Integrantes

- Antonio Lucas Barbosa Ferreira, 11201921416
- Leonardo Ryo Nakagawa, 11201812233
- Nathalia Favero Amorim, 11201922326
- Pedro de Souza Tunin, 11201811959
- Robert Chinem Fujii, 11201810569

## Instruções

### Requisitos
- Node JS v14 ou superior
- Java 1.8 ou superior

```bash
npm install
npm start
```

## Links

[Editor Web](https://isi-language.herokuapp.com)

[Vídeo](https://youtu.be/4C9FNtqHJYI)

## ANTLR Download
curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar

## Comandos

java -cp .:antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package compiler.parser -o ./compiler/parser

javac -cp .:antlr-4.7.1-complete.jar compiler/**/*.java

java -cp .:antlr-4.7.1-complete.jar compiler/main/MainClass

## Sintaxe

### Switch/Case (escolha/caso)

A sintaxe para variáveis do tipo **X** é a seguinte:

```
escolha (X) {
    caso (x1) {
        ...
    }
    caso (x2) {
        ...
    }
    .
    .
    .
}
```
Onde **X** pode ser uma variável do tipo Inteiro ou Texto, e os valores **x1** e **x2** são valores literais (não podem ser variáveis).

### Exponenciação

Decidimos utilizar o operador ```**``` para a exponenciação. A sintaxe é a seguinte:

```
a ** b
```

Onde **a** e **b** são números (Inteiro/Decimal).

# Progresso

## Critérios de Avaliação

### Anexo 1 - Elementos adicionais (pelo menos 2 dos itens abaixo) ✅

✅ Nova instrução para Switch/Case (escolha/caso)

❌ Mais tipos de dados

✅ Inclusão de novos operadores (exponenciação, raiz quadrada, logaritmos) |
 (Implementado: exponenciação)

❌ Geração de código para mais de uma linguagem diferente


### Anexo 2 - Elementos Extraordinarios (pelo menos 2 itens abaixo) ✅

❌ Criar um interpretador a partir da AST

❌ Criar um editor com Highlights de palavras reservadas (editor Desktop)

✅ Criar um editor Web para o código

✅ Tornar o compilador um Webservice para receber programas e enviar respostas de possíveis erros


### Instruções para Entrega do Projeto ✅

✅ Possuir 2 tipos de dados (pelo menos 1 String) 	

✅ Possuir a instrução de decisão (if/else)	

✅ Pelo menos 1 estrutura de repetição	

✅ Verificar Atribuições com compatibilidade de tipos (semântica) 	

✅ Possuir operações de Entrada e Saída	

✅  Aceitar números decimais 	

✅ Verificar declaração de variávies (não usar variáveis que não foram declaradas)	

✅ Verificar se há variáveis declaradas e não-utilizadas (warning)	

✅ Geração de pelo menos 1 linguagem destino (C/Java/Python)






