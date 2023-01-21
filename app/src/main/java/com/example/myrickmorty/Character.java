package com.example.myrickmorty;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class Character implements Parcelable {
    private  String name = "undefined";
    private String status = "undefined";
    private String species = "undefined";
    private String gender = "undefined";
    private String image = "undefined";
    private List<String> episode;
    private Location location;
    private int id;


    public Character(Parcel in) {
        id = in.readInt();
        name = in.readString();
        status = in.readString();
        species = in.readString();
        gender = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        image = in.readString();
        episode = in.createStringArrayList();
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(status);
        parcel.writeString(species);
        parcel.writeString(gender);
        parcel.writeParcelable(location, i);
        parcel.writeString(image);
        parcel.writeStringList(episode);
    }




    public String getName() {
        return name;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String img) {
        this.image = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(String[] episode) {
        this.episode = Arrays.asList(episode);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



    public static class Location implements Parcelable{
        private String name;
        private String url;

        public Location(Parcel in){
            name=in.readString();
            url=in.readString();
        }
        public Location(String name, String url){
            this.name = name;
            this.url = url;
        }

        public static final Creator<Location> CREATOR = new Creator<Location>() {
            @Override
            public Location createFromParcel(Parcel in) {
                return new Location(in);
            }

            @Override
            public Location[] newArray(int size) {
                return new Location[size];
            }
        };

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(url);
        }
    }
}
