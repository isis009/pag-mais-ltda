/**
 * Colaborador de produção: salário base + bônus por produtividade.
 */
public class ColaboradorProducao extends Colaborador {

    private int quantidadePecas;
    private double valorPorPeca;

    public ColaboradorProducao(int matricula, String nome, double salarioBase,
                               int quantidadePecas, double valorPorPeca) {
        super(matricula, nome, salarioBase);
        this.quantidadePecas = quantidadePecas;
        this.valorPorPeca = valorPorPeca;
    }

    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public void setQuantidadePecas(int quantidadePecas) {
        this.quantidadePecas = quantidadePecas;
    }

    public double getValorPorPeca() {
        return valorPorPeca;
    }

    public void setValorPorPeca(double valorPorPeca) {
        this.valorPorPeca = valorPorPeca;
    }

    /**
     * Produtividade = quantidadePecas x valorPorPeca
     */
    public double calcularProdutividade() {
        return quantidadePecas * valorPorPeca;
    }

    /**
     * Salário final = salário base + produtividade
     */
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }

    @Override
    public String getTipo() {
        return "Produção";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Produtividade: " + formatarMoeda(calcularProdutividade()));
    }
}
