package professor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProfessor {

    private static final String ARQUIVO_PROFESSORES = "dados/professores.txt";
    private static List<Professor> professores = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        carregarProfessores();
        int opcao;

        do {
            System.out.println("\n=== MENU PROFESSORES ===");
            System.out.println("1. Cadastrar professor");
            System.out.println("2. Listar professores");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner);
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 0:
                    salvarProfessores();
                    System.out.println("Operação encerrada. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarProfessor(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o usuário do professor: ");
        String usuario = scanner.nextLine();

        Professor professor = new Professor(nome, usuario);
        professores.add(professor);
        System.out.println("Professor cadastrado com sucesso.");
    }

    private static void listarProfessores() {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            System.out.println("\n=== Lista de Professores ===");
            for (Professor professor : professores) {
                System.out.println(professor);
            }
        }
    }

    private static void salvarProfessores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PROFESSORES))) {
            for (Professor professor : professores) {
                writer.write(professor.getNome() + "|" + professor.getUsuario());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    private static void carregarProfessores() {
        professores.clear();
        File arquivo = new File(ARQUIVO_PROFESSORES);

        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 3) {
                    Professor professor = new Professor(dados[0], dados[1]);
                    professores.add(professor);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
        }
    }
}
