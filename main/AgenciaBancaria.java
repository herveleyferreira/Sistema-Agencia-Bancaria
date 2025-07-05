package main;

import java.util.ArrayList;
import java.util.Scanner;

import Banco.Cliente;
import Banco.ContaBancaria;
import utilitarios.Utils;

public class AgenciaBancaria {

    static Scanner input = new Scanner(System.in);
    static ArrayList<ContaBancaria> contasBancarias;

    public static void main(String[] args) {
        contasBancarias = new ArrayList<>();
        boolean executar = true;

        while (executar) {
            executar = operacoes();
        }

        input.close();
        System.out.println("Sistema encerrado.");
    }

    public static boolean operacoes() {
        System.out.println("------------------------------------------------------");
        System.out.println("-------------Bem-vindos à nossa Agência---------------");
        System.out.println("------------------------------------------------------");
        System.out.println("***** Selecione uma operação que deseja realizar *****");
        System.out.println("------------------------------------------------------");
        System.out.println("|   Opção 1 - Criar conta   |");
        System.out.println("|   Opção 2 - Depositar     |");
        System.out.println("|   Opção 3 - Sacar         |");
        System.out.println("|   Opção 4 - Transferir    |");
        System.out.println("|   Opção 5 - Listar        |");
        System.out.println("|   Opção 6 - Sair          |");

        System.out.print("\nDigite a operação desejada: ");

        int operacao = -1;
        try {
            operacao = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Por favor, digite um número entre 1 e 6.\n");
            return true;
        }

        switch (operacao) {
            case 1 -> criarConta();
            case 2 -> depositar();
            case 3 -> sacar();
            case 4 -> transferir();
            case 5 -> listarContas();
            case 6 -> {
                System.out.println("Operação finalizada. Obrigado por usar a nossa agência!");
                return false;
            }
            default -> System.out.println("Opção inválida! Tente novamente.");
        }

        return true;
    }

    public static void criarConta() {
        System.out.println("\n--- Criação de nova conta ---");
        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("CPF: ");
        String cpf = input.nextLine();

        System.out.print("Email: ");
        String email = input.nextLine();

        Cliente cliente = new Cliente(nome, cpf, email);
        ContaBancaria conta = new ContaBancaria(cliente);
        contasBancarias.add(conta);

        System.out.println("\nConta criada com sucesso!");
        System.out.println("Número da sua nova conta: " + conta.getNumeroConta());
        System.out.println("Data de criação: " + Utils.dateToString(conta.getDataCriacao()) + "\n");
    }

    private static ContaBancaria encontrarConta(int numeroConta) {
        for (ContaBancaria conta : contasBancarias) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public static void depositar() {
        if (contasBancarias.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada para depósito.");
            return;
        }

        listarContasResumidas();
        System.out.print("Digite o número da conta para depósito: ");
        int numeroConta = Integer.parseInt(input.nextLine());
        ContaBancaria conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.print("Valor do depósito: ");
            double valor = Double.parseDouble(input.nextLine());
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void sacar() {
        if (contasBancarias.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada para saque.");
            return;
        }

        listarContasResumidas();
        System.out.print("Digite o número da conta para saque: ");
        int numeroConta = Integer.parseInt(input.nextLine());
        ContaBancaria conta = encontrarConta(numeroConta);

        if (conta != null) {
            System.out.print("Valor do saque: ");
            double valor = Double.parseDouble(input.nextLine());
            conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public static void transferir() {
        if (contasBancarias.size() < 2) {
            System.out.println("Pelo menos duas contas são necessárias para realizar uma transferência.");
            return;
        }

        listarContasResumidas();
        System.out.print("Conta remetente (quem envia): ");
        int numeroContaRemetente = Integer.parseInt(input.nextLine());
        ContaBancaria contaRemetente = encontrarConta(numeroContaRemetente);

        if (contaRemetente != null) {
            System.out.print("Conta destinatária (quem recebe): ");
            int numeroContaDestinatario = Integer.parseInt(input.nextLine());
            ContaBancaria contaDestinatario = encontrarConta(numeroContaDestinatario);

            if (contaDestinatario != null) {
                System.out.print("Valor da transferência: ");
                double valor = Double.parseDouble(input.nextLine());
                contaRemetente.transferir(contaDestinatario, valor);
            } else {
                System.out.println("Conta destinatária não encontrada.");
            }
        } else {
            System.out.println("Conta remetente não encontrada.");
        }
    }

    public static void listarContas() {
        if (contasBancarias.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada no sistema.");
        } else {
            System.out.println("\nLista de contas cadastradas:");
            for (ContaBancaria conta : contasBancarias) {
                System.out.println(conta);
            }
        }
    }

    // Exibe nome + número das contas (ajuda o usuário a escolher uma conta)
    private static void listarContasResumidas() {
        System.out.println("\nContas disponíveis:");
        for (ContaBancaria conta : contasBancarias) {
            System.out.println("- Conta nº " + conta.getNumeroConta() + " | Titular: " + conta.getCliente().getNome());
        }
        System.out.println();
    }
}



