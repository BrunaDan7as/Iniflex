package service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import model.Funcionario;

public class FuncionarioService {

    public static List<Funcionario> criarFuncionarios() {
    	// Parte 3.1 onde tem a inserção dos funcionários na ordem certa.
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        return funcionarios;
    }
    // Parte 3.2 Onde é a remoção do funcionário João
    public static void removerJoao(List<Funcionario> funcionarios) {
        funcionarios.removeIf(funcionario ->
                funcionario.getNome().equals("João"));
    }
    // 3.3 Para imprimir a lista dos funcionários
    public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatoData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        NumberFormat formatoSalario =
                NumberFormat.getNumberInstance(
                        Locale.forLanguageTag("pt-BR"));

        formatoSalario.setMinimumFractionDigits(2);

        System.out.println("-------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-20s%n",
                "Nome", "Nascimento", "Salário", "Função");
        System.out.println("-------------------------------------------------------------------------------");

        for (Funcionario funcionario : funcionarios) {
            System.out.printf("%-15s %-15s %-15s %-20s%n",
                    funcionario.getNome(),
                    funcionario.getDataNascimento().format(formatoData),
                    formatoSalario.format(funcionario.getSalario()),
                    funcionario.getFuncao());
        }

        System.out.println("-------------------------------------------------------------------------------");
    }
    // 3.4 Onde aplica o calculo de 10% e manter
    public static void aplicarAumento(List<Funcionario> funcionarios) {
        BigDecimal aumento = new BigDecimal("1.10");

        for (Funcionario funcionario : funcionarios) {
            funcionario.setSalario(
                    funcionario.getSalario()
                            .multiply(aumento)
                            .setScale(2, RoundingMode.HALF_UP)
            );
        }
    }
    // 3.5 Agrupar por função no map comm as chaves
    public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getFuncao
                ));
    }
    // 3.6 Mostrar o que foi agrupado por função
    public static void imprimirAgrupadosPorFuncao(Map<String, List<Funcionario>> mapa) {
        for (String funcao : mapa.keySet()) {
            System.out.println("\nFunção: " + funcao);
            System.out.println("--------------------------------");

            for (Funcionario funcionario : mapa.get(funcao)) {
                System.out.println(funcionario.getNome());
            }
        }
    }
    // 3.8 Aniversariantes dos meses 10 e 12
    public static void imprimirAniversariantes(List<Funcionario> funcionarios) {
        System.out.println("\nAniversariantes (Meses 10 e 12)");
        System.out.println("--------------------------------");

        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento()
                    .getMonthValue();

            if (mes == 10 || mes == 12) {
                System.out.println(funcionario.getNome());
            }
        }
    }
    // 3.9 Funcionário mais velho
    public static void imprimirMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = funcionarios.get(0);

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento()
                    .isBefore(maisVelho.getDataNascimento())) {
                maisVelho = funcionario;
            }
        }

        int idade = Period.between(
                maisVelho.getDataNascimento(),
                LocalDate.now()
        ).getYears();

        System.out.println("\nFuncionário Mais Velho");
        System.out.println("--------------------------------");
        System.out.println("Nome: " + maisVelho.getNome());
        System.out.println("Idade: " + idade);
    }
    // 3.10 Ordem alfabética
    public static void ordemAlfabetica(List<Funcionario> funcionarios) {
        funcionarios.sort((f1, f2) ->
                f1.getNome().compareTo(f2.getNome()));

        System.out.println("\nFuncionários em Ordem Alfabética");
        System.out.println("--------------------------------");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }
    }
    // 3.11 Total salários
    public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = BigDecimal.ZERO;

        for (Funcionario funcionario : funcionarios) {
            total = total.add(funcionario.getSalario());
        }

        NumberFormat formatoSalario =
                NumberFormat.getNumberInstance(
                        Locale.forLanguageTag("pt-BR"));

        formatoSalario.setMinimumFractionDigits(2);

        System.out.println("\nTotal dos Salários");
        System.out.println("--------------------------------");
        System.out.println(formatoSalario.format(total));
    }
    // 3.12 Salários mínimos
    public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nQuantidade de Salários Mínimos");
        System.out.println("--------------------------------");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal quantidade = funcionario.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.println(
                    funcionario.getNome() + ": " +
                            quantidade
            );
        }
    }
}