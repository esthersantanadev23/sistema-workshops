package br.com.workshops.controller;

import br.com.workshops.model.Workshop;
import br.com.workshops.service.WorkshopService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/workshops/*")
public class WorkshopApiController extends HttpServlet {

    private final WorkshopService workshopService = new WorkshopService();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    // GET: listar todos ou buscar por ID
    @Override
    protected void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Workshop> workshops = workshopService.listarTodos();

            objectMapper.writeValue(response.getWriter(), workshops);
            return;
        }

        try {
            Long id = Long.parseLong(pathInfo.substring(1));

            Workshop workshop = workshopService.buscarPorId(id);

            if (workshop == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                objectMapper.writeValue(response.getWriter(),
                        "Workshop não encontrado.");
                return;
            }

            objectMapper.writeValue(response.getWriter(), workshop);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(),
                    "ID inválido.");
        }
    }

    // POST: cadastrar workshop
    @Override
    protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Workshop workshop = objectMapper.readValue(
                    request.getInputStream(), Workshop.class);

            workshopService.inserir(workshop);

            response.setStatus(HttpServletResponse.SC_CREATED);
            objectMapper.writeValue(response.getWriter(),
                    "Workshop cadastrado com sucesso.");

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), e.getMessage());
        }
    }

    // PUT: atualizar workshop
    @Override
    protected void doPut(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = obterId(request);

            Workshop workshop = objectMapper.readValue(
                    request.getInputStream(), Workshop.class);

            workshop.setId(id);

            workshopService.alterar(workshop);

            objectMapper.writeValue(response.getWriter(),
                    "Workshop atualizado com sucesso.");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(),
                    "ID inválido.");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), e.getMessage());
        }
    }

    // DELETE: excluir workshop
    @Override
    protected void doDelete(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Long id = obterId(request);

            workshopService.deletar(id);

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(),
                    "ID inválido.");
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            objectMapper.writeValue(response.getWriter(), e.getMessage());
        }
    }

    // Extrai o ID da URL
    private Long obterId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            throw new IllegalArgumentException(
                    "O ID do workshop é obrigatório.");
        }

        return Long.parseLong(pathInfo.substring(1));
    }
}