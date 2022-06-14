package lt.viko.eif.vvilimaite.project.constructor;

public class Film {
    protected String id;
    protected String imgUrl;
    protected String filmName;

    /**
     * Film contructor which have
     * @param id film specific id
     * @param imgUrl film scene image URL
     * @param filmName film name
     */
    public Film(String id, String imgUrl, String filmName) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.filmName = filmName;
    }
    /**
     * responsible to get or put film data by calling method
     * @return film data
     */
    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
    /**
     *
     * @return film object by string format
     */
    @Override
    public String toString() {
        return String.format("Id: %s \n\t Image Url: %s\n\t Film name:  %s \n\t ",this.id,this.imgUrl, this.filmName);

    }
}


