package clientCommandService;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for multiple script files handling. Prevents script recursion.
 */
public class OpenUniqueFile {
    List<String> fileNames;
    public OpenUniqueFile(){
        fileNames = new ArrayList<>();
    }

    /**
     * Checks if the file has been opened earlier in the current script session.
     * @return true if the file hasn't been opened yet.
     */
    public boolean check(String fileName){
        if(fileNames.contains(fileName)){
                System.err.print("Script recursion!\n");
            return false;
        }
        else{
            fileNames.add(fileName);
            return true;
        }
    }
}
