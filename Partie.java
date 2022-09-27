package bouteille;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Partie {
	private int taille;
	private int [][] grille;						// variable qui permet de creer la grille 
	private int scorej1;							
	private int scorej2;							
	private String score;							 
	private String line1;							 
	private String line2;							
	private int lineNo;								
	
	public void modTaille() {
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);				// création d'un scanner pour prendre un input de l'utilisateur
		System.out.println("Saisissez la taille de la grille avec laquelle vous voulez jouer: ");
		taille=sc.nextInt();							// on assigne le chiffre entré par l'utilisateur à la variable taille
		this.grille= new int[taille][taille];			// on crée une grille avec la taille entrée par le premier joueur 
		
	}
	public Partie() {
		for (int i=0; i<taille;i++) {			// double boucle pour remplir la grille 
			for (int j=0; j<taille;j++) {
				grille[i][j]=0;				// remplis la grille de 0 
			}
		}
	}
	
	
	public String toString() {
		String str="";							// initialisation de la variable str comme une chaine de caractï¿½re vide
		String symbole="";
		for (int i=0; i<taille;i++) {			// permet de parcourir la grille  
			for (int j=0; j<taille;j++) {
													 
					if (grille[i][j]==1) {			// si l'élément de la grille est un 1 alors on affiche une croix
						symbole="X";
					}
					else if (grille[i][j]==-1) {			// si l'élément est un -1 on affiche un O 
						symbole="O";
					}
					else if (grille[i][j]==0) symbole="-";		// et si la grille contient un 0 on affiche un trait 
					str=str+"|"+symbole;						
					}
			str=str+"| "+"\n";									//affiche la grille en cours 		
		}
		System.out.println("Grille en cours");			 
		return str;										// renvoie le contenue de la variable str
	}
	
	
	public void tourDeJeu(int joueur) {
		@SuppressWarnings("resource")
		Scanner sc =new Scanner(System.in);				// création d'un scanner permettant un input clavier 
		boolean arret = false;							// création du booléen pour controler si la case demandée est deja occupée
		System.out.println("Tour du joueur"+joueur+":");	
		while (arret ==false) {							// boucle while pour le controle du contenu de la case  
			
		System.out.println("Numéro de ligne?");			
		String str = sc.nextLine();					// on demande une première entré
		int i=Integer.parseInt(str);
													
		System.out.println("Numéro de colonne?");
		str = sc.nextLine();						// on demande une autre entrée
		int j=Integer.parseInt(str);
													
		if ( grille [i][j]==0) {					// Si la case comporte un 0 alors mettre 1 si c'est le jouer 1 sinon un -1 car cela correspondrais au joueur 2
			if (joueur == 1 ) {
				grille[i][j]=1;
			}
			else {
				grille[i][j]=-1;
			}
			arret = true;							// le booléen passe a vrai 
		}
		else {										// sinon on affiche que la case est deja prise et on redemande une ligne et une colonne 
			System.out.println("La case est déjà prise");
			}
		}

	}
	
	
	
	public int sommeLigne(int i) {		// methode qui permet de faire la somme sur les lignes 
		int somme=0;
		for (int j=0; j<taille; j++) {
			somme += grille[i][j];
				
		}
		return somme; 
		
	}
	public int sommeColonne(int j) {		// mï¿½thode permettant de faire la somme sur les colonnes 
		int somme=0;
		for (int i=0; i<taille; i++) {
			somme+= grille[i][j];
			
		}
		return somme;
	}
	public int sommeDiag1() {			//  methode qui fait la somme sur la diagonale
		int somme = 0; 
		for (int i=0; i<taille;i++) {
			somme+=grille[i][i];
		}
		return somme;
	}
	public int sommeDiag2() {			// methode qui fait la somme sur la deuxiï¿½me diagonale 
		int somme = 0; 
		for (int i=0; i<taille;i++) {
			somme+=grille[i][(taille-1)-i];
		}
		return somme;
	}
	public int etatPartie() {			//methode qui gère l'état de la partie 
		for (int i=0;i<taille;i++) {
			int sommeLigne=sommeLigne(i);
			if(sommeLigne==taille) {			// si la somme de la ligne = 3 alors on augmente le score du joueur 1 et on retourne la valeur 1
				majScorej1();
				
				return 1; 
			}
			else if (sommeLigne==-taille) {		// sinon si la somme de la ligne vaut -3 alors on augmente le score du joueur 2 et on retourne la valeur -1
				majScorej2();

				return -1 ;
			
		}
		}
		for (int j=0;j<taille;j++) {
			int sommeColonne=sommeColonne(j);	
			if(sommeColonne==taille) {	// si la somme de la colonne vaut 3 alors le score du joueur 1 augmente de 1 et on retourne la valeur 1 
				majScorej1();
				return 1; 
								
			}
			else if (sommeColonne==-taille) {		// sinon si la somme de la colonne vaut -3 alors on augmente le score du joueur 1 et on retourne la valeur -1
				majScorej2();
				return -1;
			}
		}
		if (sommeDiag1()==taille || sommeDiag2()==taille) {	// si la somme de la premiï¿½re diagonale ou de la deuxiï¿½me vaut 3 alors on augmente le score du joueur 1 de 1 et on retourne 1
			majScorej1();
			return 1; 
		}
		else if (sommeDiag1()==-taille || sommeDiag2()==-taille) {// si la somme de la premiï¿½re diagonale ou de la deuxiï¿½me vaut -3 alors on augmente le score du joueur 2 de 1 et on retourne -1
			majScorej2();
			
			return-1;
		}
												// personne n'a gagné.
		return 0;								//dans le cas ou personne gagne on retourne 0 
	}
	public void majScorej1() {					// methode qui permet de mettre a jour le score du joueur 1 durant toutes les parties
		File resultat = new File("C:\\Users\\kevin\\OneDrive\\Bureau\\resultat\\resultat.txt");	// permet d'assigner à la variable résultat le fichier texte 
		if (!resultat.exists()) {		// si le fichier txt n'existe pas 
			try {
				resultat.createNewFile();		// alors on le créer 
			}	catch(IOException e) {			// si il y a une erreur alors on l'affiche dans la console 
				e.printStackTrace();
			}
		}
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(resultat),"UTF-8"));	// creer une variable qui va permettre de lire le fichier texte
			
			for (lineNo = 1; lineNo < 10; lineNo++) {		// boucle for pour parcourir le fichier texte
				if (lineNo == 1) {							// si la variable numéro de ligne vaut 1 alors on lit la ligne et on affecte cette ligne à la variable scorej1
					line1= reader.readLine();
					scorej1=Integer.parseInt(line1)+scorej1;
				}
				if(lineNo ==2) {							// si la variable numéro dee ligne vaut 2 alors on lit la ligne et on affecte cette ligne à la variable scorej2
					line2=reader.readLine();
					scorej2=Integer.parseInt(line2)+scorej2;
				}
			}scorej1++;										// on augmente scorej1 de 1
		}catch(IOException e) {								// on attrape l'erreur et on l'affiche dans la console si il y a en une 
			e.printStackTrace();
		}
		
	
			try {
				FileWriter writer = new FileWriter(resultat);			// on creer une variable pour écrire dans le fichier associer à la variable resulat
				BufferedWriter bw = new BufferedWriter(writer);
				bw.write(""+scorej1);									// on écrit la variable scorej1 dans le fichier 
				bw.newLine();											// on va à une nouvelle ligne
				bw.write(""+scorej2);									// on écrit le scorej2 dans le fichier 
				bw.close();
				writer.close();											// on ferme les fonctions pour écrire
			}catch (IOException e) {
				e.printStackTrace();									// si il y a une erreur on l'attrape et on l'affiche dans le console 
			}
		
	}	
	
	public void majScorej2() {			// methode qui permet de mettre a jour le score du joueur 2  durant toutes les parties
		File resultat = new File("C:\\Users\\kevin\\OneDrive\\resultat\\resultat.txt");			// permet d'assigner à la variable resultat le fichier texte 
		if (!resultat.exists()) {			// si le fichier txt n'existe pas 
			try {
				resultat.createNewFile();		// alors on le créer
			}	catch(IOException e) {			// si il y a une erreur on l'affiche dans la console 
				e.printStackTrace();
			}
		}
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(resultat),"UTF-8"));		// creer une variable qui va permettre de lire le fichier texte
			
			for (lineNo = 1; lineNo < 10; lineNo++) {	// boucle for pour parcourir le fichier texte 	
				if (lineNo == 1) {						// si la variable numéro de ligne vaut 1 alors on lit la ligne et on affecte cette ligne à la variable scorej1
					line1= reader.readLine();
					scorej1=Integer.parseInt(line1)+scorej1;
				}
				if(lineNo ==2) {						// si la variable numéro dee ligne vaut 2 alors on lit la ligne et on affecte cette ligne à la variable scorej2
					line2=reader.readLine();
					scorej2=Integer.parseInt(line2)+scorej2;
				}
			}scorej2++;			// on augmente scorej2 de 1 
		}catch(IOException e) {		// on attrape l'erreur et on l'affiche dans la console si il y a en une  
			e.printStackTrace();
		}
		
	
			try {
				FileWriter writer = new FileWriter(resultat);	// on creer une variable pour ï¿½crire dans le fichier associer ï¿½ la variable rï¿½sulat
				BufferedWriter bw = new BufferedWriter(writer);
				bw.write(""+scorej1);			// on écrit la variable scorej1 dans le fichier 
				bw.newLine();					// on va créer une nouvelle ligne 
				bw.write(""+scorej2);			// on écrit le scorej2 dans le fichier 
				bw.close();				
				writer.close();					// on ferme les fonctions pour écrire
			}catch (IOException e) {			// si il y a une erreur on l'attrape et on l'affiche dans le console 
				e.printStackTrace();
			}
	
	}	
		
	
	public void jouerUnePartie() {			// methode qui permet de lancer la partie 
		int etatPartie=0;					// initialisation de la variable état partie à 0 
		int nbTours=0;						// initialisation de la variable nbTours à 0 
		int joueur=1;						// Initialisation de la variable joueur à 1 ce qui implique que c'est le joueur avec les 1 qui commence 
		modTaille();
		System.out.println(" ");
		System.out.println("Pour jouer, écrivez 1 chiffre celui-ci correspond à la ligne puis appuyez sur entrée le deuxième correspond à celui de la colonne puis il faut appuyer sur entrée.");
		System.out.println("Les colonnes et les lignes sont repérées par les chiffres allant de 0 à 2");
		System.out.println(" ");
		while(etatPartie==0 && nbTours<taille*taille) {			// boucle while qui permet à la partie de se jouer 
			tourDeJeu(joueur);						// on appele la méthode tourDeJeu avec la valeur de la variable Joueur en attribut
			etatPartie=etatPartie();				// on définis la variable état partie comme étant la méhode du même nom 
			nbTours++;								// on augmente le nombre de tour de 1 a chaque fois
			if (joueur ==1) joueur=2;				// if qui permet de changer de joueur à chaque tour
			else joueur=1;
			System.out.println(toString());			// Affiche la méthode toString
		}
		if (etatPartie==0) System.out.println("Match nul") ;		// si la variable etatPartie vaut 0 alors ca affiche match nul
		else if (etatPartie==1) {									// sinon si la variable etatPartie vaut 1 alors ca affiche victoire du joueur 1 
			System.out.println("Victoire du joueur 1 ");
			System.out.println("");
		}
		else {														// sinon ca affiche victoire du joueur 2
			System.out.println("victoire du joueur 2");
			System.out.println("");
		}
		score=scorej1+"-"+scorej2;									// assignation à la variable score le score du joueur 1 et du joueur 2 
		System.out.println("le score est de "+score+".");			// affiche le score 
		System.out.println("");
		System.out.println("Pour relancer une partie il suffit de relancer le programme.");
		System.out.println("");
		
		
		
	}
	
}


