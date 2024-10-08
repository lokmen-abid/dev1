import java.util.Scanner;

public class Menu {
    private final Compagnie autoRent;

    public Menu() {
        this.autoRent = new Compagnie("AutoRent");
    }

    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("--- MENU -------------------------------");
            System.out.println("1. Gérer les bus");
            System.out.println("2. Gérer les chauffeurs");
            System.out.println("3. Gérer les trajets");
            System.out.println("4. Réservation d'un trajet"); // Asignation -> Chauffeur + Trajet + Bus
            System.out.println("5. Marquer un trajet comme terminé"); // Retire l'assignation du chaffeur et de la plaque d'immatriculation
            System.out.println("6. Ajouter des données fictives");
            System.out.println("0. Quitter");
            System.out.println("----------------------------------------");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    gererBus(scanner);
                    break;
                case 2:
                    gererChauffeurs(scanner);
                    break;
                case 3:
                    gererTrajets(scanner);
                    break;
                case 4:
                    autoRent.reserverBus(scanner);
                    break;
                case 5:
                    marquerTrajetTermine(scanner);
                    break;
                case 6:
                    ajouterDonneesFictives();
                    break;
                case 0:
                    System.out.println("Merci d'avoir choisi AutoRent!");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void gererBus(Scanner scanner) {
        int choix;
        do {
            System.out.println("--- Gestion des BUS --------------------");
            System.out.println("1. Ajouter un bus");
            System.out.println("2. Recherche par Immatriculation");
            System.out.println("3. Afficher tous les bus");
            System.out.println("4. Supprimer un Bus");
            System.out.println("0. Retour au menu principal");
            System.out.println("----------------------------------------");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    autoRent.ajouterBus(Bus.creerBus());
                    break;
                case 2:
                    System.out.println("Entrez l'immatriculation du bus:"); // Demande à l'utilisateur de saisir l'immatriculation du bus
                    String immatriculation = scanner.nextLine();
                    Bus bus = autoRent.trouverBusParImmatriculation(immatriculation); // Cherche le bus correspondant à l'immatriculation saisie
                    if (bus != null) {
                        bus.afficherInformations(); // Affiche les informations du bus trouvé
                    } else {
                        System.out.println("Bus non trouvé avec l'immatriculation: " + immatriculation);
                    }
                    break;
                case 3:
                    autoRent.afficherBus();
                    break;
                case 4:
                    supprimerBus(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void gererChauffeurs(Scanner scanner) {
        int choix;
        do {
            System.out.println("--- Gestion des CHAUFFEURS -------------");
            System.out.println("1. Ajouter un chauffeur");
            System.out.println("2. Recherche par ID");
            System.out.println("3. Afficher tous les chauffeurs");
            System.out.println("4. Supprimer un Chauffeur");
            System.out.println("0. Retour au menu principal");
            System.out.println("----------------------------------------");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    autoRent.ajouterChauffeur(Chauffeur.ajouterChauffeur());
                    break;
                case 2:
                    System.out.println("Entrez l'ID du chauffeur:");// Demande à l'utilisateur de saisir l'ID de chauffeur
                    String id = scanner.nextLine();
                    Chauffeur chauffeur = autoRent.trouverChauffeurParId(id);// Cherche le Chauffeur correspondant à l'ID saisie
                    if (chauffeur != null) {
                        chauffeur.afficherInformationsChauffeur();// Affiche les informations du Chauffeurs trouvé
                    } else {
                        System.out.println("Chauffeur non trouvé avec l'ID: " + id);
                    }
                    break;
                case 3:
                    autoRent.afficherChauffeurs();
                    break;
                case 4:
                    supprimerChauffeur(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void gererTrajets(Scanner scanner) {
        int choix;
        do {
            System.out.println("--- Gestion des TRAJETS ----------------");
            System.out.println("1. Ajouter un trajet");
            System.out.println("2. Afficher les trajets d'un chauffeur");
            System.out.println("3. Afficher tous les trajets");
            System.out.println("4. Supprimer un trajet");
            System.out.println("0. Retour au menu principal");
            System.out.println("----------------------------------------");
            System.out.print("Votre choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    autoRent.ajouterTrajet(Trajet.creerTrajet(scanner));
                    break;
                case 2:
                    afficherTrajetsChauffeur(scanner);
                    break;
                case 3:
                    afficherTousLesTrajets();
                    break;
                case 4:
                    supprimerTrajet(scanner);
                    break;
                case 0:
                    System.out.println("Retour au menu principal...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    private void afficherTousLesTrajets() {
        autoRent.afficherTrajets();
    }

    private void afficherTrajetsChauffeur(Scanner scanner) {
        System.out.println("ID du chauffeur:");
        String idChauffeur = scanner.nextLine();
        Chauffeur chauffeur = autoRent.trouverChauffeurParId(idChauffeur); // Trouve le chauffeur correspondant à l'ID saisi
        if (chauffeur != null) {
            // Si le chauffeur est trouvé , condition pour vérifie si le chauffeur a des trajets assignés
            if (chauffeur.getNombreDeTrajets() > 0) {
                chauffeur.afficherTrajets(); // Affiche les trajets du chauffeur
            } else {
                System.out.println("Aucun trajet pour ce chauffeur."); // On avait une ligne vide, ce sera plus clair qu'il n'a pas de trajet
            }
        } else {
            System.out.println("Chauffeur non trouvé avec l'ID: " + idChauffeur);
        }
    }
    private void supprimerBus(Scanner scanner) {
        System.out.println("Entrez le numéro du Bus à supprimer:");
        int indexBus = scanner.nextInt();
        scanner.nextLine();
        autoRent.supprimerBus(indexBus);
    }

    private void supprimerChauffeur(Scanner scanner) {
        System.out.println("Entrez le numéro du Chauffeur à supprimer:");
        int indexChauffeur = scanner.nextInt();
        scanner.nextLine();
        autoRent.supprimerChauffeur(indexChauffeur);
    }

    private void supprimerTrajet(Scanner scanner) {
        System.out.println("Entrez le numéro du trajet à supprimer:");
        int indexTrajet = scanner.nextInt();
        scanner.nextLine();
        autoRent.supprimerTrajet(indexTrajet);
    }

    private void marquerTrajetTermine(Scanner scanner) {
        System.out.println("ID du chauffeur:");// Demande à l'utilisateur de saisir l'ID du chauffeur
        String idChauffeur = scanner.nextLine();
        Chauffeur chauffeur = autoRent.trouverChauffeurParId(idChauffeur); // Trouver le chauffeur
        if (chauffeur != null) {
            // Si le chauffeur est trouvé , affiche les trajets assignés pour lui
            System.out.println("Trajets assignés:");
            chauffeur.afficherTrajets();
            System.out.println("Entrez le numéro du trajet à marquer comme terminé:");
            int indexTrajet = scanner.nextInt();// Demande à l'utilisateur de saisir le numéro du trajet à marquer comme terminé
            scanner.nextLine();
            chauffeur.marquerTrajetTermine(indexTrajet - 1); // -1 pour correspondre à l'index correct
        } else {
            System.out.println("Chauffeur non trouvé avec l'ID: " + idChauffeur);
        }
    }

    // Ajout de données fictives pour faciliter les tests
    private void ajouterDonneesFictives() {
        Chauffeur chauffeur1 = new Chauffeur("Frédérick", "Pérusse", 35, 2015, new String[]{"123", "Avenue A", "Trois-Rivières", "QC", "GGG 111", "Canada"}, null);
        autoRent.ajouterChauffeur(chauffeur1);
        Chauffeur chauffeur2 = new Chauffeur("Ahmed", "Ammar", 20, 2018, new String[]{"456", "Avenue B", "Trois-Rivières", "QC", "GGG 222", "Canada"}, null);
        autoRent.ajouterChauffeur(chauffeur2);
        Chauffeur chauffeur3 = new Chauffeur("Lokmen", "Abid", 24, 2016, new String[]{"789", "Avenue C", "Trois-Rivières", "QC", "GGG 333", "Canada"}, null);
        autoRent.ajouterChauffeur(chauffeur3);

        Bus bus1 = new Bus("F0Y123", 50.0, 40, "Bleu");
        autoRent.ajouterBus(bus1);
        Bus bus2 = new Bus("F0Y345", 60.0, 50, "Rouge");
        autoRent.ajouterBus(bus2);
        Bus bus3 = new Bus("F0Y789", 70.0, 60, "Jaune");
        autoRent.ajouterBus(bus3);

        Trajet trajet1 = new Trajet("Montréal", "Québec", 1000, 1250);
        Trajet trajet2 = new Trajet("Québec", "Ottawa", 1000, 1500);
        Trajet trajet3 = new Trajet("Trois-Rivières", "Montréal", 1000, 1120);

        autoRent.ajouterTrajet(trajet1);
        autoRent.ajouterTrajet(trajet2);
        autoRent.ajouterTrajet(trajet3);

        System.out.println("Données fictives ajoutées.");
    }
}