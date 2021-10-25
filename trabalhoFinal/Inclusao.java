package trabalhoFinal;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Inclusao {

	public static class Cliente {
		
		public char 	ativo;
		public String	codCliente;
		public int	anoPrimeiraCompra;
		public float 	vlrCompra;
		public char 	emDia;
		
		public String 	nomeCliente;
		
	}
	
	public static void main(String[] args) {
		
		Cliente a 		= new Cliente();
		RandomAccessFile	arquivo;
		Scanner ler		= new Scanner(System.in);
		
		String 		clienteChave;
		int 		verificaChave;
		boolean		encontrou;
		boolean 	validaChave;
		char 		confirmacao;
		
		do {
			
			do {
				
				encontrou = true;
				
				do {
				
				validaChave = true;
								
				System.out.println("********************* INCLUSAO DE CLIENTES ***********************");
				System.out.println("Digite o codigo do cliente (FIM para encerrar): ");
				
				clienteChave = ler.nextLine();
				
				if (!clienteChave.equals("FIM")) {
					
					try {
					
						verificaChave = Integer.parseInt(clienteChave);
						
						validaChave = true;
						
						if (verificaChave < 0) {
						
							System.out.println("Código de Cliente não pode ser negativo");
							
							validaChave = false;
							
						}
					
					} catch (NumberFormatException e) {
						
						System.out.println("Código Inválido ! Apenas números inteiros !");
						
						validaChave = false;
						
					}
				}
				
				if (clienteChave.equalsIgnoreCase("FIM")) {
					
					validaChave = true;
					break;
					
				}
				
				encontrou = false;
				
				} while (!validaChave);
				
				try {
					
					arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
					
					while (true) {
						
						a.ativo 			= arquivo.readChar();
						a.codCliente 			= arquivo.readUTF();
						a.nomeCliente 			= arquivo.readUTF();
						a.anoPrimeiraCompra 		= arquivo.readInt();
						a.vlrCompra 			= arquivo.readFloat();
						a.emDia 			= arquivo.readChar();
						
						if (clienteChave.equals(a.codCliente) && a.ativo == 'S') {
							
							System.out.println("Código de Cliente ja cadastrado. Digite novamente \n");
							encontrou = true;
							break;
							
						}
						
					}
					
					arquivo.close();
					
				} catch (EOFException e) {
					
					encontrou = false;
			
				} catch (IOException e) {
					
					System.out.println("Erro na abertura do arquivo - programa finalizado");
					System.exit(0);
					
				}
				
			} while (encontrou);
			
			if (clienteChave.equalsIgnoreCase("FIM")) {
				
				System.out.println("\n ************** PROGRAMA ENCERRADO ***************");
				break;
				
			}
			
			a.ativo = 'S';
			a.codCliente = clienteChave;
			
			do {
			
				System.out.println("Digite o nome do Cliente.....................: ");
				a.nomeCliente = ler.nextLine();
				
				if (a.nomeCliente.length() < 10) {
					
					System.out.println("Nome Inválido! Mínimo de 10 caracteres !");
				}
			
			}while (a.nomeCliente.length() < 10);
		
		do {
			
			System.out.println("Digite o ano da primeira compra..............: ");
			a.anoPrimeiraCompra = ler.nextInt();
			
			if (a.anoPrimeiraCompra > 2013) {
				
				System.out.println("O ano da primeira compra deve ser menor que 2014!");
				System.out.println("Digite novamente !\n");
				
			}
			
		} while (a.anoPrimeiraCompra > 2013);
		
		do {
			
			System.out.println("Digite o valor da compra.....................: ");
			a.vlrCompra = ler.nextFloat();
			
			if (a.vlrCompra < 0) {
				
				System.out.println("O valor da primeira compra não pode ser negativo !");
				System.out.println("Digite novamente! \n");
				
			}
			
		} while (a.vlrCompra < 0);
			
			System.out.println("Cliente em dia com pagamentos (S/N)..........:" );
			a.emDia = ler.next().charAt(0);
			
			do {
				
				System.out.println("\nConfirma a gravação dos dados (S/N) ? ");
				confirmacao = ler.next().charAt(0);
				
				if (confirmacao == 'S') {
					
					try {
						
						arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
						arquivo.seek(arquivo.length());
						arquivo.writeChar(a.ativo);
						arquivo.writeUTF(a.codCliente);
						arquivo.writeUTF(a.nomeCliente);
						arquivo.writeInt(a.anoPrimeiraCompra);
						arquivo.writeFloat(a.vlrCompra);
						arquivo.writeChar(a.emDia);
						arquivo.close();
						
						System.out.println("Dados gravados com sucesso! \n");
						
					} catch (IOException e) {
						
						System.out.println("Erro na gravaçao do registro - programa será finalizado");
						System.exit(0);
						
					}
					
				}
				
			} while (confirmacao != 'S' && confirmacao != 'N');
			
			ler.nextLine();
			
		} while (!a.codCliente.equalsIgnoreCase("FIM"));
		
		ler.close();
		
	}

}
