package turma;

import java.io.*;
import java.util.*;

public class Turma {
    private String codigo;
    private String codigoDisciplina;
    private String matriculaProfessor;
    private String modalidade;
    private int cargaHoraria;
    private int tipoMedia;

    public Turma(String codigo, String codigoDisciplina, String matriculaProfessor, String modalidade, int cargaHoraria, int tipoMedia) {
        this.codigo = codigo;
        this.codigoDisciplina = codigoDisciplina;
        this.matriculaProfessor = matriculaProfessor;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.tipoMedia = tipoMedia;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public String getMatriculaProfessor() {
        return matriculaProfessor;
    }

    public String getModalidade() {
        return modalidade;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public int getTipoMedia() {
        return tipoMedia;
    }

    @Override
    public String toString() {
        return codigo + "|" + codigoDisciplina + "|" + matriculaProfessor + "|" + modalidade + "|" + cargaHoraria + "|" + tipoMedia;
    }

    public static void salvarTurma(Turma turma) {
        File arquivo = new File("dados/turmas.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            bw.write(turma.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar turma: " + e.getMessage());
        }
    }
}



