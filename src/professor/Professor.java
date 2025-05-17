package professor;

import java.io.Serializable;

public class Professor implements Serializable {
    private String nome;
    private String matricula;

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String toLinhaArquivo() {
        return nome + ";" + matricula;
    }

    public static Professor fromLinhaArquivo(String linha) {
        String[] partes = linha.split(";");
        return new Professor(partes[0], partes[1]);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Matrícula: " + matricula;
    }
}