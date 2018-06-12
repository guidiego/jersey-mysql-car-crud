package br.com.carlist.car;

import br.com.carlist.sql.*;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

class Service extends SqlLayer {
    public Service() {
        super("cars");
    }

    public void save(Car c) throws SQLException {
        if (c.id == null) {
            String[] params = new String[]{c.name, c.type, c.id.toString()};
            super.insert("(name, type)", "(?, ?)", params);
        } else {
            String updateStr = "name = " + c.name + ", type = " + c.type;
            super.update(updateStr, c.id.toString());
        }
    }

    public ArrayList<Car> find() throws SQLException {
        ArrayList<Car> cars = new ArrayList<Car>();
        ResultSet rs = super.select();

        while (rs.next()) {
            cars.add(new Car(
                rs.getString("name"),
                rs.getString("type")
            ));
        }

        return cars;
    }

    public Car findOne(Integer id) throws SQLException {
        ResultSet rs = super.selectWhere(id);
        return new Car(rs.getString("nome"), rs.getString("type"));
    }

	public void remove(Integer id) throws SQLException {
        super.delete(id);
	}
}