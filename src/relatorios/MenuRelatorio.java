package relatorios;

import aluno.Aluno;
import turma.Turma;
import disciplina.Disciplina;
import professor.Professor;
import aluno.HistoricoAcademicoTurma; // Adicione este import
import java.util.List;
import java.util.Scanner;

public class MenuRelatorio {

    private Scanner scanner;
    private RelatorioAcademicoService relatorioService;

    // Construtor CORRIGIDO para receber todas as listas e o CalculoAcademicoService
    public MenuRelatorio(Scanner scanner, List<Aluno> alunos, List<Turma> turmas,
                         List<Disciplina> disciplinas, List<Professor> professores,
                         List<HistoricoAcademicoTurma> historicosAcademicos, // NOVO PARAMETRO
                         CalculoAcademicoService calculoAcademicoService) { // NOVO PARAMETRO
        this.scanner = scanner;
        // Instancia RelatorioAcademicoService com todos os parâmetros que ele espera
        this.relatorioService = new RelatorioAcademicoService(alunos, turmas, disciplinas, professores, historicosAcademicos, calculoAcademicoService);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== Menu de Relatórios ===");
            System.out.println("1. Gerar Relatório por Turma");
            System.out.println("2. Exibir Boletim de Aluno");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    gerarRelatorioPorTurma();
                    break;
                case 2:
                    exibirBoletimAluno();
                    break;
                case 0:
                    System.out.println("Operação encerrada. Retornando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void gerarRelatorioPorTurma() {
        System.out.print("Digite o código da turma para o relatório: ");
        String codTurma = scanner.nextLine();
        relatorioService.gerarRelatorioPorTurma(codTurma);
    }

    private void exibirBoletimAluno() {
        System.out.print("Digite a matrícula do aluno para o boletim: ");
        String matriculaAluno = scanner.nextLine();
        System.out.print("Digite o código da turma para o boletim (vazio para todas as turmas): ");
        String codigoTurma = scanner.nextLine(); // Pode ser vazio para todas as turmas

        // A chamada agora é mais simples
        relatorioService.exibirBoletimAluno(matriculaAluno, codigoTurma);
    }
}