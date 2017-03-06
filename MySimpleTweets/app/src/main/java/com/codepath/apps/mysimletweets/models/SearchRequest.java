package com.codepath.apps.mysimletweets.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hongnhung on 05/03/2017.
 */

public class SearchRequest implements Parcelable {
    private int page = 0;
    public SearchRequest() {

    }


    public int reset() {
        page = 0;
        return page;

    }

    public int nextPage() {
        page = page + 1;
        return page;

    }

    protected SearchRequest(Parcel in) {
        page = in.readInt();
    }

    public static final Creator<SearchRequest> CREATOR = new Creator<SearchRequest>() {
        @Override
        public SearchRequest createFromParcel(Parcel in) {
            return new SearchRequest(in);
        }

        @Override
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
    }
}
