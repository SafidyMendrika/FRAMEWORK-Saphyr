package model;

import java.util.ArrayList;

import etu1899.framework.ModelView;
import etu1899.framework.annotations.Url;


public class Personne {
    String nom;
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public Personne(){}
    public Personne(String nom){
        this.setNom(nom);
    }

    @Url(link = "personne")
    public ModelView JSP(){
        return new ModelView("Personne.jsp");
    }

    @Url(link = "list")
    public ModelView findAll(){
        ModelView mv = new ModelView();
        ArrayList<Personne> pers = new ArrayList<Personne>();

        pers.add(new Personne("Mendrika"));
        pers.add(new Personne("Safidy"));
        pers.add(new Personne("Badoda"));
        pers.add(new Personne("POPOl"));

        mv.additem("list",pers);
        mv.setView("list.jsp");
        return mv;
    }
}
