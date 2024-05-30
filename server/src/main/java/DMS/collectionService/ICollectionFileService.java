package DMS.collectionService;


import models.Organization;

import java.io.FileNotFoundException;
import java.util.Vector;

public interface ICollectionFileService {
    Vector<Organization> readVector() throws FileNotFoundException;
    void writeVector(Vector<Organization> data);
    void deleteVector(Vector<Organization> data);
}