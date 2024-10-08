import java.util.Scanner;

public class Chauffeur {
    private String prenom; // Prenom de chauffeur
    private String nom; // Nom de chauffeur
    private int age; // Age de chauffeur
    private final int anneeEmbauche; // Année d'embauche de chauffeur
    private final String idChauffeur; // identificateur de chauffeur
    private String[] adresseComplete = new String[6]; // Utilisation d'un tableau pour l'adresse complète
    private Trajet[] trajets; // Les trajets parcourit de chauffeur
    private int nombreDeTrajets; // Compteur du nombre de trajets

    // Constructeur
    public Chauffeur(String prenom, String nom, int age, int anneeEmbauche, String[] adresseComplete, Trajet[] trajets) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.anneeEmbauche = anneeEmbauche;
        this.adresseComplete = adresseComplete;
        this.idChauffeur = generateurId(); // on applique la méthode geneateurId pour assigner la valeur
        this.trajets = trajets != null ? trajets : new Trajet[10]; // Si trajets est null, initialise nouveau tableau de taille 10.
        this.nombreDeTrajets = trajets != null ? trajets.length : 0; // Si tableau trajets est null, initialise nombreDeTrajets à 0.
    }

    // Getters & Setters
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String[] getAdresseComplete() {
        return adresseComplete;
    }
    public String getIdChauffeur() {
        return idChauffeur;
    }
    // Getter pour le nombre de trajets
    public int getNombreDeTrajets() {
        return nombreDeTrajets;
    }

    // Méthode pour générer l'identifiant du chauffeur
    public  String generateurId() {
        // nom -> 4 premiers characteres
        String nomId;
        if ( this.nom != null && this.nom.length() > 4) { // Validation
            nomId = this.nom.substring(0, 4); // Substring -> 4 première lettres
        } else {
            nomId = this.nom; // Si plus petit que 4 on prend tout le nom
        }
        // Prenom -> 1er charactere
        String prenomId;
        if (this.prenom != null) { // Validation
            prenomId = this.prenom.substring(0, 1); // Substring -> première lettres
        } else {
            prenomId = "";
        }
        // Année embauche -> 2 derniers charactere
        String anneeEmbaucheId;
        String anneeEmbaucheString = Integer.toString(anneeEmbauche); // Converti l'int en String pour utiliser substring
        if (anneeEmbaucheString.length() > 2) { // Validation
            anneeEmbaucheId = anneeEmbaucheString.substring(anneeEmbaucheString.length() - 2); // Substring -> 2 derniers caractères
        } else {
            anneeEmbaucheId = anneeEmbaucheString;
        }

        return nomId + prenomId + anneeEmbaucheId; // Concaténation des 3 chaines
    }

    public static String[] setAdresseComplete(Scanner scanner) {
        // Crée un tableau de chaînes de caractères de taille 6 pour stocker les éléments de l'adresse complète
        String[] adresseComplete = new String[6];
        // Demande à l'utilisateur de saisir les attribus de l'adreese et Enregistrer chaque attribut dans une position dans le tableau
        System.out.println("Numéro civique:");
        adresseComplete[0] = scanner.nextLine();
        System.out.println("Nom de la rue:");
        adresseComplete[1] = scanner.nextLine();
        System.out.println("Ville:");
        adresseComplete[2] = scanner.nextLine();
        System.out.println("Province:");
        adresseComplete[3] = scanner.nextLine();
        System.out.println("Code postal:");
        adresseComplete[4] = scanner.nextLine();
        System.out.println("Pays:");
        adresseComplete[5] = scanner.nextLine();

        // Retourne le tableau contenant les éléments de l'adresse complète
        return adresseComplete;
    }

    // Méthode pour ajouter un trajet
    public void ajouterTrajet(Trajet trajet) {
        // Condition pour vérifie si le tableau 'trajets' est plein
        if (nombreDeTrajets == trajets.length) {
            Trajet[] nouveauxTrajets = new Trajet[trajets.length * 2];
            // Copie les éléments du tableau actuel 'trajets' dans le nouveau tableau 'nouveauxTrajets'
            System.arraycopy(trajets, 0, nouveauxTrajets, 0, trajets.length);
            trajets = nouveauxTrajets;
        }
        // Ajouter le nouveau trajet à la fin du tableau 'trajets'
        trajets[nombreDeTrajets] = trajet;
        nombreDeTrajets++; // Incrémenter le compteur 'nombreDeTrajets' après l'ajout du nouveau trajet

    }

    // Méthode pour afficher tous les trajets du chauffeur avec les indices
    public void afficherTrajets() {
        System.out.println("----------------------------------------");
        // Boucle sur le tableau 'trajets'
        for (int i = 0; i < nombreDeTrajets; i++) {
            System.out.println("Trajet " + (i + 1) + ":");// Affiche l'indice du trajet actuel (+1 pour une meilleure lisibilité)
            trajets[i].afficherTrajetCaracteristique(); // Appelle la méthode 'afficherTrajetCaracteristique' sur le trajet actuel pour afficher ses caractéristiques
            System.out.println("----------------------------------------");
        }
    }

    // Méthode pour marquer un trajet comme terminé
    public void marquerTrajetTermine(int index) {
        // Condition pour vérifie si l'index fourni est valide
        if (index >= 0 && index < nombreDeTrajets) {
            // Enregistrer le trajet dans nouvelle variable 'trajetTermine
            Trajet trajetTermine = trajets[index];
            trajetTermine.setBusUtilise(null); // Enlever l'autobus assigné au trajet

            // Déplace tous les trajets suivants d'une position vers l'avant
            for (int i = index; i < nombreDeTrajets - 1; i++) {
                trajets[i] = trajets[i + 1];
            }
            trajets[nombreDeTrajets - 1] = null; // Assigner la valeur null pour le dernier élément de tableau
            nombreDeTrajets--; // Décrémente le compteur nombreDeTrajets
            System.out.println("Trajet marqué comme terminé et retiré de la liste.");
        } else {
            System.out.println("Index de trajet invalide.");//Si l'index est invalide, affiche un message d'erreur

        }
    }

    // Méthode pour ajouter un chauffeur avec une saisie clavier par l'utilisateur
    public static Chauffeur ajouterChauffeur() {
        // Crée une instance de Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer les informations du chauffeur");
        // Demande à l'utilisateur de saisir les attribus de Chauffeur et l'enregistrer dans les variables avec vérification sur chaque attribut
        System.out.println("Prénom:");
        String prenom = scanner.nextLine();
        while (!Exception.chaineContientQueDesLettres(prenom)) {
            System.out.println("Prénom:");
            prenom = scanner.nextLine();
        }
        System.out.println("Nom:");
        String nom = scanner.nextLine();
        while (!Exception.chaineContientQueDesLettres(nom)) {
            System.out.println("Nom:");
            nom = scanner.nextLine();
        }
        int age = -1;
        boolean valid = false;

        while (!valid) {
            System.out.println("Âge:");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age < 0) {
                    System.out.println("Veuillez entrer un âge positif.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
        int anneeEmbauche = -1;
        valid = false;

        while (!valid) {
            System.out.println("Année d'embauche:");
            try {
                anneeEmbauche = Integer.parseInt(scanner.nextLine());
                if (anneeEmbauche < 0) {
                    System.out.println("Veuillez entrer une année valide (positive).");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier pour l'année.");
            }
        }
        scanner.nextLine();
        String[] adresseComplete = setAdresseComplete(scanner); // Appelle méthode setAdresseComplete pour saisir l'adresse

        // Créer une nouvelle instance de Chauffeur avec les informations fournies par l'utilisateur
        Chauffeur chauffeur = new Chauffeur(prenom, nom, age, anneeEmbauche, adresseComplete, null);

        // Boucle pour demander à l'utilisateur s'il veut ajouter des trajets au chauffeur
        while (true) {
            System.out.println("Voulez-vous ajouter un trajet à ce chauffeur ? (oui/non)"); // Plus de clareté, on assigne le trajet à la création
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                // Si la réponse est "oui", appelle la méthode 'creerTrajet' pour créer un trajet et l'ajouter au chauffeur
                chauffeur.ajouterTrajet(Trajet.creerTrajet(scanner));
            } else {
                break;
            }
        }
        // Retourne le chauffeur créé
        return chauffeur;
    }

    // Méthode pour afficher les informations du chauffeur
    public void afficherInformationsChauffeur() {
        System.out.println("----------------------------------------");
        System.out.println("ID: " + idChauffeur);
        System.out.println("Prénom: " + prenom);
        System.out.println("Nom: " + nom);
        System.out.println("Âge: " + age);
        System.out.println("Année d'embauche: " + anneeEmbauche);
        System.out.println("Adresse complète: " + adresseComplete[0] + " " + adresseComplete[1] + ", " + adresseComplete[2] + ", " + adresseComplete[3] + ", " + adresseComplete[4] + ", " + adresseComplete[5]);
        if (nombreDeTrajets > 0) {
            System.out.println("Trajets:");
            afficherTrajets();// Appelle la méthode 'afficherTrajets' pour afficher les trajets de chaque Chauffeur
        } else {
            System.out.println("Trajets: Aucun");
        }
    }
}
