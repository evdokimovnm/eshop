package net.evdokimov.eshop.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

import static net.evdokimov.eshop.inject.ApplicationContextHolder.getClassPathXmlApplicationContext;

public class DependencyInjectionServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "contextConfigLocation";

    @Override
    public final void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX_PATH);
        System.out.println("load " + APP_CTX_PATH + " -> " + appCtxPath);

        if (appCtxPath == null) {
            System.out.println("I need init param: " + APP_CTX_PATH);
            throw new ServletException(APP_CTX_PATH + " init param == null");
        }

        try {
            //load AppContext
            ApplicationContext appCtx = getClassPathXmlApplicationContext(appCtxPath);
            //then inject AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectUpTo(this.getClass(), DependencyInjectionServlet.class);
            List<Field> injectFields = FieldReflector.filterInject(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                System.out.println("I find method marked by @Inject: " + field);
                String beanName = annotation.value();
                System.out.println("I must instantiate and inject '" + beanName + "'");
                Object bean = appCtx.getBean(beanName);
                System.out.println("Instantiation - OK: '" + beanName + "'");
                if (bean == null) {
                    throw new ServletException("there isn't bean with name '" + beanName + "'");
                }
                field.set(this, bean);
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject from " + APP_CTX_PATH, e);
        }
    }
}
