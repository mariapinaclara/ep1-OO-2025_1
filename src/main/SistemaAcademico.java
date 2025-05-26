package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

import aluno.Aluno;
import aluno.AlunoEspecial; // Para AlunoEspecial, se você o tiver
import aluno.HistoricoAcademicoTurma;
import aluno.MenuAluno;
import disciplina.Disciplina;
import disciplina.MenuDisciplina;
import professor.Professor;
import professor.MenuProfessor;
import turma.Turma; // Certifique-se que o pacote está correto
import turma.MenuTurma;
import frequencia.MenuFrequencia;
import relatorios.CalculoAcademicoService;
import relatorios.MenuRelatorio;

public class SistemaAcademico {
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();
    private static List<HistoricoAcademicoTurma> historicosAcademicos = new ArrayList<>();

    private static final String PASTA_DADOS = "dados/";
    private static final String ARQUIVO_ALUNOS = PASTA_DADOS + "alunos.txt";
    private static final String ARQUIVO_PROFESSORES = PASTA_DADOS + "professores.txt";
    private static final String ARQUIVO_DISCIPLINAS = PASTA_DADOS + "disciplinas.txt";
    private static final String ARQUIVO_TURMAS = PASTA_DADOS + "turmas.txt";
    private static final String ARQUIVO_HISTORICOS = PASTA_DADOS + "historicos.txt";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarTodosOsDados();

        CalculoAcademicoService calculoAcademicoService = new CalculoAcademicoService();

