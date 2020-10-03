package com.dbs.hibernate.interceptor.event;

import java.io.Serializable;

import org.hibernate.event.internal.DefaultSaveEventListener;
import org.hibernate.event.spi.SaveOrUpdateEvent;

public class TestSaveListener extends DefaultSaveEventListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected Serializable performSaveOrUpdate(SaveOrUpdateEvent event) {
        System.out.println("performSaveOrUpdate invoked!!!");

        return super.performSaveOrUpdate(event);
    }
}
