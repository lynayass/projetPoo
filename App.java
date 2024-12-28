import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner; 



public class App {
private static final List<Agents> agents = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        boolean v = true ;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Bien> biens = new ArrayList<>();
        
        while (v == true) {
            System.out.println("Bonjour! que vouliez vous faire : \n 1-creer\n 2-modifier \n 3-afficher \n 4-supprimer \n 5-rechercher \n 6-affecter \n 7-quitter\n ");

            int index = scanner.nextInt();
            switch (index) {
                case 1:
                    creerBiens(biens);
                    break;
                case 2:
                    modiffierBien(biens);
                    break; 
                case 3:
                    afficherBiens(biens);
                    break;
                case 4:
                    supprimerBien(biens);
                    break;
                case 5:
                rechercherBien(biens);
                    break;    
                    
                case 6:
                affecterBien(biens);
                    break;
                
                case 7:
                    v = false;
                    System.out.println("Merci d'avoir utilisé notre application.");
                    break;   
                default:
                    System.out.println("erreur chois!!");
            }
        }
        
    }
      
    // Méthode pour afficher tous les biens 
    public static void afficherBiens(ArrayList<Bien> biens) { 
        if (biens.isEmpty()) { 
            System.out.println("Aucun bien disponible."); 
        } else { 
            System.out.println("Les biens : "); 
            for (int i = 0; i < biens.size(); i++) { 
                System.out.println(i+1 + ": " + biens.get(i)); 
            } 
        } 
    }

    public static void creerBiens( ArrayList<Bien> biens   ){
        Scanner scanner= new Scanner(System.in);
        System.out.print("enterez la localisatin : ");
         String localisation = scanner.nextLine();

         System.out.print("enterez la descripton : ");
         String description = scanner.nextLine();

         System.out.print("enterez la superficie : ");
         Double superficie = scanner.nextDouble();

         System.out.print("enterez le prix : ");
         Double prix = scanner.nextDouble();
         
         // Nettoyer le buffer 
         scanner.nextLine();

         System.out.print("choisisez le type : \n 1- villa \n 2- appart \n 3- locale \n ");
         int choitype = scanner.nextInt();

        TypeDeBien typeDeBien ;

        switch (choitype){ 
        case 1: 
            typeDeBien = TypeDeBien.VILLA;
            break;
        case 2: 
            typeDeBien = TypeDeBien.APPARTE;
            break; 
        case 3:
             typeDeBien = TypeDeBien.LOCALE;
            break; 
        default:
             typeDeBien = TypeDeBien.VILLA; 
            break; 
        }


       Bien bien = new Bien(superficie, prix, localisation, description, typeDeBien) ;
       biens.add(bien);
      // Bien villa1 = new Bien(200, 10000 , "Bouzareah", "belle villa", TypeDeBien.VILLA);
      // Bien appart = new Bien(100, 5000, "Alger", "belle appart", TypeDeBien.APPARTE);
       //System.out.println("ma villa " + villa1);
       //System.out.println("mon appart " + appart);
       System.out.println( "mon bien est :" + bien );
    }
    
    public static void modiffierBien( ArrayList<Bien> biens  ){
        Scanner scanner= new Scanner(System.in);
        afficherBiens(biens);
        System.out.print("Entrez l'index du bien à modifier : "); 
        int index = scanner.nextInt(); 
        if (index >= 0 && index < biens.size()) { 
        Bien bien = biens.get(index);
        System.out.print("que souhaiter vous modifier:\n 1- Localisation \n 2- Superfisie \n 3- Prix \n"); 
        int chois = scanner.nextInt();
        scanner.nextLine();//consomme le saut de la ligne 
        switch (chois) {
            case 1 : //voir
                System.out.println("enter la nouvelle localisation :\n");
                String localisation =  scanner.nextLine(); 
                bien.setLocalisation(localisation);
                break;
            case 2 :
                System.out.println("enter la nouvelle Superficie :\n");
                Double superficie =  scanner.nextDouble(); 
                bien.setSuperficie(superficie);
                break;
            case 3 :
                System.out.println("enter le nouveau prix :\n");
                Double prix =  scanner.nextDouble(); 
                bien.setPrix(prix);
                break;
            default:
            System.out.println("erreur du chois!!");
        }
    } else{
        System.out.println("erreur du chois!!");
    }
}
    public static void supprimerBien(ArrayList<Bien> biens ){
        Scanner scanner= new Scanner(System.in);
        afficherBiens(biens);
        System.out.print("Entrez l'index du bien à supprimer : "); 
        int index = scanner.nextInt(); 
        if (index >= 0 && index < biens.size()) { 

        scanner.nextLine(); 
        biens.remove(index);

    }else{
        System.out.println("erreur du chois!!");
    }
}



    //pour rechercher un bien

    public static void rechercherBien(ArrayList<Bien> biens) {
        List<Bien> biensTrouves = new ArrayList<>(); // Create a new list to store found properties
    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le type du bien à rechercher (ouo tapez 'entrer' pour ignorer) : ");
        String type = scanner.nextLine();

        System.out.print("Entrez la localisation du bien à rechercher (ou tapez 'entrer' pour ignorer)  : ");
        String localisation = scanner.nextLine();
        System.out.print("Entrez le prix du bien à rechercher (ou tapez 'entrer' pour ignorer) : ");
        String prixInput = scanner.nextLine();
        System.out.print("Entrez la superficie du bien à rechercher (ou tapez 'entrer' pour ignorer) : ");
        String superficieInput = scanner.nextLine();
        



        Double prix = prixInput.equalsIgnoreCase("") ? null : Double.valueOf(prixInput);
        Double superficie = superficieInput.equalsIgnoreCase("") ? null : Double.valueOf(superficieInput);
    
        System.out.println("Recherche avec localisation : " + (localisation.equalsIgnoreCase("ignorer") ? "ignorer" : localisation) +
                           ", prix : " + (prix == null ? "ignorer" : prix) +
                           ", superficie : " + (superficie == null ? "ignorer" : superficie));
    
        for (Bien bien : biens) {
            boolean matches = true; // Flag to check if the bien matches the search criteria
    
            if (!localisation.equalsIgnoreCase("") && !bien.getLocalisation().equalsIgnoreCase(localisation)) {
                matches = false; // Does not match localisation
            }
            if (prix != null && !bien.getPrix().equals(prix)) {
                matches = false; // Does not match price
            }
            if (superficie != null && !bien.getSuperficie().equals(superficie)) {
                matches = false; // Does not match superficie
            }
    
            if (matches) {
                biensTrouves.add(bien); // Add to found list if it matches all criteria
            }
        }
    
        if (biensTrouves.isEmpty()) {
            System.out.println("Aucun bien trouvé.");
        } else {
            for (Bien bien : biensTrouves) {
                System.out.println(bien);
            }
        }
    }

   public static void affecterBien(ArrayList<Bien> biens) { 
   Scanner scanner = new Scanner(System.in); 
   System.out.println("Entrez le nom de l'agent : "); 
   String nom = scanner.nextLine(); 
   System.out.println("Entrez le prénom de l'agent : "); 
   String prenom = scanner.nextLine(); 
   Agents agent = new Agents(nom, prenom); 
   agents.add(agent); 
   System.out.println("Agent ajouté."); 
   System.out.println("Entrez la position du bien à affecter : "); 
  try {
    int inputIndex = scanner.nextInt();
    int position = inputIndex - 1;

    if (position >= 0 && position < biens.size()) {
        Bien bien = biens.get(position);
        agent.ajouterBien(bien);
        System.out.println("Bien affecté.");
        //System.out.println("Les biens de l'agent  " + agent.getNom() + " " + agent.getPrenom() + " : \n");
        //agent.afficherBiens();
    } else {
        System.out.println("Position invalide !");
    }
} catch (InputMismatchException e) {
    System.out.println("Veuillez entrer un nombre valide.");
    scanner.next(); // Consomme l'entrée invalide
}
}
}   




