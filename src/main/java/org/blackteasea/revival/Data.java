package org.blackteasea.revival;

import java.beans.PropertyChangeSupport;
import java.util.logging.Logger;

public class Data extends PropertyChangeSupport {
    private static Data instance;
    private Logger logger;
    private Data() {
        super(new Object());
    }

    public static Data getInstance(){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public void setLogger(Logger logger){
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
