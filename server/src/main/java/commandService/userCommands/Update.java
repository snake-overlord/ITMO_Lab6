package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Objects;
import java.util.Vector;
/**
 * <b>update_by_id id : update element by its id</b>
 */
public class Update extends BaseCommand<Long> {
    public static final String name = "update_by_id";
    public static final String description = "id : update element by its id";
    private CommandResponse response;
    private Long id;
    private Organization org;
    public Update() {
        super(name, description);
    }
    @Override
    public void setArg(Long id){
        this.id = id;
    }
    @Override
    public void setOrg(Organization org){
        this.org = org;
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
        System.out.print("here");
            Vector<Organization> collection = repository.getVector();
            if(collection
                    .stream()
                    .anyMatch(x -> (Objects.equals(x.getId(), id)))){
                Organization last = collection
                        .stream()
                        .filter(x -> Objects.equals(x.getId(), id))
                        .findFirst()
                        .get();
                repository.deleteItem(last);
                repository.addItem(org, id);
            } else{
                response = new CommandResponse("Invalid ID", 2);
                return;
            }
        response = new CommandResponse("Finished!", 0);

        }

    }
