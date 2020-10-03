package com.dbs.hibernate.interceptor.event;

import org.hibernate.HibernateException;
import org.hibernate.event.internal.DefaultLoadEventListener;
import org.hibernate.event.spi.LoadEvent;

public class TestLoadListener extends DefaultLoadEventListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void onLoad(LoadEvent arg0, LoadType arg1) throws HibernateException {
        super.onLoad(arg0, arg1);

        System.out.println("onLoad invoked!!!!");

        System.out.println(arg0.getEntityId() + ", "
                + arg0.getEntityClassName());
    }
}
