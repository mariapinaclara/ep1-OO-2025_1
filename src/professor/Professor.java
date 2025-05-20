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

    @Override
    public String toString() {
        return "Nome: " + nome + " | Matrícula: " + matricula;
    }
}
