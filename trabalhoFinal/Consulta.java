package trabalhoFinal;

import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class Consulta {

	public static class Cliente {
		
		public char 	ativo;
		public int		anoPrimeiraCompra;
		public float 	vlrCompra;
		public char 	emDia;
		
		public String	codCliente;
		public String 	nomeCliente;	
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Cliente a 			= new Cliente();
		Scanner ler 		= new Scanner(System.in);
		RandomAccessFile 	arquivo;
		
		int verificaChave;
		byte opcao;
		boolean validaChave;
		boolean encontrou;
		long posicaoRegistro;
		
		String clienteChave;
		
		do {
			
			System.out.println("*************** CONSULTA DE CLIENTES ***************");
			System.out.println(" [0] SAIR");
			System.out.println(" [1] CONSULTAR APENAS 1 CLIENTE ");
			System.out.println(" [2] LISTA DE TODOS OS CLIENTES DA EMPRESA ");
			
			do {
				
				System.out.println("\nDigite a opcao desejada: ");
				opcao = ler.nextByte();
				
				if (opcao < 0 || opcao > 2) {
					
					System.out.println("Opção Inválida, digite novamente: \n");
					
				}
				
			} while (opcao < 0 || opcao > 2);
			
			switch (opcao) {
			
				case 0: 
					
					System.out.println("\n*************** PROGRAMA ENCERRADO ***************");
					break;
					
				case 1:
					
					ler.nextLine();
					
					encontrou = true;
					
					do {
					
					validaChave = true;	
						
					System.out.println("Digite o codigo do cliente: ");
					
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
							
							System.out.println("Codigo Inválido ! Apenas números inteiros !");
							
							validaChave = false;
							
						}
					}
					
					encontrou = false;
					
					} while (!validaChave);
					
					try {
						
						arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
						
						while (true) {
							
							posicaoRegistro		= arquivo.getFilePointer();
							a.ativo 			= arquivo.readChar();
							a.codCliente 		= arquivo.readUTF();
							a.nomeCliente 		= arquivo.readUTF();
							a.anoPrimeiraCompra = arquivo.readInt();
							a.vlrCompra 		= arquivo.readFloat();
							a.emDia 			= arquivo.readChar();
							
						if (clienteChave.equals(a.codCliente) && a.ativo == 'S') {
							
							encontrou = true;
							imprimirCabecalho();
							imprimirCliente(a);
							
							System.out.println("\n              FIM DE RELATORIO \n");
							ler.nextLine();
							break;
							
							}
						
						} 
			
						arquivo.close();
						
					} catch (EOFException e) {
						
						encontrou = false;
						System.out.println("Este Código do Cliente não foi encontrado no arquivo !\n");
						
					} catch (IOException e) {
						
						System.out.println("Erro na abertura do arquivo - programa será finalizado");
						System.exit(0);
						
					}
					
					break;
					
				case 2:
					
					try {
						
						arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
						
						imprimirCabecalho();
						
						while (true) {
							
							a.ativo 			= arquivo.readChar();
							a.codCliente 		= arquivo.readUTF();
							a.nomeCliente 		= arquivo.readUTF();
							a.anoPrimeiraCompra = arquivo.readInt();
							a.vlrCompra 		= arquivo.readFloat();
							a.emDia 			= arquivo.readChar();
							
							if (a.ativo == 'S') {
								
								imprimirCliente(a);
							}
							
						}
						
					} catch (EOFException e) {
						
						System.out.println("\n FIM DE RELATORIO - ENTER para continuar.. \n");
						ler.nextLine();
						
					} catch (IOException e) {
						
						System.out.println("Erro na abertura do arquivo - programa será finalizado");
						System.exit(0);
						
					}
					
					break;
			
		} 

	} while (opcao != 0);
		
		ler.close();

}

	private static void imprimirCabecalho() {
		
		System.out.println("-CodCliente- --Nome do Cliente--             --Ano 1ª Compra--  --Valor da Compra--  --Em Dia--");
		
	}
	

	
	private static void imprimirCliente(Cliente a) {
		
		System.out.println(formatarString						(a.codCliente, 13) + " " +
							formatarString						(a.nomeCliente, 31) + " " +
							formatarString(String.valueOf		(a.anoPrimeiraCompra), 19) + " " +
							formatarString(String.valueOf		(a.vlrCompra), 21) + " " +
							formatarString(Character.toString	(a.emDia), 6));
		
	}
	
	public static String formatarString (String texto, int tamanho) {
		
		if (texto.length() > tamanho) {
			
			texto = texto.substring(0, tamanho);
		
		} else {
			
			while (texto.length() < tamanho) {
				
				texto = texto + " ";
				
			}
			
		}
		
		return texto;
	}

}