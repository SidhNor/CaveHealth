package com.cavemen.cavehealth.service;


import com.cavemen.cavehealth.model.Match;
import com.cavemen.cavehealth.model.MatchesWrapper;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(converters = {MappingJackson2HttpMessageConverter.class}, rootUrl = "https://enpresenter.appspot.com/_ah/api/matchEndpoint/v1")
public interface SyncClient extends RestClientErrorHandling {

    @Post("/findPeers/{activityId}/{maxPlayers}/{timestamp}/{endawanId}/{deviceId}")
    Match findPeers(int activityId, int maxPlayers, long timestamp, int endawanId, String deviceId);

    @Get("/collectionresponse_matchdata/{endawanId}")
    MatchesWrapper findMyMatches(int endawanId);
}
