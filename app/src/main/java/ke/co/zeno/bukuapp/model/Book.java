package ke.co.zeno.bukuapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  @author Jude Kikuyu
 *  date: 10/10/2017
 *  ref:http://www.vogella.com/tutorials/AndroidParcelable/article.html
 */

public class Book implements Parcelable{
    private String title;
    private String description;
    private Integer edition;
    private Integer level;
    private Double price;
    private String image;
    private Integer drawable;
    private Integer subject;
    public Book(){

    }
    public Book(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.edition = in.readInt();
        this.level = in.readInt();
        this.price = in.readDouble();
        this.image = in.readString();
        this.drawable =in.readInt();
        this.subject = in.readInt();


    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


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
    public Integer getSubject() {
        return subject;

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

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.edition);
        dest.writeInt(this.level);
        dest.writeDouble(this.price);
        dest.writeString(this.image);
        dest.writeInt(this.drawable);
        dest.writeInt(this.subject);


    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", edition=" + edition +
                ", level=" + level +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", drawable=" + drawable +
                ", subject=" + subject +
                '}';
    }
}
