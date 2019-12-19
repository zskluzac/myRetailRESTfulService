# myRetail RESTful Service

## How to Run

To run this application, clone this repository and open it in IntelliJ.
Import the Gradle dependencies, then run the project. 
The project should take a minute or so to deploy.

Once the project is running, open a tool for sending HTTP requests (I prefer Postman).
Using this tool, send a GET request to an endpoint like `http://localhost:8080/products/13860425`
where the trailing integer is a product's id.
To test the additional functionality, send a PUT request to the same endpoint, 
and copy the response from your initial GET request into the body of the PUT request.
Before sending the request, try changing the price and/or currency code in the JSON string.
Subsequent GET requests should reflect the PUT request you made.

## Limitations

For your ease of testing, it was essential for this project to run as an independently deployable application.
Because of this, the application deploys a fresh embedded NoSQL database every time it runs and populates that database
with Target's RedSky API.
Because of this, the only product ids with available pricing information are in a range of ids specified in the
`com.myretail.productsapi.utils.Constants.java` file to keep the build duration reasonable. 
If you'd like to check this application's functionality on ids outside of that range,
you must change the values of the `PRODUCT_ID_LOWER` and `PRODUCT_ID_UPPER` constants.

Also, since I could not find a value for a product's currency code in Target's RedSky API, the value defaults to "USD".
The currency code can still be changed with a PUT request, and that change will persist.

