package professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProfessor {

    // Lista simples para guardar nomes de professores (pode trocar por objetos depois)
    private static List<String> professores = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n=== MENU DE PROFESSORES ===");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner);
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        professores.add(nome);
        System.out.println("Professor '" + nome + "' cadastrado com sucesso!");
    }

    private static void listarProfessores() {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }
        System.out.println("\nLista de Professores:");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println((i + 1) + ". " + professores.get(i));
        }
    }
}
