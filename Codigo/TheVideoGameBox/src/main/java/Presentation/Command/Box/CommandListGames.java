package Presentation.Command.Box;

import Logic.Box.SABox;
import Logic.SAAbstractFactory;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import org.bson.types.ObjectId;

import java.util.List;

public class CommandListGames implements ICommand {
    @Override
    public Context execute(Object data) {
        SABox saBox = SAAbstractFactory.getInstance().createSABox();
        List<ObjectId> result = saBox.listGames((ObjectId) data);
        Context con = null;
                                //PENDIENTE DE QUE SE AÑADAN LOS EVENTOS
        //if(result != null && !result.isEmpty()) con = new Context(Event.RES_LIST_GAMES_FROM_BOX_OK, result);
        //else con = new Context(Event.RES_LIST_GAMES_FROM_BOX_KO, result);

        return con;
    }
}
