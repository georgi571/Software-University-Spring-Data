package softuni.exam.readFile;
//TestPersonalDataServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.PersonalDataServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestPersonalDataServiceReadFromFile {

    @InjectMocks
    private PersonalDataServiceImpl personalDataService;

    @Test
    void readPersonalDataFromFile() throws IOException {
        String expected = """
                <?xml version='1.0' encoding='UTF-8'?>
                <personal_datas>
                    <personal_data>
                        <age>33</age>
                        <gender>M</gender>
                        <birth_date>1991-05-12</birth_date>
                        <card_number>123456789</card_number>
                    </personal_data>
                    <personal_data>
                        <age>37</age>
                        <gender>M</gender>
                        <birth_date>1987-04-04</birth_date>
                        <card_number>123456789</card_number>
                    </personal_data>
                    <personal_data>
                        <age>22</age>
                        <gender>F</gender>
                        <birth_date>2002-03-22</birth_date>
                        <card_number>987654321</card_number>
                    </personal_data>
                    <personal_data>
                        <age>34</age>
                        <gender>M</gender>
                        <birth_date>1990-11-15</birth_date>
                        <card_number>234567890</card_number>
                    </personal_data>
                    <personal_data>
                        <age>29</age>
                        <gender>F</gender>
                        <birth_date>1995-07-23</birth_date>
                        <card_number>345678901</card_number>
                    </personal_data>
                    <personal_data>
                        <age>56</age>
                        <gender>M</gender>
                        <birth_date>1968-01-30</birth_date>
                        <card_number>456789012</card_number>
                    </personal_data>
                    <personal_data>
                        <age>41</age>
                        <gender>F</gender>
                        <birth_date>1983-08-19</birth_date>
                        <card_number>567890123</card_number>
                    </personal_data>
                    <personal_data>
                        <age>63</age>
                        <gender>M</gender>
                        <birth_date>1961-09-14</birth_date>
                        <card_number>678901234</card_number>
                    </personal_data>
                    <personal_data>
                        <age>77</age>
                        <gender>F</gender>
                        <birth_date>1947-12-10</birth_date>
                        <card_number>789012345</card_number>
                    </personal_data>
                    <personal_data>
                        <age>54</age>
                        <gender>M</gender>
                        <birth_date>1970-04-05</birth_date>
                        <card_number>890123456</card_number>
                    </personal_data>
                    <personal_data>
                        <age>31</age>
                        <gender>F</gender>
                        <birth_date>1993-02-28</birth_date>
                        <card_number>901234567</card_number>
                    </personal_data>
                    <personal_data>
                        <age>48</age>
                        <gender>M</gender>
                        <birth_date>1976-06-20</birth_date>
                        <card_number>102345678</card_number>
                    </personal_data>
                    <personal_data>
                        <age>26</age>
                        <gender>F</gender>
                        <birth_date>1998-10-16</birth_date>
                        <card_number>213456789</card_number>
                    </personal_data>
                    <personal_data>
                        <age>37</age>
                        <gender>M</gender>
                        <birth_date>1987-11-25</birth_date>
                        <card_number>324567890</card_number>
                    </personal_data>
                    <personal_data>
                        <age>52</age>
                        <gender>F</gender>
                        <birth_date>1972-01-05</birth_date>
                        <card_number>435678901</card_number>
                    </personal_data>
                    <personal_data>
                        <age>41</age>
                        <gender>M</gender>
                        <birth_date>1983-07-14</birth_date>
                        <card_number>546789012</card_number>
                    </personal_data>
                    <personal_data>
                        <age>23</age>
                        <gender>F</gender>
                        <birth_date>2001-04-22</birth_date>
                        <card_number>657890123</card_number>
                    </personal_data>
                    <personal_data>
                        <age>35</age>
                        <gender>M</gender>
                        <birth_date>1989-03-17</birth_date>
                        <card_number>768901234</card_number>
                    </personal_data>
                    <personal_data>
                        <age>49</age>
                        <gender>F</gender>
                        <birth_date>1975-08-10</birth_date>
                        <card_number>879012345</card_number>
                    </personal_data>
                    <personal_data>
                        <age>60</age>
                        <gender>M</gender>
                        <birth_date>1964-02-01</birth_date>
                        <card_number>980123456</card_number>
                    </personal_data>
                    <personal_data>
                        <age>27</age>
                        <gender>F</gender>
                        <birth_date>1997-11-30</birth_date>
                        <card_number>123456780</card_number>
                    </personal_data>
                    <personal_data>
                        <age>62</age>
                        <gender>M</gender>
                        <birth_date>1962-12-12</birth_date>
                        <card_number>234567891</card_number>
                    </personal_data>
                    <personal_data>
                        <age>42</age>
                        <gender>F</gender>
                        <birth_date>1982-05-09</birth_date>
                        <card_number>345678902</card_number>
                    </personal_data>
                    <personal_data>
                        <age>30</age>
                        <gender>M</gender>
                        <birth_date>1994-06-23</birth_date>
                        <card_number>456789013</card_number>
                    </personal_data>
                    <personal_data>
                        <age>46</age>
                        <gender>F</gender>
                        <birth_date>1978-04-25</birth_date>
                        <card_number>567890124</card_number>
                    </personal_data>
                    <personal_data>
                        <age>57</age>
                        <gender>M</gender>
                        <birth_date>1967-07-15</birth_date>
                        <card_number>678901235</card_number>
                    </personal_data>
                    <personal_data>
                        <age>21</age>
                        <gender>F</gender>
                        <birth_date>2003-03-01</birth_date>
                        <card_number>789012346</card_number>
                    </personal_data>
                    <personal_data>
                        <age>50</age>
                        <gender>M</gender>
                        <birth_date>1974-10-29</birth_date>
                        <card_number>890123457</card_number>
                    </personal_data>
                    <personal_data>
                        <age>25</age>
                        <gender>F</gender>
                        <birth_date>1999-08-07</birth_date>
                        <card_number>901234568</card_number>
                    </personal_data>
                    <personal_data>
                        <age>39</age>
                        <gender>M</gender>
                        <birth_date>1985-12-05</birth_date>
                        <card_number>102345679</card_number>
                    </personal_data>
                    <personal_data>
                        <age>43</age>
                        <gender>F</gender>
                        <birth_date>1981-06-17</birth_date>
                        <card_number>213456780</card_number>
                    </personal_data>
                    <personal_data>
                        <age>68</age>
                        <gender>M</gender>
                        <birth_date>1956-07-28</birth_date>
                        <card_number>324567891</card_number>
                    </personal_data>
                    <personal_data>
                        <age>59</age>
                        <gender>F</gender>
                        <birth_date>1965-09-09</birth_date>
                        <card_number>435678902</card_number>
                    </personal_data>
                    <personal_data>
                        <age>53</age>
                        <gender>M</gender>
                        <birth_date>1971-03-13</birth_date>
                        <card_number>546789013</card_number>
                    </personal_data>
                    <personal_data>
                        <age>28</age>
                        <gender>F</gender>
                        <birth_date>1996-04-04</birth_date>
                        <card_number>657890124</card_number>
                    </personal_data>
                    <personal_data>
                        <age>74</age>
                        <gender>M</gender>
                        <birth_date>1950-02-14</birth_date>
                        <card_number>768901235</card_number>
                    </personal_data>
                    <personal_data>
                        <age>38</age>
                        <gender>F</gender>
                        <birth_date>1986-10-25</birth_date>
                        <card_number>879012346</card_number>
                    </personal_data>
                    <personal_data>
                        <age>66</age>
                        <gender>M</gender>
                        <birth_date>1958-06-21</birth_date>
                        <card_number>980123457</card_number>
                    </personal_data>
                    <personal_data>
                        <age>32</age>
                        <gender>F</gender>
                        <birth_date>1992-09-18</birth_date>
                        <card_number>123456791</card_number>
                    </personal_data>
                    <personal_data>
                        <age>48</age>
                        <gender>M</gender>
                        <birth_date>1976-01-11</birth_date>
                        <card_number>234567892</card_number>
                    </personal_data>
                    <personal_data>
                        <age>45</age>
                        <gender>F</gender>
                        <birth_date>1979-04-04</birth_date>
                        <card_number>345678903</card_number>
                    </personal_data>
                    <personal_data>
                        <age>57</age>
                        <gender>M</gender>
                        <birth_date>1967-03-28</birth_date>
                        <card_number>456789014</card_number>
                    </personal_data>
                    <personal_data>
                        <age>26</age>
                        <gender>F</gender>
                        <birth_date>1998-05-12</birth_date>
                        <card_number>567890125</card_number>
                    </personal_data>
                    <personal_data>
                        <age>41</age>
                        <gender>M</gender>
                        <birth_date>1983-11-22</birth_date>
                        <card_number>678901236</card_number>
                    </personal_data>
                    <personal_data>
                        <age>55</age>
                        <gender>F</gender>
                        <birth_date>1969-12-19</birth_date>
                        <card_number>789012347</card_number>
                    </personal_data>
                    <personal_data>
                        <age>30</age>
                        <gender>M</gender>
                        <birth_date>1994-08-06</birth_date>
                        <card_number>890123458</card_number>
                    </personal_data>
                    <personal_data>
                        <age>24</age>
                        <gender>F</gender>
                        <birth_date>2000-10-02</birth_date>
                        <card_number>901234569</card_number>
                    </personal_data>
                    <personal_data>
                        <age>49</age>
                        <gender>M</gender>
                        <birth_date>1975-02-07</birth_date>
                        <card_number>102345680</card_number>
                    </personal_data>
                    <personal_data>
                        <age>65</age>
                        <gender>F</gender>
                        <birth_date>1959-07-26</birth_date>
                        <card_number>213456791</card_number>
                    </personal_data>
                    <personal_data>
                        <age>33</age>
                        <gender>M</gender>
                        <birth_date>1991-11-15</birth_date>
                        <card_number>324567892</card_number>
                    </personal_data>
                    <personal_data>
                        <age>40</age>
                        <gender>F</gender>
                        <birth_date>1984-12-30</birth_date>
                        <card_number>435678903</card_number>
                    </personal_data>
                    <personal_data>
                        <age>56</age>
                        <gender>M</gender>
                        <birth_date>1968-04-09</birth_date>
                        <card_number>546789014</card_number>
                    </personal_data>
                    <personal_data>
                        <age>21</age>
                        <gender>F</gender>
                        <birth_date>2003-01-21</birth_date>
                        <card_number>657890125</card_number>
                    </personal_data>
                    <personal_data>
                        <age>31</age>
                        <gender>M</gender>
                        <birth_date>1993-09-05</birth_date>
                        <card_number>768901236</card_number>
                    </personal_data>
                    <personal_data>
                        <age>47</age>
                        <gender>F</gender>
                        <birth_date>1977-08-19</birth_date>
                        <card_number>879012347</card_number>
                    </personal_data>
                    <personal_data>
                        <age>61</age>
                        <gender>M</gender>
                        <birth_date>1963-03-07</birth_date>
                        <card_number>980123458</card_number>
                    </personal_data>
                    <personal_data>
                        <age>25</age>
                        <gender>F</gender>
                        <birth_date>1999-02-14</birth_date>
                        <card_number>123456792</card_number>
                    </personal_data>
                    <personal_data>
                        <age>42</age>
                        <gender>M</gender>
                        <birth_date>1982-11-13</birth_date>
                        <card_number>234567893</card_number>
                    </personal_data>
                    <personal_data>
                        <age>38</age>
                        <gender>F</gender>
                        <birth_date>1986-07-22</birth_date>
                        <card_number>345678904</card_number>
                    </personal_data>
                    <personal_data>
                        <age>29</age>
                        <gender>M</gender>
                        <birth_date>1995-09-29</birth_date>
                        <card_number>456789015</card_number>
                    </personal_data>
                    <personal_data>
                        <age>59</age>
                        <gender>F</gender>
                        <birth_date>1965-05-05</birth_date>
                        <card_number>567890126</card_number>
                    </personal_data>
                    <personal_data>
                        <age>62</age>
                        <gender>M</gender>
                        <birth_date>1962-12-25</birth_date>
                        <card_number>678901237</card_number>
                    </personal_data>
                    <personal_data>
                        <age>71</age>
                        <gender>F</gender>
                        <birth_date>1953-08-01</birth_date>
                        <card_number>789012348</card_number>
                    </personal_data>
                    <personal_data>
                        <age>34</age>
                        <gender>M</gender>
                        <birth_date>1990-05-17</birth_date>
                        <card_number>890123459</card_number>
                    </personal_data>
                    <personal_data>
                        <age>60</age>
                        <gender>F</gender>
                        <birth_date>1964-09-20</birth_date>
                        <card_number>901234570</card_number>
                    </personal_data>
                    <personal_data>
                        <age>26</age>
                        <gender>M</gender>
                        <birth_date>1998-04-10</birth_date>
                        <card_number>102345681</card_number>
                    </personal_data>
                    <personal_data>
                        <age>52</age>
                        <gender>F</gender>
                        <birth_date>1972-02-18</birth_date>
                        <card_number>213456792</card_number>
                    </personal_data>
                    <personal_data>
                        <age>45</age>
                        <gender>M</gender>
                        <birth_date>1979-03-15</birth_date>
                        <card_number>324567893</card_number>
                    </personal_data>
                    <personal_data>
                        <age>23</age>
                        <gender>F</gender>
                        <birth_date>2001-12-31</birth_date>
                        <card_number>435678904</card_number>
                    </personal_data>
                    <personal_data>
                        <age>47</age>
                        <gender>M</gender>
                        <birth_date>1977-11-11</birth_date>
                        <card_number>546789015</card_number>
                    </personal_data>
                    <personal_data>
                        <age>33</age>
                        <gender>F</gender>
                        <birth_date>1991-08-21</birth_date>
                        <card_number>657890126</card_number>
                    </personal_data>
                    <personal_data>
                        <age>39</age>
                        <gender>M</gender>
                        <birth_date>1985-06-10</birth_date>
                        <card_number>768901237</card_number>
                    </personal_data>
                    <personal_data>
                        <age>54</age>
                        <gender>F</gender>
                        <birth_date>1970-10-05</birth_date>
                        <card_number>879012348</card_number>
                    </personal_data>
                    <personal_data>
                        <age>48</age>
                        <gender>M</gender>
                        <birth_date>1976-07-13</birth_date>
                        <card_number>980123459</card_number>
                    </personal_data>
                    <personal_data>
                        <age>29</age>
                        <gender>F</gender>
                        <birth_date>1995-03-21</birth_date>
                        <card_number>123456793</card_number>
                    </personal_data>
                </personal_datas>""";


        String actual = personalDataService.readPersonalDataFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();;

        Assertions.assertEquals(expected, actual);
    }
}