package professor;

import java.io.*;
import java.util.Scanner;

public class MenuProfessor {
    private static final String CAMINHO_ARQUIVO = "dados/professores.txt";

    public void exibirMenu() {
        criarPastaDadosSeNaoExistir();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU DE PROFESSORES ===");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProfessor(scanner);
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void cadastrarProfessor(Scanner scanner) {
        System.out.print("Matrícula do Professor: ");
        String matricula = scanner.nextLine();

        System.out.print("Nome do Professor: ");
        String nome = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO, true))) {
            writer.write(matricula + ";" + nome);
            writer.newLine();
            System.out.println("Professor cadastrado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
        }
    }

    private void listarProfessores() {
        File arquivo = new File(CAMINHO_ARQUIVO);

        if (!arquivo.exists()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }

        System.out.println("\n=== LISTA DE PROFESSORES ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    System.out.println("Matrícula: " + partes[0] + " | Nome: " + partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler professores: " + e.getMessage());
        }
    }

    private void criarPastaDadosSeNaoExistir() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }
    }
}
