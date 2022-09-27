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
		String reponse="";																		// on initialise la variable r�ponse 
		while(!reponse.equals("stop")) {														// tant que le contenu de la variable r�ponse est diff�rent de stop 
			System.out.println("Taper oui si vous voulez remettre les scores � 0");				// on dit de taper oui si on veut remettre les scores � 0 
			System.out.println("");
			System.out.println("stop pour sortir");												// on dit de taper stop si on veut arreter 
			reponse = sc.nextLine();															// on demande un input de la part de l'utilisateur
			if (reponse.equals("oui")) {														// si la r�ponse est oui 
				try {
					FileWriter writer = new FileWriter(resultat);	// on creer une variable pour �crire dans le fichier associer � la variable r�sulat
					BufferedWriter bw = new BufferedWriter(writer);
					bw.write("0");			// on �crit sur la premi�re ligne du fichier 0 
					bw.newLine();					// on va � une nouvelle ligne 
					bw.write("0");			// on �crit sur la deuxi�me ligne du fichier 0  
					bw.close();				
					writer.close();					// on ferme les fonctions pour �crire
				}catch (IOException e) {			// si il y a une erreur on l'attrape et on l'affiche dans le console 
					e.printStackTrace();
				}
			
			}
		}
		System.out.println("A bientot.");		// dans le cas ou le texte rentr� est stop on arr�te 
		sc.close();								// on ferme le scanner 
		
}
}
