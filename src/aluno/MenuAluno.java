package aluno;

import java.util.List;
import java.util.Scanner;
import java.io.File;

public class MenuAluno {

    private static List<Aluno> alunos;
    private static final String ARQUIVO_ALUNOS = "dados/alunos.txt";

    public static void exibirMenu(Scanner scanner) {
        verificarDiretorio(); // Garante que a pasta "dados" exista

        alunos = Aluno.carregarAlunos(ARQUIVO_ALUNOS);

        int opcao;

        do {
            System.out.println("\n=== MENU DE ALUNOS ===");
            System.out.println("1. Cadastrar aluno(a)");
            System.out.println("2. Listar alunos(as)");
            System.out.println("0. Voltar ao principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner);
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 0:
                    Aluno.salvarAlunos(alunos, ARQUIVO_ALUNOS);
                    System.out.println("Operação encerrada. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarAluno(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        System.out.print("Aluno Especial? (s/n): ");
        String especial = scanner.nextLine();

        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(matricula)) {
                System.out.println("Já existe um aluno com essa matrícula.");
                return;
            }
        }

        Aluno novoAluno;
        if (especial.equalsIgnoreCase("s")) {
            novoAluno = new AlunoEspecial(nome, matricula, curso);
        } else {
            novoAluno = new Aluno(nome, matricula, curso);
        }

        alunos.add(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
        Aluno.salvarAlunos(alunos, ARQUIVO_ALUNOS);
    }

    private static void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\nLista de alunos:");
        for (Aluno aluno : alunos) {
            String tipo = (aluno instanceof AlunoEspecial) ? "Especial" : "Normal";
            System.out.println("Tipo: " + tipo + " | Nome: " + aluno.getNome() + " | Matrícula: " + aluno.getMatricula() + " | Curso: " + aluno.getCurso());
            if (!aluno.getDisciplinasMatriculadas().isEmpty()) {
                System.out.println("  Disciplinas matriculadas: " + String.join(", ", aluno.getDisciplinasMatriculadas()));
            }
        }
    }

    private static void verificarDiretorio() {
        File dir = new File("dados");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}


