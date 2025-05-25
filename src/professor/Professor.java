package professor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static void salvarLista(List<Professor> lista, String caminhoArquivo) {
        File pasta = new File(caminhoArquivo).getParentFile();
        if (pasta != null && !pasta.exists()) {
            pasta.mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Professor prof : lista) {
                pw.println(prof.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar lista de professores: " + e.getMessage());
        }
    }

    public static List<Professor> carregarLista(String caminhoArquivo) {
        List<Professor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Professor prof = Professor.fromString(linha);
                if (prof != null) {
                    lista.add(prof);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar lista de professores: " + e.getMessage());
        }
        return lista;
    }
}
