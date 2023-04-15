package main.main;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectJDBC {

    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "12345";


    public Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        return DriverManager.getConnection(url, user, password);
    }

    public void createTableJDBC(){
        String SQL = "CREATE TABLE test(id integer NOT NULL PRIMARY KEY, name varchar, value integer)";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()){
             int x = stmt.executeUpdate(SQL);
                if (x == 0) System.out.println("Successfully Created");
                else System.out.println("Creation Failed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void selectFromJDBC() {
        String SQL = "SELECT * FROM test";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            displayActor(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void insertIntoJDBC(List<DataJDBC> listData) {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            for(DataJDBC dataJDBC: listData) {
                String sql = "insert into test values('" +dataJDBC.getId()+ "', '" +dataJDBC.getName()+ "', '" +dataJDBC.getValue()+ "')";

                int x = stmt.executeUpdate(sql);
                if (x > 0) System.out.println("Successfully Inserted");
                else System.out.println("Insert Failed");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateDataJDBC(DataJDBC data) {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
//                String sql = "UPDATE test SET name='" +data.getName()+ "', WHERE id='" +data.getId()+"')";
            PreparedStatement updateStmt =
                    conn.prepareStatement("UPDATE test SET name = ? WHERE id = ?");
            updateStmt.setString(1, "name");
            updateStmt.setInt(2, 2);
                int x = updateStmt.executeUpdate();
                if (x > 0) System.out.println("Successfully updated");
                else System.out.println("update Failed");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<DataJDBC> preparingInsert(Object[] insert){
        List<DataJDBC> list = new ArrayList<>();
        List<String> insertString = (List<String>) insert[0];
        List<Integer> insertInteger = (List<Integer>) insert[1];
        for (int i = 0; i < insertString.size(); i++) {
            DataJDBC dataJDBC = new DataJDBC();
            dataJDBC.setId(i+4);
            dataJDBC.setName(insertString.get(i));
            dataJDBC.setValue(insertInteger.get(i));
            list.add(dataJDBC);
        }
        return list;
    }

    private void displayActor(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("id") + "\t"
                    + rs.getString("name") + "\t"
                    + rs.getString("value"));
        }
    }
}
