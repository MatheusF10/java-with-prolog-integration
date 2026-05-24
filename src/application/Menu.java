package application;

import java.util.List;
import java.util.Scanner;

/**
 * Text-based user interface management layer.
 * Dependencies are injected externally to promote loose coupling.
 */
public class Menu {
    private final IntegradorProlog integrador;
    private final Scanner scanner;

    /**
     * Constructor using Dependency Injection for both the Prolog engine and the input stream scanner.
     */
    public Menu(IntegradorProlog integrador, Scanner scanner) {
        this.integrador = integrador;
        this.scanner = scanner;
    }

    /**
     * Renders the main menu system loop.
     */
    public void exibir() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=================================================");
            System.out.println("   SISTEMA DE MONITORIZAÇÃO PEDAGÓGICA (PROLOG)  ");
            System.out.println("=================================================");
            System.out.println("1. Listar alunos em risco");
            System.out.println("2. Listar alunos participativos");
            System.out.println("3. Listar alunos com bom desempenho");
            System.out.println("4. Listar alunos acima da média da turma");
            System.out.println("5. Consultar aluno por ID");
            System.out.println("6. Adicionar um novo aluno");
            System.out.println("7. Atualizar a nota média de um aluno");
            System.out.println("8. Atualizar as participações no fórum");
            System.out.println("9. Remover aluno do sistema");
            System.out.println("10. [BÓNUS] Listar alunos sob observação");
            System.out.println("0. Salvar e Sair");
            System.out.print("Selecione uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("[AVISO] Entrada inválida. Por favor, digite o número de uma opção.");
            }
        }
    }

    /**
     * Routes the selected option to the appropriate subsystem.
     */
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> imprimirListaApoio("listar_em_risco", "Alunos Categorizados Em Risco");
            case 2 -> imprimirListaApoio("listar_participativos", "Alunos com Participação Adequada");
            case 3 -> imprimirListaApoio("listar_bons", "Alunos com Desempenho Positivo");
            case 4 -> imprimirListaApoio("listar_acima_media", "Alunos Acima da Média Global da Turma");
            case 5 -> consultarPorId();
            case 6 -> executarInsercao();
            case 7 -> executarModificacaoMedia();
            case 8 -> executarModificacaoParticipacao();
            case 9 -> executarRemocao();
            case 10 -> imprimirListaApoio("listar_em_observacao", "Alunos sob Observação Pedagógica (Bónus)");
            case 0 -> System.out.println("A encerrar a aplicação. Armazenamento sincronizado.");
            default -> System.out.println("[AVISO] Opção desconhecida. Tente novamente.");
        }
    }

    /**
     * Helper method to output formatted student records fetched from list predicates.
     */
    private void imprimirListaApoio(String predicado, String cabecalho) {
        System.out.println("\n--- " + cabecalho.toUpperCase() + " ---");
        List<Integer> ids = integrador.obterListaDePredicado(predicado);
        if (ids.isEmpty()) {
            System.out.println("Nenhum aluno preenche os critérios desta categoria.");
            return;
        }
        for (int id : ids) {
            System.out.printf("ID: %-3d | Nome: %-15s | Média: %-5.2f | Fórum: %d\n",
                    id, integrador.obterNomeAluno(id), integrador.obterMediaAluno(id), integrador.obterParticipacoesAluno(id));
        }
    }

    /**
     * Handles student data query by ID.
     */
    private void consultarPorId() {
        System.out.print("Digite o ID do aluno: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            String nome = integrador.obterNomeAluno(id);
            if (nome != null) {
                System.out.println("\n--- Registo de Perfil do Aluno ---");
                System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Média Atual: " + integrador.obterMediaAluno(id));
                System.out.println("Participações no Fórum: " + integrador.obterParticipacoesAluno(id));
            } else {
                System.out.println("[AVISO] Nenhum registo encontrado para o ID fornecido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Formato numérico inválido para o ID.");
        }
    }

    /**
     * Collects and processes insertion data for a new student.
     */
    private void executarInsercao() {
        try {
            System.out.print("Digite o ID numérico para o novo aluno: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (integrador.obterNomeAluno(id) != null) {
                System.out.println("[ERRO] Este ID de aluno já está em uso.");
                return;
            }
            System.out.print("Digite o nome completo: ");
            String nome = scanner.nextLine();
            if (integrador.adicionarAluno(id, nome)) {
                System.out.println("[SUCESSO] Aluno adicionado e sincronizado no motor Prolog.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Falha ao processar valores numéricos.");
        }
    }

    /**
     * Modifies a student's average grade data.
     */
    private void executarModificacaoMedia() {
        try {
            System.out.print("Digite o ID do Aluno: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (integrador.obterNomeAluno(id) == null) {
                System.out.println("[ERRO] O aluno não existe.");
                return;
            }
            System.out.print("Digite a nova nota média (0.0 a 20.0): ");
            double media = Double.parseDouble(scanner.nextLine());
            if (integrador.atualizarMedia(id, media)) {
                System.out.println("[SUCESSO] Nota média do aluno atualizada com sucesso.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Formato de entrada corrompido.");
        }
    }

    /**
     * Modifies a student's forum interaction count.
     */
    private void executarModificacaoParticipacao() {
        try {
            System.out.print("Digite o ID do Aluno: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (integrador.obterNomeAluno(id) == null) {
                System.out.println("[ERRO] O aluno não existe.");
                return;
            }
            System.out.print("Digite o novo número de participações no fórum: ");
            int parts = Integer.parseInt(scanner.nextLine());
            if (integrador.atualizarParticipacao(id, parts)) {
                System.out.println("[SUCESSO] Contagem de interações no fórum atualizada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Valor inteiro inválido.");
        }
    }

    /**
     * Deletes a student completely from the database facts.
     */
    private void executarRemocao() {
        try {
            System.out.print("Digite o ID do aluno a remover: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (integrador.obterNomeAluno(id) == null) {
                System.out.println("[ERRO] Registo não encontrado na base de conhecimento.");
                return;
            }
            System.out.print("Tem a certeza absoluta que deseja remover este aluno? (S/N): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("S") || confirmacao.equalsIgnoreCase("Y")) {
                if (integrador.removerAluno(id)) {
                    System.out.println("[SUCESSO] Todos os factos associados a este ID foram eliminados.");
                }
            } else {
                System.out.println("Remoção abortada pelo utilizador.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERRO] Formato de ID incorreto.");
        }
    }
}