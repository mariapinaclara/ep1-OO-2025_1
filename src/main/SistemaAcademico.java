package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; 

import aluno.Aluno;
import aluno.AlunoEspecial; 
import aluno.HistoricoAcademicoTurma;
import aluno.MenuAluno;
import disciplina.Disciplina;
import disciplina.MenuDisciplina;
import professor.Professor;
import professor.MenuProfessor;
import turma.Turma;
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
        MenuTurma menuTurma = new MenuTurma(scanner, turmas, alunos, disciplinas, professores, historicosAcademicos);
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
                    System.out.println("Encerrando sistema.");
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
        System.out.println("Arquivo de alunos não encontrado.");
        return;
    }
    alunos.clear(); 
    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            Aluno aluno = null;
            if (linha.startsWith("AlunoEspecial|")) {
                aluno = AlunoEspecial.fromString(linha);
            } else if (linha.startsWith("Aluno|")) { 
                aluno = Aluno.fromString(linha);
            }
            
                if (aluno != null) {
                    alunos.add(aluno);
                } else {
                    System.err.println("Linha de aluno com formato inválido ou desconhecido: " + linha);
                }
            }
            System.out.println("Alunos carregados: " + alunos.size());
        } catch (IOException e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar alunos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void salvarAlunos() {
        criarPastaDados();
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_ALUNOS))) {
        for (Aluno aluno : alunos) {
            pw.println(aluno.toString());
        }

        pw.flush(); 

        System.out.println("Alunos salvos: " + alunos.size());
    } catch (IOException e) {
        System.err.println("Erro ao salvar alunos: " + e.getMessage());
        e.printStackTrace(); 
    }
    }

    private static void carregarProfessores() {
        File arquivo = new File(ARQUIVO_PROFESSORES);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de professores não encontrado.");
            return;
        }
        professores.clear(); 
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
            System.out.println("Arquivo de disciplinas não encontrado.");
            return;
        }
        disciplinas.clear(); 
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
            System.out.println("Arquivo de turmas não encontrado.");
            return;
        }
        turmas.clear(); 
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Turma turma = Turma.fromString(linha);
                if (turma != null) {
                    turmas.add(turma);
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
            System.out.println("Arquivo de históricos acadêmicos não encontrado.");
            return;
        }
        historicosAcademicos.clear(); 
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                HistoricoAcademicoTurma historico = HistoricoAcademicoTurma.fromString(linha);

                if (historico != null) {
                    historicosAcademicos.add(historico);

                    Aluno aluno = buscarAlunoPorMatricula(historico.getMatriculaAluno());

                    if (aluno != null) {
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
        carregarAlunos();
        carregarProfessores();
        carregarDisciplinas();
        carregarTurmas();
        reassociarDadosDasTurmas();
        carregarHistoricosAcademicos();
    }

    
    private static void reassociarDadosDasTurmas() {
        for (Turma turma : turmas) {
            if (turma.getMatriculaProfessor() != null && !turma.getMatriculaProfessor().isEmpty()) {
                Professor professorReal = buscarProfessorPorMatricula(turma.getMatriculaProfessor());
                if (professorReal != null) {
                } else {
                    System.err.println("Aviso: Professor com matrícula " + turma.getMatriculaProfessor() + " não encontrado para a turma " + turma.getCodigo());
                }
            }

            
            if (turma.getCodigoDisciplina() != null && !turma.getCodigoDisciplina().isEmpty()) {
                Disciplina disciplinaReal = buscarDisciplinaPorCodigo(turma.getCodigoDisciplina());
                if (disciplinaReal != null) {
                } else {
                    System.err.println("Aviso: Disciplina com código " + turma.getCodigoDisciplina() + " não encontrada para a turma " + turma.getCodigo());
                }
            }
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