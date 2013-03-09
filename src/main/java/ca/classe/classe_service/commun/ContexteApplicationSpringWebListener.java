package ca.classe.classe_service.commun;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.Validate;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Listener Web pour initialiser ContexteApplicationUtils avec un applicationContext Spring.
 *
 * Exemple d'utilisation dans web.xml:
 *
 * <pre>
 *   &lt;context-param&gt;
 *       &lt;param-name&gt;contextClass&lt;/param-name&gt;
 *       &lt;param-value&gt;org.springframework.web.context.support.AnnotationConfigWebApplicationContext&lt;/param-value&gt;
 *   &lt;/context-param&gt;
 *
 *   &lt;context-param&gt;
 *       &lt;param-name&gt;contextConfigLocation&lt;/param-name&gt;
 *       &lt;param-value&gt;ca.ulaval.test.ConfigMonApplication&lt;/param-value&gt;
 *   &lt;/context-param&gt;
 *
 *   &lt;listener&gt;
 *       &lt;listener-class&gt;org.springframework.web.context.ContextLoaderListener&lt;/listener-class&gt;
 *   &lt;/listener&gt;
 *
 *   &lt;listener&gt;
 *       &lt;description&gt;Pour initialiser ContexteApplicationUtils&lt;/description&gt;
 *       &lt;listener-class&gt;ca.ulaval.communs.autorisation.spring.ContexteApplicationSpringWebListener&lt;/listener-class&gt;
 *   &lt;/listener&gt;
 * </pre>
 *
 * @author frpol9
 * @since 0.1
 */
public class ContexteApplicationSpringWebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent evenement) {
        ApplicationContext contexteSpring = WebApplicationContextUtils.getRequiredWebApplicationContext(evenement.getServletContext());

        Validate.notNull(contexteSpring);

        ContexteApplication contexte = new ContexteApplicationSpring(contexteSpring);
        ContexteApplicationUtils.setInstance(contexte);
    }

    @Override
    public void contextDestroyed(ServletContextEvent evenement) {
        ContexteApplicationUtils.setInstance(null);
    }
}
