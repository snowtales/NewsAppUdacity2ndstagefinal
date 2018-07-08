package com.example.android.newsappudacity;

/**
 * Created by Анастасия on 08.07.2018.
 */

public class NewsClass {
private String section;
private String dateOfPublication;
private String titleOfArticle;
private String author;
private String mUrl;

public NewsClass(String sec, String publ, String titl, String authorOfPublication, String url){
    section = sec;
    dateOfPublication = publ;
    titleOfArticle = titl;
    author = authorOfPublication;
    mUrl = url;
    }

    public String getSection(){return section;}
    public String getDateOfPublication(){return dateOfPublication;}
    public String getTitleOfArticle(){return titleOfArticle;}
    public String getAuthor(){return author;}
    public String getmUrl(){return mUrl;}
}
