<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.workshops.model.Workshop" %>

<%
    Workshop workshop = (Workshop) request.getAttribute("workshop");

    boolean editando = workshop != null;

    String titulo = editando ? workshop.getTitulo() : "";
    String descricao = editando ? workshop.getDescricao() : "";
    String data = editando ? workshop.getData().toString() : "";
    String horario = editando ? workshop.getHorario().toString() : "";
    String local = editando ? workshop.getLocal() : "";
    String vagas = editando ? workshop.getVagas().toString() : "";
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">

    <title>
        <%= editando ? "Editar Workshop" : "Novo Workshop" %>
    </title>

    <link rel="stylesheet"
          href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<div class="container">

    <h1>
        <%= editando ? "Editar Workshop" : "Novo Workshop" %>
    </h1>

    <div class="form-container">

        <form action="<%= request.getContextPath() %>/workshops" method="post">

            <% if (editando) { %>
                <input type="hidden"
                       name="id"
                       value="<%= workshop.getId() %>">
            <% } %>

            <div class="form-group">
                <label for="titulo">Título:</label>

                <input
                    type="text"
                    id="titulo"
                    name="titulo"
                    value="<%= titulo %>"
                    required>
            </div>

            <div class="form-group">
                <label for="descricao">Descrição:</label>

                <textarea
                    id="descricao"
                    name="descricao"><%= descricao %></textarea>
            </div>

            <div class="form-row">

                <div class="form-group">
                    <label for="data">Data:</label>

                    <input
                        type="date"
                        id="data"
                        name="data"
                        value="<%= data %>"
                        required>
                </div>

                <div class="form-group">
                    <label for="horario">Horário:</label>

                    <input
                        type="time"
                        id="horario"
                        name="horario"
                        value="<%= horario %>"
                        required>
                </div>

            </div>

            <div class="form-group">
                <label for="local">Local:</label>

                <input
                    type="text"
                    id="local"
                    name="local"
                    value="<%= local %>"
                    required>
            </div>

            <div class="form-group">
                <label for="vagas">Vagas:</label>

                <input
                    type="number"
                    id="vagas"
                    name="vagas"
                    value="<%= vagas %>"
                    min="1"
                    required>
            </div>

            <div class="form-actions">

                <button type="submit" class="btn btn-primary">
                    <%= editando ? "Salvar Alterações" : "Cadastrar Workshop" %>
                </button>

                <a
                    class="btn btn-secondary"
                    href="<%= request.getContextPath() %>/workshops">
                    Cancelar
                </a>

            </div>

        </form>

    </div>

</div>

</body>
</html>