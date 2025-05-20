package frequencia;

import java.io.*;
import java.util.*;

public class Frequencia {
    private String codigoTurma;
    private Map<String, Integer> presencas; 

    public Frequencia(String codigoTurma) {
        this.codigoTurma = codigoTurma;
        this.presencas = new HashMap<>();
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void registrarPresenca(String matricula) {
        presencas.put(matricula, presencas.getOrDefault(matricula, 0) + 1);
    }

    public int getPresencas(String matricula) {
        return presencas.getOrDefault(matricula, 0);
    }

    public Map<String, Integer> getPresencas() {
        return presencas;
    }

    public void salvar(String caminhoArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            pw.println(codigoTurma);
            for (Map.Entry<String, Integer> entry : presencas.entrySet()) {
                pw.println(entry.getKey() + "|" + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar frequência: " + e.getMessage());
        }
    }

    public static Frequencia carregar(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String codigoTurma = br.readLine();
            if (codigoTurma == null) return null;
            Frequencia freq = new Frequencia(codigoTurma);
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                freq.presencas.put(dados[0], Integer.parseInt(dados[1]));
            }
            return freq;
        } catch (IOException e) {
            System.out.println("Erro ao carregar frequência: " + e.getMessage());
            return null;
        }
    }
}
