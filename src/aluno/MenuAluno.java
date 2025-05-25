package aluno;

import java.util.List;
import java.util.Scanner;
import turma.Turma;


public class MenuAluno {
    private Scanner scanner;
    private List<Aluno> alunos;
    private List<Turma> turmas;
    private List<HistoricoAcademicoTurma> historicosAcademicos;

    public MenuAluno(Scanner scanner, List<Aluno> alunos, List<Turma> turmas, List<HistoricoAcademicoTurma> historicosAcademicos) {
        this.scanner = scanner;
        this.alunos = alunos;
        this.turmas = turmas;
        this.historicosAcademicos = historicosAcademicos;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== MENU DE ALUNOS ===");
            System.out.println("1. Cadastrar aluno(a)");
            System.out.println("2. Listar alunos(as)");
            System.out.println("3. Exibir histórico de um aluno");
            System.out.println("0. Voltar ao principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    listarAlunos();
                    break;
                case 3:
                    exibirHistoricoDeAluno();
                    break;
                case 0:
                    System.out.println("Operação encerrada. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarAluno() {
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
    }

    private void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n===  Lista de alunos ===");
        for (Aluno aluno : alunos) {
            String tipo = (aluno instanceof AlunoEspecial) ? "Especial" : "Normal";
            System.out.println("Tipo: " + tipo + " | Nome: " + aluno.getNome() + " | Matrícula: " + aluno.getMatricula() + " | Curso: " + aluno.getCurso());
        }
    }

    private void exibirHistoricoDeAluno() {
        System.out.print("Digite a matrícula do aluno para exibir o histórico: ");
        String matricula = scanner.nextLine();

        Aluno alunoEncontrado = null;
        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(matricula)) {
                alunoEncontrado = a;
                break;
            }
        }

        if (alunoEncontrado == null) {
            System.out.println("Aluno com matrícula " + matricula + " não encontrado.");
            return;
        }

        System.out.println("\n=== Histórico de " + alunoEncontrado.getNome() + " ===");

        List<HistoricoAcademicoTurma> historicosDoAluno = historicosAcademicos.stream().filter(h -> h.getMatriculaAluno().equalsIgnoreCase(matricula)).collect(java.util.stream.Collectors.toList());

        if (historicosDoAluno.isEmpty()) {
            System.out.println("Nenhum histórico acadêmico encontrado para este aluno.");
            return;
        }

        for (HistoricoAcademicoTurma hist : historicosDoAluno) {
            System.out.println("\n- Turma: " + hist.getCodigoTurma());
            
            Turma turmaRelacionada = turmas.stream().filter(t -> t.getCodigo().equals(hist.getCodigoTurma())).findFirst().orElse(null);

            if (turmaRelacionada != null) {
                System.out.println("  Disciplina: " + turmaRelacionada.getMateria());
                System.out.println("  Carga Horária: " + turmaRelacionada.getCargaHoraria() + "h");
            } else {
                System.out.println("  Dados da turma não encontrados (código: " + hist.getCodigoTurma() + ")");
            }

            System.out.println("  Notas:");
            if (hist.getNotas().isEmpty()) {
                System.out.println("    Nenhuma nota lançada.");
            } else {
                hist.getNotas().forEach((tipo, valor) -> System.out.println("    " + tipo + ": " + valor));
            }
            System.out.println("  Faltas: " + hist.getFaltas());
        }

        @SuppressWarnings("unused")
        HistoricoAcademicoTurma verificaUso = HistoricoAcademicoTurma.fromString("temp|temp|0|0|");
    }
}