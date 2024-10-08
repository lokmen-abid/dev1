public class Exception {


    public static boolean chaineContientQueDesLettres(String l) {
        try {
            if (l == null) {
                throw new IllegalArgumentException("La chaîne ne peut pas être nulle");
            }

            for (int i = 0; i < l.length(); i++) {
                if ((l.charAt(i)!=' ' ) && (!Character.isLetter(l.charAt(i)))) {
                    throw new IllegalArgumentException("La chaîne contient des caractères non alphabétiques");
                }
            }

            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public static boolean verifierImmatriculation(Bus[] b, String im) {
        try {
            if (im == null) {
                throw new IllegalArgumentException("L'immatriculation ne peut pas être nulle");
            }
            for (Bus bus : b) {
                if (bus.getNumeroImmatriculation().equals(im)) {
                    throw new IllegalArgumentException("L'immatriculation existe déjà");
                }
            }
            return true; // Entrée valide
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false; // Entrée non valide
        }
    }


}
