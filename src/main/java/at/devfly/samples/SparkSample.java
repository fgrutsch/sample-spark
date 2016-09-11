package at.devfly.samples;

import at.devfly.samples.init.SparkInitializer;
import at.devfly.samples.settings.SparkSettings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SparkSample {

    private static final Logger logger = LoggerFactory.getLogger(SparkSample.class);

    public void run() {
        logger.info("Starting application...");

        final Config config = ConfigFactory.load();
        final SparkSettings sparkConf = ConfigBeanFactory.create(config.getConfig("spark"), SparkSettings.class);

        logger.info("Loaded config: " + config.toString());

        new SparkInitializer(sparkConf).init();
    }

}
