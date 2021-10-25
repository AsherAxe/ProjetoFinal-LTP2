package trabalhoFinal;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class Exclusao {

	public static class Cliente{
		
		public char 	ativo;
		public int	anoPrimeiraCompra;
		public float 	vlrCompra;
		public char 	emDia;
		
		public String	codCliente;
		public String 	nomeCliente;
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Cliente a 		= new Cliente();
		Scanner ler 		= new Scanner(System.in);
		RandomAccessFile 	arquivo;

		char confirmacao;
		long posicaoRegistro = 0;
		boolean encontrou;
		int verificaChave;
		
		String clienteChave;
		
		do {
			
			do {
				
				System.out.println("********************* EXCLUSAO DE CLIENTES ***********************");
				System.out.println("Digite o codigo do cliente para exclusao (FIM para encerrar): ");
				
				clienteChave = ler.nextLine();
				
				if (!clienteChave.equals("FIM")) {
					
					try {
					
						verificaChave = Integer.parseInt(clienteChave);
					
						if (verificaChave < 0) {
						
							System.out.println("Código de Cliente não pode ser negativo");
						
						}
					
					} catch (NumberFormatException e) {
						
						System.out.println("Codigo Inválido ! Apenas números inteiros !");
						
					}
				}
				
				if (clienteChave.equals("FIM")) {
					
					break;
					
				}
				
				encontrou = false;
				
				try {
					
					arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
					
					while (true) {
						
						posicaoRegistro = arquivo.getFilePointer()
;						
						a.ativo 			= arquivo.readChar();
						a.codCliente 			= arquivo.readUTF();
						a.nomeCliente 			= arquivo.readUTF();
						a.anoPrimeiraCompra 		= arquivo.readInt();
						a.vlrCompra 			= arquivo.readFloat();
						a.emDia 			= arquivo.readChar();
						
						if (clienteChave.equals(a.codCliente) && a.ativo == 'S') {
							
							System.out.println("Código de Cliente ja cadastrado, digite outro valor\n");
							encontrou = true;
							break;
							
						}
					}
					
					arquivo.close();
					
				} catch (EOFException e) {
					
					encontrou = false;
					System.out.println("Este Codigo de Cliente não foi encontrado no arquivo !\n");
			
				} catch (IOException e) {
					
					System.out.println("Erro na abertura do arquivo - programa finalizado");
					System.exit(0);
					
				}
				
			} while (!encontrou);
			
			if (clienteChave.equals("FIM")) {
				
				System.out.println("\n ********************* PROGRAMA ENCERRADO ********************* \n");
				break;
				
			}
			
			a.ativo = 'N';
			
			System.out.println("Codigo do Cliente........................: " + a.codCliente);
			System.out.println("Nome do Cliente..........................: " + a.nomeCliente);
			System.out.println("Ano da Primeira Compra...................: " + a.anoPrimeiraCompra);
			System.out.println("Valor da Compra..........................: " + a.vlrCompra);
			System.out.println("Em dia com os pagamentos.................: " + a.emDia);
			System.out.println();
			
			do {
				
				System.out.println("\nConfirma a exclusão deste Cliente (S/N) ? ");
				confirmacao = ler.next().charAt(0);
				
				if (confirmacao == 'S') {
					
					try {
						
						arquivo = new RandomAccessFile("CLIENTES.DAT", "rw");
						arquivo.seek(posicaoRegistro);
						arquivo.writeChar(a.ativo);
						arquivo.close();
						
						System.out.println("Cliente excluido com sucesso ! \n");
						
					} catch (IOException e) {
						
						System.out.println("Erro na gravaçao do registro - programa será finalizado");
						System.exit(0);
						
					}
				}
				
			} while (confirmacao != 'S' && confirmacao != 'N');
			
			ler.nextLine();
			
		} while (!a.codCliente.equals("FIM"));

	}

}
