package aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aluno {
    private static List<Aluno> listaAlunos = new ArrayList<>();
    
    private String nome;
    private String matricula;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public static void cadastrarAluno(Scanner scanner) {
        System.out.print("Nome do aluno(a): ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        Aluno novoAluno = new Aluno(nome, matricula);
        listaAlunos.add(novoAluno);
        System.out.println("Aluno cadastrado.");
    }
    
    public static void listarAlunos() {
        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== Lista de Alunos ===");
        for (Aluno aluno : listaAlunos) {
            System.out.println("Nome: " + aluno.nome + " | Matrícula: " + aluno.matricula);
        }
    }
    
}