        MenuAluno menuAluno = new MenuAluno(scanner, alunos, turmas, historicosAcademicos);
        MenuDisciplina menuDisciplina = new MenuDisciplina(scanner, disciplinas);
        MenuProfessor menuProfessor = new MenuProfessor(scanner, professores);
        MenuTurma menuTurma = new MenuTurma(scanner, turmas, alunos, disciplinas, professores);
        MenuFrequencia menuFrequencia = new MenuFrequencia(scanner, alunos, turmas, historicosAcademicos);
        MenuRelatorio menuRelatorio = new MenuRelatorio(scanner, alunos, turmas, disciplinas, professores, historicosAcademicos, calculoAcademicoService);

        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL DO SISTEMA ACADÊMICO ===");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Disciplinas");
            System.out.println("3. Gerenciar Professores");
            System.out.println("4. Gerenciar Turmas (Matrícula)");
            System.out.println("5. Lançar Notas e Faltas (Avaliação/Frequência)");
            System.out.println("6. Gerar Relatórios e Boletins");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next();
                System.out.print("Escolha uma opção: ");
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAluno.exibirMenu();
                    break;
                case 2:
                    menuDisciplina.exibirMenu();
                    break;
                case 3:
                    menuProfessor.exibirMenu();
                    break;
                case 4:
                    menuTurma.exibirMenu();
                    break;
                case 5:
                    menuFrequencia.exibirMenu();
                    break;
                case 6:
                    menuRelatorio.exibirMenu();
                    break;
                case 0:
                    salvarTodosOsDados();
                    System.out.println("Saindo do sistema. Dados salvos.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void criarPastaDados() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            boolean criada = pasta.mkdirs();
            if (!criada) {
                System.err.println("Falha ao criar pasta de dados!");
            }
        }
    }

    private static void carregarAlunos() {
        File arquivo = new File(ARQUIVO_ALUNOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de alunos não encontrado. Criando um novo.");
            return;
        }
        alunos.clear(); // Limpa a lista antes de carregar para evitar duplicatas ao recarregar
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Aluno aluno = null;
                if (linha.contains("Especial")) {
                    try {
                        aluno = AlunoEspecial.fromString(linha);
                    } catch (Exception e) {
                        System.err.println("Erro ao carregar AlunoEspecial, tentando como Aluno normal: " + linha + " - " + e.getMessage());
                        aluno = Aluno.fromString(linha);
                    }
                } else {
                    aluno = Aluno.fromString(linha);
                }

                if (aluno != null) {
                    alunos.add(aluno);
                }
            }
            System.out.println("Alunos carregados: " + alunos.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar alunos (verifique o fromString de Aluno): " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarAlunos() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_ALUNOS))) {
            for (Aluno aluno : alunos) {
                pw.println(aluno.toString());
            }
            System.out.println("Alunos salvos: " + alunos.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    private static void carregarProfessores() {
        File arquivo = new File(ARQUIVO_PROFESSORES);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de professores não encontrado. Criando um novo.");
            return;
        }
        professores.clear(); // Limpa a lista antes de carregar
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Professor professor = Professor.fromString(linha);
                if (professor != null) {
                    professores.add(professor);
                }
            }
            System.out.println("Professores carregados: " + professores.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar professores: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar professores: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarProfessores() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_PROFESSORES))) {
            for (Professor professor : professores) {
                pw.println(professor.toString());
            }
            System.out.println("Professores salvos: " + professores.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    private static void carregarDisciplinas() {
        File arquivo = new File(ARQUIVO_DISCIPLINAS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de disciplinas não encontrado. Criando um novo.");
            return;
        }
        disciplinas.clear(); // Limpa a lista antes de carregar
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Disciplina disciplina = Disciplina.fromString(linha);
                if (disciplina != null) {
                    disciplinas.add(disciplina);
                }
            }
            System.out.println("Disciplinas carregadas: " + disciplinas.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar disciplinas: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar disciplinas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarDisciplinas() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_DISCIPLINAS))) {
            for (Disciplina disciplina : disciplinas) {
                pw.println(disciplina.toString());
            }
            System.out.println("Disciplinas salvas: " + disciplinas.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    private static void carregarTurmas() {
        File arquivo = new File(ARQUIVO_TURMAS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de turmas não encontrado. Criando um novo.");
            return;
        }
        turmas.clear(); // Limpa a lista antes de carregar
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Turma turma = Turma.fromString(linha);
                if (turma != null) {
                    turmas.add(turma);
                    // A re-associação de Professor, Disciplina e Alunos da turma
                    // será feita em 'reassociarDadosDasTurmas()'
                }
            }
            System.out.println("Turmas carregadas: " + turmas.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar turmas: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar turmas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarTurmas() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_TURMAS))) {
            for (Turma turma : turmas) {
                pw.println(turma.toString());
            }
            System.out.println("Turmas salvas: " + turmas.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar turmas: " + e.getMessage());
        }
    }

    private static void carregarHistoricosAcademicos() {
        File arquivo = new File(ARQUIVO_HISTORICOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de históricos acadêmicos não encontrado. Será criado um novo ao salvar.");
            return;
        }
        historicosAcademicos.clear(); // Limpa a lista antes de carregar
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                HistoricoAcademicoTurma historico = HistoricoAcademicoTurma.fromString(linha);

                if (historico != null) {
                    historicosAcademicos.add(historico);

                    Aluno aluno = buscarAlunoPorMatricula(historico.getMatriculaAluno());

                    if (aluno != null) {
                        // Chama o método correto em Aluno.java (que você já me confirmou)
                        aluno.adicionarHistoricoTurma(historico.getCodigoTurma(), historico);
                    } else {
                        System.err.println("Aviso: Aluno com matrícula " + historico.getMatriculaAluno() + " não encontrado para associar histórico de turma: " + historico.getCodigoTurma());
                    }
                }
            }
            System.out.println("Históricos acadêmicos carregados: " + historicosAcademicos.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar históricos acadêmicos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar históricos acadêmicos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarHistoricosAcademicos() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_HISTORICOS))) {
            for (HistoricoAcademicoTurma historico : historicosAcademicos) {
                pw.println(historico.toString());
            }
            System.out.println("Históricos acadêmicos salvos: " + historicosAcademicos.size());
        } catch (IOException e) {
            System.err.println("Erro ao salvar históricos acadêmicos: " + e.getMessage());
        }
    }

    private static Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }

    private static Professor buscarProfessorPorMatricula(String matricula) {
        for (Professor professor : professores) {
            if (professor.getMatricula().equals(matricula)) {
                return professor;
            }
        }
        return null;
    }

    private static Disciplina buscarDisciplinaPorCodigo(String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null;
    }

    private static Turma buscarTurmaPorCodigo(String codigo) {
        for (Turma turma : turmas) {
            if (turma.getCodigo().equals(codigo)) {
                return turma;
            }
        }
        return null;
    }

    private static void carregarTodosOsDados() {
        criarPastaDados();
        // Ordem de carregamento: Entidades independentes primeiro
        carregarAlunos();
        carregarProfessores();
        carregarDisciplinas();

        // Em seguida, entidades que dependem das primeiras
        carregarTurmas();

        // Após carregar as turmas, é essencial reassociar os objetos completos
        reassociarDadosDasTurmas();

        // Por fim, carregue os históricos (eles dependem de Alunos que já devem ter sido carregados)
        carregarHistoricosAcademicos();
    }

    // Método para reassociar os objetos completos (Professor, Disciplina, Alunos) às Turmas
    private static void reassociarDadosDasTurmas() {
        for (Turma turma : turmas) {
            // Reassociar Professor: Usamos getMatriculaProfessor() diretamente da Turma
            if (turma.getMatriculaProfessor() != null && !turma.getMatriculaProfessor().isEmpty()) {
                Professor professorReal = buscarProfessorPorMatricula(turma.getMatriculaProfessor());
                if (professorReal != null) {
                    // Se Turma precisasse armazenar o objeto Professor: turma.setProfessor(professorReal);
                    // Mas sua Turma armazena apenas a String da matrícula, então não é necessário um setProfessor(Professor)
                    // aqui, a menos que você queira adicionar um campo 'Professor objetoProfessor' na Turma.
                    // Por enquanto, a informação da matrícula já está na Turma, o que é o suficiente para as associações.
                } else {
                    System.err.println("Aviso: Professor com matrícula " + turma.getMatriculaProfessor() + " não encontrado para a turma " + turma.getCodigo());
                }
            }

            // Reassociar Disciplina: Usamos getCodigoDisciplina() diretamente da Turma
            if (turma.getCodigoDisciplina() != null && !turma.getCodigoDisciplina().isEmpty()) {
                Disciplina disciplinaReal = buscarDisciplinaPorCodigo(turma.getCodigoDisciplina());
                if (disciplinaReal != null) {
                    // Similar ao Professor, sua Turma armazena apenas a String do código.
                    // Não é necessário um setDisciplina(Disciplina) aqui.
                } else {
                    System.err.println("Aviso: Disciplina com código " + turma.getCodigoDisciplina() + " não encontrada para a turma " + turma.getCodigo());
                }
            }

            // Reassociar Alunos Matriculados na Turma
            // Este bloco é para associar os objetos Aluno COMPLETO à lista de alunos matriculados na Turma,
            // caso o Turma.fromString tenha carregado apenas as matrículas ou "alunos placeholder".
            // Sua Turma.java não armazena alunos matriculados no toString/fromString,
            // então esta lista estaria vazia após o fromString.
            // A matrícula de alunos na turma é feita no MenuTurma.matricularAluno().
            // Se você precisa que a Turma mantenha uma lista de objetos Aluno completos após o carregamento,
            // o `fromString` da Turma e seu `toString` precisariam ser ajustados para salvar/carregar as matrículas dos alunos.
            // Por enquanto, a lógica assume que a lista `alunosMatriculados` da Turma é populada durante a execução
            // e não é persistida diretamente com a turma.
            // Se você quer persistir os alunos matriculados dentro da Turma, precisará de:
            // 1. Uma forma de salvar as matrículas dos alunos no toString da Turma.
            // 2. Uma forma de ler essas matrículas no fromString da Turma e criar objetos Aluno "placeholder".
            // 3. Este loop de re-associação continuaria sendo necessário para substituir os placeholders pelos objetos Aluno completos.
        }
    }


    private static void salvarTodosOsDados() {
        salvarAlunos();
        salvarProfessores();
        salvarDisciplinas();
        salvarTurmas();
        salvarHistoricosAcademicos();
    }
}