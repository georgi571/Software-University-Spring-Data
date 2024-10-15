package softuni.exam.database;
//TestDbPrimaryKeys
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class TestDbPrimaryKeys {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    @Test
    void testPrimaryKeys() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        ResultSet primaryKeyVolcanologists = metaData.getPrimaryKeys(null, null, "DEVICES");
        ResultSet primaryKeyCountries = metaData.getPrimaryKeys(null, null, "SELLERS");
        ResultSet primaryKeyVolcanoes = metaData.getPrimaryKeys(null, null, "SALES");

        List<String> actualResult = new ArrayList<>();

        primaryKeyVolcanologists.next();
        actualResult.add(primaryKeyVolcanologists.getString("TABLE_NAME"));
        actualResult.add(primaryKeyVolcanologists.getString("COLUMN_NAME"));

        primaryKeyCountries.next();
        actualResult.add(primaryKeyCountries.getString("TABLE_NAME"));
        actualResult.add(primaryKeyCountries.getString("COLUMN_NAME"));

        primaryKeyVolcanoes.next();
        actualResult.add(primaryKeyVolcanoes.getString("TABLE_NAME"));
        actualResult.add(primaryKeyVolcanoes.getString("COLUMN_NAME"));

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("DEVICES");
        expectedResult.add("ID");
        expectedResult.add("SELLERS");
        expectedResult.add("ID");
        expectedResult.add("SALES");
        expectedResult.add("ID");

        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}