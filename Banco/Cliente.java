package Banco;

public class Cliente {
	
	private static int contador = 0;
	
	private int id;
	private String nome;
	private String cpf;
	private String email;
	
	public Cliente(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.id = ++contador;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
	    return cpf;
	}
	public void setCpf(String cpf) {
	    this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String toString() {
		return  "\nID: " + this.getId() +
				"\nNome: " + this.getNome() +
				"\nCPF: " + this.getCpf() +
				"\nE-mail: " + this.getEmail();	
	}

}
