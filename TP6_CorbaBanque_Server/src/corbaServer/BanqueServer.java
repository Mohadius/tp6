package corbaServer;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import service.BanqueImpl;
public class BanqueServer {
    public static void main(String[] args){
        try{
            ORB orb=ORB.init(args, null);
            POA rootPOA=POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            Context ctx=new InitialContext();
            BanqueImpl banqueImpl=new BanqueImpl();
            org.omg.CORBA.Object ref= rootPOA.servant_to_reference(banqueImpl);
            ctx.rebind("BANQUE",ref);
            System.out.println("Serveur CORBA Banque prét et en attente de requétes...");
            orb.run();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
