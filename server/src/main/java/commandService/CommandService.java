package commandService;

import commandService.userCommands.*;
import network.CommandRequest;
import network.CommandResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class for operating with available commands.
 */
public class CommandService{
    private static Map<String, BaseCommand> commands = new HashMap<>();
    static {commands.put(Add.name, new Add());
        commands.put(AddIfMax.name, new AddIfMax());
        commands.put(AddIfMin.name, new AddIfMin());
        commands.put(Clear.name, new Clear());
        commands.put(FilterByType.name, new FilterByType());
        commands.put(GroupCountingByType.name, new GroupCountingByType());
        commands.put(CountGreaterThanEmployeesCount.name, new CountGreaterThanEmployeesCount());
        commands.put(Head.name, new Head());
        commands.put(Help.name, new Help());
        commands.put(Info.name, new Info());
        commands.put(RemoveById.name, new RemoveById());
        commands.put(Show.name, new Show());
        commands.put(Update.name, new Update());
        commands.put(PrintFieldAscendingType.name, new PrintFieldAscendingType());
    }

    public CommandService(){
    }


    /**
     * Finds command by name and tries to execute.
     */
    public CommandResponse executeCommand(CommandRequest request){
        BaseCommand command = commands.get(request.getName());
        command.setArg(request.getArgs());
        if(request.getOrg()!=null){
            command.setOrg(request.getOrg());
        }
        command.execute();
        return command.getResponse();
        }

    public String printCommands() {
        StringBuilder ans = new StringBuilder();
        commands.values().forEach(x -> ans.append(x.name).append(" ").append(x.description).append("\n"));
        return ans.toString();
    }
}
