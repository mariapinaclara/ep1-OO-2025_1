package disciplina;

import java.io.*;
import java.util.Scanner;

public class MenuDisciplina {

    static Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n--- MENU DISCIPLINA ---");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 3);
    }

    public static void cadastrarDisciplina() {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());

        System.out.print("Pré-requisito (ou 'Nenhum'): ");
        String prerequisito = scanner.nextLine();

        Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria, prerequisito);
        salvarDisciplina(disciplina);
    }

    public static void salvarDisciplina(Disciplina disciplina) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disciplinas.txt", true))) {
            String linha = disciplina.getNome() + ";" +
                           disciplina.getCodigo() + ";" +
                           disciplina.getCargaHoraria() + ";" +
                           disciplina.getPrerequisito();
            writer.write(linha);
            writer.newLine();
            System.out.println("Disciplina salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplina: " + e.getMessage());
        }
    }

    public static void listarDisciplinas() {
        File arquivo = new File("disciplinas.txt");

        if (!arquivo.exists()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Disciplina disciplina = Disciplina.fromArquivos(linha);
                System.out.println(disciplina);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}
