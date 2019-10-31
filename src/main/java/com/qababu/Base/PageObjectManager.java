package com.qababu.Base;

import com.qababu.pageactions.HomePageActions;

public class PageObjectManager {

    private static HomePageActions homePageActions;

    private PageObjectManager(){

    }

    public static HomePageActions getHomePageActions(){

        return (homePageActions == null) ? homePageActions = new HomePageActions() : homePageActions;
    }
}
