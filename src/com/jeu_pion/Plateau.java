package com.jeu_pion;

public class Plateau {

    private final int maxLigne = 15;
    private final int maxColonne = 15;

    private String matrice [][];
    private int ligne;
    private int colonne;

    /**
     * Permet de créer une matrice : String de taille [i][j]
     * @param i : nombre de ligne de la matrice à créer
     * @param j : nombre de colonne de la matrice à créer
     */
    public Plateau(int i, int j)
    {
        if ((i != 0) && (j != 0))
        {
            this.matrice = new String[i][j];
            this.ligne = i;
            this.colonne = j;
        }
    }

    /**
     * Permet d'afficher la matrice sous forme de pseudo tableau en ligne de commande
     * La première et deuxième ligne correspondent aux numéros des colonnes
     * Les premiers chiffres de chaque lignes correspondent aux numéros des lignes
     */
    public void afficherPlateau()
    {
        // Affichage première ligne
        System.out.print("\n   ");
        for (int i = 0; i < this.colonne; i++)
        {
            if (i % 5 == 0)
            {
                System.out.print(" " + i / 10 + "  ");
            }
            else {
                System.out.print("    ");
            }
        }

        // Affichage deuxième migne
        System.out.print("\n   ");
        for (int i = 0; i < this.colonne; i++)
        {
            System.out.print(" " + i % 10 + "  ");
        }

        // Affichage troisième ligne
        System.out.print("\n   ");
        for (int i = 0; i < this.colonne; i++)
        {
            System.out.print("----");
        }


        System.out.println();
        // Affichage du tableau
        for (int i = 0; i < this.ligne; i++)
        {
            System.out.printf("%2d|", i);
            for (int j = 0; j < this.colonne; j++)
            {
                if (this.matrice[i][j] != null)
                {
                    System.out.print(" " + this.matrice[i][j] + " ");
                }
                else
                {
                    System.out.print("   ");
                }
                System.out.print("|");
            }
            System.out.print("\n");
        }

        // Affichage de la derniere ligne
        System.out.print("   ");
        for (int i = 0; i < this.colonne; i++)
        {
            System.out.print("----");
        }
    }

    /**
     * Permet de modifier la matrice en y insérant une valeur aux indexes [i][j]
     * @param i : numéro de la ligne à modifier
     * @param j : numéro de la colonne à modifier
     * @param valeur : valeur à ajouter à la matrice aux coordonnées [i][j]
     * @return : -1 si erreur sinon 1
     */
    public int modifierMatricePlateau(int i, int j, String valeur)
    {
       if ((i >= 0) && (j >= 0) && (i < this.colonne ) && (j < this.ligne))
       {
           this.matrice[i][j] = valeur;
           return 1;
       }
       else
       {
           return -1;
       }
    }

    /**
     * Permet d'obtenir la valeur enregistrer dans la matrice aux coordonnées [i][j]
     * @param i : numéro de la ligne de la valeur à récupérer
     * @param j : numéro de la colonne de la valeur à récupérer
     * @return : "error" si erreur sinon valeur aux coordonnées [i][j]
     */
    public String obtenirValeurMatricePlateau(int i, int j)
    {
        if ((i >= 0) && (j >= 0) && (i <= this.colonne) && (j <= this.ligne))
        {
            return this.matrice[i][j];
        }
        else
        {
            return "error";
        }
    }
}
