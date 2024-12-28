import java.util.ArrayList;
import java.util.List;


public class Agents {
    private final List<Bien> biens;
    private String nom;
    private String prenom;


    public Agents(String nom, String prenom) {
        this.biens = new ArrayList<>();
        this.nom = nom;
        this.prenom = prenom;
    }

    public void ajouterBien(Bien bien) {
        biens.add(bien); // Ajout du bien à la liste
    }

    public void afficherBiens( )  {

        if (biens.isEmpty()) {
            System.out.println("Aucun bien affecté.");
        } else {
            for (Bien bien : biens) {
                System.out.println(bien); 
            }
        }
    }

    // Getters pour le nom et le prénom
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    /*public Bien getBien(int index) {
        return biens.get(index);
    }*/

    
}
