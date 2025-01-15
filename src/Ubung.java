import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Ubung {
    public static void main(String[] args) {
        String filepath = "fallakten.tsv";
        List<PazientenFallakten> patientList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filepath));
            String firstLine = lines.get(0);
            for (String line : lines) {
                if (line != firstLine) {
                    String[] daten = line.split("\t");
                    int id = Integer.parseInt(daten[0]);
                    String patient = daten[1];
                    String symptom = daten[2];
                    String diagnose = daten[3];
                    String datum = daten[4];
                    String krankenhaus = daten[5];

                    PazientenFallakten neuesObjekt = new PazientenFallakten(id, patient, symptom, diagnose, datum, krankenhaus);
                    patientList.add(neuesObjekt);
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim lesen der Datei" + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Fehler beim Parsen der Datei" + e.getMessage());
        }

//        for (PazientenFallakten objekt:patientList){
//            System.out.println(objekt.toString());
//        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Anfangsbuchstabe: ");
        String ch = sc.nextLine();
        for (PazientenFallakten objekt : patientList) {
            if (objekt.patient.charAt(0) == ch.charAt(0)) {
                System.out.println(objekt.patient);
            }
        }

        List<PazientenFallakten> patientDaten = new ArrayList<>();

        for (PazientenFallakten objekt : patientList) {
            if (objekt.symptom.equals("Fieber")) {
                patientDaten.add(objekt);
            }
        }
        patientDaten.sort(PazientenFallakten::compareTo);

        System.out.println("\nPatienten mit Fieber gesortet: ");
        for (PazientenFallakten patient : patientDaten) {
            System.out.println(patient.datum + ": " + patient.patient + " - Diagnose: " + patient.diagnose);
        }

        int fallenNY = 0;
        int fallenSM = 0;
        int fallenPP = 0;

        for (PazientenFallakten patient : patientList) {
            if (patient.Krankenhaus.equals("St. Mary’s Medical Center"))
                fallenNY++;
            else if (patient.Krankenhaus.equals("New York General Hospital"))
                fallenSM++;
            else if (patient.Krankenhaus.equals("Princeton-Plainsboro Teaching Hospital"))
                fallenPP++;
            else throw new RuntimeException("Hospital ist falsch");
        }
        try {
            String daten = "New York General Hospital$" + fallenNY + "\n" +
                    "St. Mary's Medical Center$" + fallenSM + "\n" +
                    "Princeton-Plainsboro Teaching Hospital$" + fallenPP;
            Files.write(Path.of("fallanzahl.txt"), daten.getBytes());
        } catch (IOException e) {
            System.err.println("Fehler beim schreiben in der Datei" + e.getMessage());
        }
    }
}
