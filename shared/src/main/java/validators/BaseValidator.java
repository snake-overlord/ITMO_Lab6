package validators;

import java.io.Serializable;

public abstract class BaseValidator implements Serializable {

    private static final long serialVersionUID = 1L;
    public abstract boolean validate(String line);
    public abstract <T> T validated(String line);
}
