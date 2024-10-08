import java.util.Scanner;

public class Bus {
    // Attributs
    private String numeroImmatriculation; // Numéro de la plaque d'immatriculation
    private double capaciteReservoir; // Capacite du reservoir en litres (nombres à virgule autorisés)
    private int nombrePassager; // Nombre de passagers
    private String couleur; // Couleur du bus

    // Constructeur
    public Bus(String numeroImmatriculation, double capaciteReservoir, int nombrePassager, String couleur) {
        this.numeroImmatriculation = numeroImmatriculation;
        this.capaciteReservoir = capaciteReservoir;
        this.nombrePassager = nombrePassager;
        this.couleur = couleur;
    }

    public String getNumeroImmatriculation() {
        return numeroImmatriculation;
    }

    public void setNumeroImmatriculation(String numeroImmatriculation) {
        this.numeroImmatriculation = numeroImmatriculation;
    }

    public double getCapaciteReservoir() {
        return capaciteReservoir;
    }

    public void setCapaciteReservoir(double capaciteReservoir) {
        this.capaciteReservoir = capaciteReservoir;
    }

    public int getNombrePassager() {
        return nombrePassager;
    }

    public void setNombrePassager(int nombrePassager) {
        this.nombrePassager = nombrePassager;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    // Methode pour afficher les informations du bus
    public void afficherInformations() {
        System.out.println("----------------------------------------");
        System.out.println("Numéro d'immatriculation: " + numeroImmatriculation);
        System.out.println("Capacité du réservoir : " + capaciteReservoir + " Litres");
        System.out.println("Nombre de passagers: " + nombrePassager);
        System.out.println("Couleur: " + couleur);
    }

    // Méthode pour créer un Bus
    public static Bus creerBus() {
        Scanner scanner = new Scanner(System.in); // Crée une instance de Scanner pour lire les entrées utilisateur
        System.out.println("Veuillez entrer les informations du bus");
        // Demande à l'utilisateur de saisir les attribus de bus et l'enregistrer dans les variables
        System.out.println("Numéro d'immatriculation:");
        String numeroImmatriculation = scanner.nextLine();
        double capaciteReservoir = -1;
        boolean valid = false;

        while (!valid) {

            try {
                System.out.println("Capacité du réservoir:");
                capaciteReservoir = Double.parseDouble(scanner.nextLine());
                if (capaciteReservoir < 0) {
                    System.out.println("Veuillez entrer une capacité valide (positive).");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide pour la capacité.");
            }
        }
        int nombrePassager = -1;
        valid = false;

        while (!valid) {
            System.out.println("Nombre de passagers:");
            try {
                nombrePassager = Integer.parseInt(scanner.nextLine());
                if (nombrePassager < 0) {
                    System.out.println("Veuillez entrer un nombre positif pour le nombre de passagers.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour le nombre de passagers.");
            }
        }
        Scanner scan=new Scanner(System.in);
        System.out.println("Couleur:");
        String couleur = scan.nextLine();
        // Condition pour vérifie que la couleur ne contient que des lettres
        while (!Exception.chaineContientQueDesLettres(couleur) ){
            System.out.println("Couleur:");
            couleur = scan.nextLine();
        }
        // Retourne une nouvelle instance de Bus avec les informations fournies par l'utilisateur
        return new Bus(numeroImmatriculation, capaciteReservoir, nombrePassager, couleur);
    }

    // Métohde pour trouver le bus avec la plaque
    public static Bus trouverBusParImmatriculation(Bus[] buses , String immatriculation) {
        // parcourt de chaque élément du tableau
        for (Bus bus : buses) {
            // condition pour vérifie si l'élément actuel n'est pas nul & si son numéro d'immatriculation correspond à celui recherché
            if (bus != null && bus.getNumeroImmatriculation().equals(immatriculation)) {
                return bus; // si élément trouvé , retourne le bus correspondant
            }
        }
        // Si aucun élément n'est trouvée après parcouru le tableau, retourne null
        return null;
    }

}