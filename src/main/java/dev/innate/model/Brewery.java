package dev.innate.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Brewery {
    @JsonProperty("country")
    private String country;

    @JsonProperty("brewery_type")
    private String breweryType;

    @JsonProperty("city")
    private String city;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("website_url")
    private String websiteUrl;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("street")
    private String street;

    @JsonProperty("tag_list")
    private List<Object> tagList;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private int id;

    @JsonProperty("state")
    private String state;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("longitude")
    private String longitude;

    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry(){
        return country;
    }

    public void setBreweryType(String breweryType){
        this.breweryType = breweryType;
    }

    public String getBreweryType(){
        return breweryType;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public void setWebsiteUrl(String websiteUrl){
        this.websiteUrl = websiteUrl;
    }

    public String getWebsiteUrl(){
        return websiteUrl;
    }

    public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getStreet(){
        return street;
    }

    public void setTagList(List<Object> tagList){
        this.tagList = tagList;
    }

    public List<Object> getTagList(){
        return tagList;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPostalCode(){
        return postalCode;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public String getLongitude(){
        return longitude;
    }

    @Override
    public String toString(){
        return
                "BreweryResponse{" +
                        "country = '" + country + '\'' +
                        ",brewery_type = '" + breweryType + '\'' +
                        ",city = '" + city + '\'' +
                        ",latitude = '" + latitude + '\'' +
                        ",website_url = '" + websiteUrl + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",street = '" + street + '\'' +
                        ",tag_list = '" + tagList + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",state = '" + state + '\'' +
                        ",postal_code = '" + postalCode + '\'' +
                        ",longitude = '" + longitude + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brewery brewery = (Brewery) o;
        return id == brewery.id &&
                Objects.equals(city, brewery.city) &&
                Objects.equals(name, brewery.name) &&
                Objects.equals(state, brewery.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, name, id, state);
    }
}
