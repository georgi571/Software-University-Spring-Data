package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "is_public")
    private boolean isAlbumPublic;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "albums_pictures",
    joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id"))
    private Set<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    public Album() {
        this.pictures = new HashSet<>();
    }

    public Album(String name, String backgroundColor, boolean isAlbumPublic, User user) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.isAlbumPublic = isAlbumPublic;
        this.pictures = new HashSet<>();
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isAlbumPublic() {
        return isAlbumPublic;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public User getUser() {
        return user;
    }

    public void addPicture(Picture picture) {
        if (!this.pictures.contains(picture)) {
            this.pictures.add(picture);
            picture.getAlbums().add(this);
        }
    }
}
