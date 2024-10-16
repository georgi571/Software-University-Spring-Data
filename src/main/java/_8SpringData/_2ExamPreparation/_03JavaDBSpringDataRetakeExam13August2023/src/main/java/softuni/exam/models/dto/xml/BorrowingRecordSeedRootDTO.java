package softuni.exam.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordSeedRootDTO implements Serializable {

    @XmlElement(name = "borrowing_record")
    List<BorrowingRecordSeedDTO> borrowingRecordSeedDTOList;

    public List<BorrowingRecordSeedDTO> getBorrowingRecordSeedDTOList() {
        return borrowingRecordSeedDTOList;
    }

    public void setBorrowingRecordSeedDTOList(List<BorrowingRecordSeedDTO> borrowingRecordSeedDTOList) {
        this.borrowingRecordSeedDTOList = borrowingRecordSeedDTOList;
    }
}
