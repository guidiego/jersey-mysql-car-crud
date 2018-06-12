package br.com.carlist.sql;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlLayer {
    private String table;
    private Connection connect = null;
    private String selectQuery = "select * from ?";
    private String insertQuery = "insert into ? ";
    private String updateQuery = "update ? set ? where id = ?";
    private String removeQuery = "delete ? where id = ?";

    public SqlLayer(String table) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.table = table;
            this.connect = DriverManager.getConnection(
                "jdbc:mysql://localhost/trabandre?user=root&password=mypass"
            );
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private PreparedStatement getPrepareStatement(String query) throws SQLException {
        PreparedStatement preparedStatement =
        this.connect.prepareStatement(query);

        preparedStatement.setString(1, this.table);
        return preparedStatement;
    }

    public void insert(String insertColumns, String insertValues, String[] params) throws SQLException {
        PreparedStatement ps = this.getPrepareStatement(
            this.insertQuery + insertColumns + " VALUES " + insertValues
        );

        for (int i = 0; i < params.length; i++) {
            ps.setString(i + 1, params[i]);
        }

        ps.executeQuery();
    }

    public void update(String updateStr, String id) throws SQLException {
        PreparedStatement ps = this.getPrepareStatement(this.updateQuery);

        ps.setString(2, updateStr);
        ps.setString(3, id);

        ps.executeQuery();
    }

    public void delete(Integer id) throws SQLException {
        PreparedStatement ps = this.getPrepareStatement(this.removeQuery);
        ps.setString(2, id.toString());

        ps.executeQuery();
    }

    public ResultSet select() throws SQLException {
        PreparedStatement ps = this.getPrepareStatement(this.selectQuery);
        return ps.executeQuery();
    }

    public ResultSet selectWhere(Integer id) throws SQLException {
        PreparedStatement ps = this.getPrepareStatement(this.selectQuery + " where id = ?");
        ps.setString(2, id.toString());
        return ps.executeQuery();
    }
}