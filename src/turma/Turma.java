package disciplina;

public class Turma {
    private String codigoDisciplina;  // Para relacionar com disciplina
    private String professor;
    private String semestre;
    private String formaAvaliacao; // Ex: "Media Final 1" ou "Media Final 2"
    private boolean presencial;
    private String sala;   // Se presencial
    private String horario;
    private int capacidadeMaxima;
    private int vagasDisponiveis;

    public Turma(String codigoDisciplina, String professor, String semestre, String formaAvaliacao,
                 boolean presencial, String sala, String horario, int capacidadeMaxima) {
        this.codigoDisciplina = codigoDisciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.sala = presencial ? sala : "Remoto";
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.vagasDisponiveis = capacidadeMaxima;
    }

    // Getters e setters
    public String getCodigoDisciplina() {
         return codigoDisciplina; 
        }
    public String getProfessor() { 
        return professor; 
    }
    public String getSemestre() {
         return semestre; 
        }
    public String getFormaAvaliacao() {
         return formaAvaliacao; 
        }
    public boolean isPresencial() {
         return presencial; 
        }
    public String getSala() {
         return sala; 
        }
    public String getHorario() {
         return horario; 
        }
    public int getCapacidadeMaxima() {
         return capacidadeMaxima; 
        }
    public int getVagasDisponiveis() {
         return vagasDisponiveis; 
        }

    public void ocuparVaga() {
        if (vagasDisponiveis > 0) vagasDisponiveis--;
    }

    @Override
    public String toString() {
        return "Turma: " + codigoDisciplina + " | Prof: " + professor + " | Semestre: " + semestre +
               " | Avaliação: " + formaAvaliacao + " | " + (presencial ? "Presencial" : "Remota") + " | Sala: " + sala +
               " | Horário: " + horario + " | Vagas: " + vagasDisponiveis + "/" + capacidadeMaxima;
    }
}

