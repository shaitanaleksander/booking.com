package repositiry;

import model.User;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

public class UserRepository {

    private Datastore datastore;

    public UserRepository() {
        datastore = DataBaseSource.getDataStore();
    }

    public User getUser(String userId) {
        return datastore.createQuery(User.class).filter("_id =", new ObjectId(userId)).get();
    }

    public User saveUser(User user) {
        var userId = datastore.save(user).getId().toString();
        return getUser(userId);
    }

    public void updateUser(User user) {
        var updateQuery = datastore.createQuery(User.class).field("_id").equal(user.getId());
        var ops = datastore.createUpdateOperations(User.class);
        System.out.println(user.getName());
        if (user.getName() != null) ops = ops.set("name", user.getName());
        if (user.getPassword() != null) ops = ops.set("password", user.getPassword());
        if (user.getRole() != null) ops = ops.set("role", user.getRole());

        datastore.update(updateQuery, ops);
    }
}
