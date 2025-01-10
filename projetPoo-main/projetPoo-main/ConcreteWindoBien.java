
public class ConcreteWindoBien {
    // Fields
    private String title;
    private int width;
    private int height;

    // Constructor
    public ConcreteWindoBien(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public ConcreteWindoBien(String title) {
        this.title = title;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Method to display window details
    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
    }
}
