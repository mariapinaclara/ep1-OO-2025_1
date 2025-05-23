package disciplina;

import java.io.*;
import java.util.*;

public class MenuDisciplina {
    private static final String CAMINHO_ARQUIVO = "dados/disciplinas.txt";
    private static final String SEPARADOR = "\\|"; // Para leitura
    private static final String SEPARADOR_SAIDA = "|"; // Para escrita
    private static List<Disciplina> disciplinas = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        carregarDisciplinas();
        int opcao;

        do {
            System.out.println("\n=== MENU DE DISCIPLINAS ===");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarDisciplina(scanner);
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 0:
                    salvarDisciplinas();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void cadastrarDisciplina(Scanner scanner) {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();

        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Pré-requisitos (separados por vírgula, ou deixe em branco): ");
        String preReqStr = scanner.nextLine();
        List<String> preRequisitos = new ArrayList<>();
        if (!preReqStr.trim().isEmpty()) {
            String[] preReqArray = preReqStr.split(",");
            for (String pr : preReqArray) {
                preRequisitos.add(pr.trim());
            }
        }

        Disciplina nova = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
        disciplinas.add(nova);
        salvarDisciplinas();
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private static void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\n=== LISTA DE DISCIPLINAS ===");
            for (Disciplina d : disciplinas) {
                System.out.println(d);
            }
        }
    }

    public static void carregarDisciplinas() {
        disciplinas.clear();
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(SEPARADOR);
                if (partes.length >= 3) {
                    String nome = partes[0];
                    String codigo = partes[1];
                    int cargaHoraria = Integer.parseInt(partes[2]);

                    List<String> preRequisitos = new ArrayList<>();
                    if (partes.length == 4 && !partes[3].trim().isEmpty()) {
                        String[] prereqs = partes[3].split(",");
                        for (String pr : prereqs) {
                            preRequisitos.add(pr.trim());
                        }
                    }

                    Disciplina disciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
                    disciplinas.add(disciplina);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
    }

    public static void salvarDisciplinas() {
        File pasta = new File("dados");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Disciplina d : disciplinas) {
                String linha = String.format("%s%s%s%s%d%s%s",
                        d.getNome(), SEPARADOR_SAIDA,
                        d.getCodigo(), SEPARADOR_SAIDA,
                        d.getCargaHoraria(), SEPARADOR_SAIDA,
                        String.join(",", d.getPreRequisitos()));
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }
}
