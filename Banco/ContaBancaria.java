package Banco;

import java.util.Date;
import utilitarios.Utils;

public class ContaBancaria {

    private static int contadorContas = 1;

    private int numeroConta;
    private Cliente cliente;
    private Double saldo = 0.0;

    private Date dataCriacao; // Armazena a data da criação da conta

    // Construtor: recebe o cliente e define número da conta e data atual
    public ContaBancaria(Cliente cliente) {
        this.numeroConta = contadorContas;
        this.cliente = cliente;
        this.dataCriacao = new Date(); // Pega a data e hora do momento da criação
        contadorContas++;
    }

    // Getter da data de criação (necessário para exibir no main)
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Getters e setters
    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    // oString com formatação do saldo e da data de criação usando Utils
    @Override
    public String toString() {
        return "\nNúmero da Conta: " + this.getNumeroConta() +
               "\nID do Cliente: " + this.cliente.getId() +
               "\nNome: " + this.cliente.getNome() +
               "\nCPF: " + this.cliente.getCpf() +
               "\nE-mail: " + this.cliente.getEmail() +
               "\nData de Criação: " + Utils.dateToString(this.getDataCriacao()) +
               "\nSaldo: " + Utils.doubleToString(this.getSaldo()) +
               "\n";
    }

    // Métodos de operação bancária
    public void depositar(Double valor) {
        if (valor > 0) {
            setSaldo(getSaldo() + valor);
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito!");
        }
    }

    public void sacar(Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            setSaldo(getSaldo() - valor);
            System.out.println("Saque realizado com sucesso!");
        } else {
            System.out.println("Não foi possível realizar o saque.");
        }
    }

    public void transferir(ContaBancaria contaParaDeposito, Double valor) {
        if (valor > 0 && this.getSaldo() >= valor) {
            this.saldo -= valor;
            contaParaDeposito.depositar(valor); 
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Transferência não realizada: valor inválido ou saldo insuficiente.");
        }
    }
}

