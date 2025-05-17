package professor;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MenuProfessor {
    private static final String ARQUIVO = "professores.txt";
    private ArrayList<Professor> listaProfessores = new ArrayList<>();

    public MenuProfessor() {
        carregarDoArquivo();
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n=== MENU PROFESSOR ===");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Digite um número válido: ");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner);
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 0:
                    System.out.println("Operação concluída. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarProfessor(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Professor novo = new Professor(nome, matricula);
        listaProfessores.add(novo);
        salvarNoArquivo();
        System.out.println("Professor cadastrado com sucesso!");
    }

    private void listarProfessores() {
        if (listaProfessores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        System.out.println("\n=== PROFESSORES CADASTRADOS ===");
        for (Professor prof : listaProfessores) {
            System.out.println(prof);
        }
    }

    private void salvarNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Professor prof : listaProfessores) {
                writer.write(prof.toLinhaArquivo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Professor prof = Professor.fromLinhaArquivo(linha);
                listaProfessores.add(prof);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
        }
    }
}