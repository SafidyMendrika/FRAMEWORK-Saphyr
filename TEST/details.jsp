<h1>eto details leaka badoda</h1>
<% 
    String nom = (String) request.getAttribute("nom");
    String prenom = (String) request.getAttribute("prenom"); 

%>
<h2>nom : <%= nom %></h2>
<h2>prenom : <%= prenom %></h2>