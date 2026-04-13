import java.util.List;
import java.util.Map;

import model.Funcionario;
import service.FuncionarioService;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios =
                FuncionarioService.criarFuncionarios();

        FuncionarioService.removerJoao(funcionarios);

        FuncionarioService.imprimirFuncionarios(funcionarios);

        FuncionarioService.aplicarAumento(funcionarios);
        
        System.out.println("Com Aumento");
        FuncionarioService.imprimirFuncionarios(funcionarios);
        
        

        Map<String, List<Funcionario>> mapa =
                FuncionarioService.agruparPorFuncao(funcionarios);

        FuncionarioService.imprimirAgrupadosPorFuncao(mapa);
        FuncionarioService.imprimirAniversariantes(funcionarios);
        FuncionarioService.imprimirMaisVelho(funcionarios);
        FuncionarioService.ordemAlfabetica(funcionarios);
        FuncionarioService.imprimirTotalSalarios(funcionarios);
        FuncionarioService.imprimirSalariosMinimos(funcionarios);
    }
}