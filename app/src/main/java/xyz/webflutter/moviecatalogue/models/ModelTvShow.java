package xyz.webflutter.moviecatalogue.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelTvShow implements Parcelable {
    private String title;
    private String overview;
    private String status;
    private String languages;
    private String runtime;
    private String genre;
    private String network;
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.status);
        dest.writeString(this.languages);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.network);
        dest.writeString(this.thumbnail);
    }

    public ModelTvShow() {
    }

    private ModelTvShow(Parcel in) {
        this.title = in.readString();
        this.overview = in.readString();
        this.status = in.readString();
        this.languages = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.network = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<ModelTvShow> CREATOR = new Creator<ModelTvShow>() {
        @Override
        public ModelTvShow createFromParcel(Parcel source) {
            return new ModelTvShow(source);
        }

        @Override
        public ModelTvShow[] newArray(int size) {
            return new ModelTvShow[size];
        }
    };
}
