package service;
import java.util.ArrayList;
import java.util.List;
import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;
public class BanqueImpl extends IBanqueRemotePOA {
    private List<Compte> comptes = new ArrayList<>();
    @Override
    public void creerCompte(Compte cpte){
        comptes.add(cpte);
        System.out.println("Compte créé : Code "+ cpte.code + ", Solde = "+cpte.solde);
    }
    @Override
    public void verser(float mt, int code){
        Compte c=getCompte(code);
        if(c != null){
            c.solde +=mt;
            System.out.println("Versement de "+ mt +" sur le compte "+ code +". Nouveau solde : "+c.solde);
        } else {
            System.out.println("Compte introuvable pour le code : "+code);
        }
    }
    @Override
    public void retirer(float mt, int code){
        Compte c = getCompte(code);
        if(c !=null){
            if(c.solde>=mt){
                c.solde -=mt;
                System.out.println("Retraot de "+mt+" du compte "+code+". Nouveau solde :"+c.solde);
            } else {
                System.out.println("Solde insuffisant pour le compte "+code);
            }
        }else{
                System.out.println("Compte introuvable pour le code : "+code);
            }
        }
        @Override
    public Compte getCompte(int code){
        for(Compte c : comptes){
            if(c.code==code){
                return c;
            }
        }
        return null;
    }
    @Override
    public Compte[] getComptes(){
        return comptes.toArray(new Compte[0]);
    }
    @Override
    public double conversion(float mt){
        return mt*3.35;
    }
}
