package com.shf.app21_service.romote;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private int id;
    private String name;
    private double price;

    public Student() {
    }

    public Student(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

//    将当前对象的属性数据打包：写道包对象中
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(price);
    }

//    添加一个静态成员，名为CREATOR，该对象实现了Parcelable.Create接口
    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>(){

//        解包：读取包中的数据并封装成对象
    @Override
    public Student createFromParcel(Parcel parcel) {
        String name = parcel.readString();
        int id = parcel.readInt();
        return null;
    }

    @Override
    public Student[] newArray(int i) {
        return new Student[0];
    }
}
}
