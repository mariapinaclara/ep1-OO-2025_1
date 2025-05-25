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
        File pasta = new File(caminhoArquivo).getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

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
                String[] dados = linha.split("\\|");
                if (dados.length == 2) {
                    freq.presencas.put(dados[0], Integer.parseInt(dados[1]));
                }
            }

            return freq;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar frequência: " + e.getMessage());
            return null;
        }
    }

    public static void salvarLista(List<Frequencia> lista, String caminhoArquivo) {
        File pasta = new File(caminhoArquivo).getParentFile();
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoArquivo))) {
            for (Frequencia freq : lista) {
                pw.println(freq.getCodigoTurma());
                for (Map.Entry<String, Integer> entry : freq.getPresencas().entrySet()) {
                    pw.println(entry.getKey() + "|" + entry.getValue());
                }
                pw.println(); 
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar lista de frequências: " + e.getMessage());
        }
    }

    public static List<Frequencia> carregarLista(String caminhoArquivo) {
        List<Frequencia> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            Frequencia freqAtual = null;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    if (freqAtual != null) {
                        lista.add(freqAtual);
                        freqAtual = null;
                    }
                } else {
                    if (freqAtual == null) {
                        freqAtual = new Frequencia(linha);
                    } else {
                        String[] dados = linha.split("\\|");
                        if (dados.length == 2) {
                            freqAtual.presencas.put(dados[0], Integer.parseInt(dados[1]));
                        }
                    }
                }
            }

            if (freqAtual != null) {
                lista.add(freqAtual);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar lista de frequências: " + e.getMessage());
        }

        return lista;
    }
}

