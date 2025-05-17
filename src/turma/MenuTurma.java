package turma;

import java.io.*;
import java.util.Scanner;

public class MenuTurma {
    static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== MENU DE TURMAS ===");
            System.out.println("1. Cadastrar nova turma");
            System.out.println("2. Listar turmas cadastradas");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarTurma();
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

    public static void cadastrarTurma() {
        System.out.print("Código da turma: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        System.out.print("Nome do professor: ");
        String nomeProfessor = scanner.nextLine();

        System.out.print("Período: ");
        String periodo = scanner.nextLine();

        Turma turma = new Turma(codigo, nomeDisciplina, nomeProfessor, periodo);
        salvarTurma(turma);
    }

    public static void salvarTurma(Turma turma) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("turmas.txt", true))) {
            String linha = turma.getCodigo() + ";" +
                           turma.getNomeDisciplina() + ";" +
                           turma.getNomeProfessor() + ";" +
                           turma.getPeriodo();
            writer.write(linha);
            writer.newLine();
            System.out.println("Turma salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar turma: " + e.getMessage());
        }
    }

    public static void listarTurmas() {
        File arquivo = new File("turmas.txt");

        if (!arquivo.exists()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Turma turma = Turma.fromArquivo(linha);
                System.out.println(turma);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
