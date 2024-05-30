package DMS;

import DMS.DMS_ControlService.ControlService;
import DMS.DMS_ControlService.IControlService;
import DMS.collectionService.CollectionFileService;

import java.util.Objects;
/**
 * DMS configuration builds all DMS services with provided file name.
 */
public class DMS_Configuration {
    private static ControlService controlService;

    /**
     * Gets environment variable "LAB5_ITEMS_PATH". If variable is null, returns basic file name.
     * @return - file name as String
     *
     */
    public static String getEnv() {
        String env = System.getenv("LAB5_ITEMS_PATH");
        if (Objects.nonNull(env)) {
            System.out.printf("Будет использовано имя файла: %s.", env);
            return env;
        } else {
            System.out.printf("Будет использовано стандартное имя файла: %s.\n", "C:\\Users\\79832\\test\\test.xml");
            return "C:\\Users\\79832\\test\\test.xml";
        }
        }

    /**
     * Creates DMS ControlService object
     * @return - new DMS ControlService object
     */
    public static ControlService build(){
        if(controlService==null) {
            String fileName = getEnv();
            controlService = new ControlService(new CollectionFileService(fileName));
        }
        return controlService;
    }
}
