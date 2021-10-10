package bans.qaadhar.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AadharCard implements Parcelable {
    private String uuid;
    private String name;
    private String dateOfBirth;
    private String gender;
    private String careOf;
    private String district;
    private String landmark;
    private String house;
    private String location;
    private String pinCode;
    private String postOffice;
    private String state;
    private String street;
    private String subDistrict;
    private String vtc;
    private Bitmap image;
    private String email;
    private String mobile;
    private String signature;

    @Override
    public String toString() {
        return "AadharCard{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", careOf='" + careOf + '\'' +
                ", district='" + district + '\'' +
                ", landmark='" + landmark + '\'' +
                ", house='" + house + '\'' +
                ", location='" + location + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", postOffice='" + postOffice + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", vtc='" + vtc + '\'' +
                ", image=" + image +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }

    public void setUuid(String aUuid) {
        name = aUuid;
    }

    public void setName(String aName) {
        name = aName;
    }

    public void setDateOfBirth(String aDateOfBirth) {
        dateOfBirth = aDateOfBirth;
    }

    public void setGender(String aGender) {
        gender = aGender;
    }

    public void setCareOf(String aCareOf) {
        careOf = aCareOf;
    }

    public void setDistrict(String aDistrict) {
        district = aDistrict;
    }

    public void setLandmark(String aLandmark) {
        landmark = aLandmark;
    }

    public void setHouse(String aHouse) {
        house = aHouse;
    }

    public void setLocation(String aLocation) {
        location = aLocation;
    }

    public void setPinCode(String aPinCode) {
        pinCode = aPinCode;
    }

    public void setPostOffice(String aPostOffice) {
        postOffice = aPostOffice;
    }

    public void setState(String aState) {
        state = aState;
    }

    public void setStreet(String aStreet) {
        street = aStreet;
    }

    public void setSubDistrict(String aSubDistrict) {
        subDistrict = aSubDistrict;
    }

    public void setVtc(String aVtc) {
        vtc = aVtc;
    }

    public void setImage(Bitmap aImage) {
        image = aImage;
    }

    public void setEmail(String aEmail) {
        email = aEmail;
    }

    public void setMobile(String aMobile) {
        mobile = aMobile;
    }

    public void setSignature(String aSignature) {
        signature = aSignature;
    }

    public AadharCard() {
        uuid = "";
        name = "";
        dateOfBirth = "";
        gender = "";
        careOf = "";
        district = "";
        landmark = "";
        house = "";
        location = "";
        pinCode = "";
        postOffice = "";
        state = "";
        street = "";
        subDistrict = "";
        vtc = "";
        email = "";
        mobile = "";
        signature = "";
    }


    public String getAddress() {
        return "Village- " + vtc +
                ", Post- " + postOffice +
                ", Dist. " + district +
                "(" + state + ")" +
                ", Pin- " + pinCode;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getCareOf() {
        return careOf;
    }

    public String getDistrict() {
        return district;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getHouse() {
        return house;
    }

    public String getLocation() {
        return location;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public String getVtc() {
        return vtc;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSignature() {
        return signature;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uuid);
        dest.writeString(this.name);
        dest.writeString(this.dateOfBirth);
        dest.writeString(this.gender);
        dest.writeString(this.careOf);
        dest.writeString(this.district);
        dest.writeString(this.landmark);
        dest.writeString(this.house);
        dest.writeString(this.location);
        dest.writeString(this.pinCode);
        dest.writeString(this.postOffice);
        dest.writeString(this.state);
        dest.writeString(this.street);
        dest.writeString(this.subDistrict);
        dest.writeString(this.vtc);
        dest.writeParcelable(this.image, flags);
        dest.writeString(this.email);
        dest.writeString(this.mobile);
        dest.writeString(this.signature);
    }

    public void readFromParcel(Parcel source) {
        this.uuid = source.readString();
        this.name = source.readString();
        this.dateOfBirth = source.readString();
        this.gender = source.readString();
        this.careOf = source.readString();
        this.district = source.readString();
        this.landmark = source.readString();
        this.house = source.readString();
        this.location = source.readString();
        this.pinCode = source.readString();
        this.postOffice = source.readString();
        this.state = source.readString();
        this.street = source.readString();
        this.subDistrict = source.readString();
        this.vtc = source.readString();
        this.image = source.readParcelable(Bitmap.class.getClassLoader());
        this.email = source.readString();
        this.mobile = source.readString();
        this.signature = source.readString();
    }

    protected AadharCard(Parcel in) {
        this.uuid = in.readString();
        this.name = in.readString();
        this.dateOfBirth = in.readString();
        this.gender = in.readString();
        this.careOf = in.readString();
        this.district = in.readString();
        this.landmark = in.readString();
        this.house = in.readString();
        this.location = in.readString();
        this.pinCode = in.readString();
        this.postOffice = in.readString();
        this.state = in.readString();
        this.street = in.readString();
        this.subDistrict = in.readString();
        this.vtc = in.readString();
        this.image = in.readParcelable(Bitmap.class.getClassLoader());
        this.email = in.readString();
        this.mobile = in.readString();
        this.signature = in.readString();
    }

    public static final Parcelable.Creator<AadharCard> CREATOR = new Parcelable.Creator<AadharCard>() {
        @Override
        public AadharCard createFromParcel(Parcel source) {
            return new AadharCard(source);
        }

        @Override
        public AadharCard[] newArray(int size) {
            return new AadharCard[size];
        }
    };
}
