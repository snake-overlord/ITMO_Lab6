package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Objects;
import java.util.Vector;
/**
 * <b>remove_by_id id: remove an element from the collection by its id</b>
 */
public class RemoveById extends BaseCommand<Long> {
    public static final String name = "remove_by_id";
    public static final String description = "id: remove an element from the collection by its id";
    private Long id;
    private CommandResponse response;
    public RemoveById() {
        super(name, description);
    }
    @Override
    public void setArg(Long id){
        this.id = id;
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
            Vector<Organization> collection = repository.getVector();
            collection
                    .stream()
                    .filter(x -> Objects.equals(x.getId(), id))
                    .findFirst()
                    .ifPresentOrElse(repository::deleteItem, () -> response = new CommandResponse("Invalid ID", 2));
        if(response==null){
            response = new CommandResponse("Finished!", 0);
        }
        }
    }
