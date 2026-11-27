import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal do Sistema de Folha de Pagamento.
 * Exibe o menu, cadastra colaboradores, gera a folha e o resumo.
 */
public class Principal {

    // Lista que armazena todos os colaboradores cadastrados
    private static final ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiroValido("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarPadrao();
                    break;
                case 2:
                    cadastrarComissionado();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    gerarFolha();
                    break;
                case 5:
                    exibirResumo();
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Escolha um número de 0 a 5.");
            }
        } while (opcao != 0);

        sc.close();
    }

    // ---------------------------------------------------------------
    // Menu
    // ---------------------------------------------------------------

    public static void exibirMenu() {
        System.out.println("\n===== SISTEMA DE FOLHA DE PAGAMENTO =====");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair");
        System.out.println("=========================================");
    }

    // ---------------------------------------------------------------
    // Cadastros
    // ---------------------------------------------------------------

    public static void cadastrarPadrao() {
        System.out.println("\n--- Cadastro de Colaborador Padrão ---");
        int matricula = lerMatricula();
        String nome = lerTextoValido("Nome completo: ");
        double salarioBase = lerDoubleValido("Salário base: R$ ");

        colaboradores.add(new ColaboradorPadrao(matricula, nome, salarioBase));
        System.out.println("Colaborador padrão cadastrado com sucesso!");
    }

    public static void cadastrarComissionado() {
        System.out.println("\n--- Cadastro de Colaborador Comissionado ---");
        int matricula = lerMatricula();
        String nome = lerTextoValido("Nome completo: ");
        double salarioBase = lerDoubleValido("Salário base: R$ ");
        double valorVendas = lerDoubleValido("Valor total de vendas no mês: R$ ");
        double percentual = lerPercentualValido("Percentual de comissão (0 a 100): ");

        colaboradores.add(new ColaboradorComissionado(matricula, nome, salarioBase,
                valorVendas, percentual));
        System.out.println("Colaborador comissionado cadastrado com sucesso!");
    }

    public static void cadastrarProducao() {
        System.out.println("\n--- Cadastro de Colaborador Produção ---");
        int matricula = lerMatricula();
        String nome = lerTextoValido("Nome completo: ");
        double salarioBase = lerDoubleValido("Salário base: R$ ");
        int quantidadePecas = lerInteiroValido("Quantidade de peças produzidas: ");
        double valorPorPeca = lerDoubleValido("Valor por peça: R$ ");

        colaboradores.add(new ColaboradorProducao(matricula, nome, salarioBase,
                quantidadePecas, valorPorPeca));
        System.out.println("Colaborador de produção cadastrado com sucesso!");
    }

    // ---------------------------------------------------------------
    // Folha de pagamento e resumo
    // ---------------------------------------------------------------

    public static void gerarFolha() {
        System.out.println("\n========== FOLHA DE PAGAMENTO ==========");

        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        for (Colaborador c : colaboradores) {
            c.exibirDados();
            System.out.println("Salário Final: " + Colaborador.formatarMoeda(c.calcularSalarioFinal()));
            System.out.println("----------------------------------------");
        }
    }

    public static void exibirResumo() {
        System.out.println("\n========== RESUMO DA FOLHA ==========");

        if (colaboradores.isEmpty()) {
            System.out.println("Nenhum colaborador cadastrado.");
            return;
        }

        System.out.println("Quantidade de colaboradores: " + colaboradores.size());
        System.out.println("Total da folha: " + Colaborador.formatarMoeda(calcularTotalFolha()));
        System.out.println("Média salarial: " + Colaborador.formatarMoeda(calcularMediaSalarial()));
    }

    public static double calcularTotalFolha() {
        double total = 0;
        for (Colaborador c : colaboradores) {
            total += c.calcularSalarioFinal();
        }
        return total;
    }

    public static double calcularMediaSalarial() {
        if (colaboradores.isEmpty()) {
            return 0;
        }
        return calcularTotalFolha() / colaboradores.size();
    }

    // ---------------------------------------------------------------
    // Leitura e validação de dados
    // ---------------------------------------------------------------

    /**
     * Lê um número inteiro não negativo, repetindo até o valor ser válido.
     */
    public static int lerInteiroValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor < 0) {
                    System.out.println("Valor inválido: não pode ser negativo.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: digite um número inteiro.");
            }
        }
    }

    /**
     * Lê um número decimal não negativo, repetindo até o valor ser válido.
     * Aceita vírgula ou ponto como separador decimal.
     */
    public static double lerDoubleValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (!Double.isFinite(valor)) {
                    System.out.println("Digite um número válido.");
                    continue;
                }
                if (valor < 0) {
                    System.out.println("Valor inválido: não pode ser negativo.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: digite um número.");
            }
        }
    }

    /**
     * Lê um percentual entre 0 e 100.
     */
    public static double lerPercentualValido(String mensagem) {
        while (true) {
            double valor = lerDoubleValido(mensagem);
            if (valor <= 100) {
                return valor;
            }
            System.out.println("Valor inválido: o percentual não pode passar de 100.");
        }
    }

    /**
     * Lê um texto que não pode ficar vazio.
     */
    public static String lerTextoValido(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Entrada inválida: o campo não pode ficar vazio.");
        }
    }

    /**
     * Lê a matrícula e impede que seja repetida.
     */
    public static int lerMatricula() {
        while (true) {
            int matricula = lerInteiroValido("Matrícula: ");
            if (!matriculaExiste(matricula)) {
                return matricula;
            }
            System.out.println("Matrícula já cadastrada. Informe outra.");
        }
    }

    public static boolean matriculaExiste(int matricula) {
        for (Colaborador c : colaboradores) {
            if (c.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }
}