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

        <div class="dashboard-cards">

            <div class="dashboard-card">
                <div class="card-icon">📚</div>
                <div class="card-info">
                    <h3>Total de Workshops</h3>
                    <p id="totalWorkshops">${totalWorkshops}</p>
                </div>
            </div>

            <div class="dashboard-card">
                <div class="card-icon">📅</div>
                <div class="card-info">
                    <h3>Próximos Workshops</h3>
                    <p id="proximosWorkshops">${proximosWorkshops}</p>
                </div>
            </div>

            <div class="dashboard-card">
                <div class="card-icon">👥</div>
                <div class="card-info">
                    <h3>Total de Vagas</h3>
                    <p id="totalVagas">${totalVagas}</p>
                </div>
            </div>

        </div>

        <%
            String status = request.getParameter("status");
            String mensagem = null;
            String tipo = "sucesso";

            if ("cadastrado".equals(status)) {
                mensagem = "Workshop cadastrado com sucesso!";
            } else if ("atualizado".equals(status)) {
                mensagem = "Workshop atualizado com sucesso!";
            } else if ("erro".equals(status)) {
                mensagem = "Não foi possível salvar o workshop. Verifique os dados e tente novamente.";
                tipo = "erro";
            }

            if (mensagem != null) {
        %>
            <div class="alert alert-<%= tipo %>">
                <%= mensagem %>
            </div>
        <%
            }
        %>

    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/workshops?acao=novo">
        Novo Workshop
    </a>
    <div class="search-container">
        <input type="text" id="buscaWorkshop"
            placeholder="Buscar por título ou local...">
    </div>
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
<script>
    document.getElementById("buscaWorkshop").addEventListener("input", function () {
        const busca = this.value.toLowerCase();
        const linhas = document.querySelectorAll("tbody tr");

        linhas.forEach(function (linha) {
            const titulo = linha.cells[1].textContent.toLowerCase();
            const local = linha.cells[5].textContent.toLowerCase();

            if (titulo.includes(busca) || local.includes(busca)) {
                linha.style.display = "";
            } else {
                linha.style.display = "none";
            }
        });
    });
</script>
</body>
</html>