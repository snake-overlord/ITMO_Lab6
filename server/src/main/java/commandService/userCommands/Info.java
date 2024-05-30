package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.time.format.DateTimeFormatter;
import java.util.Vector;
/**
 * <b>info : information about the collection (type, initialization date, number of elements, etc.)</b>
 */
public class Info extends BaseCommand {
    public static final String name = "info";
    public static final String description = ": information about the collection (type, initialization date, number of elements, etc.)";
    private CommandResponse response;
    public Info() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
            Vector<Organization> collection = repository.getVector();
            String result = "";
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            result += String.format("Тип: %s\n", collection.getClass());
            result += String.format("Дата инициализации: %s\n", repository.getDate().format(format));
            result += String.format("Количество элементов: %d", collection.size());
        response = new CommandResponse(result, 0);
        }
    }