# Helidon OpenAPI issue reproducer

This example implements a simple Hello World REST service using MicroProfile.

## Reproduce the issue

Build and run the application:
```bash
mvn package
java -jar target/helidon-issue-1288.jar
```

Make sure the endpoints work:
```
curl -X GET http://localhost:8080/app1/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/app2/greet
{"message":"Hello World!"}
```

Try the OpenAPI endpoint:
`curl http://localhost:8080/openapi`

Expected response:
```yaml
---
openapi: 3.0.1
info:
  title: Generated API
  version: "1.0"
paths:
  /app1/greet:
    get:
      summary: Default hello world endpoint
      description: This method returns Hello World
      responses:
        default:
          description: JSON with greeting
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/GreetingMessage'
  /app2/greet:
    get:
      summary: Default hello world endpoint
      description: This method returns Hello World
      responses:
        default:
          description: JSON with greeting
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/GreetingMessage'
components:
  schemas:
    GreetingMessage:
      properties:
        message:
          type: string
```

Actual response:
```yaml
---
openapi: 3.0.1
info:
  title: Generated API
  version: "1.0"
paths:
  /app2/greet:
    get:
      summary: Default hello world endpoint
      description: This method returns Hello World
      responses:
        default:
          description: JSON with greeting
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/GreetingMessage'
components:
  schemas:
    GreetingMessage:
      properties:
        message:
          type: string
```