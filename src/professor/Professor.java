package professor;

public class Professor {
    private String nome;
    private String usuario; 

    public Professor(String nome, String usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "| Usuário: " + usuario;
    }
}

