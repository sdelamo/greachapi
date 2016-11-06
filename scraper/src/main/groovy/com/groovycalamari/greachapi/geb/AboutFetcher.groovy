package com.groovycalamari.greachapi.geb

import com.groovycalamari.greachapi.About
import com.groovycalamari.greachapi.GreachWebsite
import geb.Browser

class AboutFetcher {

    static About fetchAbout() {
        def browser = new Browser(baseUrl: GreachWebsite.BASEURL)
        browser.to HomePage
        browser.page as About
    }
}
