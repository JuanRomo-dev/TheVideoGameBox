package Data.Game;

import Logic.Game.TGame;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;


public class DAOGameStub implements DAOGame {

    public List<TGame> searchAllByName(String name) {
        return new ArrayList<TGame>();
    }

    public TGame searchOne(ObjectId _id) {
        return new TGame(null, null, null, null, null, null, -1, null);
    }

    @Override
    public List<TGame> random() {
        return new ArrayList<TGame>();
    }
}
