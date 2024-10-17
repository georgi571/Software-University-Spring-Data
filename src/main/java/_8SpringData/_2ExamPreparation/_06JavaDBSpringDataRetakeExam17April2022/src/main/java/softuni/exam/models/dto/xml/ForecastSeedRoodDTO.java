package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastSeedRoodDTO implements Serializable {

    @XmlElement(name = "forecast")
    private List<ForecastSeedDTO> forecastSeedDTOList;

    public List<ForecastSeedDTO> getForecastSeedDTOList() {
        return forecastSeedDTOList;
    }

    public void setForecastSeedDTOList(List<ForecastSeedDTO> forecastSeedDTOList) {
        this.forecastSeedDTOList = forecastSeedDTOList;
    }
}
