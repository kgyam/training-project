package org.example.ioc.factory;


import javax.naming.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * 组件上下文，用于获取在servlet中注册的组件
 *
 * @author kg yam
 * @date 2021-03-05 16:33
 * @since
 */
public class JndiComponentFactory extends AbstractComponentFactory {

    private static final Logger logger = Logger.getLogger (JndiComponentFactory.class.getName ());

    /**
     * jndi上下文对象
     */
    private Context context;

    private static final String ENV_NAME = "java:comp/env";
    private ClassLoader classLoader;


    private boolean isFinish = false;

    public JndiComponentFactory() {
        super ();
    }


    /**
     * 初始化上下文
     */
    @Override
    void initEnv() {
        try {
            logger.info ("initContext...");
            Context context = (Context) new InitialContext ().lookup (ENV_NAME);
            this.context = context;
            this.classLoader = this.getClass ().getClassLoader ();
        } catch (NamingException e) {
            throw new RuntimeException (e);
        }
    }

    /**
     * 实例化组件
     */
    @Override
    void instantiateComponent() {
        if (context == null) {
            throw new RuntimeException ("javax.naming.Context is null");
        }
        List<String> componentNameList = listAllComponentName ();
        componentNameList.forEach (componentName -> {
            try {
                Object component = context.lookup (componentName);
                componentContainer.put (componentName, component);
            } catch (NamingException e) {
                e.printStackTrace ();
            }
        });
    }


    private List<String> listAllComponentName() {
        return listComponentName ("/");
    }

    private List<String> listComponentName(String conetxtName) {
        try {
            NamingEnumeration<NameClassPair> nameClassPairNamingEnumeration = context.list (conetxtName);
            if (nameClassPairNamingEnumeration == null) {
                return Collections.EMPTY_LIST;
            }

            List<String> componentNameList = new LinkedList<> ();
            while (nameClassPairNamingEnumeration.hasMoreElements ()) {
                NameClassPair nameClassPair = nameClassPairNamingEnumeration.next ();
                Class clazz = classLoader.loadClass (nameClassPair.getClassName ());
                /*
                如果遍历的类型也是一个javax.naming.Context,那么递归遍历这个Context下的元素
                 */
                if (Context.class.isAssignableFrom (clazz)) {
                    componentNameList.addAll (listComponentName (nameClassPair.getName ()));
                } else {
                    String name = conetxtName.startsWith ("/") ? nameClassPair.getName () : conetxtName + "/" + nameClassPair.getName ();
                    componentNameList.add (name);
                }
            }
            return componentNameList;
        } catch (Exception e) {
            throw new RuntimeException (e);
        }
    }


}
