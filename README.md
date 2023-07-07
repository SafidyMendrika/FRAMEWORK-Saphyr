# FRAMEWORK-Saphyr
### Achievement : 
    - [1;8]U[10;14]
> # Needed library 
> - **GSON** : library of google to jsonize an object :  **[downlaod GSON](https://jar-download.com/artifacts/com.google.code.gson/gson/2.8.2/source-code)**
> - **servlet-api** : library how provide the HTTP's object :  **[download servlet-api](http://www.java2s.com/Code/Jar/s/Downloadservletapijar.htm)**
---

# Initials Parameters
> - ### Add the **'framework.jar'** file in your classpath or your environement
> - ### By default , create a web.xml under WEB-INF folder and then put these code : 
```
<web-app >
      <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu1899.framework.servlet.FrontServlet</servlet-class>

    </servlet>
    <servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

</web-app>
```
> - ### **PS  :**  If it doesn't work ,  copy all .java files near your environement and compile these together

# Devellopement env
> - **Annotaions :** 
>> - Url(link = "link.do") : SCOPE => METHOD
>> - Auth(profile = "profile who can access to the method") : SCOPE => METHOD
>> - Scope(value = "singleton/global") //the scope of the class// : SCOPE : CLASS 
>> - ParameterName(value = "name of the parameter") //the name of the parameter// : SCOPE : PARAMETER
> - **Contraints :**
>> - all method annoted by Url ,if have a paremeter,must be annoted : @ParameterName("the parameter name")
>> - if you want to have access to httpSession in model , you can already put a Field ***public HashMap<String,Ovject> session***


## @Copyright 2023 [RAZAFIMALAZA Safidy Mendrika ETU001899 ](https://github.com/SafidyMendrika) 
