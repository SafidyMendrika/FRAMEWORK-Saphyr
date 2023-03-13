package etu1899.framework;

public class Mapping {
    String className;
    String Method;

    public Mapping(){}
    public Mapping(String className,String method){
        this.setClassName(className);
        this.setMethod(method);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getClassName() {
        return className;
    }

    public String getMethod() {
        return Method;
    }
}
