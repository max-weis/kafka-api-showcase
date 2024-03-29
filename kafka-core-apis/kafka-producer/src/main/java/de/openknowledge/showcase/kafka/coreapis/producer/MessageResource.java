/*
 * Copyright (C) open knowledge GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package de.openknowledge.showcase.kafka.coreapis.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * JAX-RS resource that produces messages which are send by a Kafka producer.
 */
@Path("messages")
public class MessageResource {

  private static final Logger LOG = LoggerFactory.getLogger(MessageResource.class);

  @Inject
  private KafkaProducer producer;

  @GET
  public Response sendMessage(@QueryParam("msg") String message) {
    LOG.info("Send custom message {}", message);

    CustomMessage customMessage = new CustomMessage(message);
    producer.send(customMessage);

    LOG.info("Message send");

    return Response
        .status(Response.Status.ACCEPTED)
        .build();
  }
}
