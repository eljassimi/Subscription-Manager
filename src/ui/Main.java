package ui;

import entity.*;
import service.AbonnementService;
import service.LoggerService;
import service.PaiementService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
                System.out.println("8. Consulter paiements manqués");
                System.out.println("9. Consulter Montant total impayé");
                System.out.println("10. Afficher somme payée d'un abonnement");
                System.out.println("11. Afficher les 5 derniers paiements");
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
                         UpdateAbonnement();
                            break;
                        case "4":
                         DeleteAbonnement();
                            break;
                        case "5":
                            EnregistrerPaiement();
                            break;
                        case "6":
                            ModifierPaiement();
                            break;
                        case "7":
                            SupprimerPaiement();
                            break;
                        case "8":
                            DetectImpyees();
                            break;
                        case "9":
                            MontantImpyees();
                            break;
                        case "10":
                            Montantpyees();
                            break;
                        case "11":
                            LastFivePaiement();
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

                System.out.print("Statut (ACTIVE/SUSPENDU/RESILIE): ");
                String statut = sc.nextLine().toUpperCase();

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

                    AbonnementAvecEngagement a = new AbonnementAvecEngagement(nomService, montantMensuel, dateDebut, StatutAbonnement.valueOf(statut),dure);
                    abonnementService.Enregistrer(a);
                    LoggerService.log("Creation abonnement avec engagement: " + a.getNomService());
                }else{
                    Abonnement a = new Abonnement(nomService, montantMensuel, dateDebut, StatutAbonnement.valueOf(statut));
                    abonnementService.Enregistrer(a);
                    LoggerService.log("Creation abonnement sans engagement: " + a.getNomService());
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

            public static void UpdateAbonnement() throws Exception {
                System.out.println("Entrer le id de L'abonnement : ");
                String id = sc.nextLine();
                System.out.println("Entrer les nouvaux informations : \n");

                System.out.print("Nom du service: ");
                String nomService = sc.nextLine();

                System.out.print("Montant mensuel: ");
                double montantMensuel = Double.parseDouble(sc.nextLine());

                System.out.print("Date de debut (yyyy-mm-dd): ");
                LocalDate dateDebut = LocalDate.parse(sc.nextLine());

                System.out.print("Statut (ACTIVE/SUSPENDU/RESILIE): ");
                String statut = sc.nextLine().toUpperCase();

                String type;
                do {
                    System.out.print("Cet abonnement est-il avec engagement (A) ou sans engagement (S) ? ");
                    type = sc.nextLine().trim().toUpperCase();
                    if (!type.equals("A") && !type.equals("S")) {
                        System.out.println("Choix invalide, Veuillez saisir 'A' pour Avec engagement ou 'S' pour Sans engagement.");
                    }
                } while (!type.equals("A") && !type.equals("S"));


                if (type.equals("A")){
                    System.out.println("Enter la durée du contract (Mois) : ");
                    int dure = sc.nextInt();

                    AbonnementAvecEngagement a = new AbonnementAvecEngagement();
                    a.setNomService(nomService);
                    a.setMontantMensuel(montantMensuel);
                    a.setDateDebut(dateDebut);
                    a.setStatut(StatutAbonnement.valueOf(statut));
                    a.setDureeEngagementMois(dure);
                    abonnementService.update(a,id);
                    LoggerService.log("Update abonnement avec engagement: " + a.getNomService());
                }else{
                    Abonnement a = new Abonnement();
                    a.setNomService(nomService);
                    a.setMontantMensuel(montantMensuel);
                    a.setDateDebut(dateDebut);
                    a.setStatut(StatutAbonnement.valueOf(statut));
                    abonnementService.update(a,id);
                    LoggerService.log("Update abonnement sans engagement: " + a.getNomService());
                }
                System.out.println("Abonnement Modifier avec succes !");
            }

            public static void DeleteAbonnement()throws Exception{
                System.out.println("Entrer l'id de l'abonnement : ");
                String id = sc.nextLine();
                abonnementService.delete(id);
                LoggerService.log("Delete abonnement de ce id :  " +id);
                System.out.println("Abonnement Delete by succes !");
            }

            public static void EnregistrerPaiement() throws Exception {
                System.out.println("--- Enregistrer un nouveau Paiement ---");

                System.out.print("ID abonnement : ");
                String abonnementId = sc.nextLine();

                Abonnement abonnement = abonnementService.findById(abonnementId)
                        .orElseThrow(() -> new Exception("Abonnement introuvable !"));

                LocalDate prochaineEcheance = abonnementService.genererEcheances(abonnement).stream()
                        .filter(date -> !date.isBefore(LocalDate.now()))
                        .findFirst()
                        .orElseThrow(() -> new Exception("Aucune Echeance Detecté (Abonnement Termine)" ));

                System.out.println("Echeance Generé : " + prochaineEcheance);

                System.out.print("Date de Paiement (yyyy-mm-dd) : ");
                LocalDate datePaiement = LocalDate.parse(sc.nextLine());

                System.out.print("Type de paiement : ");
                String type = sc.nextLine();

                System.out.print("Statut (PAYE, NON_PAYE, EN_RETARD) : ");
                StatutPaiement statut = StatutPaiement.valueOf(sc.nextLine().toUpperCase());

                Paiement paiement = new Paiement(abonnementId, prochaineEcheance, datePaiement, type, statut);
                paiementService.Enregistrer(paiement);

                LoggerService.log("Paiement enregistré pour abonnement ID " + abonnementId + " | Montant: " + abonnement.getMontantMensuel() + " | Statut: " + statut);
                System.out.println("Paiement enregistré !");
            }

            public static void ModifierPaiement() throws Exception {
                System.out.println("--- Modifier un Paiement ---");

                System.out.print("ID du paiement à modifier : ");
                String paiementId = sc.nextLine();


                Paiement paiement = paiementService.findById(paiementId);
                System.out.println("Paiement actuel : " + paiement);

                System.out.print("Nouvelle date de paiement (yyyy-mm-dd) [" + paiement.getDatePaiement() + "] : ");
                String datePaiementStr = sc.nextLine();
                paiement.setDatePaiement(LocalDate.parse(datePaiementStr));


                System.out.print("Nouveau type de paiement [" + paiement.getTypePaiement() + "] : ");
                String typePaiement = sc.nextLine();
                paiement.setTypePaiement(typePaiement);


                System.out.print("Nouveau statut (PAYE, NON_PAYE, EN_RETARD) [" + paiement.getStatut() + "] : ");
                String statutStr = sc.nextLine();
                paiement.setStatut(StatutPaiement.valueOf(statutStr.toUpperCase()));

                paiementService.Update(paiement,paiementId);
                LoggerService.log("Paiement modifier de ce ID "+ paiementId);

                System.out.println("Paiement modifié avec succès !");
            }

            public static void SupprimerPaiement() throws Exception {
                System.out.println("--- Supprimer un Paiement ---");

                System.out.print("ID du paiement à supprimer : ");
                String paiementId = sc.nextLine();
                paiementService.Delete(paiementId);
                LoggerService.log("Paiement Supprimer de ce ID "+ paiementId);
                System.out.println("Paiement a Supprimer avec Succes ! ");
            }

            public static void DetectImpyees() throws Exception {
                   paiementService.detecterImpayes().stream().forEach(System.out::println);
            }
            public static void MontantImpyees() throws Exception{
                System.out.println("ID du Abonnement : ");
                String AbonnementID = sc.nextLine();
                System.out.println("Montant Total Impyee est  : "+paiementService.montantTotalImpayesParAbonnement(AbonnementID));
            }

            public static void Montantpyees()throws Exception{
                System.out.println("ID du Abonnement : ");
                String AbonnementID = sc.nextLine();
                System.out.println("Montant Total payée est  : "+paiementService.montantTotalpayesParAbonnement(AbonnementID));
            }

            public static void LastFivePaiement() throws Exception {
             System.out.println("--- List of the last 5 paiements ---");
             paiementService.GetLastFivePaiement().forEach(System.out::println);
            }
}