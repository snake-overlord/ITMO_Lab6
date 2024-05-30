package commandService;


import models.Organization;

import java.time.LocalDateTime;
import java.util.Vector;

public interface ICollectionRepository {
    void setDate();
    LocalDateTime getDate();
    void addItem(Organization organization);
    void addItems(Vector<Organization> collection);
    Vector<Organization> getVector();

    void deleteItem(Organization org);

    void clearCollection();

    void addItem(Organization org, Long id);
}
