package turma;

public class Turma {
    private String codigo;
    private String nomeDisciplina;
    private String nomeProfessor;
    private String periodo;

    public Turma(String codigo, String nomeDisciplina, String nomeProfessor, String periodo) {
        this.codigo = codigo;
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.periodo = periodo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public String getPeriodo() {
        return periodo;
    }
}


