package turma;

import java.util.ArrayList;
import aluno.Aluno;
import disciplina.Disciplina;

public class Turma {
    private Disciplina disciplina;
    private String professorResponsavel;
    private String semestre;
    private String formaAvaliacao;
    private boolean presencial;
    private String sala; //só se for presencial
    private String horario;
    private int capacidadeMaxima;
    private ArrayList <Aluno> alunosMatriculados;

    public Turma(Disciplina disciplina, String professorResponsavel, String semestre, String formaAvaliacao, boolean presencial, String sala, String horario, int capacidadeMaxima) {
        this.disciplina = disciplina;
        this.professorResponsavel = professorResponsavel;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.sala = sala;
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = new ArrayList<>();
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.contains(aluno)) {
            System.out.println("Aluno já está matriculado nesta turma.");
            return false;
        }
        if (alunosMatriculados.size() >= capacidadeMaxima) {
            System.out.println("Turma cheia! Não é possível matricular mais alunos.");
            return false;
        }
        alunosMatriculados.add(aluno);
        return true;
    }

    public boolean removerAluno(Aluno aluno) {
        return alunosMatriculados.remove(aluno);
    }

    public void listarAluno() {
        if (alunosMatriculados.isEmpty()) {
            System.out.println("Nenhum aluno matriculado nesta turma.");
        } else {
            for (Aluno aluno : alunosMatriculados) {
                System.out.println(aluno);
            }
        }
    }

    public String getHorario() {
        return horario;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public String getSemestre() {
        return semestre;
    }

    public String getProfessorResponsavel() {
        return professorResponsavel;
    }

    //@Override
    //public String toString() {
        //return "Turma: " + disciplina.getNome() + " | Semestre: " + semestre + " | Professor: " + professorResponsavel + " | Horário: " + horario + " | Modalidade: " + (presencial ? "Presencial | Sala: " + sala : "Remota") + " | Alunos Matriculados: " + alunosMatriculados.size() + "/" + capacidadeMaxima;   
    //}

    @Override
    public String toString() {
        String nomeDisciplina = (disciplina != null) ? disciplina.getNome() : "Sem Disciplina";
    String nomeProfessor = (professorResponsavel != null) ? professorResponsavel.toString() : "Sem Professor";
    String modalidade = presencial ? "Presencial | Sala: " + sala : "Remota";
    int qtdAlunos = (alunosMatriculados != null) ? alunosMatriculados.size() : 0;

    return "Turma: " + nomeDisciplina
         + " | Semestre: " + semestre
         + " | Professor: " + nomeProfessor
         + " | Horário: " + horario
         + " | Modalidade: " + modalidade
         + " | Alunos Matriculados: " + qtdAlunos + "/" + capacidadeMaxima;
    
    }

}