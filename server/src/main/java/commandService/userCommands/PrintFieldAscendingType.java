package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import models.OrganizationType;
import network.CommandResponse;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
/**
 * <b>print_field_ascending_type : print values of the type field of all elements in ascending order</b>
 */
public class PrintFieldAscendingType extends BaseCommand {
    public static final String name = "print_field_ascending_type";
    public static final String description = ": print values of the type field of all elements in ascending order";
    private CommandResponse response;
    public PrintFieldAscendingType(){
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
            SortedMap<String, OrganizationType> map = new TreeMap<>();
            Vector<Organization> collection = repository.getVector();
            for (Organization org : collection) {
                map.put(org.getType().name(), org.getType());
            }
        response = new CommandResponse(map.values().toString(), 0);
        }

        }