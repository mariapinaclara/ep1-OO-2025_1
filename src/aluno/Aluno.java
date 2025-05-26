package aluno; // Verifique se este é o seu pacote correto para Aluno

import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Collectors; // Necessário se você usar stream no toString() ou outros métodos

public class Aluno {
    private String nome;
    private String matricula;
    private int idade;
    private String curso;
    // Adicionado: Mapa para armazenar históricos acadêmicos por turma.
    // A chave é o código da turma, o valor é o objeto HistoricoAcademicoTurma.
    private Map<String, HistoricoAcademicoTurma> historicosAcademicosTurma;

    public Aluno(String nome, String matricula, int idade, String curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
        this.curso = curso;
        this.historicosAcademicosTurma = new HashMap<>(); // Inicializa o mapa
    }

    // --- Getters ---
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

    // Adicionado: Getter para o mapa de históricos acadêmicos
    public Map<String, HistoricoAcademicoTurma> getHistoricosAcademicosTurma() {
        return historicosAcademicosTurma;
    }

    // Adicionado: Método para obter um histórico acadêmico por código de turma
    // Isso resolve o erro "The method getHistoricoTurma(String) is undefined for the type Aluno"
    public HistoricoAcademicoTurma getHistoricoTurma(String codigoTurma) {
        return historicosAcademicosTurma.get(codigoTurma);
    }

    // --- Setters (se necessário, embora para matricula e nome pode não fazer sentido após criação) ---
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    // Adicionado: Método para adicionar/atualizar um histórico acadêmico de uma turma específica
    // Isso resolve o erro "adicionarHistoricoTurma is not applicable for the arguments (HistoricoAcademicoTurma)"
    // e permite que o SistemaAcademico passe o código da turma e o objeto Historico.
    public void adicionarHistoricoTurma(String codigoTurma, HistoricoAcademicoTurma historico) {
        if (historico != null) { // Garante que não adicionamos um histórico nulo
            this.historicosAcademicosTurma.put(codigoTurma, historico);
        } else {
            System.err.println("Erro: Tentativa de adicionar histórico nulo para o aluno " + this.matricula + " na turma " + codigoTurma);
        }
    }

    // Adicionado: Método para definir todos os históricos de uma vez (usado principalmente no carregamento)
    // Isso resolve o erro "The method setHistoricosAcademicos(new ArrayList<>()) is undefined for the type Aluno"
    public void setHistoricosAcademicosTurma(Map<String, HistoricoAcademicoTurma> historicos) {
        this.historicosAcademicosTurma = historicos != null ? new HashMap<>(historicos) : new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno|").append(nome).append("|")
          .append(matricula).append("|")
          .append(idade).append("|")
          .append(curso);

        // Não salvamos os históricos diretamente no toString do Aluno,
        // eles são salvos em um arquivo separado (historicos.txt) e associados
        // durante o carregamento pelo SistemaAcademico.
        return sb.toString();
    }

    // Método estático para reconstruir um objeto Aluno de uma string
    public static Aluno fromString(String linha) {
        String[] partes = linha.split("\\|");
        if (partes.length < 5 || !partes[0].equals("Aluno")) { // Verifica o prefixo "Aluno|"
            // Pode ser um AlunoEspecial, ou formato inválido
            return null;
        }
        try {
            String nome = partes[1];
            String matricula = partes[2];
            int idade = Integer.parseInt(partes[3]);
            String curso = partes[4];
            // Ao carregar do arquivo de alunos, o mapa de históricos começa vazio
            // Ele será preenchido pelo carregarHistoricosAcademicos() no SistemaAcademico
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