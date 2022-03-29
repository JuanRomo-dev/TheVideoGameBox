package Presentation.View;

import java.util.List;

import Logic.Game.TGame;
import Logic.User.TUser;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.Box.ViewCreateBox;
import Presentation.View.Game.ViewSearchByName;
import Presentation.View.Game.ViewShowOne;
import Presentation.View.Main.ViewPrincipal;
import Presentation.View.User.ViewRegister;

public class ViewFactory extends ViewAbstractFactory {

    @Override
    public IView createView(Context context) {
        switch (context.getEvent()) {
            case Event.VIEW:
                currentView = new ViewPrincipal();
				break;
            
			case Event.RES_SEARCH_ALL_BY_NAME_OK:
				currentView = new ViewSearchByName((List<TGame>) context.getData());
				break;
			case Event.RES_SEARCH_ONE_OK:
				currentView = new ViewShowOne((TGame) context.getData());
				break;
			case Event.VIEW_CREATE_BOX:
				currentView = new ViewCreateBox();
				break;
			
			case Event.RES_CREATE_BOX_OK:
				break;
			case Event.RES_CREATE_BOX_KO:
				break;
			case Event.VIEW_CREATE_USER:
				currentView = new ViewRegister();
				break;
			case Event.RES_CREATE_USER_OK:
				break;
			case Event.RES_CREATE_USER_KO:
				break;
		}

        return currentView;
    }

    @Override
    public IView getCurrentIView() {
        return currentView;
    }
}
