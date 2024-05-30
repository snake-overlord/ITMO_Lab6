package validators;

import java.text.NumberFormat;
import java.text.ParsePosition;

public class LongValidator extends BaseValidator{
    @Override
    public boolean validate(String line) {
        boolean result = false;
        Long ecount = null;
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(line, pos);
        if (pos.getIndex() == line.length() | !(line.length() > 19)) {
            ecount = Long.parseLong(line);
        }
        if(ecount!=null){
            result = true;
        }
        return result;
    }
    @Override
    public Long validated(String line){
        if(validate(line)){
            return Long.parseLong(line);
        }
        return null;
    }
}
