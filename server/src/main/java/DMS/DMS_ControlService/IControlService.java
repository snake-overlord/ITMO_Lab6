package DMS.DMS_ControlService;


import models.Organization;

import java.io.FileNotFoundException;
import java.util.Vector;

public interface IControlService {
    Vector<Organization> create();
    Vector<Organization> read() throws FileNotFoundException;
    void update(Vector<Organization> current);
    void delete(Vector<Organization> org);

}
