// example class

package example;

import java.util.ArrayList;
import java.util.HashMap;

import etu1899.framework.ModelView;
import etu1899.framework.annotations.Url;
import etu1899.framework.annotations.RestAPI;
import etu1899.framework.annotations.ParameterName;


public class Personne {
    String nom;
    int age;
    public HashMap<String,Object> session;

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
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

    @Url(link = "personne.do")
    public ModelView JSP(){
        return new ModelView("Personne.jsp");
    }

    @Url(link = "list.do")
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

    @Url(link = "final.do")
    @RestAPI("")
    public ArrayList<Personne> getFinal(){
        ModelView mv = new ModelView();
        ArrayList<Personne> pers = new ArrayList<Personne>();

        pers.add(new Personne("Mendrika"));
        pers.add(new Personne("Safidy"));
        pers.add(new Personne("Badoda"));
        pers.add(new Personne("POPOl"));

        return pers;
    }
    @Url(link = "manampy.do")
    public ModelView manampy(){
        ModelView mv = new ModelView();
        ArrayList<Personne> pers = new ArrayList<Personne>();

        pers.add(this);

        System.out.println(this.getNom() +" : "+this.getAge());

        mv.additem("list",pers);
        mv.setView("list.jsp");
        return mv;
    }
    @Url(link = "details.do")
    public ModelView details(){
        ModelView mv = new ModelView();

        String nom = "";
        String prenom = "";
/*
        if (id == 1) {
            nom = "mendrika";
            prenom = "Safidy";
        }else if(id == 2){

            nom = "Popol";
            prenom = "Kely";
        }else if(id == 3){

            nom = "Design";
            prenom = " er";
        }
        */
        nom = "mendrika";
        prenom = "Safidy";
        
        mv.additem("nom",nom);
        mv.additem("prenom",prenom);
        mv.setView("details.jsp");

        //mv.setJsonizable(true);
        return mv;
    }
    @Url(link = "session.do")
    public ModelView session(){
        ModelView mv = new ModelView();

        String admin =  (String) this.session.get("admin");
        
        mv.additem("admin",admin);
        
        mv.setView("testsession.jsp");

        //mv.setJsonizable(true);   
        return mv;
    }
}
