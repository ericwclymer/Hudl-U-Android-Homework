package clymer.eric.hudlu.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Cdddadreated by eric clymer on 11/26/2015.
 */
public class MashableNews {
    // this is necessary because the api returns objects in an array with key "new"
    @SerializedName("new")
    public List<MashableNewsItem> newsItems;
}
