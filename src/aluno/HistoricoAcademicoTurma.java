package aluno;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HistoricoAcademicoTurma {
    private String matriculaAluno;
    private String codigoTurma;
    private Map<String, Double> notas;
    private int faltas;
    private int cargaHorariaTurma;
    private int tipoMediaTurma;

    public HistoricoAcademicoTurma(String matriculaAluno, String codigoTurma, int cargaHorariaTurma, int tipoMediaTurma) {
        this.matriculaAluno = matriculaAluno;
        this.codigoTurma = codigoTurma;
        this.notas = new HashMap<>();
        this.faltas = 0;
        this.cargaHorariaTurma = cargaHorariaTurma;
        this.tipoMediaTurma = tipoMediaTurma;
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
    public int getTipoMediaTurma() {
        return tipoMediaTurma; 
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

   public double calcularMediaFinal() {
        double media = 0.0;
        double p1 = notas.getOrDefault("p1", 0.0);
        double p2 = notas.getOrDefault("p2", 0.0);
        double s = notas.getOrDefault("s", 0.0);
        double p3 = notas.getOrDefault("p3", 0.0);
        double l = notas.getOrDefault("l", 0.0);

        if (tipoMediaTurma == 1) { 
            media = (p1 + p2 + p3 + l) / 4.0;
        } else if (tipoMediaTurma == 2) { 
            media = (p1 * 0.2 + p2 * 0.3 + p3 * 0.3 + l * 0.2);
        } else {
            return 0.0;
        }
        return media;
    }

    public String getStatusAprovacao(double mediaFinal) {
        double limiteFaltas = cargaHorariaTurma * 0.25;

        if (faltas > limiteFaltas) {
            return "Reprovado por Falta";
        } else if (mediaFinal >= 7.0) {
            return "Aprovado";
        } else if (mediaFinal >= 5.0) {
            return "Em Recuperação";
        } else {
            return "Reprovado por Nota";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(matriculaAluno).append("|").append(codigoTurma).append("|").append(faltas).append("|").append(cargaHorariaTurma).append("|").append(tipoMediaTurma).append("|");

        if (!notas.isEmpty()) {
            String notasString = notas.entrySet().stream().map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(","));
            sb.append(notasString);
        }
        return sb.toString();
    }

   public static HistoricoAcademicoTurma fromString(String linha) {
    if (linha == null || linha.trim().isEmpty()) {
        return null;
    }
    String[] partes = linha.split("\\|");

    if (partes.length < 5 || partes.length > 6) {
        return null; 
    }

    try {
        String matriculaAluno = partes[0];
        String codigoTurma = partes[1];
        int faltas = Integer.parseInt(partes[2]);
        int cargaHorariaTurma = Integer.parseInt(partes[3]);
        int tipoMediaTurma = Integer.parseInt(partes[4]);

        HistoricoAcademicoTurma historico = new HistoricoAcademicoTurma(matriculaAluno, codigoTurma, cargaHorariaTurma, tipoMediaTurma);
        historico.setFaltas(faltas);

        if (partes.length == 6 && !partes[5].isEmpty()) {
            String[] notasArray = partes[5].split(",");
            for (String notaPar : notasArray) {
                String[] notaDados = notaPar.split(":");
                if (notaDados.length == 2) {
                    try {
                        historico.adicionarNota(notaDados[0], Double.parseDouble(notaDados[1]));
                    } catch (NumberFormatException e) {

                    }
                } else {
                   
                }
            }
        }
            return historico;
        } catch (NumberFormatException e) {
            return null; 
        } catch (Exception e) {
            return null; 
        }
    }
}