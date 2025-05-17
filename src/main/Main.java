package main;

import java.util.Scanner;
import aluno.MenuAluno;
import disciplina.MenuDisciplina;
import professor.MenuProfessor;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Menu de Alunos");
            System.out.println("2. Menu de Disciplinas");
            System.out.println("3. Menu de Turmas");
            System.out.println("4. Menu de Professores");
            System.out.println("5. Avaliações");
            System.out.println("6. Frequência");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    MenuAluno.exibirMenu(scanner);
                    break;
                case 2:
                    MenuDisciplina.exibirMenu();
                    break;
                case 3:
                    //MenuTurma.exibirMenu(scanner);
                    break;
                case 4:
                    MenuProfessor menuProfessor = new MenuProfessor();
                    menuProfessor.exibirMenu();
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
