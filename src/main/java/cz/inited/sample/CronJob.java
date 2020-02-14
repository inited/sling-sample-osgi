package cz.inited.sample;

import java.util.Dictionary;

import org.apache.felix.scr.annotations.*;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, metatype = true, description = "Pravidelne spoustejici cron job.")
@Service(value = Runnable.class)
@Property(name = "scheduler.expression", value = "*/10 ? ? ? ? ?", description = "Vyraz pro cron scheduler")
public class CronJob implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(CronJob.class);

    static private String message;

    @Property(value = "Hello world", description = "Zprava pro vypis")
    private static final String PAR_MESSAGE = "Zprava";   // pojmenuju si jak chci

    public void run() {
        log.info("CRON message: " + message);
    }

    @Activate
    protected void activate(ComponentContext componentContext) {
        Dictionary<String, ?> properties = componentContext.getProperties();
        message = (String) properties.get(PAR_MESSAGE);
    }

    @Deactivate
    protected void deactivate(ComponentContext componentcontext) {
    }
}
