package data;

/**
 * Created by Mohamed Hamed on 2/27/2017.
 */
public class Product {
    private String product_title;
    private String product_description;
    // Stores image url
    private String product_image;


    public Product(){

    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_title() {

        return product_title;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_image() {
        return product_image;
    }
}
