# isilanguage-compiler

## ANTLR Download
curl -O https://www.antlr.org/download/antlr-4.7.1-complete.jar

## Comandos

java -cp .:antlr-4.7.1-complete.jar org.antlr.v4.Tool IsiLang.g4 -package compiler.parser -o ./compiler/parser

javac -cp .:antlr-4.7.1-complete.jar compiler/**/*.java

java -cp .:antlr-4.7.1-complete.jar compiler/main/MainClass