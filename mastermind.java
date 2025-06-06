
import java.util.Random;
import java.util.Scanner;

public class mastermind {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		char restart;

		do {
			int[] senha = new int[4];
			int cont = 10;
			preencher(senha);
			boolean validar = false;
			
			while (cont > 0 && validar == false) {
				String resposta = iniciar(senha, kb);
				resposta = verificar(resposta, senha, kb);
				int corretos = comparar(resposta, senha);
				if (corretos == 4) {
					System.out.println("Parabéns!!! Você acertou a senha e completou o jogo!");
					validar = true;
					System.out.print("A senha era: ");
					for(int i= 0; i<senha.length; i++) {
						System.out.print(senha[i]);
					}
					System.out.println();
				}else {
					cont--;
					System.out.println("\nSENHA INCORRETA!");
					System.out.println("Tentativas restantes: " + cont);
				}
			}
		
			System.out.println("Você atingiu o número máximo de tentativas!\n");
			System.out.print("A senha era: ");
			for(int i= 0; i<senha.length; i++) {
				System.out.print(senha[i]);
			}
		
			System.out.println("\nGostaria de realizar outra tentativa? (S-Sim/N-Não)");
			restart = kb.next().toUpperCase().charAt(0);
			while (restart != 'S' && restart !='N') {
				System.out.println("ERRO! Digite 'S' para 'Sim' ou 'N' para Não:");
				restart = kb.next().toUpperCase().charAt(0);
			}

		}while(restart == 'S');

		System.out.println("\nObrigado por participar!");
		kb.close();
	}

	public static void preencher(int[] vetorsenha) {
		Random rand = new Random();
		for (int i = 0; i < vetorsenha.length; i++) {
			vetorsenha[i] = rand.nextInt(6) + 1;
		}
	}

	public static String iniciar(int[] senha, Scanner kb) {
		System.out.println("\nDigite uma senha de até 4 dígitos e com números entre 1 e 6 (sem espaços)");
		return kb.nextLine();
	}

	public static String verificar(String resposta, int[] senha, Scanner kb) {
		while(resposta.length() != senha.length){
			System.out.println("Quantidade de dígitos incorreta, tente novamente.");
			resposta = kb.nextLine();
		}

		for (int i = 0; i < resposta.length(); i++) {
			char c = resposta.charAt(i);
			if (c < '1' || c > '6') {
				System.out.println("Todos os dígitos devem estar entre 1 e 6. Tente novamente:");
				resposta = kb.nextLine();
			}
		}
		return resposta;
	}

	public static int comparar(String resposta, int[] senha) {
		int corretos = 0;
		int deslocados = 0;
		int[] digitosenha = new int[7];
		int[] digitoresposta = new int[7];

		for (int i = 0; i < resposta.length(); i++) {
			int digit = resposta.charAt(i) - '0';
			if (digit == senha[i]) {
				corretos++;
			}
			digitosenha[senha[i]]++;
			digitoresposta[digit]++;
		}

		int comum = 0;
		for (int i = 1; i <= 6; i++) {
			int min;
			if (digitosenha[i] < digitoresposta[i]) {
				min = digitosenha[i];
			} else {
				min = digitoresposta[i];
			}
			comum += min;
		}
		deslocados = comum - corretos;

		System.out.println("Dígitos corretos fora de posição: " + deslocados);
		System.out.println("Dígitos na posição correta: " + corretos);
		return corretos;
	}
}