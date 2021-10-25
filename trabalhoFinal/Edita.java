package trabalhoFinal;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")

public class Edita {

	public static class Cliente {
		
		public char ativo;
		public int anoPrimeiraCompra;
		public float vlrCompra;
		public char emDia;
		
		public String codCliente;
		public String nomeCliente;
		
	}
	
	@SuppressWarnings("unused")
	
	public static void main(String[] args) {
		
		Cliente a = new Cliente();
		Scanner ler = new Scanner(System.in);
		RandomAccessFile arquivo;
		
		boolean encontrou;
		boolean validaChave;
		char confirmacao;
		long posisaoRegistro = 0;
		byte opcao;
		int verificaChave;
		
		String clienteChave;
		
		do {
			
			validaChave = true;
			encontrou = true;
			
			System.out.println(" **************** ALTERA��O DE DADOS DO CLIENTE *****************");
			System.out.println("Digite o C�digo do Cliente ( FIM para encerrar ) :");
			
			clienteChave = ler.nextLine();
			
			if (clienteChave.equalsIgnoreCase("FIM")) {
				
				break;
				
			} else {
				
				try {
					
					verificaChave = Integer.parseInt(clienteChave);
					
					validaChave = true;
					
					if (verificaChave < 0) {
						
						System.out.println("C�digo de Cliente n�o pode ser inv�lido !");
						
						validaChave = false;
						
					}
					
				} catch (NumberFormatException e) {
					
					System.out.println("C�digo Inv�lido! Apenas n�meros inteiros !");
					
					validaChave = false;
					
				}
				
			}
			
		} while (!validaChave);

		try {
			
			arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
			
			while (true) {
				
				a.ativo 			= arquivo.readChar();
				a.codCliente 		= arquivo.readUTF();
				a.nomeCliente 		= arquivo.readUTF();
				a.anoPrimeiraCompra = arquivo.readInt();
				a.vlrCompra 		= arquivo.readFloat();
				a.emDia 			= arquivo.readChar();
				
				if (clienteChave.equals(a.codCliente) && a.ativo == 'S') {
					
					encontrou = true;
					break;
				}
				
			}
			
			arquivo.close();
			
		} catch (EOFException e) {
			
			encontrou = false;
			System.out.println("Este Cliente n�o foi encontrado no arquivo !\n");
			
		} catch (IOException e) {
			
			System.out.println("Erro na abertura do arquivo - programa finalizado");
			System.exit(0);
			
		} 
	
		if (clienteChave.equals("FIM")) {
			
			System.out.println("\n ******************** PROGRAMA ENCERRADO ************* \n");
			
		}
		
		a.ativo = 'S';
		a.codCliente = clienteChave;
		
		do {
			
			System.out.println(" [1] Nome do Cliente...............: " + a.nomeCliente);
			System.out.println(" [2] Ano da Primeira Compra........: " + a.anoPrimeiraCompra);
			System.out.println(" [3] Valor da Compra...............: " + a.vlrCompra);
			
			System.out.println("\nDigite o numero do campo que deseja alterar (0 para finalizar) :");
			opcao = ler.nextByte();
			
		} while (opcao != 0 && opcao > 3 || opcao < 0);
		
		switch (opcao) {
		
			case 0:
				
				System.out.println("Saindo da Edi��o dos dados do Cliente!");
				break;
				
			case 1:
				
				do {
				
				ler.nextLine();
				System.out.println("\nDigite o NOVO NOME do Cliente.................: ");
				a.nomeCliente = ler.nextLine();
				
				if (a.nomeCliente.length() < 10) {
					
					System.out.println("Nome Inv�lido! M�nimo de 10 caracteres !");
					
					}
				
				break;
				
				} while (a.nomeCliente.length() < 10);
			
				System.out.println("Novo valor foi processado para a modifica��o. \n");
				
				break;
				
			case 2: 
				
				do {
					
					System.out.println("\nDigite o NOVO ano da primeira compra..............: ");
					a.anoPrimeiraCompra = ler.nextInt();
					
					if (a.anoPrimeiraCompra > 2013) {
						
						System.out.println("O ano da primeira compra deve ser menor que 2014!");
						
					}
					
					break;
					
				} while (a.anoPrimeiraCompra > 2013);
				
				break;
				
			case 3:
				
				do {
					
					ler.nextLine();
					System.out.println("Digite o NOVO VALOR da Compra..................:");
					a.vlrCompra = ler.nextFloat();
					
						if (a.vlrCompra < 0) {
							
							System.out.println("O valor da primeira compra n�o pode ser negativo !");
							
						}
					
					break;
					
					}while (a.vlrCompra < 0);
					
					System.out.println("Novo valor foi processado para a modifica��o.\n");
					
					break;
				
			}
		
		do {
			
			System.out.println("Confirma altera��es (S/N)? :");
			
			confirmacao = ler.next().charAt(0);
			
			if (confirmacao == 'S') {
				
				try {
					
					arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
					arquivo.seek(posisaoRegistro);
					arquivo.writeChar('N');
					arquivo.seek(arquivo.length());
					arquivo.writeChar(a.ativo);
					arquivo.writeUTF(a.codCliente);
					arquivo.writeUTF(a.nomeCliente);
					arquivo.writeInt(a.anoPrimeiraCompra);
					arquivo.writeFloat(a.vlrCompra);
					arquivo.writeChar(a.emDia);
					arquivo.close();
					
					System.out.println("Dados alterados com sucesso! \n");
					
				} catch (IOException e) {
					
					System.out.println("Erro na abertura do arquivo - programa finalizado");
					System.exit(0);
					
				}
				
			}
			
		} while (confirmacao != 'S' && confirmacao != 'N');
		
		ler.nextLine();
		
		ler.close();
		
	}

}
