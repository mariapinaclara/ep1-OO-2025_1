package main;

import aluno.MenuAluno;
import java.util.Scanner;
import disciplina.MenuDisciplina;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Menu de Alunos");
            System.out.println("2. Disciplinas");
            System.out.println("3. Turmas");
            System.out.println("4. Avaliações");
            System.out.println("5. Frequência");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    MenuAluno.exibirMenu(scanner);
                    break;
                case 2:
                    MenuDisciplina.exibirMenu(scanner);
                    break;
                case 3:
                    //MenuTurma.exibirMenu(scanner);
                    break;
                case 4:
                    //MenuAvaliacao.exibirMenu(scanner);
                    break;
                case 5:
                    //MenuFrequencia.exibirMenu(scanner);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção Inválida.");
            }

        } while (opcao != 0);
            scanner.close();
    }
   
}
