package turma;

import java.util.List;
import java.util.Scanner;
import aluno.Aluno;
import aluno.HistoricoAcademicoTurma; 
import disciplina.Disciplina;
import professor.Professor;

public class MenuTurma {
    private Scanner scanner;
    private List<Turma> turmas;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<HistoricoAcademicoTurma> historicosAcademicos; 

    public MenuTurma(Scanner scanner, List<Turma> turmas, List<Aluno> alunos, List<Disciplina> disciplinas, List<Professor> professores, List<HistoricoAcademicoTurma> historicosAcademicos) {
        this.scanner = scanner;
        this.turmas = turmas;
        this.alunos = alunos;
        this.disciplinas = disciplinas;
        this.professores = professores;
        this.historicosAcademicos = historicosAcademicos; 
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE TURMAS ===");
            System.out.println("1. Cadastrar Turma");
            System.out.println("2. Listar Turmas");
            System.out.println("3. Matricular Aluno em Turma");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarTurma();
                    break;
                case 2:
                    listarTurmas();
                    break;
                case 3:
                    matricularAlunoEmTurma();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarTurma() {
        System.out.print("Código da Turma: ");
        String codigo = scanner.nextLine();

        boolean codigoTurmaExistente = false;
        for (Turma t : turmas) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                codigoTurmaExistente = true;
                break;
            }
        }

        if (codigoTurmaExistente) {
            System.out.println("Erro: Já existe uma turma com este código.");
            return;
        }

        System.out.print("Código da Disciplina: ");
        String codigoDisciplina = scanner.nextLine();

        Disciplina disciplinaEncontrada = null;
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigoDisciplina)) {
                disciplinaEncontrada = d;
                break;
            }
        }
        if (disciplinaEncontrada == null) {
            System.out.println("Erro: Disciplina não encontrada.");
            return;
        }

        System.out.print("Matrícula do Professor: ");
        String matriculaProfessor = scanner.nextLine();

        Professor professorEncontrado = null;
        for (Professor p : professores) {
            if (p.getMatricula().equalsIgnoreCase(matriculaProfessor)) {
                professorEncontrado = p;
                break;
            }
        }
        if (professorEncontrado == null) {
            System.out.println("Erro: Professor não encontrado.");
            return;
        }

        System.out.print("Modalidade (Presencial/Remota): ");
        String modalidade = scanner.nextLine();
        System.out.print("Carga Horária da Turma: "); 
        int cargaHoraria = scanner.nextInt();
        System.out.print("Tipo de Média (1 para Tipo A, 2 para Tipo B): ");
        int tipoMedia = scanner.nextInt();
        scanner.nextLine();

        Turma novaTurma = new Turma(codigo, codigoDisciplina, matriculaProfessor, modalidade, cargaHoraria, tipoMedia);
        this.turmas.add(novaTurma);
        System.out.println("Turma cadastrada com sucesso!");
    }

    private void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }
        System.out.println("\n--- Lista de Turmas ---");
        for (Turma t : turmas) {
            System.out.println("Código: " + t.getCodigo() + " |Disciplina: " + t.getCodigoDisciplina() + " | Professor: " + t.getMatriculaProfessor() + " | Modalidade: " + t.getModalidade() + " | Carga Horária: " + t.getCargaHoraria() + " | Tipo Média: " + t.getTipoMedia());

            if (!t.getAlunosMatriculados().isEmpty()) {
                System.out.print("   Alunos Matriculados: ");
                for (Aluno a : t.getAlunosMatriculados()) {
                    System.out.print(a.getNome() + " (" + a.getMatricula() + "); ");
                }
                System.out.println();
            } else {
                System.out.println("   Nenhum aluno matriculado nesta turma.");
            }
        }
    }

    private void matricularAlunoEmTurma() {
        System.out.print("Matrícula do Aluno: ");
        String matriculaAluno = scanner.nextLine();

        Aluno alunoEncontrado = null;
        for (Aluno a : alunos) {
            if (a.getMatricula().equalsIgnoreCase(matriculaAluno)) {
                alunoEncontrado = a;
                break;
            }
        }
        if (alunoEncontrado == null) {
            System.out.println("Erro: Aluno não encontrado.");
            return;
        }

        System.out.print("Código da Turma: ");
        String codigoTurma = scanner.nextLine();

        Turma turmaEncontrada = null;
        for (Turma t : turmas) {
            if (t.getCodigo().equalsIgnoreCase(codigoTurma)) {
                turmaEncontrada = t;
                break;
            }
        }
        if (turmaEncontrada == null) {
            System.out.println("Erro: Turma não encontrada.");
            return;
        }

        if (turmaEncontrada.getAlunosMatriculados().contains(alunoEncontrado)) {
            System.out.println("Erro: Aluno já matriculado nesta turma.");
            return;
        }

        Disciplina disciplinaDaTurma = null;
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equals(turmaEncontrada.getCodigoDisciplina())) {
                disciplinaDaTurma = d;
                break;
            }
        }

        if (disciplinaDaTurma == null) {
            System.err.println("Erro: Disciplina associada à turma não encontrada. Não é possível criar histórico.");
            return;
        }

        int cargaHorariaParaHistorico = disciplinaDaTurma.getCreditos(); 
        int tipoMediaDaTurma = turmaEncontrada.getTipoMedia(); 

        HistoricoAcademicoTurma novoHistorico = new HistoricoAcademicoTurma(alunoEncontrado.getMatricula(), turmaEncontrada.getCodigo(), cargaHorariaParaHistorico, tipoMediaDaTurma);

        turmaEncontrada.matricularAluno(alunoEncontrado);

        alunoEncontrado.adicionarHistoricoTurma(turmaEncontrada.getCodigo(), novoHistorico);

        this.historicosAcademicos.add(novoHistorico);

        System.out.println("Aluno " + alunoEncontrado.getNome() + " matriculado na turma " + turmaEncontrada.getCodigo() + " com sucesso! Histórico criado.");
    }
}


