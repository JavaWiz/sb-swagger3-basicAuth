## Spring Boot + Swagger 3 (OpenAPI 3) + Security Example(Basic Authentication)
### Getting Started
springdoc-openapi java library helps automating the generation of API documentation using spring boot projects. springdoc-openapi works by examining an application at runtime to infer API semantics based on spring configurations, class structure and various annotations.

Automatically generates documentation in JSON/YAML and HTML format APIs. This documentation can be completed by comments using swagger-api annotations.

For the integration between spring-boot and swagger-ui, add the library to the list of your project dependencies (No additional configuration is needed)
```
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>1.5.11</version>
</dependency>
```
This will automatically deploy swagger-ui to a spring-boot application:
* Documentation will be available in HTML format, using the official swagger-ui jars.
* The Swagger UI page will then be available at `http://server:port/context-path/swagger-ui.html` and the OpenAPI description will be available at the following url for json format: `http://server:port/context-path/v3/api-docs`
  * server: The server name or IP
  * port: The server port
  * context-path: The context path of the application
* Documentation can be available in yaml format as well, on the following path : /v3/api-docs.yaml

For custom path of the swagger documentation in HTML format, add a custom springdoc property, in your spring-boot configuration file: .
```
# swagger-ui custom path
springdoc.swagger-ui.path=/swagger-ui.html
```
For custom path of the OpenAPI documentation in Json format, add a custom springdoc property, in your spring-boot configuration file:
```
# /api-docs endpoint custom path
springdoc.api-docs.path=/api-docs
```

### Spring Security Configuration
* Annotate the `SecurityConfiguration` class with `@EnableWebSecurity` to apply the class to the global WebSecurity.
* `SecurityConfiguration` class should extend to `WebSecurityConfigurerAdapter`, which provides us a configuration methods,to define rules to specify what URIs to protect or pass through.
* Extending `WebSecurityConfigurerAdapter` allows customizing spring security by overriding methods.

### OpenAPI Authentication and Authorization
OpenAPI uses the term security scheme for authentication and authorization schemes. OpenAPI 3.0 lets you describe APIs protected using the following security schemes:
* HTTP authentication schemes (they use the Authorization header):
  * Basic
  * Bearer
  * Other HTTP schemes as defined by RFC 7235 and HTTP Authentication Scheme Registry
* API keys in headers, query string or cookies
  * Cookie authentication
* OAuth 2
* OpenID Connect Discovery

### Describing Security
Security is described using the securitySchemes and security keywords. We use `securitySchemes` to define all security schemes our API supports, then use security to apply specific schemes to the whole API or individual operations. All security schemes used by the API must be defined in the global `components/securitySchemes` section. This section contains a list of named security schemes, where each scheme can be of type:
* http – for Basic, Bearer and other HTTP authentications schemes

### Configure Swagger for Spring Security
In `SbSwagger3Application` class specify SecurityScheme.
```
@SecurityScheme(name = "TaskAPISecureScheme", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
```

Finally, in the controller class use `@SecurityRequirement(name = "TaskAPISecureScheme")` to define security requirements for the single operation (when applied at method level) or for all operations of a class (when applied at class level).

