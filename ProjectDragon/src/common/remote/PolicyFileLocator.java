package common.remote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

//TODO Do more to point out that this is not my (robinandersson) code (although I adapted it to fit the project)? (Code taken from http://code.nomad-labs.com/2010/03/26/an-improved-rmi-tutorial-with-eclipse/)
/**
 * class to locate our most "secure" policy file
 *
 * @author srasul
 * @author robinandersson
 *
 */
public class PolicyFileLocator {

    public static final String POLICY_FILE_NAME = "/common/remote/allow_all.policy";

    public static String getLocationOfPolicyFile() {
        try {
        	
            File tempFile = File.createTempFile("rmi", ".policy");            
            InputStream is = PolicyFileLocator.class.getResourceAsStream(POLICY_FILE_NAME);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            int read = 0;
            
            while((read = is.read()) != -1) {
                writer.write(read);
            }
            
            writer.close();
            tempFile.deleteOnExit();
            return tempFile.getAbsolutePath();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
