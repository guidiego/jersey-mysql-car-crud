package br.com.carlist.car;


import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {
    private Connection connection = null;

    public Service() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String uri = System.getenv("DB_URI");
            String port = System.getenv("DB_PORT");
            String usr = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            String jdbc_uri = "jdbc:mysql://";
            jdbc_uri += uri != null ? uri : "0.0.0.0";
            jdbc_uri += ":" + (port != null ? port : "3306") + "/";
            jdbc_uri += "cars_crud";

            if (usr == null) {
                usr = "root";
            }

            System.out.println(jdbc_uri);
            System.out.println(usr);
            System.out.println(pass);
            this.connection = DriverManager.getConnection(jdbc_uri, usr, pass);
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
                rs.getString("name"),
                rs.getString("type")
            ));
        }

        return list;
    }

    public Car insert(Car car) throws SQLException{
        PreparedStatement ps = this.connection
            .prepareStatement(
                "insert into cars (name, type) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

        ps.setString(1, car.name);
        ps.setString(2, car.type);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        car.id = rs.getInt(1);

        return car;
    }

    public Car update(Car car) throws SQLException {
        PreparedStatement ps = this.connection
            .prepareStatement(
                "update cars set name = ?, type = ? where id = ?"
            );

        ps.setString(1, car.name);
        ps.setString(2, car.type);
        ps.setInt(3, car.id);
        ps.executeUpdate();

        return car;
    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement ps = this.connection
            .prepareStatement("delete from cars where id = ?");

        ps.setInt(1, id);
        ps.executeUpdate();
    }
}