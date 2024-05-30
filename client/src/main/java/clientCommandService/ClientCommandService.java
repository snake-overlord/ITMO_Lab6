package clientCommandService;

import exceptions.InvalidInputException;
import models.Organization;
import network.CommandList;
import network.CommandRequest;
import validators.BaseValidator;
import java.util.Optional;

public class ClientCommandService{

    private CollectionInput collectionInput;

    public ClientCommandService(CollectionInput collectionInput){
        this.collectionInput = collectionInput;
    }

    private CommandRequest validate(String[] input) {
        Optional<CommandRequest> request = CommandList.get().stream()
                .filter(com -> com.getName().equals(input[0]))
                .findFirst();
        try {
            CommandRequest command = request.orElseThrow(() ->
                    new InvalidInputException(String.format("Command %s is not found", input[0])));
            if (input.length - 1 != command.getArgsNum()) {
                throw new InvalidInputException(String.format("%s arguments required. Provided: %d", command.getArgsNum(), input.length - 1));
            }
            else if(input.length > 1) {
                BaseValidator validator = command.getValidator();
                boolean validated = validator.validate(input[1]);
                if (!validated) {
                    throw new InvalidInputException("Illegal arguments!");
                }
            }
            return request.get();

        } catch (InvalidInputException e) {
            System.err.print(e.getMessage());
        }
        return null;
    }

    public CommandRequest getRequest(String[] args) {
        CommandRequest command = validate(args);
        if(command==null){
            return null;
        }
        if (args.length > 1) {
            String arg = args[1];
            var validated = command.getValidator().validated(arg);
            command.setArg(validated);
        }
        if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("update") || args[0].equalsIgnoreCase("add_if_min") || args[0].equalsIgnoreCase("add_if_max")){
            Organization org = collectionInput.get();
            command.setOrg(org);
        }
        return command;
    }
}
