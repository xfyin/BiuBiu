package letus179.com.biubiu.entity;

import java.io.Serializable;

/**
 * 收货地址
 *
 * Created by xfyin on 2017/10/4.
 */

public class MyAddress implements Serializable{
    private String name;
    private String phone;
    private String location;
    private String detail;

    public MyAddress() {
    }

    public MyAddress(String name, String phone, String location, String detail) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "MyAddress{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
