package aluno;

import java.util.Scanner;

public class MenuAluno {
    public static void exibirMenu(Scanner scanner) {
        int opcao = -1;

        do {
            System.out.println("\n=== MENU DE ALUNOS ===");
            System.out.println("1. Cadastrar aluno(a)");
            System.out.println("2. Listar alunos(a)");
            System.out.println("0. Voltar ao principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); //Limpa o buffer

            switch (opcao) {
                case 1:
                    Aluno.cadastrarAluno(scanner);
                    break;
                case 2:
                    Aluno.listarAlunos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
