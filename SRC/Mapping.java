package etu1899.framework;

import java.lang.reflect.Method;

public class Mapping {
    String className;
    Method method;

    public Mapping(){}
    public Mapping(String className,Method method){
        this.setClassName(className);
        this.setMethod(method);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getClassName() {
        return className;
    }

    public Method getMethod() {
        return method;
    }
    @Override
    public String toString() {
        return "Mapping{" +
                "className='" + className + '\'' +
                ", method='" + method.getName() + '\'' +
                '}';
    }
}
