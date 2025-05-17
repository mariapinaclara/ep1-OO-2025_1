package disciplina;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private String prerequisitio;

    public Disciplina(String nome, String codigo, int cargaHoraria, String prerequisito) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.prerequisitio = prerequisito;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getPrerequisito() {
        return prerequisitio;
    }

    //Método para salvar em formato de texto
    //public String toString() {
        //return nome + ";" + codigo + ";" + cargaHoraria + ";" + prerequisitio;
    //}

    //Método para ler o arquivo
    public static Disciplina fromArquivos(String linha) {
        String[] partes = linha.split(";");
        String nome = partes[0];
        String codigo = partes[1];
        int cargaHoraria = Integer.parseInt(partes[2]);
        String prerequisito = partes[3];
        return new Disciplina(nome, codigo, cargaHoraria, prerequisito);
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " | Código: " + codigo + " | Carga Horária: " + cargaHoraria + " horas | Pré-Requisito: " + prerequisitio;
    }
}