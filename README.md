# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** Maria Clara de Freitas Pina
- **Matrícula:** 232021900
- **Curso:** Engenharia de Software
- **Turma:** T06

---

## Instruções para Compilação e Execução

1. **Compilação:**  
- 'cd src/main/java/

javac com/seuprojeto/**/*.java'

2. **Execução:**  
- 'java -cp src/main/java/ com.sistemaacademico.SistemaAcademico'

3. **Estrutura de Pastas:**  

├── src/ 

│   ├── main/ 

│   │   ├── java/ 

│   │   │       └── sistemaacademico/ 

│   │   │           ├── aluno/ 

│   │   │           │   ├── Aluno.java 

│   │   │           │   ├── AlunoEspecial.java 

│   │   │           │   ├── HistoricoAcademicoTurma.java 

│   │   │           │   └── MenuAluno.java 

│   │   │           ├── avaliacao/ 

│   │   │           │   ├── Avaliacao.java 

│   │   │           │   └── MenuAvaliacao.java 

│   │   │           ├── disciplina/ 

│   │   │           │   ├── Disciplina.java 

│   │   │           │   └── MenuDisciplina.java 

│   │   │           ├── frequencia/ 

│   │   │           │   ├── Frequencia.java 

│   │   │           │   └── MenuFrequencia.java 

│   │   │           ├── professor/ 

│   │   │           │   ├── Professor.java 

│   │   │           │   └── MenuProfessor.java 

│   │   │           ├── relatorios/ 

│   │   │           │   ├── CalculoAcademicoService.java 

│   │   │           │   ├── MenuRelatorio.java 

│   │   │           │   └── RelatorioAcademicoService.java 

│   │   │           ├── turma/ 

│   │   │           │   ├── Turma.java 

│   │   │           │   └── MenuTurma.java 

│   │   │           ├── util/   

│   │   │           │   └── PersistenciaUtils.java 

│   │   │           └── SistemaAcademico.java   

│   │   └── resources/ 

│   │       └── dados/ 

│   │           ├── alunos.txt 

│   │           ├── disciplinas.txt 

│   │           ├── historicos.txt 

│   │           ├── professores.txt 

│   │           └── turmas.txt 

├── .gitignore 

└── README.md 

4. **Versão do JAVA utilizada:**  
   O desenvolvimento e os testes deste projeto foram realizados utilizando a Java Development Kit (JDK) versão 21.

---

## Vídeo de Demonstração

- [Inserir o link para o vídeo no YouTube/Drive aqui]

---

## Prints da Execução

1. Menu Principal:  
   ![Inserir Print 1](caminho/do/print1.png)

2. Cadastro de Aluno:  
   ![Inserir Print 2](caminho/do/print2.png)

3. Relatório de Frequência/Notas:  
   ![Inserir Print 3](caminho/do/print3.png)

---

## Principais Funcionalidades Implementadas

- [OK] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [OK] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [OK] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [OK] Lançamento de notas e controle de presença
- [OK] Cálculo de média final e verificação de aprovação/reprovação
- [OK] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [OK] Persistência de dados em arquivos (.txt ou .csv)
- [OK] Tratamento de duplicidade de matrículas
- [OK] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- Tive dificuldade para implementar nos códigos o método de persistência: para salvar os dados inseridos mesmo após encerrar o programa.

- A questão dos relatórios também foi um pouco  dificil para mim, pois estava meio confusa com o que deveria ser implementado e o que deveria aparecer na saída.

- Um desafio significativo foi a persistência e correta leitura do tipoMedia dos históricos acadêmicos, o que inicialmente impedia o cálculo da média final. A resolução envolveu um processo rigoroso de depuração e a garantia da recriação dos arquivos de dados para que as informações fossem salvas e carregadas no formato correto, permitindo que a lógica de cálculo funcionasse.

---

## Contato

- Email pessoal: maria.pinaclara@gmail.com
