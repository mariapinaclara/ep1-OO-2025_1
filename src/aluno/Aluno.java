package aluno;

import java.util.*;

public class Aluno {
    private String nome;
    private String matricula;
    private String curso;
    protected List<String> disciplinasMatriculadas;
    // Mapa para armazenar o histórico do aluno em cada turma
    private Map<String, HistoricoAcademicoTurma> historicosPorTurma;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinasMatriculadas = new ArrayList<>();
        this.historicosPorTurma = new HashMap<>(); // Inicialize o novo mapa
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public List<String> getDisciplinasMatriculadas() {
        return new ArrayList<>(disciplinasMatriculadas); // Retorna uma cópia para segurança
    }

    public void matricularDisciplina(String codigoDisciplina) {
        if (!disciplinasMatriculadas.contains(codigoDisciplina)) {
            disciplinasMatriculadas.add(codigoDisciplina);
            System.out.println("Matriculado na disciplina: " + codigoDisciplina);
        } else {
            System.out.println("Já matriculado na disciplina: " + codigoDisciplina);
        }
    }

    public void adicionarHistoricoTurma(String idTurma, HistoricoAcademicoTurma historico) {
        this.historicosPorTurma.put(idTurma, historico);
    }

    public HistoricoAcademicoTurma getHistoricoTurma(String idTurma) {
        return historicosPorTurma.get(idTurma);
    }

    public Map<String, HistoricoAcademicoTurma> getHistoricosAcademicos() { 
        return historicosPorTurma;
    }

    @Override
    public String toString() {
        String disciplinas = String.join(";", disciplinasMatriculadas);
        return nome + "|" + matricula + "|" + curso + "|" + disciplinas;
    }

    public static Aluno fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length < 3 || partes.length > 4) {
            System.err.println("Formato de linha inválido para Aluno: " + linha);
            return null;
        }

        Aluno aluno = new Aluno(partes[0], partes[1], partes[2]);

        if (partes.length == 4 && !partes[3].isEmpty()) {
            String[] disciplinas = partes[3].split(";");
            for (String disc : disciplinas) {
                if (!disc.isEmpty()) {
                    aluno.disciplinasMatriculadas.add(disc);
                }
            }
        }
        return aluno;
    }
}