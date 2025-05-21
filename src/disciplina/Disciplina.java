package disciplina;

import java.util.ArrayList;
import java.util.List;
import turma.Turma;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private List<String> preRequisitos;
    private List<Turma> turmas;

    public Disciplina(String nome, String codigo, int cargaHoraria, List<String> preRequisitos) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = preRequisitos != null ? preRequisitos : new ArrayList<>();
        this.turmas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public List<String> getPreRequisitos() {
        return preRequisitos;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void adicionarTurma(Turma turma) {
        turmas.add(turma);
    }

    @Override
    public String toString() {
        return String.format("Disciplina: %s | Código: %s | Carga Horária: %d | Pré-Requisitos: %s",
                nome, codigo, cargaHoraria, String.join(", ", preRequisitos));
    }
}

