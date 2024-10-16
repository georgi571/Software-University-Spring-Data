package softuni.exam.models.dto.xml;

import softuni.exam.util.LocalDateTimeAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordSeedDTO implements Serializable {


    @XmlElement(name = "borrow_date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @NotNull
    private LocalDate borrowDate;

    @XmlElement(name = "return_date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @NotNull
    private LocalDate returnDate;

    @XmlElement(name = "book")
    private BookSeedDTO bookSeedDTO;

    @XmlElement(name = "member")
    private LibraryMemberSeedDTO libraryMemberSeedDTO;

    @XmlElement(name = "remarks")
    @Size(min = 3, max = 100)
    private String remarks;

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookSeedDTO getBookSeedDTO() {
        return bookSeedDTO;
    }

    public void setBookSeedDTO(BookSeedDTO bookSeedDTO) {
        this.bookSeedDTO = bookSeedDTO;
    }

    public LibraryMemberSeedDTO getLibraryMemberSeedDTO() {
        return libraryMemberSeedDTO;
    }

    public void setLibraryMemberSeedDTO(LibraryMemberSeedDTO libraryMemberSeedDTO) {
        this.libraryMemberSeedDTO = libraryMemberSeedDTO;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
