import java.util.Random;//Importa bilioteca Random para gerar números aleatórios
import java.util.Scanner;//Importa biblioteca Scanner para entrada de dados

public class mastermindComment {//Cria a classe mastermindComment
	public static void main(String[] args) {//Método principal que inicia o jogo
		Scanner kb = new Scanner(System.in);//Cria um objeto Scanner para ler a entrada do usuário
		char restart;//Variável para controlar se o usuário deseja reiniciar o jogo

		System.out.println("===== MASTERMIND =====");
		System.out.println("Você tem 10 tentativas para descobrir a senha.");
		System.out.println("A senha é composta por 4 dígitos, podendo ser números naturais de 1 a 6 (sem espaços) e os números podem aparecer mais de uma vez na senha.");
		System.out.println("A cada tentativa, você receberá um retorno sobre quantos dígitos estão corretos e quantos estão fora de posição.");

		do {//Inicia o loop do jogo, permitindo que o usuário jogue várias vezes

			int[] senha = new int[4];//Cria um vetor de inteiros para armazenar a senha, com 4 posições

			int cont = 10;//Contador de tentativas, inicia em 10, e vai diminuir até zero, conforme o usário errar

			preencher(senha);//Chama o método preencher para gerar a senha aleatória

			boolean validar = false;//Variável booleana para verificar se a senha foi acertada
			
			while (cont > 0 && validar == false) {//Enquanto o usuário tiver tentativas e não acertar a senha

				String resposta = iniciar(senha, kb);//Chama o método iniciar para receber a tentativa do usuário

				resposta = verificar(resposta, senha, kb);//Chama o método verificar para validar a resposta do usuário

				int corretos = comparar(resposta, senha);//Chama o método comparar para verificar quantos dígitos estão corretos

				if (corretos == 4) {//Se o usuário acertar todos os dígitos

					System.out.println("\nParabéns!!! Você acertou a senha e completou o jogo!");

					validar = true;//Define validar como verdadeiro para sair do loop de tentativas

					for(int i= 0; i<senha.length; i++) {//Imprime a senha correta
						System.out.print(senha[i]);
					}
					System.out.println();
				}else {//Se o usuário não acertar todos os dígitos

					cont--;//Decrementa o contador de tentativas
					System.out.println("\nSENHA INCORRETA!");
					System.out.println("Tentativas restantes: " + cont);
				}
			}//Fim do loop de tentativas, se o usuário não acertar a senha em 10 chances
			System.out.println("FIM DE JOGO!");
			System.out.print("A senha era: ");

			for(int i= 0; i<senha.length; i++) {//Imprime a senha correta
				System.out.print(senha[i]);
			}
		
			System.out.println("\nGostaria de realizar outra tentativa? (S-Sim/N-Não)");
			restart = kb.next().toUpperCase().charAt(0);//Lê a resposta do usuário se deseja jogar novamente e a converte para maiúscula

			while (restart != 'S' && restart !='N') {//Enquanto a resposta não for válida
				System.out.println("ERRO! Digite 'S' para 'Sim' ou 'N' para Não:");
				restart = kb.next().toUpperCase().charAt(0);//Lê novamente a resposta, até que seja válida
			}

		}while(restart == 'S');//Enquanto o usuário desejar jogar novamente, executa o loop

		System.out.println("\nObrigado por participar!");
		kb.close();
	}

	public static void preencher(int[] vetorsenha) {//Método para preencher o vetor senha com números aleatórios de 1 a 6

		Random rand = new Random();//Cria um objeto Random para gerar números aleatórios

		for (int i = 0; i < vetorsenha.length; i++) {//Percorre o vetor senha
			vetorsenha[i] = rand.nextInt(6) + 1;//Gera um número aleatório entre 1 e 6 e atribui à posição i do vetor senha
		}
	}

	public static String iniciar(int[] senha, Scanner kb) {//Método para solicitar ao usuário que digite uma senha
		System.out.println("\nDigite uma senha:");
		return kb.nextLine();
	}

	public static String verificar(String resposta, int[] senha, Scanner kb) {//Método para verificar se a resposta do usuário está correta em relação ao tamanho e aos dígitos permitidos

		while(resposta.length() != senha.length){//Enquanto a resposta não tiver o tamanho correto
			System.out.println("Quantidade de dígitos incorreta, tente novamente.");

			resposta = kb.nextLine();//Solicita uma nova resposta ao usuário
		}

		for (int i = 0; i < resposta.length(); i++) {// Percorre cada dígito da resposta

			char c = resposta.charAt(i);//Obtém o dígito atual (localizado em i) como caractere

			if (c < '1' || c > '6') {//Se o dígito não estiver entre '1' e '6'

				System.out.println("Todos os dígitos devem estar entre 1 e 6. Tente novamente:");

				resposta = kb.nextLine();//Solicita uma nova resposta ao usuário
			}
		}
		return resposta;
	}

	public static int comparar(String resposta, int[] senha) {//Método para comparar a resposta do usuário com a senha gerada
		int corretos = 0;
		int deslocados = 0;

		int[] digitosenha = new int[7];//Cria um vetor para contar a ocorrência de cada dígito na senha, de 1 a 6

		int[] digitoresposta = new int[7];//Cria um vetor para contar a ocorrência de cada dígito na resposta do usuário, de 1 a 6

		for (int i = 0; i < resposta.length(); i++) {// Percorre cada dígito da resposta do usuário

			int digit = resposta.charAt(i) - '0';//Converte o caractere digitado para um inteiro (subtraindo '0' para obter o valor numérico)

			if (digit == senha[i]) {// Se o dígito da resposta for igual ao dígito correspondente na senha

				corretos++;//Incrementa o contador de dígitos corretos
			}

			digitosenha[senha[i]]++;//Incrementa o contador do dígito correspondente na senha
			digitoresposta[digit]++;//Incrementa o contador do dígito correspondente na resposta do usuário
		}

		int comum = 0;//Variável para contar os dígitos comuns entre a senha e a resposta do usuário

		for (int i = 1; i <= 6; i++) {//Percorre os dígitos de 1 a 6 para calcular os comuns

			int min;//Variável para armazenar o menor valor entre a senha e a resposta do usuário

			if (digitosenha[i] < digitoresposta[i]) {//Se a quantidade de dígitos na senha for menor que na resposta do usuário

				min = digitosenha[i];//Atribui o valor da senha ao mínimo
			} else {
				min = digitoresposta[i];//Caso contrário, atribui o valor da resposta do usuário ao mínimo
			}
			comum += min;//Incrementa o total de dígitos comuns
		}
		deslocados = comum - corretos;//Calcula os dígitos fora de posição subtraindo os corretos do total comum
                
		System.out.println("\nDígitos corretos fora de posição: " + deslocados);
		System.out.println("Dígitos na posição correta: " + corretos);
		return corretos;
	}
}