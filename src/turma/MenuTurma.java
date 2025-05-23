package turma;

import java.io.*;
import java.util.*;

public class MenuTurma {
    private static final String CAMINHO_ARQUIVO = "dados/turmas.txt";
    private static List<Turma> turmas = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        carregarTurmas();
        int opcao;
        do {
            System.out.println("\n=== Menu de Turmas ===");
            System.out.println("1. Cadastrar turma");
            System.out.println("2. Listar turmas");
            System.out.println("0. Voltar ao menu principal");
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
                    salvarTurmas();
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
        turmas.add(turma);
        salvarTurmas();
        System.out.println("Turma cadastrada com sucesso!");
    }

    private static void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            System.out.println("\n=== Lista de Turmas ===");
            for (Turma turma : turmas) {
                System.out.println(turma);
            }
        }
    }

    private static void salvarTurmas() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Turma turma : turmas) {
                writer.write(String.format("%s|%s|%s|%s|%d|%d",
                        turma.getCodigo(),
                        turma.getCodigoDisciplina(),
                        turma.getMatriculaProfessor(),
                        turma.getModalidade(),
                        turma.getCargaHoraria(),
                        turma.getTipoMedia()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    private static void carregarTurmas() {
        turmas.clear();
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 6) {
                    String codigo = partes[0];
                    String codDisc = partes[1];
                    String matricula = partes[2];
                    String modalidade = partes[3];
                    int carga = Integer.parseInt(partes[4]);
                    int tipoMedia = Integer.parseInt(partes[5]);

                    Turma turma = new Turma(codigo, codDisc, matricula, modalidade, carga, tipoMedia);
                    turmas.add(turma);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar turmas: " + e.getMessage());
        }
    }
}



