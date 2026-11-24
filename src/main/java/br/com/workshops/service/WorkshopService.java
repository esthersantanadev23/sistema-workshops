package br.com.workshops.service;

import br.com.workshops.dao.WorkshopDAO;
import br.com.workshops.model.Workshop;

import java.util.List;

public class WorkshopService {

    private final WorkshopDAO workshopDAO;

    public WorkshopService() {
        this.workshopDAO = new WorkshopDAO();
    }

    public List<Workshop> listarTodos() {
        return workshopDAO.listarTodos();
    }

    public Workshop buscarPorId(Long id) {
        return workshopDAO.buscarPorId(id);
    }

    public void inserir(Workshop workshop) {
        validar(workshop);
        workshopDAO.inserir(workshop);
    }

    public void alterar(Workshop workshop) {
        validar(workshop);

        if (workshop.getId() == null) {
            throw new IllegalArgumentException("O ID do workshop é obrigatório.");
        }

        workshopDAO.alterar(workshop);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID do workshop é obrigatório.");
        }

        workshopDAO.deletar(id);
    }

    private void validar(Workshop workshop) {
        if (workshop == null) {
            throw new IllegalArgumentException("O workshop é obrigatório.");
        }

        if (workshop.getTitulo() == null || workshop.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título é obrigatório.");
        }

        if (workshop.getData() == null) {
            throw new IllegalArgumentException("A data é obrigatória.");
        }

        if (workshop.getHorario() == null) {
            throw new IllegalArgumentException("O horário é obrigatório.");
        }

        if (workshop.getLocal() == null || workshop.getLocal().isBlank()) {
            throw new IllegalArgumentException("O local é obrigatório.");
        }

        if (workshop.getVagas() == null || workshop.getVagas() <= 0) {
            throw new IllegalArgumentException("O número de vagas deve ser maior que zero.");
        }
    }
}
