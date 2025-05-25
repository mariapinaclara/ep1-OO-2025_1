package relatorios;

import java.util.List;
import aluno.Aluno;
import disciplina.Disciplina;
import professor.Professor;
import turma.Turma;
import aluno.HistoricoAcademicoTurma;

public class RelatorioAcademicoService {
    private CalculoAcademicoService calculoAcademicoService;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Turma> turmas;
    private List<HistoricoAcademicoTurma> historicosAcademicos;

    public RelatorioAcademicoService(List<Aluno> alunos, List<Turma> turmas, List<Disciplina> disciplinas, List<Professor> professores, List<HistoricoAcademicoTurma> historicosAcademicos, CalculoAcademicoService calculoAcademicoService) {
        this.alunos = alunos;
        this.turmas = turmas;
        this.disciplinas = disciplinas;
        this.professores = professores;
        this.historicosAcademicos = historicosAcademicos;
        this.calculoAcademicoService = calculoAcademicoService;
    }

    public void gerarRelatorioPorTurma(String codigoTurma) {
        Turma turma = encontrarTurmaPorCodigo(codigoTurma);

        if (turma == null) {
            System.out.println("Turma com código " + codigoTurma + " não encontrada.");
            return;
        }

        System.out.println("\n=== RELATÓRIO DA TURMA: " + turma.getCodigo() + " ===");
        Disciplina disc = encontrarDisciplinaPorCodigo(turma.getCodigoDisciplina());
        System.out.println("Disciplina: " + (disc != null ? disc.getNome() : "N/A (código: " + turma.getCodigoDisciplina() + ")"));

        Professor prof = encontrarProfessorPorMatricula(turma.getMatriculaProfessor());
        System.out.println("Professor: " + (prof != null ? prof.getNome() : "N/A (matrícula: " + turma.getMatriculaProfessor() + ")"));
        System.out.println("Carga Horária: " + turma.getCargaHoraria() + "h");
        System.out.println("Alunos Matriculados:");

        if (turma.getAlunosMatriculados() == null || turma.getAlunosMatriculados().isEmpty()) {
            System.out.println("    Nenhum aluno matriculado nesta turma.");
            System.out.println("----------------------------------------");
            return;
        }

        System.out.println("    Total de Alunos: " + turma.getAlunosMatriculados().size());

        for (Aluno aluno : turma.getAlunosMatriculados()) {
            System.out.println("\n    - Aluno: " + aluno.getNome() + " (Matrícula: " + aluno.getMatricula() + ")");

            HistoricoAcademicoTurma historico = historicosAcademicos.stream().filter(h -> h.getMatriculaAluno().equals(aluno.getMatricula()) && h.getCodigoTurma().equals(turma.getCodigo())).findFirst().orElse(null);

            if (historico != null) {
                System.out.println("      Notas:");
                if (historico.getNotas().isEmpty()) {
                    System.out.println("        Nenhuma nota lançada.");
                } else {
                    historico.getNotas().forEach((tipo, valor) -> System.out.println("        " + tipo + ": " + valor));
                }

                double mediaFinal = calculoAcademicoService.calcularMediaFinal(historico, turma.getTipoMedia());
                String statusAprovacao = calculoAcademicoService.verificarStatusAprovacao(mediaFinal, historico.getFaltas(), historico.getCargaHorariaTurma());

                System.out.println("      Faltas: " + historico.getFaltas());
                System.out.printf("      Média Final: %.2f\n", mediaFinal);
                System.out.println("      Status: " + statusAprovacao);

            } else {
                System.out.println("    Nenhum histórico acadêmico encontrado para este aluno nesta turma.");
            }
        }
        System.out.println("\n----------------------------------------");
    }

    public void exibirBoletimAluno(String matriculaAluno, String codigoTurma) {
        Aluno aluno = encontrarAlunoPorMatricula(matriculaAluno);
        if (aluno == null) {
            System.out.println("Aluno com matrícula " + matriculaAluno + " não encontrado.");
            return;
        }

        System.out.println("\n== BOLETIM ESCOLAR DO ALUNO: " + aluno.getNome() + " (" + aluno.getMatricula() + ") ===");

        // Filtrar históricos da lista GLOBAL 'historicosAcademicos' para o aluno encontrado
        List<HistoricoAcademicoTurma> historicosDoAluno = historicosAcademicos.stream().filter(h -> h.getMatriculaAluno().equals(matriculaAluno) && (codigoTurma == null || codigoTurma.isEmpty() || h.getCodigoTurma().equals(codigoTurma))).collect(java.util.stream.Collectors.toList());


        if (historicosDoAluno.isEmpty()) {
            System.out.println("Nenhum histórico acadêmico encontrado para este aluno.");
            System.out.println("\n----------------------------------------");
            return;
        }

        for (HistoricoAcademicoTurma historico : historicosDoAluno) {
            Turma turma = encontrarTurmaPorCodigo(historico.getCodigoTurma());
            System.out.println("\n    - Turma: " + historico.getCodigoTurma());
            if (turma != null) {
                System.out.println("      Nome da Turma: " + turma.getMateria());
                Disciplina disc = encontrarDisciplinaPorCodigo(turma.getCodigoDisciplina());
                System.out.println("      Disciplina: " + (disc != null ? disc.getNome() : "N/A (código: " + turma.getCodigoDisciplina() + ")"));
                Professor prof = encontrarProfessorPorMatricula(turma.getMatriculaProfessor());
                System.out.println("      Professor: " + (prof != null ? prof.getNome() : "N/A (matrícula: " + turma.getMatriculaProfessor() + ")"));
                System.out.println("      Carga Horária da Turma: " + turma.getCargaHoraria() + "h");
            } else {
                System.out.println("      Dados da turma não encontrados (código: " + historico.getCodigoTurma() + ")");
            }

            System.out.println("      Notas:");
            if (historico.getNotas().isEmpty()) {
                System.out.println("        Nenhuma nota lançada.");
            } else {
                historico.getNotas().forEach((tipo, valor) -> System.out.println("        " + tipo + ": " + valor));
            }

            double mediaFinal = calculoAcademicoService.calcularMediaFinal(historico, turma != null ? turma.getTipoMedia() : 1);
            String statusAprovacao = calculoAcademicoService.verificarStatusAprovacao(mediaFinal, historico.getFaltas(), historico.getCargaHorariaTurma());

            System.out.println("      Faltas Registradas: " + historico.getFaltas());
            System.out.printf("      Média Final: %.2f\n", mediaFinal);
            System.out.println("      Status de Aprovação: " + statusAprovacao);
        }
        System.out.println("\n----------------------------------------");
    }

    private Aluno encontrarAlunoPorMatricula(String matricula) {
        if (this.alunos == null) return null;
        for (Aluno a : this.alunos) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    private Turma encontrarTurmaPorCodigo(String codigo) {
        if (this.turmas == null) return null;
        for (Turma t : this.turmas) {
            if (t.getCodigo().equals(codigo)) {
                return t;
            }
        }
        return null;
    }

    private Disciplina encontrarDisciplinaPorCodigo(String codigo) {
        if (this.disciplinas == null) return null;
        for (Disciplina d : this.disciplinas) {
            if (d.getCodigo().equals(codigo)) {
                return d;
            }
        }
        return null;
    }

    private Professor encontrarProfessorPorMatricula(String matricula) {
        if (this.professores == null) return null;
        for (Professor p : this.professores) {
            if (p.getMatricula().equals(matricula)) {
                return p;
            }
        }
        return null;
    }
}