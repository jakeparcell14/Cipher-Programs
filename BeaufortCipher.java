import java.util.*;

public class BeaufortCipher 
{

	public static void main(String[] args) 
	{
		boolean exit = false;

		char[] alphabet = getAlphabet();
		
		while(!exit)
		{
			displayMenu();

			int choice = retrieveMenuChoice(1, 3);

			switch(choice)
			{
				case 1:
					encryptPlaintext(alphabet);
					break;
					
				case 2:
					decryptCiphertext(alphabet);
					break;
					
				case 3:
					exit = true;
			}
		}
	}

	/**
	 * displays menu options
	 */
	public static void displayMenu()
	{
		System.out.println("1. Encode Plaintext");
		System.out.println("2. Decode Ciphertext");
		System.out.println("3. Exit");
	}

	/**
	 * prompts user for a menu choice and tests input for validity
	 * @param lowerBound lowest possible valid user input
	 * @param upperBound highest possible valid user input
	 * @return valid user input
	 */
	public static int retrieveMenuChoice(int lowerBound, int upperBound)
	{
		Scanner in = new Scanner(System.in);

		boolean valid = false;

		while(!valid)
		{
			System.out.print("Enter Menu Choice: ");

			if(in.hasNextInt())
			{
				int choice = in.nextInt();

				if(choice >= lowerBound && choice <= upperBound)
				{
					return choice;
				}
				else
				{					
					System.out.println("Invalid Entry");
				}
			}
			else
			{
				//clear buffer
				in.next();

				System.out.println("Invalid Entry");
			}
		}

		return -1;
	}
	
	/**
	 * creates char array with each letter of the alphabet in order
	 * @return array of each letter of the alphabet
	 */
	public static char[] getAlphabet()
	{		
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		
		return alphabet;
	}

	/**
	 * uses a key to encrypt user-generated plaintext
	 */
	public static void encryptPlaintext(char[] alphabet)
	{
		Scanner in = new Scanner(System.in);
								
		System.out.print("Enter plaintext message to be encrypted (Press enter when finished): ");
		
		if(in.hasNextLine())
		{
			String plaintext = in.nextLine();
			
			String key = createKey(plaintext, alphabet);
			
			String letters = String.valueOf(alphabet);
			
			String cipherText = "";
			
			System.out.println("Key: " + key);	
			
			for(int i = 0; i < plaintext.length(); i++)
			{
				int plaintextIndex = letters.indexOf(plaintext.substring(i, i + 1));

				int keyIndex = letters.indexOf(key.substring(i, i + 1));
				
				if(keyIndex >= 0 && plaintextIndex >= 0)
				{
					int cipherIndex = (keyIndex - plaintextIndex + 52) % 26;
					
					cipherText = cipherText + alphabet[cipherIndex];
				}
				else
				{
					cipherText = cipherText + plaintext.substring(i, i + 1);
				}
			}
			
			System.out.println("Ciphertext: " + cipherText);
			System.out.println();
		}
	}
	
	/**
	 * creates key with random letters
	 * @param  plaintext given text
	 * @param  alphabet  array of letters of the alphabet
	 * @return string of random letters that represent the plaintext
	 */
	public static String createKey(String plaintext, char[] alphabet)
	{
		String key = new String();
				
		for(int i = 0; i < plaintext.length(); i++)
		{
			//assigns random character to each letter in the plaintext
			if(!plaintext.substring(i, i + 1).equals(" "))
			{
				int index = (int) (Math.random() * 26);
				
				key = key + alphabet[index];
			}
			else
			{
				key = key + " ";
			}
		}
		
		return key;
	}
	
	/**
	 * decrypts ciphertext and displays it to the console
	 * @param alphabet array of letters of the alphabet
	 */
	public static void decryptCiphertext(char[] alphabet)
	{
		
		Scanner in = new Scanner(System.in);
		
		String ciphertext = "";
		
		System.out.print("Enter Ciphertext: ");
		
		if(in.hasNextLine())
		{
			ciphertext = in.nextLine();
		}
		
		String key = "";
		
		System.out.print("Enter Key: ");
		
		if(in.hasNextLine())
		{
			key = in.nextLine();
		}
		
		String letters = String.valueOf(alphabet);
		
		String plaintext = "";
		
		for(int i = 0; i < ciphertext.length(); i++)
		{
			int ciphertextIndex = letters.indexOf(ciphertext.substring(i, i + 1));

			int keyIndex = letters.indexOf(key.substring(i, i + 1));
			
			if(keyIndex >= 0 && ciphertextIndex >= 0)
			{
				int cipherIndex = (keyIndex - ciphertextIndex + 52) % 26;
				
				plaintext = plaintext + alphabet[cipherIndex];
			}
			else
			{
				plaintext = plaintext + ciphertext.substring(i, i + 1);
			}
		}
		
		System.out.println("Plaintext: " + plaintext);
		System.out.println();
	}
}
