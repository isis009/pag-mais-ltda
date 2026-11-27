import java.util.Locale;

/**
 * Superclasse que representa um colaborador da empresa.
 * Guarda os dados comuns a todos os tipos de vínculo.
 */
public class Colaborador {

    // Atributos privados (encapsulamento)
    private int matricula;
    private String nome;
    private double salarioBase;

    // Construtor
    public Colaborador(int matricula, String nome, double salarioBase) {
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    // Getters e setters
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    /**
     * Calcula o salário final. Na superclasse, é apenas o salário base.
     * As subclasses sobrescrevem este método quando há valores adicionais.
     */
    public double calcularSalarioFinal() {
        return salarioBase;
    }

    /**
     * Retorna o tipo do colaborador. Sobrescrito em cada subclasse.
     */
    public String getTipo() {
        return "Colaborador";
    }

    /**
     * Exibe os dados básicos. As subclasses chamam super.exibirDados()
     * e acrescentam suas informações específicas.
     */
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Base: " + formatarMoeda(salarioBase));
    }

    /**
     * Formata um valor no padrão brasileiro. Exemplo: R$ 2.000,00
     */
    public static String formatarMoeda(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "R$ %,.2f", valor);
    }
}
