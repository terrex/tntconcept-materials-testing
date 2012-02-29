/*
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com
 */

package com.autentia.intra.util;

import com.autentia.intra.dao.ITransferObject;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.Map;

public class HibernateUtil {
    private static final Log log = LogFactory.getLog(HibernateUtil.class);

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new Configuration();
            cfg.configure();
            sessionFactory = cfg.buildSessionFactory();

        }
        catch (Throwable e) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    /**
     * Devuelve la sesi�n para el thread que hace la llamada. Es decir cada thread tiene su propia sesi�n.
     *
     * @return sesi�n para el thread que hace la llamada.
     * @throws HibernateException
     */
    public static Session currentSession() throws HibernateException {
        Session s = session.get();
        // Open a new Session, if this thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);    // Store it in the ThreadLocal variable
        } else if (!s.isOpen()) {
            s = sessionFactory.openSession();
            session.set(s);    // Store it in the ThreadLocal variable
        }
        return s;
    }

    public static Session getSession() throws HibernateException {
        Session s = sessionFactory.openSession();
        return s;
    }

    /**
     * Cierra la sesi�n actual para el thread que hace la llamada.
     *
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        if (s != null) {
            if (s.isOpen())
                s.close();
        }
        //session.set(null);
    }

    public static void closeSession(Session s) throws HibernateException {
        if (s != null) {
            s.flush();
            s.close();
        }
    }

    /**
     * Fully evict an object (including its related objects) from Hibernate cache.
     * This method is nececessary since HibernateSession.evict() does not evict
     * related objects, only the main passed instance. Hibernate can be configured
     * with cascade="evict" to evict the full object, but we prefer to do it
     * programatically better than by configuration, which can lead to more problems.
     *
     * @param dto
     */
    public static void evictFullObject(Object dto) {
        Session s = currentSession();

        s.evict(dto);
        try {
            Map props = BeanUtilsBean.getInstance().getPropertyUtils().describe(dto);
            Collection vals = props.values();
            for (Object val : vals) {
                if (val instanceof ITransferObject) {
                    s.evict(val);
                }
            }
        }
        catch (Exception e) {
            log.error("evictFullObject - exception", e);
        }
    }

}
















