package persistencia;

import java.io.*;
import java.util.*;

public class PersistenciaUtils {

    public static void salvarLista(String nomeArquivo, List<String> linhas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    public static List<String> carregarLista(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        File file = new File(nomeArquivo);
        if (!file.exists()) return linhas;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo " + nomeArquivo + ": " + e.getMessage());
        }

        return linhas;
    }
}
