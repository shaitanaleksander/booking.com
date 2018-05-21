package repositiry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

class DataBaseSource {

    private static final Datastore datastore;

    static {
        Morphia morphia = new Morphia();
        morphia.mapPackage("src/main/java/model");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        datastore = morphia.createDatastore(mongoClient, "booking_com");
    }

    static Datastore getDataStore() {
        return datastore;
    }
}
