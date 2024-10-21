package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedRootDTO implements Serializable {

    @XmlElement(name = "ticket")
    private List<TicketSeedDTO> ticketSeedDTOS;

    public List<TicketSeedDTO> getTicketSeedDTOS() {
        return ticketSeedDTOS;
    }

    public void setTicketSeedDTOS(List<TicketSeedDTO> ticketSeedDTOS) {
        this.ticketSeedDTOS = ticketSeedDTOS;
    }
}
