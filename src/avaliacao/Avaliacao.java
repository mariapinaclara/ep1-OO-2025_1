package avaliacao;

import java.io.*;
import java.util.*;

public class Avaliacao {
    private String codigoTurma;
    private String descricao;
    private double peso;
    private Map<String, Double> notas; 

    public Avaliacao(String codigoTurma, String descricao, double peso) {
        this.codigoTurma = codigoTurma;
        this.descricao = descricao;
        this.peso = peso;
        this.notas = new HashMap<>();
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void lancarNota(String matricula, double nota) {
        notas.put(matricula, nota);
    }

    public Double getNota(String matricula) {
        return notas.get(matricula);
    }

    public Map<String, Double> getNotas() {
        return notas;
    }

    // Salvar avaliações em arquivo
    public void salvar(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            pw.println(codigoTurma + ";" + descricao + ";" + peso);
            for (Map.Entry<String, Double> entry : notas.entrySet()) {
                pw.println(entry.getKey() + ";" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar avaliação: " + e.getMessage());
        }
    }

    // Carregar avaliação do arquivo
    public static Avaliacao carregar(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = br.readLine();
            if (linha == null) return null;
            String[] dados = linha.split(";");
            Avaliacao av = new Avaliacao(dados[0], dados[1], Double.parseDouble(dados[2]));
            while ((linha = br.readLine()) != null) {
                String[] notaData = linha.split(";");
                av.lancarNota(notaData[0], Double.parseDouble(notaData[1]));
            }
            return av;
        } catch (IOException e) {
            System.out.println("Erro ao carregar avaliação: " + e.getMessage());
            return null;
        }
    }
}
