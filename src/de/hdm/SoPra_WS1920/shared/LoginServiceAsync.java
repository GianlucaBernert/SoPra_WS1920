
import java.util.*;

/**
 * 
 */
public interface LoginServiceAsync {

    /**
     * 
     */
    public void login(in requestURL:String, in async:AsyncCallback<LoginInfo>): LoginInfo();

}