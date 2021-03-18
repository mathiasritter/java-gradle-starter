import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public aspect Logging {

    private static final Logger logger = LogManager.getLogger(Logging.class.getSimpleName());

    after(): execution(public static void main(String[])) {
        logger.info("Hello from AspectJ!");
    }

}
