import java.util.Scanner;
import java.lang.Math;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
double total = 0.0;
double valor = 0.0;
int i = 0;
int quantidade = 0;
String nomeImposto = "";
nomeImposto = _key.nextLine();
valor = _key.nextDouble();
quantidade = _key.nextInt();
if(valor>25.5){
switch(nomeImposto) {
case "ICMS":
total = (double)(0);
i = (int)(0);
while(i<quantidade){
total = (double)(total+valor*1.25);
i = (int)(i+1);
}

System.out.println("Produto com imposto de 25 porcento");
System.out.println("Total a pagar ");
System.out.println(total);
break;

case "ISS":
total = (double)(0);
i = (int)(0);
while(i<quantidade){
total = (double)(total+valor*1.15);
i = (int)(i+1);
}

System.out.println("Produto com imposto de 15 porcento");
System.out.println("Total a pagar ");
System.out.println(total);
break;

}

}
else {
total = (double)(0);
i = (int)(0);
while(i<quantidade){
total = (double)(total+valor);
i = (int)(i+1);
}

System.out.println("Produto livre de imposto");
System.out.println("Total a pagar ");
System.out.println(total);
}

  }}