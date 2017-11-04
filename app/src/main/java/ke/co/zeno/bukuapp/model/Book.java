package ke.co.zeno.bukuapp.model;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 */

public class Book {
    private String title;
    private String description;
    private Integer edition;


    private Integer level;
    private Double price;
    private String image;
    private int drawable;
    public Book() {


    }

    public String getTitle() {
        return title;

    }
    public int getEdition() {

        return edition;
    }
    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getDrawable() {
        return drawable;
    }

    public Integer getLevel() {
        return level;
    }

    public String getImage() {
        return image;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
