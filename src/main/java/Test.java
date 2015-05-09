import com.github.kazuki43zoo.container.AppConfig;
import com.github.kazuki43zoo.container.TestBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by shimizukazuki on 2015/05/09.
 */
public class Test {

public static void main(String... args){
    ConfigurableApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    TestBean testBean = context.getBean(TestBean.class);
    testBean.print();
    context.registerShutdownHook();
}
}
