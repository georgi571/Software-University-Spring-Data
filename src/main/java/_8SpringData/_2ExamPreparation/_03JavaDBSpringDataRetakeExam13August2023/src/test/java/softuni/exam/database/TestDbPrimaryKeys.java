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

        ResultSet primaryKeyBooks = metaData.getPrimaryKeys(null, null, "BOOKS");
        ResultSet primaryKeyBorrowingRecords = metaData.getPrimaryKeys(null, null, "BORROWING_RECORDS");
        ResultSet primaryKeyLibraryMembers = metaData.getPrimaryKeys(null, null, "LIBRARY_MEMBERS");

        List<String> actualResult = new ArrayList<>();

        primaryKeyBooks.next();
        actualResult.add(primaryKeyBooks.getString("TABLE_NAME"));
        actualResult.add(primaryKeyBooks.getString("COLUMN_NAME"));

        primaryKeyBorrowingRecords.next();
        actualResult.add(primaryKeyBorrowingRecords.getString("TABLE_NAME"));
        actualResult.add(primaryKeyBorrowingRecords.getString("COLUMN_NAME"));

        primaryKeyLibraryMembers.next();
        actualResult.add(primaryKeyLibraryMembers.getString("TABLE_NAME"));
        actualResult.add(primaryKeyLibraryMembers.getString("COLUMN_NAME"));

        List<String> expectedResult = new ArrayList<>();

        expectedResult.add("BOOKS");
        expectedResult.add("ID");
        expectedResult.add("BORROWING_RECORDS");
        expectedResult.add("ID");
        expectedResult.add("LIBRARY_MEMBERS");
        expectedResult.add("ID");

        Assertions.assertArrayEquals(expectedResult.stream().sorted().toArray(), actualResult.stream().sorted().toArray());
    }

    private DatabaseMetaData getDatabaseMetaData() throws SQLException {
        DataSource dataSource = getJdbcTemplate().getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        return connection.getMetaData();
    }
}