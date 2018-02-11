package view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Data {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty count;
    private final SimpleDoubleProperty percentage;

    public Data(String name, int count, double percentage) {
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleIntegerProperty(count);
        this.percentage = new SimpleDoubleProperty(percentage);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public double getPercentage() {
        return percentage.get();
    }

    public SimpleDoubleProperty percentageProperty() {
        return percentage;
    }


    public void setName(String name) {
        this.name.set(name);
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public void setPercentage(double percentage) {
        this.percentage.set(percentage);
    }
}
