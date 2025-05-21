package turma;

import java.io.*;
import java.util.*;

public class MenuTurma {
    private static final String ARQUIVO_TURMAS = "dados/turmas.txt";
    private static List<Turma> turmas = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        carregarTurmas();

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
                    System.out.println("Voltando ao menu principal...");
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

        Turma turma = new Turma(codigo, nomeDisciplina, nomeProfessor, periodo);
        turmas.add(turma);

        salvarTurmas();

        System.out.println("Turma cadastrada com sucesso!");
    }

    private static void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        System.out.println("\n=== LISTA DE TURMAS ===");
        for (Turma turma : turmas) {
            System.out.println("Turma: " + turma.getCodigo() +
                    " | Disciplina: " + turma.getNomeDisciplina() +
                    " | Professor: " + turma.getNomeProfessor() +
                    " | Período: " + turma.getPeriodo());
        }
    }

    private static void salvarTurmas() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TURMAS))) {
            for (Turma turma : turmas) {
                String linha = turma.getCodigo() + "|" +
                        turma.getNomeDisciplina() + "|" +
                        turma.getNomeProfessor() + "|" +
                        turma.getPeriodo();
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    private static void carregarTurmas() {
        turmas.clear();
        File arquivo = new File(ARQUIVO_TURMAS);
        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 4) {
                    Turma turma = new Turma(partes[0], partes[1], partes[2], partes[3]);
                    turmas.add(turma);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar turmas: " + e.getMessage());
        }
    }
}


