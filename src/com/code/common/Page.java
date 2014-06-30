package com.code.common;

import org.openqa.selenium.support.PageFactory;

/**
 * Created by jinkai on 2014/6/21.
 */
public class Page {
    public Page()
    {
        PageFactory.initElements(TestCase.driver,this);
    }
}
