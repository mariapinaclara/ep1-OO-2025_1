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

    public void salvar(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            pw.println(codigoTurma + "|" + descricao + "|" + peso);
            for (Map.Entry<String, Double> entry : notas.entrySet()) {
                pw.println(entry.getKey() + "|" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar avaliação: " + e.getMessage());
        }
    }

    public static void salvarLista(List<Avaliacao> lista, String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Avaliacao av : lista) {
                pw.println(av.getCodigoTurma() + "|" + av.getDescricao() + "|" + av.getPeso());
                    for (Map.Entry<String, Double> nota : av.getNotas().entrySet()) {
                        pw.println(nota.getKey() + "|" + nota.getValue());
                    }
                pw.println("---"); 
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar avaliações: " + e.getMessage());
        }
    }


    public static List<Avaliacao> carregarLista(String caminhoArquivo) {
        List<Avaliacao> avaliacoes = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
                String linha;
                Avaliacao av = null;
            while ((linha = br.readLine()) != null) {
                if (linha.equals("---")) {
                    if (av != null) {
                        avaliacoes.add(av);
                            av = null;
                }
            } else if (av == null) {
                String[] dados = linha.split("\\|");
                av = new Avaliacao(dados[0], dados[1], Double.parseDouble(dados[2]));
            } else {
                String[] notaData = linha.split("\\|");
                av.lancarNota(notaData[0], Double.parseDouble(notaData[1]));
            }
        }
        if (av != null) {
            avaliacoes.add(av);
        }
    } catch (IOException e) {
        System.out.println("Erro ao carregar avaliações: " + e.getMessage());
    }
    return avaliacoes;
    }
}
