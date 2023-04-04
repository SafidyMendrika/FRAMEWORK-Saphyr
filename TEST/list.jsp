<%@page import="java.util.ArrayList,model.Personne" %>

<h1>eto liste leaka badoda</h1>
<% ArrayList<Personne> list = (ArrayList<Personne>) request.getAttribute("list"); %>

<% for(Personne p : list){ %>
    <p><%= p.getNom() %></p>
<% } %>
