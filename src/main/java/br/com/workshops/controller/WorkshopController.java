
package br.com.workshops.controller;

import br.com.workshops.model.Workshop;
import br.com.workshops.service.WorkshopService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/workshops")
public class WorkshopController extends HttpServlet {

    private final WorkshopService workshopService = new WorkshopService();

    // Armazena uma mensagem temporária na sessão
    private void definirMensagem(HttpServletRequest request, String mensagem, String tipo) {
        var sessao = request.getSession();

        sessao.setAttribute("mensagem", mensagem);
        sessao.setAttribute("tipoMensagem", tipo);

        System.out.println("=== TESTE DE MENSAGEM ===");
        System.out.println("Mensagem: " + mensagem);
        System.out.println("Tipo: " + tipo);
        System.out.println("ID da sessão: " + sessao.getId());
        System.out.println("=========================");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("novo".equals(acao)) {
            request.getRequestDispatcher("/views/workshops/form.jsp")
                    .forward(request, response);
            return;
        }

        if ("editar".equals(acao)) {
            Long id = Long.parseLong(request.getParameter("id"));

            Workshop workshop = workshopService.buscarPorId(id);

            request.setAttribute("workshop", workshop);

            request.getRequestDispatcher("/views/workshops/form.jsp")
                    .forward(request, response);
            return;
        }

        if ("excluir".equals(acao)) {
            Long id = Long.parseLong(request.getParameter("id"));

            try {
                workshopService.deletar(id);
                definirMensagem(request, "Workshop excluído com sucesso!", "sucesso");
            } catch (Exception e) {
                definirMensagem(request, "Não foi possível excluir o workshop.", "erro");
            }

            response.sendRedirect(request.getContextPath() + "/workshops");
            return;
        }

        var workshops = workshopService.listarTodos();

        int totalWorkshops = workshops.size();
        int proximosWorkshops = 0;
        int totalVagas = 0;

        for (Workshop workshop : workshops) {
            if (!workshop.getData().isBefore(LocalDate.now())) {
                proximosWorkshops++;
            }

            totalVagas += workshop.getVagas();
        }

        request.setAttribute("workshops", workshops);
        request.setAttribute("totalWorkshops", totalWorkshops);
        request.setAttribute("proximosWorkshops", proximosWorkshops);
        request.setAttribute("totalVagas", totalVagas);

        request.getRequestDispatcher("/views/workshops/lista.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

        try {
            Workshop workshop = new Workshop();

            if (id != null && !id.isBlank()) {
                workshop.setId(Long.parseLong(id));
            }

            workshop.setTitulo(request.getParameter("titulo"));
            workshop.setDescricao(request.getParameter("descricao"));
            workshop.setData(LocalDate.parse(request.getParameter("data")));
            workshop.setHorario(LocalTime.parse(request.getParameter("horario")));
            workshop.setLocal(request.getParameter("local"));
            workshop.setVagas(Integer.parseInt(request.getParameter("vagas")));

            if (workshop.getId() == null) {
                workshopService.inserir(workshop);

                response.sendRedirect(request.getContextPath()
                        + "/workshops?status=cadastrado");
            } else {
                workshopService.alterar(workshop);

                response.sendRedirect(request.getContextPath()
                        + "/workshops?status=atualizado");
            }

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath()
                    + "/workshops?status=erro");
        }
    }
}