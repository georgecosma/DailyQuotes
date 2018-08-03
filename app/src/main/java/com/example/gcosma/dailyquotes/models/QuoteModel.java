package com.example.gcosma.dailyquotes.models;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gcosma on 12/13/2016.
 */

public class QuoteModel {
    private String quote = null;
    private String author = null;
    private List<String> tags = null;
    private String category = null;
    private String date = null;
    private String title = null;
    private String background = null;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTags() {

        String tagString = " ";
        for(Iterator<String> i = tags.iterator(); i.hasNext(); )
            tagString = tagString + i.next().toString() +" ";

        return tagString;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }
}
