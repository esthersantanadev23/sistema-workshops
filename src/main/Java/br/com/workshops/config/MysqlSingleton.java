package br.com.workshops.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlSingleton {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_workshops";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static MysqlSingleton instance;
    private Connection conexao;

    private MysqlSingleton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado no projeto.", e);
        }
    }

    public static synchronized MysqlSingleton getInstance() {
        if (instance == null) {
            instance = new MysqlSingleton();
        }
        return instance;
    }

    public Connection obterConexao() throws SQLException {
        if (this.conexao == null || this.conexao.isClosed()) {
            this.conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return this.conexao;
    }
    public ResultSet executar(String sql, Object... parametros) throws SQLException {
    var ps = obterConexao().prepareStatement(sql);

    for (int i = 0; i < parametros.length; i++) {
        ps.setObject(i + 1, parametros[i]);
    }

    return ps.executeQuery();
}

    public int executarUpdate(String sql, Object... parametros) throws SQLException {
        var ps = obterConexao().prepareStatement(sql);

        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }

        return ps.executeUpdate();
    }
}