package aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
//import java.util.Map; // Import necessário se você for acessar historicosAcademicosTurma

public class AlunoEspecial extends Aluno {

    // Construtor corrigido para incluir 'idade', que é exigido pelo construtor de Aluno
    public AlunoEspecial(String nome, String matricula, int idade, String curso) {
        super(nome, matricula, idade, curso);
    }

    // Método para verificar se o AlunoEspecial pode ser matriculado em mais uma turma/disciplina
    // Baseado no número de históricos acadêmicos (que representam matrículas em turmas/disciplinas)
    // Este método será chamado ANTES de matricular o aluno em uma turma no MenuTurma.
    public boolean podeMatricularNovaTurma() {
        // AlunoEspecial pode ter no máximo 2 disciplinas/turmas
        // O Aluno herda historicosAcademicosTurma
        return this.getHistoricosAcademicosTurma().size() < 2;
    }

    // Sobrescreve o toString para incluir o marcador "AlunoEspecial|" e os dados de Aluno
    @Override
    public String toString() {
        // Usa o formato base do Aluno, mas adiciona o identificador
        // É importante que o Aluno.fromString saiba identificar "AlunoEspecial|"
        // e o fromString de AlunoEspecial saiba lidar com isso.
        return "AlunoEspecial|" + getNome() + "|" + getMatricula() + "|" + getIdade() + "|" + getCurso();
    }

    // Método estático para reconstruir um objeto AlunoEspecial de uma string (compatível com o toString)
    public static AlunoEspecial fromString(String linha) {
        String[] partes = linha.split("\\|");
        // Verifica o prefixo "AlunoEspecial|" e o número de partes
        if (partes.length < 5 || !partes[0].equals("AlunoEspecial")) {
            System.err.println("Formato inválido para AlunoEspecial: " + linha);
            return null;
        }
        try {
            String nome = partes[1];
            String matricula = partes[2];
            int idade = Integer.parseInt(partes[3]); // Converte a idade
            String curso = partes[4];
            return new AlunoEspecial(nome, matricula, idade, curso);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número ao carregar AlunoEspecial (idade): " + linha + " - " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar AlunoEspecial: " + linha + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    // SE VOCÊ QUISER MANTER salvar/carregar ESPECÍFICOS para AlunoEspecial.txt
    // MANTENHA CONSISTÊNCIA DE FORMATO E ARQUIVO!
    // ATENÇÃO: É geralmente melhor ter um único arquivo 'alunos.txt' para todos os tipos de Aluno.
    // Se você realmente quer dois arquivos, seu SistemaAcademico vai precisar carregar AMBOS
    // e o Aluno.fromString precisará lidar com o formato 'AlunoEspecial|...'
    public static void salvarLista(List<AlunoEspecial> lista) { // Renomeado para evitar conflito se houver um 'salvar' na classe base
        try (PrintWriter pw = new PrintWriter(new FileWriter("dados/alunosEspeciais.txt"))) { // Salva na pasta 'dados' e em um arquivo específico
            for (AlunoEspecial a : lista) {
                pw.println(a.toString()); // Usa o toString sobrescrito para formato consistente
            }
            System.out.println("Alunos Especiais salvos: " + lista.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos especiais: " + e.getMessage());
        }
    }

    public static List<AlunoEspecial> carregarLista() { // Renomeado para evitar conflito
        List<AlunoEspecial> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("dados/alunosEspeciais.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                AlunoEspecial a = AlunoEspecial.fromString(linha); // Usa o fromString para carregar
                if (a != null) {
                    lista.add(a);
                }
            }
            System.out.println("Alunos Especiais carregados: " + lista.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar alunos especiais: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar alunos especiais: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}