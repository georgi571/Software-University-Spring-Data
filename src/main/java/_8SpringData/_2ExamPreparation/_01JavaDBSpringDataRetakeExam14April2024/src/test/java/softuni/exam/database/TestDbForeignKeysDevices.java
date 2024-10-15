package softuni.exam.database;
//TestDbForeignKeysDevices
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
public class TestDbForeignKeysDevices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Test
    void testForeignKeysVolcanologists() throws SQLException {
        DatabaseMetaData metaData = getDatabaseMetaData();

        ResultSet volcanologistsKeys = metaData.getImportedKeys(null, null, "DEVICES");
        ResultSet volcanoesKeys = metaData.getImportedKeys(null, null, "SALES");

        List<String> actualResult = new ArrayList<>();

        volcanologistsKeys.next();
        actualResult.add(volcanologistsKeys.getString("PKTABLE_NAME"));
        actualResult.add(volcanologistsKeys.getString("FKTABLE_NAME"));
        actualResult.add(volcanologistsKeys.getString("PKCOLUMN_NAME"));
        actualResult.add(volcanologistsKeys.getString("FKCOLUMN_NAME"));

        volcanoesKeys.next();
        actualResult.add(volcanoesKeys.getString("PKTABLE_NAME"));
        actualResult.add(volcanoesKeys.getString("FKTABLE_NAME"));
        actualResult.add(volcanoesKeys.getString("PKCOLUMN_NAME"));
        actualResult.add(volcanoesKeys.getString("FKCOLUMN_NAME"));

        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("SALES");
        expectedResult.add("DEVICES");
        expectedResult.add("ID");
        expectedResult.add("SALE_ID");
        expectedResult.add("SELLERS");
        expectedResult.add("SALES");
        expectedResult.add("ID");
        expectedResult.add("SELLER_ID");

        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}