package ir.reza.showimaes.Model;


public class Image
{


    private String link;
    private String title;

    public Image()
    {

    }

    public String getUrl() {
        return link;
    }

    public void setUrl(String url) {
        this.link = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
