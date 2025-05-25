package aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AlunoEspecial extends Aluno {

    public AlunoEspecial(String nome, String matricula, String curso) {
        super(nome, matricula, curso);
    }

    @Override
    public void matricularDisciplina(String codigoDisciplina) {
        if (disciplinasMatriculadas.size() < 2) { 
            super.matricularDisciplina(codigoDisciplina);
        } else {
            System.out.println("Aluno Especial só pode cursar no máximo 2 disciplinas.");
        }
    }

   public static void salvar(List<AlunoEspecial> lista) {
    try (PrintWriter pw = new PrintWriter(new FileWriter("AlunoEspecial.txt"))) {
        for (AlunoEspecial a : lista) {
            pw.println(a.getMatricula() + "," + a.getNome() + "," + a.getCurso());
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static List<AlunoEspecial> carregar() {
    List<AlunoEspecial> lista = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("AlunoEspecial.txt"))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(",");
            AlunoEspecial a = new AlunoEspecial(campos[1], campos[0], campos[2]);
            lista.add(a);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return lista;
    }
}
