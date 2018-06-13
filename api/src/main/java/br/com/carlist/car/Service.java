package br.com.carlist.car;


import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    private Connection connection = null;

    public Service() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.connection = DriverManager.getConnection(
                "jdbc:mysql://0.0.0.0:3306/cars_crud", "root", "mypass"
            );

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Car> select() throws SQLException{
        List<Car> list = new ArrayList<Car>();
        ResultSet rs = this.connection
            .prepareStatement("select * from cars").executeQuery();

        while (rs.next()) {
            list.add(new Car(
                Integer.parseInt(rs.getString("id")),
                rs.getString("nome"),
                rs.getString("tipo")
            ));
        }

        return list;
    }
}