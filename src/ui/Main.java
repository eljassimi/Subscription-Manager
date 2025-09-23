package ui;

import entity.Abonnement;
import entity.AbonnementAvecEngagement;
import entity.AbonnementSansEngagement;
import entity.StatutAbonnement;
import service.AbonnementService;
import service.PaiementService;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {
        public static AbonnementService abonnementService = new AbonnementService();
        public static PaiementService paiementService = new PaiementService();
            private static Scanner sc = new Scanner(System.in);
        public static void main (String[]args){
            while (true) {
                System.out.println("\n--- Subscription Manager ---");
                System.out.println("1. Créer abonnement");
                System.out.println("2. Lister abonnements");
                System.out.println("3. Modifier abonnement");
                System.out.println("4. Supprimer abonnement");
                System.out.println("5. Enregistrer paiement");
                System.out.println("6. Modifier paiement");
                System.out.println("7. Supprimer paiement");
                System.out.println("8. Consulter paiements manqués et montant total impayé");
                System.out.println("9. Afficher somme payée d'un abonnement");
                System.out.println("10. Afficher les 5 derniers paiements");
                System.out.println("0. Quitter");
                System.out.print("Choix: ");
                String choice = sc.nextLine();
                try {
                    switch (choice) {
                        case "1":
                        CreateAbonnement();
                        break;
                        case "2":
                         DisplayAllAbonnements();
                            break;
                        case "3":
                            break;
                        case "4":
                            break;
                        case "0":
                            System.exit(0);
                        default:
                            System.out.println("Choix invalide");
                    }
                } catch (Exception e) {
                    System.err.println("Erreur: " + e.getMessage());
                }
            }
        }

        public static void CreateAbonnement() throws Exception {
                System.out.println("--- Creation un nouvel abonnement ---");

                System.out.print("Nom du service: ");
                String nomService = sc.nextLine();

                System.out.print("Montant mensuel: ");
                double montantMensuel = Double.parseDouble(sc.nextLine());

                System.out.print("Date de debut (yyyy-mm-dd): ");
                LocalDate dateDebut = LocalDate.parse(sc.nextLine());

                System.out.print("Date de fin (yyyy-mm-dd): ");
                LocalDate dateFin = LocalDate.parse(sc.nextLine());

                System.out.print("Statut (ACTIVE/SUSPENDU/RESILIE): ");
                String statut = sc.nextLine();

            String type;
            do {
                System.out.print("Cet abonnement est-il avec engagement (A) ou sans engagement (S) ? ");
                type = sc.nextLine().trim().toUpperCase();
                if (!type.equals("A") && !type.equals("S")) {
                    System.out.println("Choix invalide, Veuillez saisir 'A' pour Avec engagement ou 'S' pour Sans engagement.");
                }
            } while (!type.equals("A") && !type.equals("S"));


            if (type.equals("A")){
                    System.out.println("Enter la durée du contract : ");
                    int dure = sc.nextInt();

                    AbonnementAvecEngagement a = new AbonnementAvecEngagement();
                    a.setNomService(nomService);
                    a.setMontantMensuel(montantMensuel);
                    a.setDateDebut(dateDebut);
                    a.setDateFin(dateFin);
                    a.setStatut(StatutAbonnement.valueOf(statut));
                    a.setDureeEngagementMois(dure);
                    abonnementService.Enregistrer(a);
                }else{
                    Abonnement a = new Abonnement();
                    a.setNomService(nomService);
                    a.setMontantMensuel(montantMensuel);
                    a.setDateDebut(dateDebut);
                    a.setDateFin(dateFin);
                    a.setStatut(StatutAbonnement.valueOf(statut));
                    abonnementService.Enregistrer(a);
                }

                System.out.println("Abonnement cree avec succes !");
            }


            public static void DisplayAllAbonnements() throws Exception{
                abonnementService.findAll().stream().forEach(a->{
                    System.out.println("Nom du Service : "+a.getNomService());
                    System.out.println("Montant Mensuel : "+a.getMontantMensuel());
                    System.out.println("Date debut : "+a.getDateDebut());
                    System.out.println("Date de fin : "+a.getDateFin());
                    System.out.println("Status  : "+a.getStatut());
                    System.out.println("\n------------------------\n");
                });
            }

        }