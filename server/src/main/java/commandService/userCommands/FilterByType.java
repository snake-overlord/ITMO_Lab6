package commandService.userCommands;

import commandService.BaseCommand;
import models.OrganizationType;
import network.CommandResponse;

/**
 * <b>filter_by_type type (COMMERCIAL, PUBLIC, etc.) : output elements with the specified type</b>
 */
public class FilterByType extends BaseCommand<OrganizationType> {
    public static final String name = "filter_by_type";
    public static final String description = "type (COMMERCIAL, PUBLIC, etc.) : output elements with the specified type";
    private OrganizationType type;
    private CommandResponse response;
    public FilterByType(){
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }
    @Override
    public void setArg(OrganizationType type){
        this.type = type;
    }

    public void execute(){
        StringBuilder str = new StringBuilder();
        repository.getVector().stream()
                .filter(org -> org.getType().equals(type))
                .forEach(org -> str.append("ID: " + org.getId() + " Name: " + org.getName()));
        response = new CommandResponse(str.toString(), 0);
        }

        }



