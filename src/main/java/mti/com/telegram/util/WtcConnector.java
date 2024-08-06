package mti.com.telegram.util;

// import javax.naming.InitialContext;
// import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import weblogic.wtc.gwt.TuxedoConnection;
// import weblogic.wtc.gwt.TuxedoConnectionFactory;
// import weblogic.wtc.jatmi.Reply;
// import weblogic.wtc.jatmi.TPException;
// import weblogic.wtc.jatmi.TPReplyException;
// import weblogic.wtc.jatmi.TypedBuffer;
// import weblogic.wtc.jatmi.TypedCArray;

// TODO 
public class WtcConnector {
    private static final Logger logger = LogManager.getLogger(WtcConnector.class);

    public static byte[] connectTxd(byte[] paramArrayOfbyte) throws Exception {
        // TuxedoConnection tuxedoConnection = null;
        // byte[] arrayOfByte = null;
        // try {
        //     InitialContext initialContext = new InitialContext();
        //     TuxedoConnectionFactory tuxedoConnectionFactory = (TuxedoConnectionFactory) initialContext
        //             .lookup("tuxedo.services.TuxedoConnection");
        //     tuxedoConnection = tuxedoConnectionFactory.getTuxedoConnection();
        //     if (logger.isInfoEnabled())
        //         logger.info("Request[" + paramArrayOfbyte.length + "] : [" + new String(paramArrayOfbyte) + "]");
        //     TypedCArray typedCArray1 = new TypedCArray();
        //     typedCArray1.carray = paramArrayOfbyte;
        //     typedCArray1.setSendSize(paramArrayOfbyte.length);
        //     Reply reply = tuxedoConnection.tpcall("SLCFPROXY", (TypedBuffer) typedCArray1, 0);
        //     TypedCArray typedCArray2 = (TypedCArray) reply.getReplyBuffer();
        //     arrayOfByte = typedCArray2.carray;
        //     if (logger.isInfoEnabled())
        //         logger.info("Response[" + arrayOfByte.length + "] : [" + new String(arrayOfByte) + "]");
        // } catch (NamingException namingException) {
        //     logger.error("Could not get TuxedoConnectionFactory : NamingException:\n" + namingException.getMessage());
        //     logger.error(namingException);
        //     throw namingException;
        // } catch (TPReplyException tPReplyException) {
        //     logger.error("tpcall threw TPReplyExcption " + tPReplyException);
        //     logger.error(tPReplyException);
        //     throw tPReplyException;
        // } catch (TPException tPException) {
        //     logger.error("tpcall threw TPException " + tPException);
        //     logger.error(tPException);
        //     throw tPException;
        // } catch (Exception exception) {
        //     logger.error("tpcall threw exception: " + exception);
        //     logger.error(exception);
        //     throw exception;
        // } finally {
        //     tuxedoConnection.tpterm();
        // }
        // return arrayOfByte;
        byte[] temp = {};
        return temp;
    }
}
