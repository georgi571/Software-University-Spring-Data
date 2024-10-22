package softuni.exam.util.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapterXML extends XmlAdapter<String, LocalDateTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s,formatter);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.format(formatter);
    }
}
