package etu1899.framework;

public class ModelView {
    String view;

    public ModelView(String view){
        this.setView(view);
    }
    public void setView(String view) {
        this.view = view;
    }
    public String getView() {
        return view;
    }
}
