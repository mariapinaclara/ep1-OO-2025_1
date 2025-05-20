package disciplina;

import java.io.*;
import java.util.*;

public class MenuDisciplina {
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static final String ARQUIVO_DISCIPLINAS = "disciplinas.txt";

    public static void exibirMenu(Scanner scanner) {
        carregarDisciplinas();

        int opcao = -1;
        do {
            System.out.println("\n=== MENU DE DISCIPLINAS ===");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("3. Criar turma");
            System.out.println("4. Listar turmas");
            System.out.println("0. Voltar ao principal");
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
                case 3:
                    criarTurma(scanner);
                    break;
                case 4:
                    listarTurmas();
                    break;
                case 0:
                    salvarDisciplinas();
                    System.out.println("Voltando ao menu principal...");
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

        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Disciplina já cadastrada com esse código.");
                return;
            }
        }

        System.out.print("Carga horária: ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Quantidade de pré-requisitos: ");
        int qtdPreReq = scanner.nextInt();
        scanner.nextLine();

        List<String> preRequisitos = new ArrayList<>();
        for (int i = 0; i < qtdPreReq; i++) {
            System.out.print("Código do pré-requisito " + (i + 1) + ": ");
            String pre = scanner.nextLine();
            preRequisitos.add(pre);
        }

        Disciplina novaDisciplina = new Disciplina(nome, codigo, cargaHoraria, preRequisitos);
        disciplinas.add(novaDisciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
        salvarDisciplinas();
    }

    private static void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        System.out.println("\nLista de disciplinas:");
        for (Disciplina d : disciplinas) {
            System.out.println(d);
        }
    }

    private static void criarTurma(Scanner scanner) {
        System.out.print("Código da disciplina para criar turma: ");
        String codigo = scanner.nextLine();

        Disciplina disciplina = null;
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                disciplina = d;
                break;
            }
        }

        if (disciplina == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Nome do professor: ");
        String professor = scanner.nextLine();

        System.out.print("Semestre (ex: 2025.1): ");
        String semestre = scanner.nextLine();

        System.out.println("Formas de avaliação:");
        System.out.println("1 - Média Final = (P1 + P2 + P3 + L + S)/5");
        System.out.println("2 - Média Final = (P1 + 2*P2 + 3*P3 + L + S)/8");
        System.out.print("Escolha a forma de avaliação (1 ou 2): ");
        String formaAvaliacao = scanner.nextLine();

        System.out.print("A turma é presencial? (s/n): ");
        boolean presencial = scanner.nextLine().equalsIgnoreCase("s");

        String sala = "";
        if (presencial) {
            System.out.print("Sala: ");
            sala = scanner.nextLine();
        }

        System.out.print("Horário (ex: seg 19h às 21h): ");
        String horario = scanner.nextLine();

        System.out.print("Capacidade máxima de alunos: ");
        int capacidade = scanner.nextInt();
        scanner.nextLine();

        // Verificar se já existe turma da mesma disciplina no mesmo horário (não pode repetir)
        for (Turma t : disciplina.getTurmas()) {
            if (t.getHorario().equalsIgnoreCase(horario)) {
                System.out.println("Já existe uma turma dessa disciplina neste horário.");
                return;
            }
        }

        Turma novaTurma = new Turma(codigo, professor, semestre, formaAvaliacao, presencial, sala, horario, capacidade);
        disciplina.adicionarTurma(novaTurma);
        System.out.println("Turma criada com sucesso!");
        salvarDisciplinas();
    }

    private static void listarTurmas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        System.out.println("\nTurmas cadastradas:");
        for (Disciplina d : disciplinas) {
            for (Turma t : d.getTurmas()) {
                System.out.println(t);
            }
       

