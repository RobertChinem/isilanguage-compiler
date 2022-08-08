import java.util.Scanner;
import java.lang.Math;

public class MainClass {
    public static void main(String args[]) {
        Scanner _key = new Scanner(System.in);
        int idade = 0;
        String aluno = "";
        String prof = "";
        aluno = _key.nextLine();
        prof = _key.nextLine();
        idade = _key.nextInt();
        switch (aluno) {
            case "a":
                switch (prof) {
                    case "p":
                        System.out.println("ap");
                        break;

                    case "pp":
                        System.out.println("app");
                        break;

                }

                if (Math.pow(idade, 2) <= 18) {
                    System.out.println(idade);
                }

                break;

            case "b":
                switch (prof) {
                    case "p":
                        System.out.println("bp");
                        break;

                    case "pp":
                        System.out.println("bpp");
                        break;

                }

                if (idade <= 20) {
                    System.out.println(idade);
                }

                break;

        }

    }
}