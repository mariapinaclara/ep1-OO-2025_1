package aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AlunoEspecial extends Aluno {
    public AlunoEspecial(String nome, String matricula, int idade, String curso) {
        super(nome, matricula, idade, curso);
    }

    public boolean podeMatricularNovaTurma() {
        return this.getHistoricosAcademicosTurma().size() < 2;
    }

    @Override
    public String toString() {
        return "AlunoEspecial|" + getNome() + "|" + getMatricula() + "|" + getIdade() + "|" + getCurso();
    }

    public static AlunoEspecial fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length < 5 || !partes[0].equals("AlunoEspecial")) {
            System.err.println("Formato inválido para AlunoEspecial: " + linha);
            return null;
        }
        try {
            String nome = partes[1];
            String matricula = partes[2];
            int idade = Integer.parseInt(partes[3]); 
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

    public static void salvarLista(List<AlunoEspecial> lista) { 
        try (PrintWriter pw = new PrintWriter(new FileWriter("dados/alunosEspeciais.txt"))) { 
            for (AlunoEspecial a : lista) {
                pw.println(a.toString()); 
            }
            System.out.println("Alunos Especiais salvos: " + lista.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos especiais: " + e.getMessage());
        }
    }

    public static List<AlunoEspecial> carregarLista() { 
        List<AlunoEspecial> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("dados/alunosEspeciais.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                AlunoEspecial a = AlunoEspecial.fromString(linha); 
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