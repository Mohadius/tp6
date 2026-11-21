package corbaClient;
import corbaBanque.Compte;
import corbaBanque.IBanqueRemote;
import corbaBanque.IBanqueRemoteHelper;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            Context context = new InitialContext();
            org.omg.CORBA.Object remoteObjet = (org.omg.CORBA.Object) context.lookup("BANQUE");
            IBanqueRemote banque = IBanqueRemoteHelper.narrow(remoteObjet);
            System.out.println("Connexion au service Banque réussie.");
            Compte compte1=new Compte(1,1000.0f);
            banque.creerCompte(compte1);
            System.out.println("Compte créé : code= "+compte1.code+", solde initial= "+ compte1.solde+" DT");
            System.out.println("\n--- Versement ---");
            banque.verser(500.0f,1);
            Compte compteApresVersement=banque.getCompte(1);
            System.out.println("Nouveau solde du compte "+compteApresVersement.code+" : "+compteApresVersement.solde+" DT");
            System.out.println("\n--- Retrait ---");
            banque.retirer(200.0f,1);
            Compte compteApresRetrait=banque.getCompte(1);
            System.out.println("Nouveau solde du compte "+ compteApresRetrait.code +" : "+compteApresRetrait.solde+"DT");
            System.out.println("\n--- Consultation du compte ---");
            Compte compteConsulte= banque.getCompte(1);
            System.out.println("Code : "+compteConsulte.code+ " | Solde : "+compteConsulte.solde+" DT");
            System.out.println("\n--- Création d'un deuxième compte ---");
            Compte compte2=new Compte(2,2500.0f);
            banque.creerCompte(compte2);
            System.out.println("Compte créé : code= "+compte2.code+", solde initial= "+compte2.solde+" DT");
            System.out.println("\n--- Liste de tous les comptes ---");
            Compte[] comptes=banque.getComptes();
            for(Compte compte : comptes){
                System.out.println("Code : "+compte.code+" | Solde : "+compte.solde+" DT");
            }
            System.out.println("\n--- Conversion Euro vers Dinar ---");
            float montantEuro=100.0f;
            double montantDinar=banque.conversion(montantEuro);
            System.out.println(montantEuro+" EUR= "+montantDinar+" DT");
            System.out.println("\nTous les tests sont terminés.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion au serveur");
            e.printStackTrace();
        }
    }
}
