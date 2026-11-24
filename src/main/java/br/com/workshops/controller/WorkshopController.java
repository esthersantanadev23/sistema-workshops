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

            workshopService.deletar(id);

            response.sendRedirect(request.getContextPath() + "/workshops");
            return;
        }

        request.setAttribute("workshops", workshopService.listarTodos());

        request.getRequestDispatcher("/views/workshops/lista.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");

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
        } else {
            workshopService.alterar(workshop);
        }

        response.sendRedirect(request.getContextPath() + "/workshops");
    }
}