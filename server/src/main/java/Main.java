import DMS.DMS_ControlService.ControlService;
import commandService.BaseCommand;
import commandService.CollectionRepository;
import commandService.userCommands.Save;
import models.Organization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.util.Vector;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        ControlService controlService = DMS.DMS_Configuration.build();
        Save save = new Save(controlService);
        Runtime.getRuntime().addShutdownHook(new Thread(save::execute));
        CollectionRepository repository = CollectionRepository.getInstance();
        Vector<Organization> collection;
        try {
            collection = controlService.read();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        repository.addItems(collection);
        logger.info("Items successfully uploaded to the collection: {}.", repository.getVector().size());
        UDPServer.run();

    }
}