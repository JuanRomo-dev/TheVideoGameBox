package Data.User;

import Data.Connection;
import Logic.User.TUser;
import Logic.User.User;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.types.ObjectId;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

public class DAOUserImp implements DAOUser {

	@Override
	public ObjectId create(TUser tUser) {
		ObjectId result;
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<User> users = db.getCollection("users", User.class);

			User insert = new User(tUser);

			users.createIndex(Indexes.ascending("username"), new IndexOptions().unique(true));
			users.createIndex(Indexes.ascending("email"), new IndexOptions().unique(true));
			result = Objects.requireNonNull(users.insertOne(insert).getInsertedId()).asObjectId().getValue();
		} catch (MongoException | NullPointerException e) {
			result = null;
			e.getMessage();
		}
		return result;
	}

	@Override
	public TUser logIn(String email) {
		TUser tUser;
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			tUser = Objects.requireNonNull(db.getCollection("users", User.class).find(eq("email", email)).first()).toTransfer();
		} catch (MongoException e) {
			tUser = null;
		}
		return tUser;
	}

	@Override
	public void deleteFromDatabase(ObjectId id) {
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<User> boxes = db.getCollection("users", User.class);
			boxes.deleteOne(eq("_id", id));
		} catch (MongoException e) {

		}
	}

}
