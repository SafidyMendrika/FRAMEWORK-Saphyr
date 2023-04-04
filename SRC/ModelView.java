package etu1899.framework;

import java.util.HashMap;

public class ModelView {
    String view;
    HashMap<String,Object> data;

    public ModelView(){
        this.setView("");
        this.setData(new HashMap<String,Object>());
        
    }
    public ModelView(String view){
        this.setView(view);
        this.setData(new HashMap<String,Object>());
    }
    public void setView(String view) {
        this.view = view;
    }
    public void additem(String key,Object value){
        this.getData().put(key, value);

    }
    public String getView() {
        return view;
    }
    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public HashMap<String, Object> getData() {
        return data;
    }

}
