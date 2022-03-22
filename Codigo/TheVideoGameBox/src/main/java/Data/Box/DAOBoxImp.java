package Data.Box;

import static com.mongodb.client.model.Filters.eq;
import org.bson.types.ObjectId;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

import Data.Connection;
import Logic.Box.Box;
import Logic.Box.TBox;

import java.util.ArrayList;
import java.util.List;

public class DAOBoxImp implements DAOBox {

	public ObjectId create(TBox box) {
		ObjectId result;

		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

			Box insert = new Box(box);

			result = boxes.insertOne(insert).getInsertedId().asObjectId().getValue();
		} catch (MongoException | NullPointerException e) {
			result = null;
		}

		return result;
	}

	@Override
	public ObjectId addGame(TBox tBox, ObjectId idGame) {
		List<ObjectId> gameList = new ArrayList<>();
		try {
			MongoDatabase db = Connection.getInstance().getConnection();
			Box box = db.getCollection("boxes", Box.class).find(eq("_id", tBox.getId())).first();
			if(box.getGameList() != null) gameList = box.getGameList();
			gameList.add(idGame);
			db.getCollection("boxes", Box.class).updateOne(eq("_id", tBox.getId()), Updates.set("gameList", gameList));
		} catch (MongoException e) {
			return null;
		}
		return idGame;
	}

    @Override
    public void deleteFromDatabase(ObjectId id) {
        try {
            MongoDatabase db = Connection.getInstance().getConnection();
            MongoCollection<Box> boxes = db.getCollection("boxes", Box.class);

            boxes.deleteOne(eq("_id", id));
        } catch (MongoException e) {

        }
    }
}
