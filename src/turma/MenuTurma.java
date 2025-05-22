package turma;

import java.util.Scanner;

public class MenuTurma {
    public static void exibirMenu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu de Turmas ===");
            System.out.println("1. Cadastrar turma");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarTurma(scanner);
                    break;
                case 0:
                    System.out.println("Operação encerrada. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarTurma(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigo = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codDisc = scanner.nextLine();

        System.out.print("Matrícula do professor: ");
        String matricula = scanner.nextLine();

        System.out.print("Modalidade (Presencial/Remota): ");
        String modalidade = scanner.nextLine();

        System.out.print("Carga horária: ");
        int carga = scanner.nextInt();

        System.out.print("Tipo de média (1 ou 2): ");
        int tipoMedia = scanner.nextInt();
        scanner.nextLine();

        Turma turma = new Turma(codigo, codDisc, matricula, modalidade, carga, tipoMedia);
        Turma.salvarTurma(turma);
        System.out.println("Turma cadastrada com sucesso!");
    }
}


