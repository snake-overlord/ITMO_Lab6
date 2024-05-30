package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Vector;
/**
 * <b>count_greater_than_employees_count count : output the number of elements with Employee Count field greater than the specified one</b>
 */
public class CountGreaterThanEmployeesCount extends BaseCommand<Long>{
    public static final String name = "count_greater_than_employees_count";
    public static final String description = "count : output the number of elements with Employee Count field greater than the specified one";
    private Long ecount;
    private CommandResponse response;
    public CountGreaterThanEmployeesCount() {
        super(name, description);
    }
    @Override
    public void setArg(Long ecount) {
        this.ecount = ecount;
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {

        Vector<Organization> collection = repository.getVector();
        long count = collection.stream()
                .filter(org -> org.getEmployeesCount() > ecount)
                .count();
        response = new CommandResponse(String.format("Elements with Employee Count field greater than %s: %s", ecount, count), 0);
        }
    }
