/*
 * Copyright (zaytsev_dv)
 *  *|
 *  *|
 *  *|(•.•). i'm watching for you.....
 *  *|⊂ﾉ
 *  *|
 *  *|
 */
package com.ecviron.api.rest;

import com.ecviron.api.producers.EventProducer;
import ecviron.generated.swaggerCodegen.api.EcvironApi;
import ecviron.generated.swaggerCodegen.model.GetPostEventResponse;
import ecviron.generated.swaggerCodegen.model.PostEventRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@AllArgsConstructor
@RestController
@Slf4j
public class ValidableProduceEventApiRestController extends EventValidator implements EcvironApi {

    private final EventProducer producer;

    @Override
    public ResponseEntity<GetPostEventResponse> produceEvent(PostEventRequest postEventRequest) {
        validate(postEventRequest);
        String timestamp = null;
        boolean isSuccess = true;
        try {
            timestamp = Timestamp.from(ZonedDateTime.now().toInstant()).toString();
            producer.produce(postEventRequest);
        } catch (Exception ex) {
            isSuccess = false;
            log.error("something wrong with sending to Queue");
        }

        return ResponseEntity.ok(new GetPostEventResponse(timestamp, isSuccess));
    }
}
