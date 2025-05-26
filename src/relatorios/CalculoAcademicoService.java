package relatorios;

import aluno.HistoricoAcademicoTurma;
import java.util.Map;

public class CalculoAcademicoService {
    public double calcularMediaFinal(HistoricoAcademicoTurma historico, int tipoMedia) {
        if (historico == null || historico.getNotas().isEmpty()) {
            return 0.0; 
        }

        Map<String, Double> notas = historico.getNotas();
        double media = 0.0;

        switch (tipoMedia) {
            case 1: 
                double p1 = notas.getOrDefault("P1", 0.0);
                double p2 = notas.getOrDefault("P2", 0.0);
                double p3 = notas.getOrDefault("P3", 0.0);
                double lista = notas.getOrDefault("L", 0.0); 
                double substitutiva = notas.getOrDefault("S", 0.0);

                if (substitutiva > 0) { 
                    double menorProva = Math.min(p1, Math.min(p2, p3));
                    if (substitutiva > menorProva) {
                        if (menorProva == p1) p1 = substitutiva;
                        else if (menorProva == p2) p2 = substitutiva;
                        else if (menorProva == p3) p3 = substitutiva;
                    }
                }
                media = (p1 + p2 + p3) / 3.0; 
                if (lista > 0) { 
                     media = (media * 3 + lista) / 4.0; 
                }
                break;

            case 2: 
                p1 = notas.getOrDefault("P1", 0.0);
                p2 = notas.getOrDefault("P2", 0.0);
                p3 = notas.getOrDefault("P3", 0.0);
                lista = notas.getOrDefault("L", 0.0); 
                substitutiva = notas.getOrDefault("S", 0.0);

                if (substitutiva > 0) {
                    double menorProva = Math.min(p1, Math.min(p2, p3));
                    if (substitutiva > menorProva) {
                        if (menorProva == p1) p1 = substitutiva;
                        else if (menorProva == p2) p2 = substitutiva;
                        else if (menorProva == p3) p3 = substitutiva;
                    }
                }

                
                media = (p1 * 2 + p2 * 3 + p3 * 5) / (2 + 3 + 5); 
                if (lista > 0) { 
                    media = (media * 10 + lista * 2) / (10 + 2); 
                }
                break;

            default: 
                double somaNotas = 0.0;
                int countNotas = 0;
                if (notas.containsKey("P1")) { 
                    somaNotas += notas.get("P1"); countNotas++; 
                }
                if (notas.containsKey("P2")) {
                     somaNotas += notas.get("P2"); countNotas++; 
                }
                if (notas.containsKey("P3")) {
                     somaNotas += notas.get("P3"); countNotas++; 
                }
                if (notas.containsKey("L")) {
                     somaNotas += notas.get("L"); countNotas++; 
                }
                if (notas.containsKey("S")) {
                     somaNotas += notas.get("S"); countNotas++; 
                }
                media = countNotas > 0 ? somaNotas / countNotas : 0.0;
                System.out.println("Aviso: Tipo de média desconhecido. Calculando média simples das notas existentes.");
                break;
        }
        return media;
    }

    public String verificarStatusAprovacao(double mediaFinal, int faltas, int cargaHorariaTurma) {
        boolean aprovadoPorNota = mediaFinal >= 7.0; 

        double percentualFaltas = 0.0;
        if (cargaHorariaTurma > 0) {
            percentualFaltas = (double) faltas / cargaHorariaTurma * 100;
        }

        boolean aprovadoPorFalta = percentualFaltas <= 25.0;

        if (aprovadoPorNota && aprovadoPorFalta) {
            return "Aprovado";
        } else if (!aprovadoPorNota && aprovadoPorFalta) {
            return "Reprovado por Nota";
        } else if (aprovadoPorNota && !aprovadoPorFalta) {
            return "Reprovado por Falta";
        } else { 
            return "Reprovado por Nota e Falta";
        }
    }
}