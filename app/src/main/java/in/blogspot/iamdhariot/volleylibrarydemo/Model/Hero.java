package in.blogspot.iamdhariot.volleylibrarydemo.Model;

/**
 * Hero model class
 * to store the fetched data in objects.
 *
 * */
public class Hero {
    String name,imageUrl;


    public Hero(String name,String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
