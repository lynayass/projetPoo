import java.util.ArrayList;
import java.util.Scanner;

public class Bien {
    private Double superficie;
    private Double prix;
    private String localisation;
    private TypeDeBien typeDeBien;
    

    public Bien(Double superficie, Double prix, String localisation, String description, TypeDeBien typeDeBien ) {
        this.superficie = superficie;
        this.prix = prix;
        this.localisation = localisation;
        this.typeDeBien = typeDeBien;
        
    }

    // Ajout de la méthode modifierBien 
    public void modifierBien(Double superficie, Double prix, String localisation, String description, TypeDeBien typeDeBien) { 
        this.superficie = superficie; 
        this.prix = prix; 
        this.localisation = localisation;
        this.typeDeBien = typeDeBien; 
    }

    //ajout de la methode supprimerBien
    public void supprimerBien(Scanner scanner, ArrayList<Bien> biens) {
    System.out.print("Entrez l'index du bien à supprimer : ");
    int index = scanner.nextInt();
    if (index >= 0 && index < biens.size()) {
        biens.remove(index);
        System.out.println("Bien supprimé.");
    } else {
        System.out.println("Index invalide !");
    }
}
    public void setLocalisation(String localisation ){
        this.localisation = localisation;


    }

    public void setSuperficie( Double superficie ){
        this.superficie = superficie;
        

    }
    public void setPrix( Double prix ){
        this.prix = prix;
        
    }

    public String getLocalisation(){
        return localisation;
    }

    public Double getSuperficie(){
        return superficie;
    }

    public Double getPrix(){
        return prix;
    }

    public TypeDeBien getTypeDeBien(){
        return typeDeBien;
    }
   

    @Override
    public String toString(){
        return typeDeBien.name() + " a "+ localisation + " , superficie : "+ superficie +" m² , prix : " + prix + " da " ;
        
    }
}
