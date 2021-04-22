/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Aplicação console do Quebra-Cabeca
 * 
 */

import java.util.Scanner;

public class QuebraCabecaConsole {
	private static Scanner teclado;

	public static void main(String[] args) throws Exception {
		
		teclado = new Scanner(System.in);
		int dimensao;
		System.out.print("Digite a dimensao:");
		dimensao = teclado.nextInt();
		QuebraCabeca jogo = new QuebraCabeca(dimensao,null);
		jogo.iniciar();
		System.out.println(jogo);
		
		//teclado = new Scanner(System.in);
		int linhaorigem, colunaorigem, linhadestino, colunadestino;
		while(!jogo.finalizado()) {
			System.out.print("Digite a linha (origem):");
			linhaorigem = teclado.nextInt();
			System.out.print("Digite a coluna (origem): ");
			colunaorigem = teclado.nextInt();
			System.out.print("Digite a linha (destino):");
			linhadestino = teclado.nextInt();
			System.out.print("Digite a coluna (destino): ");
			colunadestino = teclado.nextInt();
			
			jogo.jogar(linhaorigem,colunaorigem,linhadestino,colunadestino);
			System.out.println(jogo);

		}
		System.out.println("\n GAME OVER !!");
	}
}