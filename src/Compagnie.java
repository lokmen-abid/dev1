import java.util.Scanner;

public class Compagnie {
    private final String nom; // Nom de compagnie
    private Chauffeur[] chauffeurs; // Liste des Chauffeurs
    private int nombreDeChauffeurs; // Nombre des Chauffeurs
    private Bus[] busFlotte; // Liste des Bus
    private int nombreDeBus; // Nombre des Bus
    private Trajet[] trajets; // Liste des Trajets
    private int nombreDeTrajets; // Nombre des Trajets

    public Compagnie(String nom) {
        this.nom = nom;
        this.chauffeurs = new Chauffeur[10]; // Initialise le Tableau Chauffeurs de longeur 10
        this.nombreDeChauffeurs = 0; // Initialise le nombre de chauffeurs à 0
        this.busFlotte = new Bus[10];// Initialise le Tableau BusFlotte de longeur 10
        this.nombreDeBus = 0;// Initialise le nombre de bus à 0
        this.trajets = new Trajet[10];// Initialise le Tableau Trajets de longeur 10
        this.nombreDeTrajets = 0; // Initialise le nombre de Trajets à 0
    }

    // Méthode pour ajouter un chauffeur
    public void ajouterChauffeur(Chauffeur chauffeur) {
        // Condition pour vérifie si le tableau de chauffeurs est plein
        if (nombreDeChauffeurs == chauffeurs.length) {
            Chauffeur[] nouveauxChauffeurs = new Chauffeur[chauffeurs.length * 2];
            // Copie les chauffeurs existants dans le nouveau tableau
            System.arraycopy(chauffeurs, 0, nouveauxChauffeurs, 0, chauffeurs.length);
            chauffeurs = nouveauxChauffeurs;
        }
        // Ajoute le nouveau Chauffeur à la première position vide dans le tableau
        chauffeurs[nombreDeChauffeurs] = chauffeur;
        nombreDeChauffeurs++; // Incrémente le nombre de chauffeurs
    }

    // Méthode pour ajouter un bus
    public void ajouterBus(Bus bus) {
        // Condition pour vérifie si le tableau de Bus est plein
        if (nombreDeBus == busFlotte.length) {
            Bus[] nouveauxBus = new Bus[busFlotte.length * 2];
            // Copie les Bus existants dans le nouveau tableau
            System.arraycopy(busFlotte, 0, nouveauxBus, 0, busFlotte.length);
            busFlotte = nouveauxBus;
        }
        // Ajoute le nouveau Bus à la première position vide dans le tableau
        busFlotte[nombreDeBus] = bus;
        nombreDeBus++;// Incrémente le nombre de Bus
    }

    // Méthode pour ajouter un trajet
    public void ajouterTrajet(Trajet trajet) {
        // Condition pour vérifie si le tableau de Trajet est plein
        if (nombreDeTrajets == trajets.length) {
            Trajet[] nouveauxTrajets = new Trajet[trajets.length * 2];
            // Copie les Trajets existants dans le nouveau tableau
            System.arraycopy(trajets, 0, nouveauxTrajets, 0, trajets.length);
            trajets = nouveauxTrajets;
        }
        // Ajoute le nouveau Trajet à la première position vide dans le tableau
        trajets[nombreDeTrajets] = trajet;
        nombreDeTrajets++;// Incrémente le nombre de Trajet
    }

    // Méthode pour affiche tous les chauffeurs de l'entreprise
    public void afficherChauffeurs() {
        // Parcourt sur tous les chauffeurs dans le tableau
        for (int i = 0; i < nombreDeChauffeurs; i++) {
            // Appelle la méthode afficherInformationsChauffeur pour afficher les détails du chauffeur
            chauffeurs[i].afficherInformationsChauffeur();
        }
    }

    // Méthode pour afficher tous les bus de l'entreprise
    public void afficherBus() {
        // Parcourt sur tous les Bus dans le tableau
        for (int i = 0; i < nombreDeBus; i++) {
            // Appelle la méthode afficherInformations pour afficher les détails du Bus
            busFlotte[i].afficherInformations();
        }
    }

    // Méthode pour affiche tous les trajets de l'entreprise
    public void afficherTrajets() {
        // Parcourt sur tous les Trajet dans le tableau
        for (int i = 0; i < nombreDeTrajets; i++) {
            System.out.println("--- Trajet ID: " + i +" -----------------------");
            // Appelle la méthode afficherTrajetCaracteristique pour afficher les détails du Trajet
            trajets[i].afficherTrajetCaracteristique();
        }
    }

    // Méthode pour récupère un trajet par son #index
    public Trajet getTrajet(int index) {
        // Condition pour vérifie si l'index est valide
        if (index >= 0 && index < nombreDeTrajets) {
            return trajets[index]; // Retourne le trajet à l'index donnée

        }
        return null;
    }

    // Métohde pour trouver le chauffeur avec son ID
    public Chauffeur trouverChauffeurParId(String idChauffeur) {
        // Parcourt tous les Chauffeurs dans le tableau chauffeurs
        for (Chauffeur chauffeur : chauffeurs) {
            // Condition pour vérifie que le chauffeur n'est pas null et que son ID égale à l'ID donnée
            if (chauffeur != null && chauffeur.getIdChauffeur().equals(idChauffeur)) {
                return chauffeur; // Retourne le chauffeur correspondant
            }
        }
        return null;
    }

    public Bus trouverBusParImmatriculation(String immatriculation) {
        // Parcourt tous les Bus dans le tableau chauffeurs
        for (Bus bus : busFlotte) {
            // Condition pour vérifie que le Bus n'est pas null et que numéroImmatriculation égale à numéroImmatriculation donnée
            if (bus != null && bus.getNumeroImmatriculation().equals(immatriculation)) {
                return bus; // Retourne le Bus correspondant
            }
        }
        return null;
    }

