package Presentation.Command.User;

import org.bson.types.ObjectId;

import Logic.SAAbstractFactory;
import Logic.User.SAUser;
import Logic.User.TUser;
import Presentation.Command.ICommand;
import Presentation.Controller.Context;
import Presentation.Controller.Event;

public class CommandCreateUser implements ICommand{

	@Override
	public Context execute(Object data) {
		SAUser saUser = SAAbstractFactory.getInstance().createSAUser();
		ObjectId result = saUser.createUser((TUser) data);
		Context con;
	  	
		if(result != null) con = new Context(Event.RES_CREATE_USER_OK, result);
		else con = new Context(Event.RES_CREATE_USER_KO, result);
		
		return con;
	}

}
