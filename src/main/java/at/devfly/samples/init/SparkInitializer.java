package at.devfly.samples.init;

import at.devfly.samples.rest.PersonResource;
import at.devfly.samples.rest.response.ErrorResponse;
import at.devfly.samples.settings.SparkSettings;
import at.devfly.samples.util.JsonResponseTransformer;
import at.devfly.samples.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Spark;

import static javaslang.API.*;
import static javaslang.Predicates.is;
import static spark.Spark.*;

public class SparkInitializer implements Initializer {

    private static final Logger logger = LoggerFactory.getLogger(SparkInitializer.class);

    private JsonResponseTransformer responseTransformer;
    private PersonResource personResource;

    public SparkInitializer(SparkSettings conf) {
        port(conf.getPort());
        threadPool(conf.getMaxThreads());
        responseTransformer = new JsonResponseTransformer();
        personResource = new PersonResource();
    }

    public void init() {
        setupExceptionHandler();
        setupBeforeRequest();
        setupAfterRequest();
        setupRoutes();
    }

    private void setupBeforeRequest() {
        before((req, res) -> {
            logger.info(requestInfo(req));

            final Boolean contentTypeSupported = Match(req.contentType()).of(
                    Case(is("application/json"), true),
                    Case($(), false)
            );

            if (!contentTypeSupported) halt(415, String.format("Content-Type is not supported: %s", req.contentType()));
        });
    }

    private void setupAfterRequest() {
        after((req, res) -> {
            res.type("application/json");
        });
    }

    private void setupRoutes() {
        Spark.get("/persons/:id", personResource::getPerson, responseTransformer);
        Spark.get("persons", personResource::getPersons, responseTransformer);
        Spark.get("error", personResource::getError, responseTransformer);
    }

    private void setupExceptionHandler() {
        // Catch other exceptions...

        exception(Exception.class, (ex, req, res) -> {
            res.status(500);
            res.type("application/json");
            res.body(JsonUtil.write(new ErrorResponse(ex.getMessage(), "Ooops something went wrong!")));
        });
    }

    private static String requestInfo(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.requestMethod());
        sb.append(" " + request.url());
        sb.append(" " + request.body());
        return sb.toString();
    }

}
