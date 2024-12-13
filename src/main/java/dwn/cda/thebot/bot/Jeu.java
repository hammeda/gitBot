package dwn.cda.thebot.bot;

public class Jeu {
    private Pfc choixJoueur1 = null;
    private Pfc choixJoueur2 = null;

    // Méthode pour enregistrer le choix d'un joueur
    public void enregistrerChoix(String joueur, String choixUtilisateur) {
        // Convertir le choix en majuscule pour que l'entrée soit insensible à la casse
        try {
            // Renommer la variable locale pour éviter le conflit
            Pfc choixJoueur = Pfc.valueOf(choixUtilisateur.toUpperCase()); // Associe le choix à l'énumération correspondante

            if (joueur.equals("Joueur1")) {
                choixJoueur1 = choixJoueur;
                System.out.println("Joueur 1 a choisi : " + choixJoueur);
            } else if (joueur.equals("Joueur2")) {
                choixJoueur2 = choixJoueur;
                System.out.println("Joueur 2 a choisi : " + choixJoueur);
            }

            // Lorsque les deux joueurs ont fait leur choix, déterminer le gagnant
            if (choixJoueur1 != null && choixJoueur2 != null) {
                determinerGagnant();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Choix invalide. Veuillez choisir parmi Pierre, Feuille ou Ciseaux.");
        }
    }

    // Méthode pour déterminer le gagnant après que les 2 joueurs ont fait leurs choix
    private void determinerGagnant() {
        if (choixJoueur1 == choixJoueur2) {
            System.out.println("C'est une égalité !");
        } else if ((choixJoueur1 == Pfc.PIERRE && choixJoueur2 == Pfc.CISEAUX) ||
                (choixJoueur1 == Pfc.CISEAUX && choixJoueur2 == Pfc.FEUILLE) ||
                (choixJoueur1 == Pfc.FEUILLE && choixJoueur2 == Pfc.PIERRE)) {
            System.out.println("Le Joueur 1 gagne !");
        } else {
            System.out.println("Le Joueur 2 gagne !");
        }

        // Réinitialiser les choix pour une nouvelle manche
        resetJeu();
    }

    // Réinitialiser les choix des joueurs pour un nouveau jeu
    private void resetJeu() {
        choixJoueur1 = null;
        choixJoueur2 = null;
    }
}
