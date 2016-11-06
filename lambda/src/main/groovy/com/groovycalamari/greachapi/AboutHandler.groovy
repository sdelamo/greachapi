package com.groovycalamari.greachapi

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.groovycalamari.greachapi.geb.AboutFetcher

class AboutHandler implements RequestHandler<Request, About> {

    @SuppressWarnings('UnusedMethodParameter')
    About handleRequest(Request request, Context context) {
        AboutFetcher.fetchAbout()
    }
}
