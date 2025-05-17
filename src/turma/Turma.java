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

    public static Turma fromArquivo(String linha) {
        String[] partes = linha.split(";");
        String codigo = partes[0];
        String nomeDisciplina = partes[1];
        String nomeProfessor = partes[2];
        String periodo = partes[3];
        return new Turma(codigo, nomeDisciplina, nomeProfessor, periodo);
    }

    @Override
    public String toString() {
        return "Código: " + codigo + " | Disciplina: " + nomeDisciplina + " | Professor: " + nomeProfessor + " | Período: " + periodo;
    }
}
