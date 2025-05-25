package professor;


public class Professor {
    private String nome;
    private String matricula;
    private String departamento;

    public Professor(String nome, String matricula, String departamento) {
        this.nome = nome;
        this.matricula = matricula;
        this.departamento = departamento;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    @Override
    public String toString() {
        return nome + "|" + matricula + "|" + departamento;
    }


    public static Professor fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length != 3) {
            System.err.println("Formato de linha inválido para Professor: " + linha);
            return null;
        }
        return new Professor(partes[0], partes[1], partes[2]);
    }
}
