package network;

import models.Organization;
import validators.BaseValidator;

import java.io.Serializable;

public class CommandRequest<T> implements  Serializable{
   private static final long serialVersionUID = 1L;
    T T;
    private final BaseValidator validator;
    private final int argsNum;
    private final String name;
    private Organization org;
    public CommandRequest(String name, BaseValidator validator, int argsNum){
        this.name = name;
        this.validator = validator;
        this.argsNum = argsNum;
    }

    public String getName() {
        return name;
    }

    public void setArg(T t) {
        T = t;
    }

    public void setOrg(Organization org){
        this.org = org;
    }


    public T getArgs(){
        return T;
    }
    public Organization getOrg(){
        return org;
    }
    public BaseValidator getValidator(){
        return  validator;
    }
    public int getArgsNum(){
        return argsNum;
    }
}
