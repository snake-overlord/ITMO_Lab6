package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * <b>group_counting_by_type : group the elements of the collection by the value of the type field, print the number of elements in each group</b>
 */
public class GroupCountingByType extends BaseCommand {
    public static final String name = "group_counting_by_type";
    public static final String description = ": group the elements of the collection by the value of the type field, print the number of elements in each group";
    private CommandResponse response;
    public GroupCountingByType() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
        Vector<Organization> collection = repository.getVector();
        Map<String, Long> typeCounts = collection.stream()
                .collect(Collectors.groupingBy(org -> org.getType().name(), Collectors.counting()));

        String result = typeCounts.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        response = new CommandResponse(result, 0);
                }

            }