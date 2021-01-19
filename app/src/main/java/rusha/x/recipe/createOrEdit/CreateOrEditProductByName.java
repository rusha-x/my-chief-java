package rusha.x.recipe.createOrEdit;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class CreateOrEditProductByName {
    @SerializedName ("id")
    public final Integer id;

    @SerializedName ("name")
    public final String name;

    @SerializedName ("price")
    public final double price;

    @SerializedName ("unit")
    public final String unit;

    public CreateOrEditProductByName(Integer id, String name, double price, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrEditProductByName that = (CreateOrEditProductByName) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(unit, that.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, unit);
    }
}