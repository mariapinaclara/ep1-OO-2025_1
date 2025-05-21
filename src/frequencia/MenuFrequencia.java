package frequencia;

import java.io.File;
import java.util.*;

public class MenuFrequencia {
    private static final String PASTA_FREQUENCIA = "dados/frequencias/";
    private static List<Frequencia> frequencias = new ArrayList<>();

    public static void exibirMenu(Scanner scanner) {
        carregarTodasAsFrequencias();

        int opcao;
        do {
            System.out.println("\n=== MENU DE FREQUÊNCIA ===");
            System.out.println("1. Registrar presença");
            System.out.println("2. Listar presenças");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarPresenca(scanner);
                    break;
                case 2:
                    listarPresencas();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void registrarPresenca(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        Frequencia freq = buscarFrequencia(codigoTurma);
        if (freq == null) {
            freq = new Frequencia(codigoTurma);
            frequencias.add(freq);
        }

        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();

        freq.registrarPresenca(matricula);
        freq.salvar(PASTA_FREQUENCIA + codigoTurma + ".txt");

        System.out.println("Presença registrada com sucesso.");
    }

    private static void listarPresencas() {
        if (frequencias.isEmpty()) {
            System.out.println("Nenhuma frequência registrada.");
            return;
        }

        System.out.println("\n=== FREQUÊNCIAS REGISTRADAS ===");
        for (Frequencia f : frequencias) {
            System.out.println("Turma: " + f.getCodigoTurma());
            for (Map.Entry<String, Integer> entry : f.getPresencas().entrySet()) {
                System.out.println("Aluno: " + entry.getKey() + " | Presenças: " + entry.getValue());
            }
            System.out.println("--------------------------");
        }
    }

    private static Frequencia buscarFrequencia(String codigoTurma) {
        for (Frequencia f : frequencias) {
            if (f.getCodigoTurma().equalsIgnoreCase(codigoTurma)) {
                return f;
            }
        }

        String caminho = PASTA_FREQUENCIA + codigoTurma + ".txt";
        Frequencia freq = Frequencia.carregar(caminho);
        if (freq != null) {
            frequencias.add(freq);
        }
        return freq;
    }

    private static void carregarTodasAsFrequencias() {
        frequencias.clear();
        File pasta = new File(PASTA_FREQUENCIA);
        if (!pasta.exists()) {
            pasta.mkdirs();
            return;
        }

        File[] arquivos = pasta.listFiles((dir, nome) -> nome.endsWith(".txt"));
        if (arquivos == null) return;

        for (File arq : arquivos) {
            Frequencia f = Frequencia.carregar(arq.getPath());
            if (f != null) {
                frequencias.add(f);
            }
        }
    }
}

