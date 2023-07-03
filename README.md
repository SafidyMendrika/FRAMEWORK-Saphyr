# FRAMEWORK-Saphyr
 
> # Needed library 
> - **GSON** : library of google to jsonize an object :  **[downlaod GSON](https://jar-download.com/artifacts/com.google.code.gson/gson/2.8.2/source-code)**
> - **servlet-api** : library how provide the HTTP's object :  **[downlaod servlet-api](http://www.java2s.com/Code/Jar/s/Downloadservletapijar.htm)**
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

## @Copyright 2023 [RAZAFIMALAZA Safidy Mendrika ETU001899 ](https://github.com/SafidyMendrika) 
