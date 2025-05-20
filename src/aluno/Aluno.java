package aluno;

import java.io.*;
import java.util.*;

public class Aluno {

    private String nome;
    private String matricula;
    private String curso;
    private List<String> disciplinasMatriculadas;

    public Aluno(String nome, String matricula, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public List<String> getDisciplinasMatriculadas() { return disciplinasMatriculadas; }

    // Matricular disciplina, evitando duplicata
    public void matricularDisciplina(String codigoDisciplina) {
        if (!disciplinasMatriculadas.contains(codigoDisciplina)) {
            disciplinasMatriculadas.add(codigoDisciplina);
            System.out.println("Matriculado na disciplina: " + codigoDisciplina);
        } else {
            System.out.println("Já matriculado na disciplina: " + codigoDisciplina);
        }
    }

    // Converter objeto para linha texto para salvar em arquivo
    @Override
    public String toString() {
        // Formato: nome;matricula;curso;disciplina1,disciplina2,...
        String disciplinas = String.join(",", disciplinasMatriculadas);
        return nome + ";" + matricula + ";" + curso + ";" + disciplinas;
    }

    // Parse de linha para objeto Aluno
    public static Aluno fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes.length < 3) return null;

        Aluno aluno = new Aluno(partes[0], partes[1], partes[2]);

        if (partes.length == 4 && !partes[3].isEmpty()) {
            String[] disciplinas = partes[3].split(",");
            aluno.disciplinasMatriculadas.addAll(Arrays.asList(disciplinas));
        }

        return aluno;
    }

    // Salvar lista de alunos em arquivo
    public static void salvarAlunos(List<Aluno> alunos, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Aluno a : alunos) {
                writer.write(a.toString());
                writer.newLine();
            }
            System.out.println("Alunos salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    // Carregar lista de alunos do arquivo
    public static List<Aluno> carregarAlunos(String caminhoArquivo) {
        List<Aluno> alunos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Aluno a = Aluno.fromString(linha);
                if (a != null) {
                    alunos.add(a);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Retornando lista vazia.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        }

        return alunos;
    }
}
