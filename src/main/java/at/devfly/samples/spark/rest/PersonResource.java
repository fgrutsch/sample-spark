package at.devfly.samples.spark.rest;

import at.devfly.samples.spark.model.Person;
import at.devfly.samples.spark.rest.response.ResponseEnvelope;
import at.devfly.samples.spark.rest.response.SuccessResponse;
import javaslang.collection.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.UUID;


public class PersonResource {

    private static final Logger logger = LoggerFactory.getLogger(PersonResource.class);

    private List<Person> persons;

    public PersonResource() {
        logger.info("Initializing PersonResource");

        persons = List.of(Person.newBuilder()
                        .id(UUID.fromString("08e3ba1f-2990-4a98-9260-59b568dbbd19"))
                        .firstName("Max")
                        .lastName("Mustermann")
                        .age(30)
                        .build(),
                Person.newBuilder()
                        .id(UUID.fromString("118e315d-95cd-4d5f-814d-75c51c4cccdc"))
                        .firstName("Max2")
                        .lastName("Mustermann2")
                        .age(302)
                        .build()
        );
    }

    public ResponseEnvelope getPerson(Request req, Response res) {
        final String requestedId = req.params(":id");

        return new SuccessResponse(
                persons.filter(p -> p.getId().equals(UUID.fromString(requestedId))).head()
        );
    }

    public ResponseEnvelope getPersons(Request req, Response res) {
        return new SuccessResponse(
                persons.toJavaList()
        );
    }

    public ResponseEnvelope getError(Request req, Response res) throws Exception {
        throw new Exception("Just a test error");
    }

}