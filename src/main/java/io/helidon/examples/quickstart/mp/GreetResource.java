/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.helidon.examples.quickstart.mp;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * A simple JAX-RS resource to greet you. Examples:
 *
 * Get default greeting message:
 * curl -X GET http://localhost:8080/greet
 */
@Path("/greet")
@RequestScoped
public class GreetResource {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    /**
     * Return a wordly greeting message.
     *
     * @return {@link JsonObject}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Default hello world endpoint",
               description = "This method returns Hello World")
    @APIResponse(description = "JSON with greeting",
                 content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GreetingMessage.class)))
    public JsonObject getDefaultMessage() {
        return JSON.createObjectBuilder()
                .add("message", "Hello World")
                .build();
    }

    /**
     * Open API schema definition.
     */
    public static class GreetingMessage {
        private String message;

        /**
         * Gets the message value.
         *
         * @return message value
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the message value.
         *
         * @param message message value to set
         */
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
