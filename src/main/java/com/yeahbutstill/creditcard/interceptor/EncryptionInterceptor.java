package com.yeahbutstill.creditcard.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EncryptionInterceptor extends EmptyInterceptor {

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onLoad");
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onFlushDirty");
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onSave");
        return super.onSave(entity, id, state, propertyNames, types);
    }

}
