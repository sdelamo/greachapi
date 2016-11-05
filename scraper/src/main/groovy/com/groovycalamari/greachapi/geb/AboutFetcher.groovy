package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.About
import geb.Browser

class AboutFetcher {

    static About fetchAbout() {
        def browser = new Browser()

        browser.to HomePage
        browser.page as About
    }
}
