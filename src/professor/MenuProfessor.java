package professor; 

import java.util.List;
import java.util.Scanner;

public class MenuProfessor {
    private Scanner scanner;
    private List<Professor> professores; 

    public MenuProfessor(Scanner scanner, List<Professor> professores) {
        this.scanner = scanner;
        this.professores = professores;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== MENU DE PROFESSORES ===");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    cadastrarProfessor();
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarProfessor() {
        System.out.print("Nome do Professor: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula do Professor: ");
        String matricula = scanner.nextLine();

        boolean matriculaExistente = false;
        for (Professor p : professores) {
            if (p.getMatricula().equalsIgnoreCase(matricula)) {
                matriculaExistente = true;
                break;
            }
        }

        if (matriculaExistente) {
            System.out.println("Erro: Já existe um professor com esta matrícula.");
        } else {
            System.out.print("Departamento do Professor: ");
            String departamento = scanner.nextLine();

            Professor novoProfessor = new Professor(nome, matricula, departamento);
            this.professores.add(novoProfessor); 
            System.out.println("Professor cadastrado com sucesso!");
        }
    }

    private void listarProfessores() {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
            return;
        }
        System.out.println("\n--- Lista de Professores ---");
        for (Professor p : professores) {
            System.out.println("Matrícula: " + p.getMatricula() + " | Nome: " + p.getNome() + " | Departamento: " + p.getDepartamento());
        }
    }
}
