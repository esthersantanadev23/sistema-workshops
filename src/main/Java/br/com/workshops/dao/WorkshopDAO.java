package br.com.workshops.dao;

import br.com.workshops.model.Workshop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class WorkshopDAO extends MysqlDAO {

    public List<Workshop> listarTodos() {
        String sql = "SELECT * FROM workshops ORDER BY id";

        List<Workshop> lista = new ArrayList<>();

        try (ResultSet rs = executar(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar workshops.", e);
        }

        return lista;
    }

    public Workshop buscarPorId(Long id) {
        String sql = "SELECT * FROM workshops WHERE id = ?";

        try (ResultSet rs = executar(sql, id)) {
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar workshop.", e);
        }

        return null;
    }

    public void inserir(Workshop workshop) {
        String sql = "INSERT INTO workshops " +
                "(titulo, descricao, data, horario, local, vagas) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            executarUpdate(
                    sql,
                    workshop.getTitulo(),
                    workshop.getDescricao(),
                    workshop.getData(),
                    workshop.getHorario(),
                    workshop.getLocal(),
                    workshop.getVagas()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir workshop.", e);
        }
    }

    public void alterar(Workshop workshop) {
        String sql = "UPDATE workshops SET " +
                "titulo = ?, descricao = ?, data = ?, horario = ?, " +
                "local = ?, vagas = ? WHERE id = ?";

        try {
            executarUpdate(
                    sql,
                    workshop.getTitulo(),
                    workshop.getDescricao(),
                    workshop.getData(),
                    workshop.getHorario(),
                    workshop.getLocal(),
                    workshop.getVagas(),
                    workshop.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar workshop.", e);
        }
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM workshops WHERE id = ?";

        try {
            executarUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar workshop.", e);
        }
    }

    private Workshop mapear(ResultSet rs) throws SQLException {
        Workshop workshop = new Workshop();

        workshop.setId(rs.getLong("id"));
        workshop.setTitulo(rs.getString("titulo"));
        workshop.setDescricao(rs.getString("descricao"));

        Date data = rs.getDate("data");
        if (data != null) {
            workshop.setData(data.toLocalDate());
        }

        Time horario = rs.getTime("horario");
        if (horario != null) {
            workshop.setHorario(horario.toLocalTime());
        }

        workshop.setLocal(rs.getString("local"));
        workshop.setVagas(rs.getInt("vagas"));

        return workshop;
    }
}