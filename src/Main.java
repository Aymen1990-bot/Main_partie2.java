import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Classe UserForms
class UserForms {
    // Map pour stocker les erreurs de validation
    public Map<String, String> errors = new HashMap<>();

    // Méthode générique de validation de champ (espace réservé, pour une utilisation potentielle future)
    public void validateField(String fieldName, String value) {
        // La logique de validation générique peut être ajoutée ici si nécessaire
        // Pour simplifier, supposons que tous les champs sont valides par défaut
    }
}

// Classe FinancingForm (hérite de UserForms)
class FinancingForm extends UserForms {
    // Méthode de validation pour le VIN (numéro d'identification du véhicule)
    public void validateVIN(String vin) {
        // Vérifier si le VIN est null ou ne comporte pas exactement 17 caractères
        if (vin == null || vin.length() != 17) {
            // Si invalide, ajouter un message d'erreur à la map errors
            errors.put("VIN", "Le numéro d'identification du véhicule doit contenir exactement 17 caractères.");
        }
    }

    // Méthode de validation pour le montant du prêt
    public void validateLoanAmount(double amount) {
        // Vérifier si le montant du prêt est en dehors de la plage valide (0 à 60 000)
        if (amount <= 0 || amount > 60000) {
            // Si invalide, ajouter un message d'erreur à la map errors
            errors.put("LoanAmount", "La valeur maximale du prêt est de 60 000 $.");
        }
    }

    // Méthode de validation pour la durée du prêt
    public void validateLoanDuration(int duration) {
        // Vérifier si la durée du prêt est en dehors de la plage valide (1 à 4)
        if (duration <= 0 || duration > 4) {
            // Si invalide, ajouter un message d'erreur à la map errors
            errors.put("LoanDuration", "La durée maximale du prêt est de 4 ans.");
        }
    }

    // Méthode de validation pour le kilométrage du véhicule (pour les véhicules d'occasion)
    public void validateMileage(int mileage, boolean isUsedVehicle) {
        // Vérifier si le véhicule est d'occasion et que le kilométrage dépasse 230 000
        if (isUsedVehicle && mileage > 230000) {
            // Si invalide, ajouter un message d'erreur à la map errors
            errors.put("Mileage", "Le kilométrage ne doit pas excéder 230 000 pour un véhicule d'occasion.");
        }
    }
}

// Classe principale pour tester la validation
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Exemple d'utilisation :
        FinancingForm financingForm = new FinancingForm();

        // Saisir les informations du formulaire
        System.out.print("Entrez le numéro d'identification du véhicule (VIN) : ");
        String vin = scanner.nextLine();
        financingForm.validateVIN(vin);

        System.out.print("Entrez le montant du prêt désiré : ");
        double loanAmount = scanner.nextDouble();
        financingForm.validateLoanAmount(loanAmount);

        System.out.print("Entrez la durée du prêt en années : ");
        int loanDuration = scanner.nextInt();
        financingForm.validateLoanDuration(loanDuration);

        System.out.print("Le véhicule est-il d'occasion (true/false) : ");
        boolean isUsedVehicle = scanner.nextBoolean();

        // Valider le kilométrage uniquement pour les véhicules d'occasion
        if (isUsedVehicle) {
            System.out.print("Entrez le kilométrage du véhicule : ");
            int mileage = scanner.nextInt();
            financingForm.validateMileage(mileage, isUsedVehicle);
        }

        // Afficher les erreurs s'il y en a
        if (!financingForm.errors.isEmpty()) {
            System.out.println("\nErreurs de validation du formulaire:");
            for (Map.Entry<String, String> entry : financingForm.errors.entrySet()) {
                // Imprimer chaque message d'erreur
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            // Si aucune erreur, imprimer le message de réussite
            System.out.println("\nFormulaire de demande de financement validé avec succès!");
        }

        // Fermer le scanner
        scanner.close();
    }
}
