package car.rodsoft.com.testappcar.Model;

public class CarDetail {

  private String id;
  private String[] details;
  private int img;

    public CarDetail() {
    }

    public CarDetail(String id, String[] details, int img) {
        this.id = id;
        this.details = details;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
