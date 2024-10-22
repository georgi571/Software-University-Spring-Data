package softuni.exam.domain.dtos.json;

import com.google.gson.annotations.Expose;

public class PictureDTO {

    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
