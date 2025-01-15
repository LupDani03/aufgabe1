import java.util.Date;

public class PazientenFallakten {
    int id;
    String patient;
    String symptom;
    String diagnose;
    String datum;
    String Krankenhaus;

    public PazientenFallakten(int id, String patient, String symptom, String diagnose, String datum, String Krankenhaus) {
        this.id = id;
        this.patient = patient;
        this.symptom = symptom;
        this.diagnose = diagnose;
        this.datum = datum;
        this.Krankenhaus = Krankenhaus;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + patient + ", Symptom: " + symptom + ", Diagnose: " + diagnose + ", Datum: " + datum + ", Krankenhaus: " + Krankenhaus;
    }

    public int compareTo(PazientenFallakten o) {
        return o.datum.compareTo(this.datum);
    }
}
