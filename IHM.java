package bouteille;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class IHM {
	Scanner sc= new Scanner(System.in);
	Partie maPartie;
	public IHM() {
		maPartie= new Partie();
		maPartie.jouerUnePartie();
	}
	public void utilitaire() {
		File resultat = new File("C:\\Users\\kevin\\OneDrive\\Bureau\\resultat\\resultat.txt");		// on initialise la variable qui designe le fichier texte
		String reponse="";																		// on initialise la variable réponse 
		while(!reponse.equals("stop")) {														// tant que le contenu de la variable réponse est différent de stop 
			System.out.println("Taper oui si vous voulez remettre les scores à 0");				// on dit de taper oui si on veut remettre les scores à 0 
			System.out.println("");
			System.out.println("stop pour sortir");												// on dit de taper stop si on veut arreter 
			reponse = sc.nextLine();															// on demande un input de la part de l'utilisateur
			if (reponse.equals("oui")) {														// si la réponse est oui 
				try {
					FileWriter writer = new FileWriter(resultat);	// on creer une variable pour écrire dans le fichier associer à la variable résulat
					BufferedWriter bw = new BufferedWriter(writer);
					bw.write("0");			// on écrit sur la première ligne du fichier 0 
					bw.newLine();					// on va à une nouvelle ligne 
					bw.write("0");			// on écrit sur la deuxième ligne du fichier 0  
					bw.close();				
					writer.close();					// on ferme les fonctions pour écrire
				}catch (IOException e) {			// si il y a une erreur on l'attrape et on l'affiche dans le console 
					e.printStackTrace();
				}
			
			}
		}
		System.out.println("A bientot.");		// dans le cas ou le texte rentré est stop on arrète 
		sc.close();								// on ferme le scanner 
		
}
}
