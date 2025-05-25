package disciplina;

public class Disciplina {
    private String codigo;
    private String nome;
    private int creditos;

    public Disciplina(String codigo, String nome, int creditos) {
        this.codigo = codigo;
        this.nome = nome;
        this.creditos = creditos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) { 
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) { 
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) { 
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return codigo + "|" + nome + "|" + creditos;
    }

    public static Disciplina fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length != 3) {
            System.err.println("Formato de linha inválido para Disciplina: " + linha);
            return null;
        }
        try {
            return new Disciplina(partes[0], partes[1], Integer.parseInt(partes[2]));
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter créditos da disciplina: " + e.getMessage() + " na linha: " + linha);
            return null;
        }
    }
}