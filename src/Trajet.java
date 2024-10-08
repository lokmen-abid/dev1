import java.util.Scanner;

public class Trajet {
    private String villeDepart;//ville de départ
    private String villeArrive;//ville d'arrivé
    private int kilometrageDepart; // Numéro de kilométrage au départ
    private int kilometrageArrive; // Numéro de kilométrage au arrivé
    private Bus busUtilise; // Bus utiliser pour ce trajet

    // Constructeur
    public Trajet(String villeDepart, String villeArrive, int kilometrageDepart, int kilometrageArrive) {
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.kilometrageDepart = kilometrageDepart;
        this.kilometrageArrive = kilometrageArrive;
        this.busUtilise = null; // Bus non assigné initialement
    }

    // Getters & setters
    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public int getKilometrageDepart() {
        return kilometrageDepart;
    }

    public void setKilometrageDepart(int kilometrageDepart) {
        this.kilometrageDepart = kilometrageDepart;
    }

    public int getKilometrageArrive() {
        return kilometrageArrive;
    }

    public void setKilometrageArrive(int kilometrageArrive) {
        this.kilometrageArrive = kilometrageArrive;
    }

    public Bus getBusUtilise() {
        return busUtilise;
    }

    public void setBusUtilise(Bus busUtilise) {
        this.busUtilise = busUtilise;
    }

    // Méthode pour afficher les informations du Trajet
    public void afficherTrajetCaracteristique() {
        System.out.println("la ville de depart est " + this.villeDepart);
        System.out.println("la ville d'arrivé est " + this.villeArrive);
        System.out.println("le kilometrage au depart " + this.kilometrageDepart);
        System.out.println("le kilometrage a l'arrivé est  " + this.kilometrageArrive);
        if (busUtilise != null) {
            System.out.println("le bus utilise est d'immatriculation: " + this.busUtilise.getNumeroImmatriculation());
        } else {
            System.out.println("Bus utilisé: Non assigné");
        }
    }

    // Méthode pour créer un trajet
    public static Trajet creerTrajet(Scanner scanner) {
        // Crée une nouvelle instance de Scanner pour lire les entrées utilisateur
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez entrer les informations du trajet:");
        // Demande à l'utilisateur de saisir les attribus de Trajet et l'enregistrer dans les variables
        System.out.println("Ville de départ:");
        String villeDepart = scan.nextLine();
        while(!Exception.chaineContientQueDesLettres(villeDepart)){
            System.out.println("Ville de départ:");
            villeDepart = scan.nextLine();
        }
        System.out.println("Ville d'arrivée:");
        String villeArrive = scan.nextLine();
        while(!Exception.chaineContientQueDesLettres(villeArrive)){
            System.out.println("Ville d'arrivée:");
            villeArrive = scan.nextLine();
        }
        int kilometrageDepart = -1;
        boolean validDepart = false;

        while (!validDepart) {

            try {
                System.out.println("Kilométrage de départ:");
                kilometrageDepart = Integer.parseInt(scanner.nextLine());
                if (kilometrageDepart < 0) {
                    System.out.println("Veuillez entrer un kilométrage positif pour le départ.");
                } else {
                    validDepart = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour le kilométrage de départ.");

            }
        }

        // Demande du kilométrage à l'arrivée
        int kilometrageArrivee = -1;
        boolean validArrivee = false;

        while (!validArrivee) {

            try {
                System.out.println("Kilométrage à l'arrivée:");
                kilometrageArrivee = Integer.parseInt(scanner.nextLine());
                if (kilometrageArrivee < 0 || kilometrageArrivee < kilometrageDepart) {
                    System.out.println("Veuillez entrer un kilométrage positif et supérieur ou égal au kilométrage de départ.");
                } else {
                    validArrivee = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour le kilométrage à l'arrivée.");

            }
        }

        // Retourne une nouvelle instance de Trajet avec les informations fournies par l'utilisateur
        return new Trajet(villeDepart, villeArrive, kilometrageDepart,kilometrageArrivee );
    }
}
