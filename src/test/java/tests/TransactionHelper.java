package tests;

import com.thenaglecode.core.util.TransactionUtil;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/16/13
 * Time: 5:21 PM
 */
@SessionScoped
public class TransactionHelper implements Serializable {

    @EJB
    TransactionUtil txUtil;

    public void wrapInTx(TransactionUtil.Invokable invokable){
        txUtil.wrapInTx(invokable);
    }
}
