import java.util.ArrayList;
import java.util.Scanner;

public class Bien {
    private Double superficie;
    private Double prix;
    private String localisation;
    private TypeDeBien typeDeBien;
    private int id;
    private String description;
    private double price;

    public Bien(Double superficie, Double prix, String localisation, String description, TypeDeBien typeDeBien ) {
        this.superficie = superficie;
        this.prix = prix;
        this.localisation = localisation;
        this.typeDeBien = typeDeBien;
        this.price = price;
    }

    public Bien(String typeBien, String localisation2, double superficie2, double prix2) {
        //TODO Auto-generated constructor stub
    }

    // Ajout de la méthode modifierBien 
    public void modifierBien(Double superficie, Double prix, String localisation, String description, TypeDeBien typeDeBien) { 
        this.superficie = superficie; 
        this.prix = prix; 
        this.localisation = localisation;
        this.typeDeBien = typeDeBien;
        this.description = description; 
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
   
    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return typeDeBien.name() + " a "+ localisation + " , superficie : "+ superficie +" m² , prix : " + prix + " da " + " , description : " + description;
        
    }
    

    public void setNom(String nouveauNom) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNom'");
    }

    
   
    public double getPrice() {
        return price;
    }


    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setVisible'");
    }
    
}

   
    

