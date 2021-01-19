package rusha.x.product;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("id")
    public final int id;

    @SerializedName("name")
    @NonNull
    public final String name;

    @SerializedName("price")
    public final double price;

    @SerializedName("unit")
    @NonNull
    public final String unit;

    public Product(int id, @NonNull String name, double price, @NonNull String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        unit = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(unit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}