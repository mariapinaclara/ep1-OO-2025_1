package disciplina; 

import java.util.List;
import java.util.Scanner;

public class MenuDisciplina {
    private Scanner scanner;
    private List<Disciplina> disciplinas; 

    public MenuDisciplina(Scanner scanner, List<Disciplina> disciplinas) {
        this.scanner = scanner;
        this.disciplinas = disciplinas;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE DISCIPLINAS ===");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Listar Disciplinas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    listarDisciplinas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarDisciplina() {
        System.out.print("Código da Disciplina: ");
        String codigo = scanner.nextLine();

        boolean codigoExistente = false;
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equalsIgnoreCase(codigo)) {
                codigoExistente = true;
                break;
            }
        }

        if (codigoExistente) {
            System.out.println("Erro: Já existe uma disciplina com este código.");
        } else {
            System.out.print("Nome da Disciplina: ");
            String nome = scanner.nextLine();
            System.out.print("Créditos da Disciplina: ");
            int creditos = scanner.nextInt();
            scanner.nextLine(); 

            Disciplina novaDisciplina = new Disciplina(codigo, nome, creditos);
            this.disciplinas.add(novaDisciplina); 
            System.out.println("Disciplina cadastrada com sucesso!");
        }
    }

    private void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        System.out.println("\n--- Lista de Disciplinas ---");
        for (Disciplina d : disciplinas) {
            System.out.println("Código: " + d.getCodigo() + " | Nome: " + d.getNome() + " | Créditos: " + d.getCreditos());
        }
    }
}
