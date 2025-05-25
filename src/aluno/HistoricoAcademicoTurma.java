package aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; // Necessário para o toString()

public class HistoricoAcademicoTurma {
    private String matriculaAluno;
    private String codigoTurma;
    private Map<String, Double> notas; // Armazena notas como P1, P2, L, S
    private int faltas;
    private int cargaHorariaTurma; // Carga horária da disciplina para calcular percentual de faltas

    public HistoricoAcademicoTurma(String matriculaAluno, String codigoTurma, int cargaHorariaTurma) {
        this.matriculaAluno = matriculaAluno;
        this.codigoTurma = codigoTurma;
        this.notas = new HashMap<>();
        this.faltas = 0;
        this.cargaHorariaTurma = cargaHorariaTurma;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public Map<String, Double> getNotas() {
        return notas;
    }

    public int getFaltas() {
        return faltas;
    }

    public int getCargaHorariaTurma() {
        return cargaHorariaTurma;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public void setNotas(Map<String, Double> notas) {
        this.notas = new HashMap<>(notas); 
    }

    public void adicionarNota(String tipoNota, double valor) {
        this.notas.put(tipoNota, valor);
    }

    public void adicionarFalta() {
        this.faltas++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(matriculaAluno).append("|").append(codigoTurma).append("|").append(faltas).append("|").append(cargaHorariaTurma).append("|");

        if (!notas.isEmpty()) {
            String notasString = notas.entrySet().stream().map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(","));
            sb.append(notasString);
        }
        return sb.toString();
    }

    public static HistoricoAcademicoTurma fromString(String linha) {
        String[] partes = linha.split("\\|");

        
        if (partes.length < 4 || partes.length > 5) {
            System.err.println("Formato de linha inválido para HistoricoAcademicoTurma. Esperado 4 ou 5 partes, encontrado " + partes.length + ": " + linha);
            return null;
        }

        try {
            String matriculaAluno = partes[0];
            String codigoTurma = partes[1];
            int faltas = Integer.parseInt(partes[2]);
            int cargaHorariaTurma = Integer.parseInt(partes[3]);

            HistoricoAcademicoTurma historico = new HistoricoAcademicoTurma(matriculaAluno, codigoTurma, cargaHorariaTurma);
            historico.setFaltas(faltas); 

            if (partes.length == 5 && !partes[4].isEmpty()) {
                String[] notasArray = partes[4].split(",");
                for (String notaPar : notasArray) {
                    String[] notaDados = notaPar.split(":");
                    if (notaDados.length == 2) {
                        historico.adicionarNota(notaDados[0], Double.parseDouble(notaDados[1]));
                    }
                }
            }
            return historico;
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número ao carregar histórico acadêmico: " + e.getMessage() + " na linha: " + linha);
            return null;
        }
    }

 public static void salvar(List<HistoricoAcademicoTurma> lista) {
    try (PrintWriter pw = new PrintWriter(new FileWriter("HistoricoAcademicoTurma.txt"))) {
        for (HistoricoAcademicoTurma h : lista) {
            pw.println(h.toString());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static List<HistoricoAcademicoTurma> carregar() {
    List<HistoricoAcademicoTurma> lista = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("HistoricoAcademicoTurma.txt"))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            HistoricoAcademicoTurma h = HistoricoAcademicoTurma.fromString(linha);
            if (h != null) {
                lista.add(h);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return lista;
    }
}
