package disciplina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static void salvarLista(List<Disciplina> lista, String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Disciplina d : lista) {
                pw.println(d.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public static List<Disciplina> carregarLista(String caminhoArquivo) {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Disciplina d = Disciplina.fromString(linha);
                if (d != null) {
                    disciplinas.add(d);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }
}