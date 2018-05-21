package repositiry;

import model.User;
import org.mongodb.morphia.Datastore;

public class UserRepository {

    private Datastore datastore;

    public UserRepository() {
        datastore = DataBaseSource.getDataStore();
    }

    public User getUser(User user) {
        return datastore.get(user);
    }

    public void saveUser(User user) {
        datastore.save(user);
    }

}
