package com.qababu.base;

import com.qababu.pagecctions.BookStorePageActions;

public class PageObjectManager {

    private static BookStorePageActions homePageActions;

    private PageObjectManager(){

    }

    public static BookStorePageActions getHomePageActions(){

        return (homePageActions == null) ? homePageActions = new BookStorePageActions() : homePageActions;
    }
}
