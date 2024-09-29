package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "caption", nullable = false)
    private String caption;

    @Column(name = "path")
    private String path;

    @ManyToMany(mappedBy = "pictures", fetch = FetchType.EAGER)
    private Set<Album> albums;

    public Picture() {
    }

    public Picture(String title, String caption, String path) {
        this.title = title;
        this.caption = caption;
        this.path = path;
        albums = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getPath() {
        return path;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        if (!this.albums.contains(album)) {
            this.albums.add(album);
            album.getPictures().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;
        Picture picture = (Picture) o;
        return title.equals(picture.title) && caption.equals(picture.caption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, caption);
    }
}
