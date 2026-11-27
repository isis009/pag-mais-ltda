/**
 * Colaborador comissionado: salário base + comissão sobre as vendas.
 */
public class ColaboradorComissionado extends Colaborador {

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado(int matricula, String nome, double salarioBase,
                                   double valorVendas, double percentualComissao) {
        super(matricula, nome, salarioBase);
        this.valorVendas = valorVendas;
        this.percentualComissao = percentualComissao;
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(double valorVendas) {
        this.valorVendas = valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    /**
     * Comissão = valorVendas x percentualComissao / 100
     */
    public double calcularComissao() {
        return valorVendas * percentualComissao / 100;
    }

    /**
     * Salário final = salário base + comissão
     */
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularComissao();
    }

    @Override
    public String getTipo() {
        return "Comissionado";
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Comissão: " + formatarMoeda(calcularComissao()));
    }
}
