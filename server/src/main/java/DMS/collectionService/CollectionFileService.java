package DMS.collectionService;


import models.Organization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
/**
 * Class for collection file handling.
 */
public class CollectionFileService implements ICollectionFileService{
    private static final Logger logger = LogManager.getLogger(CollectionFileService.class);
    String file;

    /**
     *
     * @param name - xml file name.
     */
    public CollectionFileService(String name){
        this.file = name;
    }


    @Override
    public Vector<Organization> readVector(){
        Vector<Organization> deserialized = new Vector<>();

        try(FileInputStream fis = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fis)) {
            final boolean[] e = {false};
            decoder.setExceptionListener(ex -> e[0] = true);
            deserialized = (Vector<Organization>) decoder.readObject();
            if(e[0]){
                logger.error("XML data is corrupted. Continue with an empty collection...\n");
                return new Vector<>();
            }
            for(Organization org : deserialized){
                if(org.validate())
                    logger.info(org);
                else
                    logger.error("Organization {} has invalid fields! \n", org.getName());
            }
        } catch (IOException e) {
            logger.error("IO error occurred: {}", e.getMessage());
        }
        return deserialized;
    }


    @Override
    public void writeVector(Vector<Organization> data) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.writeObject(data);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }

    @Override
    public void deleteVector(Vector<Organization> data) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)))) {
            encoder.remove(data);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
        }
    }
}
