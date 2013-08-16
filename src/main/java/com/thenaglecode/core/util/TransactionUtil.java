package com.thenaglecode.core.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created with IntelliJ IDEA.
 * User: jxnagl
 * Date: 8/16/13
 * Time: 1:30 PM
 */
@Stateless
@LocalBean
public class TransactionUtil {
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void wrapInTx(Invokable invokable) {
        invokable.invoke();
    }
    public interface Invokable {
        public void invoke();
    }
}
