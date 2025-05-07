package disciplina;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private String prerequisito;

    //Construtor
    public Disciplina(String nome, String codigo, int cargaHoraria, String prerequisito) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.prerequisito = prerequisito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getPrerequisito() {
        return prerequisito;
    }

    public void setPrerequisito(String prerequisito) {
        this.prerequisito = prerequisito;
    }

    //Método toString para exibir a disciplina de forma legível
    @Override
    public String toString() {
        return "Disciplina: " + nome + " | Código: " + codigo + " | Carga Horária: " + cargaHoraria + " horas | Pré-Requisito: " + prerequisito;
    }

    
}
