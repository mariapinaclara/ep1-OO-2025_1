package avaliacao;

import java.util.*;

public class MenuAvaliacao {
    private static final String PASTA_AVALIACOES = "dados/avaliacoes/";

    private static List<Avaliacao> avaliacoes = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        int opcao = -1;

        do {
            System.out.println("\n=== MENU DE AVALIAÇÕES ===");
            System.out.println("1. Criar avaliação");
            System.out.println("2. Listar avaliações");
            System.out.println("3. Lançar nota");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarAvaliacao(scanner);
                    break;
                case 2:
                    listarAvaliacoes();
                    break;
                case 3:
                    lancarNota(scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void criarAvaliacao(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        System.out.print("Descrição da avaliação: ");
        String descricao = scanner.nextLine();

        System.out.print("Peso da avaliação (ex: 0.4): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();

        Avaliacao nova = new Avaliacao(codigoTurma, descricao, peso);
        avaliacoes.add(nova);

        // Salvar no arquivo (nome baseado no código turma + descrição)
        nova.salvar(PASTA_AVALIACOES + codigoTurma + "_" + descricao.replaceAll("\\s+", "_") + ".txt");

        System.out.println("Avaliação criada e salva com sucesso.");
    }

    private static void listarAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação cadastrada.");
            return;
        }
        System.out.println("Avaliações cadastradas:");
        for (Avaliacao av : avaliacoes) {
            System.out.printf("Turma: %s, Descrição: %s, Peso: %.2f%n", av.getCodigoTurma(), av.getDescricao(), av.getPeso());
        }
    }

    private static void lancarNota(Scanner scanner) {
        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();

        System.out.print("Digite a descrição da avaliação: ");
        String descricao = scanner.nextLine();

        Avaliacao av = buscarAvaliacao(codigoTurma, descricao);
        if (av == null) {
            System.out.println("Avaliação não encontrada.");
            return;
        }

        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        System.out.print("Nota: ");
        double nota = scanner.nextDouble();
        scanner.nextLine();

        av.lancarNota(matricula, nota);
        av.salvar(PASTA_AVALIACOES + codigoTurma + "_" + descricao.replaceAll("\\s+", "_") + ".txt");

        System.out.println("Nota lançada com sucesso.");
    }

    private static Avaliacao buscarAvaliacao(String codigoTurma, String descricao) {
        for (Avaliacao av : avaliacoes) {
            if (av.getCodigoTurma().equals(codigoTurma) && av.getDescricao().equalsIgnoreCase(descricao)) {
                return av;
            }
        }
        return null;
    }
}
