package commandService;


import models.Organization;
import network.CommandResponse;

/**
 * Abstract user command class.
 */
public abstract class BaseCommand<T> {
    String name;
    String description;

    public ICollectionRepository repository = CollectionRepository.getInstance();

    public BaseCommand(String name, String description){
        this.name = name;
        this.description = description;
    }
    public abstract CommandResponse getResponse();

    public abstract void execute();
    public void setArg(T value){};
    public void setOrg(Organization org){};


}
