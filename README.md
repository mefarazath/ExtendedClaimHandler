# Custom Claim Handler
---
This component allows to manipulate claims that are returned by Identity Framework for an authenticated user.

## Building From Source

Clone this repository (`https://github.com/mefarazath/CustomClaimHandler.git`) 

Use maven install to build
`mvn clean install`.

## Deploying to IS 5.3.0

* Copy **org.wso2.custom.extensions.claim.handler-1.0.0-SNAPSHOT.jar** file to **wso2is-5.3.0/repository/components/dropins**
 folder
 
* Update the \<ClaimHandler> tag in wso2is-5.3.0/repository/conf/identity/application-authentication.xml as follows,
````xml
<ClaimHandler>com.wso2.sample.claim.handler.CustomClaimHandler</ClaimHandler>
````