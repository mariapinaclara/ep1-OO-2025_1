package aluno;

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
}
