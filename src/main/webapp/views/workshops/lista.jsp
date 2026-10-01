<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.workshops.model.Workshop" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Workshops</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<div class="container">

    <h1>Workshops</h1>

    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/workshops?acao=novo">
        Novo Workshop
    </a>

    <div class="table-container">

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Descrição</th>
                    <th>Data</th>
                    <th>Horário</th>
                    <th>Local</th>
                    <th>Vagas</th>
                    <th>Ações</th>
                </tr>
            </thead>

            <tbody>

            <%
                List<Workshop> workshops =
                    (List<Workshop>) request.getAttribute("workshops");

                if (workshops != null && !workshops.isEmpty()) {

                    for (Workshop workshop : workshops) {
            %>

                <tr>
                    <td><%= workshop.getId() %></td>
                    <td><%= workshop.getTitulo() %></td>
                    <td><%= workshop.getDescricao() %></td>
                    <td><%= workshop.getData() %></td>
                    <td><%= workshop.getHorario() %></td>
                    <td><%= workshop.getLocal() %></td>
                    <td><%= workshop.getVagas() %></td>

                    <td>
                        <a class="btn-edit"
                           href="<%= request.getContextPath() %>/workshops?acao=editar&id=<%= workshop.getId() %>">
                            Editar
                        </a>

                        |

                        <a class="btn-delete"
                           href="<%= request.getContextPath() %>/workshops?acao=excluir&id=<%= workshop.getId() %>"
                           onclick="return confirm('Deseja realmente excluir este workshop?');">
                            Excluir
                        </a>
                    </td>
                </tr>

            <%
                    }

                } else {
            %>

                <tr>
                    <td colspan="8" class="empty">
                        Nenhum workshop cadastrado.
                    </td>
                </tr>

            <%
                }
            %>

            </tbody>

        </table>

    </div>

</div>

</body>
</html>