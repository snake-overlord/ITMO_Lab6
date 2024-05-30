package network;

import models.Organization;
import models.OrganizationType;
import validators.LongValidator;
import validators.OrgTypeValidator;

import java.util.ArrayList;

public class CommandList {
    public static ArrayList<CommandRequest> get(){
        ArrayList<CommandRequest> commands = new ArrayList<>();
        commands.add(new CommandRequest<Organization>("add", null, 0));
        commands.add(new CommandRequest<Organization>("add_if_min",  null, 0));
        commands.add(new CommandRequest<Organization>("add_if_max",  null, 0));
        commands.add(new CommandRequest<>("clear",  null, 0));
        commands.add(new CommandRequest<Long>("count_greater_than_employees_count",  new LongValidator(), 1));
        commands.add(new CommandRequest<OrganizationType>("filter_by_type", new OrgTypeValidator(), 1));
        commands.add(new CommandRequest<>("group_counting_by_type",  null, 0));
        commands.add(new CommandRequest<>("head",  null, 0));
        commands.add(new CommandRequest<>("help",  null, 0));
        commands.add(new CommandRequest<>("info",  null, 0));
        commands.add(new CommandRequest<>("show",  null, 0));
        commands.add(new CommandRequest<>("print_field_ascending_type",  null, 0));
        commands.add(new CommandRequest<Long>("remove_by_id",  new LongValidator(), 1));
        commands.add(new CommandRequest<Long>("update_by_id", new LongValidator(), 1));
        return commands;
    }
}
