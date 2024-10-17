package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobSeedRootDTO implements Serializable {
    @XmlElement(name = "job")
    private List<JobSeedDTO> jobSeedDTOList;

    public List<JobSeedDTO> getJobSeedDTOList() {
        return jobSeedDTOList;
    }

    public void setJobSeedDTOList(List<JobSeedDTO> jobSeedDTOList) {
        this.jobSeedDTOList = jobSeedDTOList;
    }
}
