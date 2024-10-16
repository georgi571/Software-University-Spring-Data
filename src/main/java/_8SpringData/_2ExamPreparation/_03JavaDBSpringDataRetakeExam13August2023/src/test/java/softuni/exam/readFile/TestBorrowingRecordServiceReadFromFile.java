package softuni.exam.readFile;
//TestBorrowingRecordServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.BorrowingRecordsServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestBorrowingRecordServiceReadFromFile {

    @InjectMocks
    private BorrowingRecordsServiceImpl borrowingRecordsService;

    @Test
    void readAstronomersFromFile() throws IOException {
        String expected = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<borrowing_records>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-01-13</borrow_date>\n" +
                "        <return_date>2022-09-11</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>Handle with care, fragile book.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-01-13</borrow_date>\n" +
                "        <return_date>2022-09-11</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the RingsX</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>Extended borrowing period approved by librarian.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-01-13</borrow_date>\n" +
                "        <return_date>2022-09-11</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>272</id>\n" +
                "        </member>\n" +
                "        <remarks>Member provided student ID for verification.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-06-01</borrow_date>\n" +
                "        <return_date>2023-01-07</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>14</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned with minor damage, to be repaired.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-03-09</borrow_date>\n" +
                "        <return_date>2023-06-18</return_date>\n" +
                "        <book>\n" +
                "            <title>Pride and Prejudice</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>29</id>\n" +
                "        </member>\n" +
                "        <remarks>Special discount applied for senior citizen member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-09-29</borrow_date>\n" +
                "        <return_date>2023-04-10</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Book in great condition, due back 14 days from borrow date. Contact if extensions needed. Be careful.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-09-05</borrow_date>\n" +
                "        <return_date>2022-11-28</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>12</id>\n" +
                "        </member>\n" +
                "        <remarks>Member provided student ID for verification.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-10-14</borrow_date>\n" +
                "        <return_date>2023-02-17</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>8</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book renewal, granted for one week.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-01-13</borrow_date>\n" +
                "        <return_date>2023-04-13</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Not returned, follow up with member required.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-10-03</borrow_date>\n" +
                "        <return_date>2022-09-17</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Out of stock, reservation placed for next available copy.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-16</borrow_date>\n" +
                "        <return_date>2022-09-28</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>5</id>\n" +
                "        </member>\n" +
                "        <remarks>Member mentioned lost library card, identity verified.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-12-16</borrow_date>\n" +
                "        <return_date>2023-03-13</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>2</id>\n" +
                "        </member>\n" +
                "        <remarks>Book reserved, notify member when available.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-12-20</borrow_date>\n" +
                "        <return_date>2023-06-19</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Special request for a specific edition of the book.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-03-11</borrow_date>\n" +
                "        <return_date>2023-06-02</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>9</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned in excellent condition, no issues</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-09-15</borrow_date>\n" +
                "        <return_date>2022-12-11</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>18</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book recommendation, provided suggestion.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-08-09</borrow_date>\n" +
                "        <return_date>2022-10-29</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Overdue notice sent, please follow up with member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-05-19</borrow_date>\n" +
                "        <return_date>2023-01-19</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>6</id>\n" +
                "        </member>\n" +
                "        <remarks>Temporary membership granted for a visiting guest.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-16</borrow_date>\n" +
                "        <return_date>2023-03-11</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>10</id>\n" +
                "        </member>\n" +
                "        <remarks>Member reported missing page, book exchanged for replacement.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-09-26</borrow_date>\n" +
                "        <return_date>2022-12-04</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>10</id>\n" +
                "        </member>\n" +
                "        <remarks>Inter-library loan initiated for unavailable book.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-09-27</borrow_date>\n" +
                "        <return_date>2022-11-28</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>20</id>\n" +
                "        </member>\n" +
                "        <remarks>Borrower forgot to return personal bookmark.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-08-28</borrow_date>\n" +
                "        <return_date>2022-10-13</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>3</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned with minor damage, to be repaired.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-06-14</borrow_date>\n" +
                "        <return_date>2022-10-06</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>2</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned with a thank-you note from the member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-08-15</borrow_date>\n" +
                "        <return_date>2022-11-21</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Member provided student ID for verification.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-11-10</borrow_date>\n" +
                "        <return_date>2022-08-19</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>\"Special discount applied for senior citizen member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-08-03</borrow_date>\n" +
                "        <return_date>2023-03-19</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>28</id>\n" +
                "        </member>\n" +
                "        <remarks>Temporary membership granted for a visiting guest.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-12-13</borrow_date>\n" +
                "        <return_date>2023-07-29</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>22</id>\n" +
                "        </member>\n" +
                "        <remarks>Borrower forgot to return personal bookmark.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-01-26</borrow_date>\n" +
                "        <return_date>2023-03-18</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hobbit</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>21</id>\n" +
                "        </member>\n" +
                "        <remarks>Extended borrowing period approved by librarian.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-06-02</borrow_date>\n" +
                "        <return_date>2023-05-19</return_date>\n" +
                "        <book>\n" +
                "            <title>Brave New World</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>9</id>\n" +
                "        </member>\n" +
                "        <remarks>Special request for a specific edition of the book.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-11-08</borrow_date>\n" +
                "        <return_date>2023-03-20</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>18</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned in excellent condition, no issues</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-02-11</borrow_date>\n" +
                "        <return_date>2023-07-19</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>28</id>\n" +
                "        </member>\n" +
                "        <remarks>Book reserved, notify member when available.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-30</borrow_date>\n" +
                "        <return_date>2022-09-02</return_date>\n" +
                "        <book>\n" +
                "            <title>Brave New World</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>11</id>\n" +
                "        </member>\n" +
                "        <remarks>Late return, fine applied as per library policy.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-28</borrow_date>\n" +
                "        <return_date>2022-11-10</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>6</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book recommendation, provided suggestion.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-05-27</borrow_date>\n" +
                "        <return_date>2022-08-11</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>6</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book renewal, granted for one week.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-02-22</borrow_date>\n" +
                "        <return_date>2023-01-18</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>5</id>\n" +
                "        </member>\n" +
                "        <remarks>Member mentioned lost library card, identity verified.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-09-16</borrow_date>\n" +
                "        <return_date>2023-03-14</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>13</id>\n" +
                "        </member>\n" +
                "        <remarks>Out of stock, reservation placed for next available copy.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-10-08</borrow_date>\n" +
                "        <return_date>2023-04-17</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>21</id>\n" +
                "        </member>\n" +
                "        <remarks>Not returned, follow up with member required.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-06-16</borrow_date>\n" +
                "        <return_date>2022-08-14</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned with minor damage, to be repaired.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-11-24</borrow_date>\n" +
                "        <return_date>2023-01-25</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>Overdue notice sent, please follow up with member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-03-28</borrow_date>\n" +
                "        <return_date>2023-01-11</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>2</id>\n" +
                "        </member>\n" +
                "        <remarks>Inter-library loan initiated for unavailable book.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-24</borrow_date>\n" +
                "        <return_date>2022-08-16</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>28</id>\n" +
                "        </member>\n" +
                "        <remarks>Member reported missing page, book exchanged for replacement.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-04</borrow_date>\n" +
                "        <return_date>2023-06-26</return_date>\n" +
                "        <book>\n" +
                "            <title>To Kill a Mockingbird</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>18</id>\n" +
                "        </member>\n" +
                "        <remarks>Temporary membership granted for a visiting guest.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-06-22</borrow_date>\n" +
                "        <return_date>2022-11-19</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>24</id>\n" +
                "        </member>\n" +
                "        <remarks>\"Special discount applied for senior citizen member.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-01-12</borrow_date>\n" +
                "        <return_date>2023-04-17</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book recommendation, provided suggestion.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-08-29</borrow_date>\n" +
                "        <return_date>2022-08-16</return_date>\n" +
                "        <book>\n" +
                "            <title>The Chronicles of Narnia</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>5</id>\n" +
                "        </member>\n" +
                "        <remarks>Book reserved, notify member when available.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-06-05</borrow_date>\n" +
                "        <return_date>2023-03-06</return_date>\n" +
                "        <book>\n" +
                "            <title>The Hitchhiker's Guide to the Galaxy</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>1</id>\n" +
                "        </member>\n" +
                "        <remarks>Member requested book renewal, granted for one week.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-08-24</borrow_date>\n" +
                "        <return_date>2023-06-23</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>27</id>\n" +
                "        </member>\n" +
                "        <remarks>Not returned, follow up with member required.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-07-10</borrow_date>\n" +
                "        <return_date>2023-07-18</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>14</id>\n" +
                "        </member>\n" +
                "        <remarks>Extended borrowing period approved by librarian.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-12-06</borrow_date>\n" +
                "        <return_date>2023-06-26</return_date>\n" +
                "        <book>\n" +
                "            <title>The Great Gatsby</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>6</id>\n" +
                "        </member>\n" +
                "        <remarks>Member mentioned lost library card, identity verified.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-11-04</borrow_date>\n" +
                "        <return_date>2023-06-02</return_date>\n" +
                "        <book>\n" +
                "            <title>1984</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>19</id>\n" +
                "        </member>\n" +
                "        <remarks>Returned with minor damage, to be repaired.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2021-02-24</borrow_date>\n" +
                "        <return_date>2022-08-30</return_date>\n" +
                "        <book>\n" +
                "            <title>The Catcher in the Rye</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>4</id>\n" +
                "        </member>\n" +
                "        <remarks>Extended borrowing period approved by librarian.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2019-09-10</borrow_date>\n" +
                "        <return_date>2023-01-07</return_date>\n" +
                "        <book>\n" +
                "            <title>The Lord of the Rings</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>1</id>\n" +
                "        </member>\n" +
                "        <remarks>Member provided student ID for verification.</remarks>\n" +
                "    </borrowing_record>\n" +
                "    <borrowing_record>\n" +
                "        <borrow_date>2020-07-05</borrow_date>\n" +
                "        <return_date>2023-03-27</return_date>\n" +
                "        <book>\n" +
                "            <title>Harry Potter and the Sorcerer's Stone</title>\n" +
                "        </book>\n" +
                "        <member>\n" +
                "            <id>25</id>\n" +
                "        </member>\n" +
                "        <remarks>Late return, fine applied as per library policy.</remarks>\n" +
                "    </borrowing_record>\n" +
                "</borrowing_records>\n";

        String actual = borrowingRecordsService.readBorrowingRecordsFromFile();

        Assertions.assertEquals(expected, actual);
    }
}