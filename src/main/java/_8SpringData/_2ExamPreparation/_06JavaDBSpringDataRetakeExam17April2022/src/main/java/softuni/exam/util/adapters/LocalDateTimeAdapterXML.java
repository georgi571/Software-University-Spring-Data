package softuni.exam.util.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

public class LocalDateTimeAdapterXML extends XmlAdapter<String, LocalTime> {
    @Override
    public LocalTime unmarshal(String s) throws Exception {
        return LocalTime.parse(s);
    }

    @Override
    public String marshal(LocalTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
