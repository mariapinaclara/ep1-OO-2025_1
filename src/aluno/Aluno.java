package aluno; 

import java.util.HashMap;
import java.util.Map;
import aluno.HistoricoAcademicoTurma;

public class Aluno {
    private String nome;
    private String matricula;
    private int idade;
    private String curso;
    private Map<String, HistoricoAcademicoTurma> historicosAcademicosTurma;

    public Aluno(String nome, String matricula, int idade, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
        this.curso = curso;
        this.historicosAcademicosTurma = new HashMap<>(); 
    }

  
    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getIdade() {
        return idade;
    }

    public String getCurso() {
        return curso;
    }

    
    public Map<String, HistoricoAcademicoTurma> getHistoricosAcademicosTurma() {
        return historicosAcademicosTurma;
    }

   
    public HistoricoAcademicoTurma getHistoricoTurma(String codigoTurma) {
        return historicosAcademicosTurma.get(codigoTurma);
    }

    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

  
    public void adicionarHistoricoTurma(String codigoTurma, HistoricoAcademicoTurma historico) {
        if (historico != null) { 
            this.historicosAcademicosTurma.put(codigoTurma, historico);
        } else {
            System.err.println("Erro: Tentativa de adicionar histórico nulo para o aluno " + this.matricula + " na turma " + codigoTurma);
        }
    }

   
    public void setHistoricosAcademicosTurma(Map<String, HistoricoAcademicoTurma> historicos) {
        this.historicosAcademicosTurma = historicos != null ? new HashMap<>(historicos) : new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno|").append(nome).append("|").append(matricula).append("|").append(idade).append("|").append(curso);

        return sb.toString();
    }

   
    public static Aluno fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length < 5 || !partes[0].equals("Aluno")) { 
            
            return null;
        }
        try {
            String nome = partes[1];
            String matricula = partes[2];
            int idade = Integer.parseInt(partes[3]);
            String curso = partes[4];
          
            return new Aluno(nome, matricula, idade, curso);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao converter número ao carregar Aluno: " + linha + " - " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar Aluno: " + linha + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }    
}