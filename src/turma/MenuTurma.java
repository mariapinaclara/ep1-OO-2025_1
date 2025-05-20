package turma;

import java.io.*;
import java.util.Scanner;

public class MenuTurma {
    private static final String ARQUIVO_TURMAS = "turmas.txt";

    public static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU DE TURMAS ===");
            System.out.println("1. Cadastrar Turma");
            System.out.println("2. Listar Turmas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarTurma(scanner);
                    break;
                case 2:
                    listarTurmas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarTurma(Scanner scanner) {
        System.out.print("Código da Turma: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome da Disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        System.out.print("Nome do Professor: ");
        String nomeProfessor = scanner.nextLine();

        System.out.print("Período (ex: 2024.1): ");
        String periodo = scanner.nextLine();

        String linha = codigo + "|" + nomeDisciplina + "|" + nomeProfessor + "|" + periodo;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TURMAS, true))) {
            writer.write(linha);
            writer.newLine();
            System.out.println("Turma cadastrada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar turma: " + e.getMessage());
        }
    }

    private static void listarTurmas() {
        File arquivo = new File(ARQUIVO_TURMAS);

        if (!arquivo.exists()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        System.out.println("\n=== LISTA DE TURMAS ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 4) {
                    System.out.println("Turma: " + partes[0] +
                            " | Disciplina: " + partes[1] +
                            " | Professor: " + partes[2] +
                            " | Período: " + partes[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler turmas: " + e.getMessage());
        }
    }
}

