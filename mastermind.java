
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;//TEMPORARIO PARA FIM DE TESTES (exibe a senha)
public class mastermind {
	public static void main(String[] args) {
		int[] senha = new int [4];
		int cont =1;
		preencher(senha);
		
		while(cont <= 3){
			String resposta = iniciar(senha);
			verificar(resposta, senha);
			comparar(resposta, senha);
			cont++;
		}
	}
	
	public static void preencher (int[] vetorsenha) {
		Random rand = new Random();
		for(int i = 0; i < vetorsenha.length; i++){
			vetorsenha[i] = rand.nextInt(6);
		}
		System.out.println(Arrays.toString(vetorsenha));//TEMPORARIO PARA FIM DE TESTES (exibe a senha)
	}

	public static String iniciar(int[] senha){
		Scanner kb = new Scanner(System.in);
		String resposta;
		System.out.println("Digite uma senha de até 4 dígitos e com números entre 1 e 6 (sem espaços)");
		resposta = kb.nextLine();
		kb.close();
		return resposta;
	}

	public static String verificar(String resposta, int[] senha){
		Scanner kb = new Scanner(System.in);
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
		kb.close();
		return resposta;
	}

	public static void comparar(String resposta, int[] senha){
		
	}
}