package turma;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import aluno.Aluno;
import aluno.HistoricoAcademicoTurma;

public class Turma {
    private String codigo;
    private String materia; 
    private String codigoDisciplina;
    private String matriculaProfessor;
    private String modalidade;
    private int cargaHoraria;
    private int tipoMedia;
    private List<Aluno> alunosMatriculados; 
    private List<String> matriculasAlunos = new ArrayList<>();


    public Turma(String codigo, String materia, String codigoDisciplina, String matriculaProfessor, String modalidade, int cargaHoraria, int tipoMedia) {
        this.codigo = codigo;
        this.materia = materia; 
        this.codigoDisciplina = codigoDisciplina;
        this.matriculaProfessor = matriculaProfessor;
        this.modalidade = modalidade;
        this.cargaHoraria = cargaHoraria;
        this.tipoMedia = tipoMedia;
        this.alunosMatriculados = new ArrayList<>();
    }

    public Turma(String codigo, String codigoDisciplina, String matriculaProfessor, String modalidade, int cargaHoraria, int tipoMedia) {
        this(codigo, "Turma " + codigo, codigoDisciplina, matriculaProfessor, modalidade, cargaHoraria, tipoMedia); 
    }


    public String getCodigo() {
        return codigo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getMatriculaProfessor() {
        return matriculaProfessor;
    }

    public void setMatriculaProfessor(String matriculaProfessor) {
        this.matriculaProfessor = matriculaProfessor;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getTipoMedia() {
        return tipoMedia;
    }

    public void setTipoMedia(int tipoMedia) {
        this.tipoMedia = tipoMedia;
    }

    public List<String> getMatriculasAlunos() {
    return matriculasAlunos;
    }

    public void setMatriculasAlunos(List<String> matriculasAlunos) {
    this.matriculasAlunos = matriculasAlunos;
    }


    public void matricularAluno(Aluno aluno) {
        if (aluno != null && !this.alunosMatriculados.contains(aluno)) {
            this.alunosMatriculados.add(aluno);
            System.out.println("Aluno " + aluno.getNome() + " matriculado na turma " + this.codigo);

            HistoricoAcademicoTurma novoHistorico = new HistoricoAcademicoTurma(aluno.getMatricula(), this.codigo, this.cargaHoraria);
            aluno.adicionarHistoricoTurma(this.codigo, novoHistorico);
        } else if (aluno != null) {
            System.out.println("Aluno " + aluno.getNome() + " já está matriculado na turma " + this.codigo);
        } else {
            System.out.println("Erro: Aluno inválido.");
        }
    }

    public void desmatricularAluno(Aluno aluno) {
        if (aluno != null && this.alunosMatriculados.remove(aluno)) {
            System.out.println("Aluno " + aluno.getNome() + " desmatriculado da turma " + this.codigo);
        } else if (aluno != null) {
            System.out.println("Aluno " + aluno.getNome() + " não está matriculado na turma " + this.codigo);
        } else {
            System.out.println("Erro: Aluno inválido.");
        }
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados; 
    }

    public void setAlunosMatriculados(List<Aluno> alunos) {
        if (alunos != null) {
            this.alunosMatriculados = new ArrayList<>(alunos); 
        } else {
            this.alunosMatriculados = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return codigo + "|" + materia + "|" + codigoDisciplina + "|" + matriculaProfessor + "|" + modalidade + "|" + cargaHoraria + "|" + tipoMedia;
    }

    public static Turma fromString(String linha) {
        String[] partes = linha.split("\\|");

        if (partes.length != 7) {
            System.err.println("Formato de linha inválido para Turma. Esperado 7 partes, encontrado " + partes.length + ": " + linha);
            return null;
        }
        try {
            String codigo = partes[0];
            String nome = partes[1]; 
            String codigoDisciplina = partes[2];
            String matriculaProfessor = partes[3];
            String modalidade = partes[4];
            int cargaHoraria = Integer.parseInt(partes[5]);
            int tipoMedia = Integer.parseInt(partes[6]);

            return new Turma(codigo, nome, codigoDisciplina, matriculaProfessor, modalidade, cargaHoraria, tipoMedia);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número ao carregar turma (cargaHoraria ou tipoMedia): " + e.getMessage() + " na linha: " + linha);
            return null;
        }
    }

     public static void salvarLista(List<Turma> lista, String caminhoArquivo) {
        File pasta = new File(caminhoArquivo).getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Turma turma : lista) {
                pw.println(turma.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar lista de turmas: " + e.getMessage());
        }
    }

    public static List<Turma> carregarLista(String caminhoArquivo) {
        List<Turma> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Turma turma = Turma.fromString(linha);
                if (turma != null) {
                    lista.add(turma);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar lista de turmas: " + e.getMessage());
        }
        return lista;
    }
}

