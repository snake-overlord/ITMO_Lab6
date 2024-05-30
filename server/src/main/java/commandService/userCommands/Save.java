package commandService.userCommands;

import DMS.DMS_ControlService.ControlService;
import commandService.CollectionRepository;

public class Save {
    ControlService controlService;
    public Save(ControlService controlService) {
        this.controlService = controlService;
    }

    public void execute() {
        controlService.update(CollectionRepository.getInstance().getVector());
        }

}
