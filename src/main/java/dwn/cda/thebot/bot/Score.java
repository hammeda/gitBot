package dwn.cda.thebot.bot;
public class Score {
    public static int score = 0;

    public void updateExperience(boolean aGagne) {
        if (aGagne) {
            score += 1; // Incrémente si gagné
        } else {
            score -= 1; // Décrémente si perdu
        }
    }

    public boolean hasLost(){
        return score >= -3;
    }
}


