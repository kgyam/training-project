package org.example.user.web.context;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 组件上下文，用于获取在servlet中注册的组件
 *
 * @author kg yam
 * @date 2021-03-05 16:33
 * @since
 */
public class ComponentContext {

    private static Context context;
    public static final String COMPONENT_CONTEXT = "component_context";

    public ComponentContext() {
        init ();
    }

    private void init() {
        try {
            Context context = (Context) new InitialContext ().lookup ("java:comp/env");
            ComponentContext.context = context;
        } catch (NamingException e) {
            e.printStackTrace ();
        }
    }

    public static <C> C getInstance(String name) {
        try {
            return (C) context.lookup (name);
        } catch (NamingException e) {
            throw new IllegalArgumentException ("look up fail,by" + name, e);
        }
    }

    public void destroy() throws RuntimeException {
        if (ComponentContext.context != null) {
            try {
                context.close ();
            } catch (NamingException e) {
                throw new RuntimeException (e);
            }
        }
    }
}
