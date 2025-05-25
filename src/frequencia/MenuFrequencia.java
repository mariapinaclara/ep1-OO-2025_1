package frequencia; 

import java.util.List;
import java.util.Scanner;
import aluno.Aluno;
import aluno.HistoricoAcademicoTurma; 
import turma.Turma;

public class MenuFrequencia { 
    private Scanner scanner;
    private List<Aluno> alunos;
    private List<Turma> turmas;
    private List<HistoricoAcademicoTurma> historicosAcademicos; 

    public MenuFrequencia(Scanner scanner, List<Aluno> alunos, List<Turma> turmas, List<HistoricoAcademicoTurma> historicosAcademicos) {
        this.scanner = scanner;
        this.alunos = alunos;
        this.turmas = turmas;
        this.historicosAcademicos = historicosAcademicos;
    }

    public void exibirMenu() {
        System.out.println("\n=== Lançar Notas e Gerenciar Faltas ===");
        System.out.print("Digite a matrícula do aluno: ");
        String matriculaAluno = scanner.nextLine();

        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();

        Aluno aluno = encontrarAlunoPorMatricula(matriculaAluno);
        Turma turma = encontrarTurmaPorCodigo(codigoTurma);

        if (aluno == null) {
            System.out.println("Erro: Aluno não encontrado.");
            return;
        }
        if (turma == null) {
            System.out.println("Erro: Turma não encontrada.");
            return;
        }

        boolean alunoMatriculadoNaTurma = false;
        if (turma.getAlunosMatriculados() != null) {
            for (Aluno a : turma.getAlunosMatriculados()) {
                if (a.getMatricula().equals(matriculaAluno)) {
                    alunoMatriculadoNaTurma = true;
                    break;
                }
            }
        }

        if (!alunoMatriculadoNaTurma) {
            System.out.println("Erro: Aluno não está matriculado nesta turma. Matricule-o primeiro.");
            return;
        }

        HistoricoAcademicoTurma historico = aluno.getHistoricoTurma(codigoTurma);
        if (historico == null) {
            historico = new HistoricoAcademicoTurma(matriculaAluno, codigoTurma, turma.getCargaHoraria());
            aluno.adicionarHistoricoTurma(codigoTurma, historico);
            this.historicosAcademicos.add(historico); 
            System.out.println("Aviso: Histórico acadêmico para este aluno e turma criado agora.");
        }

        int subOpcao;
        do {
            System.out.println("\n=== Operações para " + aluno.getNome() + " na Turma " + turma.getCodigo() + " ===");
            System.out.println("1. Lançar Nota");
            System.out.println("2. Registrar Falta");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            subOpcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (subOpcao) {
                case 1:
                    System.out.print("Tipo da nota (P1, P2, P3, L, S): ");
                    String tipoNota = scanner.nextLine();
                    System.out.print("Valor da nota: ");
                    double valorNota = scanner.nextDouble();
                    scanner.nextLine(); 
                    historico.adicionarNota(tipoNota, valorNota);
                    System.out.println("Nota lançada com sucesso!");
                    break;
                case 2:
                    historico.adicionarFalta();
                    System.out.println("Falta registrada. Total de faltas: " + historico.getFaltas());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (subOpcao != 0);
    }

    private Aluno encontrarAlunoPorMatricula(String matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    private Turma encontrarTurmaPorCodigo(String codigo) {
        for (Turma t : turmas) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }
}

