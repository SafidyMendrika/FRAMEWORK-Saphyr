package etu1899.framework;

public class Mapping {
    String className;
    String methodName;

    public Mapping(){}
    public Mapping(String className,String method){
        this.setClassName(className);
        this.setMethodName(method);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String method) {
        methodName = method;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
    @Override
    public String toString() {
        return "Mapping{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
