package dwn.cda.thebot.bot;

public class CommandListener {
    private Jeu jeu = new Jeu();

    // Cette méthode sera appelée chaque fois qu'un message est reçu
    public void onMessageReceived(String playerName, String message) {
        // Si le message commence par la commande "!choix", on extrait le choix
        if (message.startsWith("!choix ")) {
            String choix = message.substring(7).trim(); // Extraire le choix (pierre, feuille, ciseaux)

            // Enregistrer le choix du joueur
            if (playerName.equals("Joueur1")) {
                jeu.enregistrerChoix("Joueur1", choix);
            } else if (playerName.equals("Joueur2")) {
                jeu.enregistrerChoix("Joueur2", choix);
            }
        }
    }

    // Cette méthode démarre un nouveau jeu
    public void startGame() {
        System.out.println("Le jeu commence ! Choisissez pierre, feuille ou ciseaux.");
    }
}