    // Méthode pour asigner un BUS à un TRAJET et à un CHAUFFEUR
    public void reserverBus(Scanner scanner) {
        System.out.println("Est-ce une réservation pour un trajet existant? (oui/non)"); // Plus simple et permet de réutiliser des trajets
        String reponse = scanner.nextLine();
        Trajet trajet;

        if (reponse.equalsIgnoreCase("oui")) {
            // Affiche les trajets disponibles pour sélection
            System.out.println("Trajets disponibles:");
            afficherTrajets();
            System.out.println("----------------------------------------");
            System.out.println("Entrez le numéro du trajet à assigner:");
            int indexTrajet = scanner.nextInt(); // Demande de l'utilisateur de saisir le numéro du trajet à assigner
            scanner.nextLine();
            trajet = getTrajet(indexTrajet); // Appelle à la méthode getTrajet pour récupèrer le trajet correspondant à l'index
            if (trajet == null) {
                System.out.println("Trajet non trouvé: " + indexTrajet);
                return;
            }
        } else {
            trajet = Trajet.creerTrajet(scanner);
            ajouterTrajet(trajet);
        }

        System.out.println("Chauffeurs disponibles:");
        afficherChauffeursPourReservation();
        System.out.println("----------------------------------------");
        System.out.println("Entrez l'ID du chauffeur:");
        String idChauffeur = scanner.nextLine(); // Demande à l'utilisateur de saisir l'ID du chauffeur
        Chauffeur chauffeur = trouverChauffeurParId(idChauffeur); //Appelle à la méthode trouverChauffeurParId pour trouver le chauffeur correspondant à l'ID

        if (chauffeur != null) {
            System.out.println("Numéro d'immatriculation du bus utilisé:");
            String numeroImmatriculation = scanner.nextLine(); // Demande à l'utilisateur de saisir le numéro d'immatriculation du bus utilisé
            //Appelle à la méthode trouverBusParImmatriculation pour trouver le Bus correspondant à numéroImmatriculation
            Bus bus = trouverBusParImmatriculation(numeroImmatriculation);
            if (bus != null) {
                // Assigne le bus au trajet et ajoute le trajet au chauffeur
                trajet.setBusUtilise(bus);
                chauffeur.ajouterTrajet(trajet);
                System.out.println("Trajet assigné au chauffeur avec succès: " + chauffeur.getIdChauffeur() + " avec le bus " + bus.getNumeroImmatriculation());
            } else {
                // Si le bus n'est pas trouvé, affiche un message d'erreur
                System.out.println("Immatriculation non trouvée: " + numeroImmatriculation);
            }
        } else {
            // Si le chauffeur n'est pas trouvé, affiche un message d'erreur
            System.out.println("ID non trouvé: " + idChauffeur);
        }
    }

    // Liste les chauffeurs Nom + ID pour la réservation
    public void afficherChauffeursPourReservation() {
        // Parcourt tous les chauffeurs  dans le tableau
        for (int i = 0; i < nombreDeChauffeurs; i++) {
            Chauffeur chauffeur = chauffeurs[i]; // Récupère le chauffeur à l'index courant
            // Affiche le prénom, le nom et l'ID du chauffeur
            System.out.println(chauffeur.getPrenom() + " " + chauffeur.getNom() + " (ID: " + chauffeur.getIdChauffeur() + ")");
        }
    }

    // Méthode pour supprimer un trajet
    public void supprimerTrajet(int index) {
        // Condition pour vérifie si l'index est valide
        if (index >= 0 && index < nombreDeTrajets) {
            // Décale tous les trajets suivants vers la gauche pour couvrir l'espace du trajet supprimé
            for (int i = index; i < nombreDeTrajets - 1; i++) {
                trajets[i] = trajets[i + 1];
            }
            trajets[nombreDeTrajets - 1] = null;// Donner la valeur null à le dernier élément du tableau
            nombreDeTrajets--; // Décrémente le nombre total de trajets
            System.out.println("Trajet supprimé avec succès.");
        } else {
            System.out.println("Index de trajet invalide.");
        }
    }

    public void supprimerChauffeur(int index) {
        // Condition pour vérifie si l'index est valide
        if (index >= 0 && index < nombreDeChauffeurs) {
            // Décale tous les chauffeurs suivants vers la gauche pour couvrir l'espace du chauffeur supprimé
            for (int i = index; i < nombreDeChauffeurs - 1; i++) {
                chauffeurs[i] = chauffeurs[i + 1];
            }
            chauffeurs[nombreDeChauffeurs - 1] = null;// Donner la valeur null à le dernier élément du tableau
            nombreDeChauffeurs--; // Décrémente le nombre total de bus
            System.out.println("Chauffeur supprimé avec succès.");
        } else {
            System.out.println("Index de Chauffeur invalide.");
        }
    }

    public void supprimerBus(int index) {
        // Condition pour vérifie si l'index est valide
        if (index >= 0 && index < nombreDeBus) {
            // Décale tous les Bus suivants vers la gauche pour couvrir l'espace du Bus supprimé
            for (int i = index; i < nombreDeBus - 1; i++) {
                busFlotte[i] = busFlotte[i + 1];
            }
            busFlotte[nombreDeBus - 1] = null;// Donner la valeur null à le dernier élément du tableau
            nombreDeBus--; // Décrémente le nombre total de Bus
            System.out.println("Bus supprimé avec succès.");
        } else {
            System.out.println("Index de Bus invalide.");
        }
    }
}
