package model;

import etu1899.framework.ModelView;
import etu1899.framework.annotations.Url;


public class Personne {
    
    @Url(link = "personne")
    public ModelView JSP(){
        return new ModelView("Personne.jsp");
    }
}
