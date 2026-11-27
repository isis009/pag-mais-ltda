/**
 * Colaborador padrão: recebe apenas o salário base.
 */
public class ColaboradorPadrao extends Colaborador {

    public ColaboradorPadrao(int matricula, String nome, double salarioBase) {
        super(matricula, nome, salarioBase);
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase();
    }

    @Override
    public String getTipo() {
        return "Padrão";
    }
}