package lab_5.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lab_5.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        try (InputStream input = MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            config = new HikariConfig(prop);
            ds = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void createTablesStructure() throws SQLException {
        String createCar = """
                create table if not exists car (
                    vin         text unique, 
                    brand        text, 
                    plate_number text,
                    release_date date, 
                    price_per_day float, 
                    mileage     int ,
                    primary key (vin)
                    );
                """;
        String createRenter = """
                create table if not exists renter
                (
                        document_id      text unique,
                        first_name       text,
                        last_name        text,
                        driver_licence   text unique,
                        primary key (document_id)
                )
                """;

        String createCarRental = """
                create table if not exists car_rental
                (
                        id                serial unique,
                        car_vin               text,
                        renter_id            text,
                        pick_up_location    text,
                        drop_off_location   text,
                        start_date         date,
                        end_date           date,
                        total_price        double,
                        primary key (id),
                        foreign key (car_vin) references car (vin),
                        foreign key (renter_id) references renter (document_id)
                );
                """;
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.execute(createCar);
            st.execute(createRenter);
            st.execute(createCarRental);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }
}