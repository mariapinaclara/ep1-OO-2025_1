package aluno;

import java.util.HashMap;
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

    // --- Getters ---
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

    // --- Setters (Adicionados para facilitar o fromString) ---
    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public void setNotas(Map<String, Double> notas) {
        this.notas = new HashMap<>(notas); // Para copiar as notas e evitar referência direta
    }

    // --- Métodos Adicionais ---
    public void adicionarNota(String tipoNota, double valor) {
        this.notas.put(tipoNota, valor);
    }

    public void adicionarFalta() {
        this.faltas++;
    }

    // --- Métodos para Persistência (CRUCIAIS) ---

    @Override
    public String toString() {
        // Formato: matriculaAluno|codigoTurma|faltas|cargaHorariaTurma|tipoNota1:valor1,tipoNota2:valor2,...
        StringBuilder sb = new StringBuilder();
        sb.append(matriculaAluno).append("|")
          .append(codigoTurma).append("|")
          .append(faltas).append("|")
          .append(cargaHorariaTurma).append("|");

        // Adiciona as notas
        if (!notas.isEmpty()) {
            String notasString = notas.entrySet().stream()
                                     .map(entry -> entry.getKey() + ":" + entry.getValue())
                                     .collect(Collectors.joining(","));
            sb.append(notasString);
        }
        return sb.toString();
    }

    public static HistoricoAcademicoTurma fromString(String linha) {
        String[] partes = linha.split("\\|");

        // Esperamos 4 partes fixas + 1 parte opcional para as notas
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
            historico.setFaltas(faltas); // Define as faltas

            // Carrega as notas, se existirem
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
}