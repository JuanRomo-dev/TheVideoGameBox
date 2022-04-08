package Presentation.Controller;

import Presentation.View.IView;

import java.util.Stack;

public abstract class ApplicationController {
    private static ApplicationController instance;

    protected IView currentView = null;
    protected Stack<IView> viewStack = new Stack<IView>();

    public static ApplicationController getInstance() {
        if (instance == null)
            instance = new ApplicationControllerImp();

        return instance;
    }

    public abstract void action(Context context);

    public void clearViewStack(){
        viewStack.clear();
    }
}
