package disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuDisciplina {
    private static List < Disciplina > listaDisciplinas = new ArrayList <>();

    public static void exibirMenu(Scanner scanner) {
        int opcao = -1;

        do {
            System.out.println("\n=== MENU DE DISCIPLINAS ===");
            System.out.println("1. Cadastrar disciplina");
            System.out.println("2. Listar disciplinas");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); //Limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarDisciplina(scanner);
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida.");
            }
        } while (opcao != 3);
    }

    private static void cadastrarDisciplina(Scanner scanner) {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Carga horária (em horas): ");
        int cargaHoraria = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Pré-Requisito (se houver): ");
        String prerequisito = scanner.nextLine();

        Disciplina novDisciplina = new Disciplina(nome, codigo, cargaHoraria, prerequisito);
        listaDisciplinas.add(novDisciplina);
        System.out.println("Disciplina cadastrada.");
    }

    private static void listarDisciplinas() {
        if (listaDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }

        System.out.println("\n=== Lista de Disciplinas ===");
        for (Disciplina disciplina : listaDisciplinas) {
            System.out.println(disciplina);
        }
        }

    
}
