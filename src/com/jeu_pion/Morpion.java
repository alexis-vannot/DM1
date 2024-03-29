package com.jeu_pion;

/**
 * Classe Morpion fille de Jeu
 * @author : Remi B
 * @version : 1.0
 */
public class Morpion extends Jeu {
	
	private static final String SEPARATEUR = ";";
	private static final int MAX_PLATEAU = 5;

	/**
	 * Constructeur qui fait appel à une autre méthode afin de créer le plateau de jeu du morpion
	 */
	public Morpion()
	{
		this.initialisation();
	}


	/**
	 * Permet de créer un plateau de jeu qu'il desire avec une taille maximale MAX_PLATEAU
	 */
	@Override
	public void initialisation()
	{
		int taillePlateau;
		String str;
		str = String.format("Entrer la taille du plateau (max %d): ", MAX_PLATEAU);
		Jeu.affichable.affichageElement(str);
		// Vérification que la taille du tableau est cohérente
		do 
		{
			taillePlateau = scanner.nextInt();
			scanner.nextLine(); // Remise à "zéro" du scanner
			if((taillePlateau <= 0) || (taillePlateau > MAX_PLATEAU))
			{
				str = String.format("Veuillez entrer une valeur entre 1 et %d: ", MAX_PLATEAU);
				Jeu.affichable.affichageElement(str);
			}
		} while ((taillePlateau <= 0) || (taillePlateau > MAX_PLATEAU));
		plateau = new Plateau(taillePlateau, taillePlateau);
	}


	/**
	 * Méthode qui permet de demander au joueur passé en paramètre de saisir des
	 * coordonnées afin de modifier le plateau de jeu
	 * @param joueur : joueur qui doit modifier le plateau de jeu
	 */
	@Override
	public void jouer(Joueur joueur) 
	{
		String positionPion;
		String str;
		do {
			// On demande au joueur de saisir des coordonnées pour qu'il place son pion
			str = String.format("%n%n%s doit jouer. Placer votre pion (ex: 0;0 -> ligne;colonne) : ", joueur.getPrenom());
			Jeu.affichable.affichageElement(str);
			positionPion = scanner.next();
			// On vérifie l'exactitude des coordonnées saisies et on place le pion
		} while (!gestionPosition(positionPion, joueur.getCaractere()));
	}


	/**
	 * Cette méthode permet de vérifier si un joueur a gagné, perdu ou s'il y a égalité
	 * @param joueur : joueur pour lequel on souhaite vérifier une victoire, défaite, égalité
	 * @return 0 si le joueur doit "rejouer", 1 si victoire, 2 si egalité
	 */
	@Override
	public int verification(Joueur joueur)
	{
		// Colonne ou ligne, il s'agit d'un plateau carré
		int taillePlateau = plateau.getColonneMatricePlateau();
		int cpt = 0;
		int cpt1 = 0;
		int cpt2;
		int cpt3;

		String joueurCaract = joueur.getCaractere();

		// Boucle pour le nombre de lignes
		for (int i = 0; i < taillePlateau; i++)
		{
			// Vérifications pour les diagonales
			if(plateau.obtenirValeurMatricePlateau( i, i) == joueurCaract)
			{
				cpt++;
			}
			if(plateau.obtenirValeurMatricePlateau( i, (taillePlateau - 1) - i) == joueurCaract)
			{
				cpt1++;
			}
			cpt2 = 0;
			cpt3 = 0;
			// Boucle pour le nombre colonnes
			for(int t = 0; t < taillePlateau; t++)
			{
				// Vérifications sur les lignes et les colonnes
				if(plateau.obtenirValeurMatricePlateau( i, t) == joueurCaract)
				{
					cpt2++;
				}
				if(plateau.obtenirValeurMatricePlateau( t, i) == joueurCaract)
				{
					cpt3++;
				}
			}
			// Vérification victoire sur lignes et colonnes
			if(cpt2 == taillePlateau|| cpt3 == taillePlateau)
			{
				return 1;
			}
		}

		// Vérification victoire sur diagonales
		if(cpt == taillePlateau || cpt1 == taillePlateau)
		{
			return 1;
		}
		// Vérification égalité sur l'ensemble du plateau
		else if (plateau.isMatricePlateauComplete())
		{
			return 2;
		}
		// Rejouer
		else
		{
			return 0;
		}
	}


	/**
	 * Méthode qui modifie le plateau de jeu si les coordonnées du joueur sont correctes avec son caractère
	 * @param coordonnees : les coordonnées du pion que le joueur souhaite insérer dans le plateau sous la forme /!\ X;Y /!\
	 * @param caractere : le caractère du joueur qu'on va insérer dans le plateau
	 * @return true si le joueur a bien modifié le plateau de jeu sinon false
	 */
	private boolean gestionPosition(String coordonnees, String caractere)
	{
		int x;
		int y;
		boolean res;
		// Séparation des coordonnées passées en paramètre -> (forme x;y)
		String [] pos = coordonnees.split(SEPARATEUR);
		x = Integer.parseInt(pos[0]);
		y = Integer.parseInt(pos[1]);
		// Vérification que les coordonnées soient présentes dans le plateau
		if ((x >= 0 ) && (x < plateau.getLigneMatricePlateau()) && (y >= 0) && (y < plateau.getColonneMatricePlateau()))
		{
			// Vérification que la case correspondante aux coordonnées n'est pas déjà occupée
			if (plateau.obtenirValeurMatricePlateau(x, y) == null )
			{
				plateau.modifierMatricePlateau(x, y, caractere);
				res = true;
			}
			else
			{
				Jeu.affichable.affichageElement("Cette case est déjà prise");
				res = false;
			}
		}
		else 
		{
			Jeu.affichable.affichageElement("Cette case n'est pas dans le plateau");
			res = false;
		}
		return res;
	}
}

