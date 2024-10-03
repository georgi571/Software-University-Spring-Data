DELIMITER $$
CREATE PROCEDURE bookshop_system.usp_total_amount_of_books_author_write(`first_name` VARCHAR(255), `last_name` VARCHAR(255), OUT count_books INT)
BEGIN
    SELECT
        COUNT(*) INTO `count_books`
    FROM bookshop_system.books AS b
    JOIN bookshop_system.authors AS a ON a.id = b.author_id
    WHERE a.first_name = `first_name` AND a.last_name = `last_name`;
END$$
DELIMITER ;