import java.time.LocalDateTime;

public class RendezVous {
    private Bien bien;
    private LocalDateTime dateHeure;

    public RendezVous(Bien bien, LocalDateTime dateHeure) {
        this.bien = bien;
        this.dateHeure = dateHeure;
    }

    public RendezVous(String agentNom, String agentPrenom, String date, String heure) {
        //TODO Auto-generated constructor stub
    }

    public RendezVous(String agentNom, String agentPrenom, LocalDateTime dateHeure2) {
        //TODO Auto-generated constructor stub
    }

    public Bien getBien() {
        return bien;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "bien=" + bien +
                ", dateHeure=" + dateHeure +
                '}';
    }

    public void add(RendezVous rendezVous) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}