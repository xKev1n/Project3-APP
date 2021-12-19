package feec.vutbr.cz.api;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SqlBasicView {

    private StringProperty col1 = new SimpleStringProperty();
    private DoubleProperty col2 = new SimpleDoubleProperty();
    private StringProperty col3 = new SimpleStringProperty();

    public void setCol1(String col1) {
        this.col1.set(col1);
    }

    public String getCol1() {
        return col1.get();
    }

    public void setCol2(Double col2) {
        this.col2.set(col2);
    }

    public Double getCol2() {
        return col2.get();
    }

    public void setCol3(String col3) {
        this.col3.set(col3);
    }

    public String getCol3() {
        return col3.get();
    }
}
