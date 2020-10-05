# Project Setup:

1.Download the zip from **master branch**.  
2.Unzip the zip file   
3.Open Eclipse                                                                         
&nbsp;&nbsp;&nbsp;File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip  
&nbsp;&nbsp;&nbsp;Select the project  
4.Choose the Spring Boot Application file (ScreenshotApiApplication) (Annotated with @SpringBootApplication)  
5.Right Click on the file and Run as Java Application  
6.Tomcat Server will startup at <http://localhost:8080/>  
7.Servlet Context Path is set as : ***screenshot-application***   
8.So all request will be mapped after : <http://localhost:8080/screenshot-application/>

## Alternative

Directly run the **jar** file ***screenshot-api-0.0.1-SNAPSHOT.jar*** available in git repository via command prompt to start the application directly at <http://localhost:8080/>
```bash
java -jar screenshot-api-0.0.1-SNAPSHOT.jar
```

## Screenshot API Endpoint

***Endpoint url*** : <http://localhost:8080/screenshot-application/api?url=urlToPageWhoseScreenshotNeedToBeCaptured> 

**Examples:**                               
<http://localhost:8080/screenshot-application/api?url=https://www.google.com> 
             
<http://localhost:8080/screenshot-application/api?url=https://en.wikipedia.org/wiki/Java_(programming_language)>


### Sample screenshot :

Added as part of github project itself with name ***"screenshot-sample.png"***

### API Authentication
API is secured using basic spring security authentication.  
Credentials are mentioned below  
**username : john**  
**password : john**


## Testing REST API:

#### 1.Chrome/Firefox/IE ####
1.Open browser  
2.Type the endpoint url -> <http://localhost:8080/screenshot-application/api?url=urlToPageWhoseScreenshotNeedToBeCaptured>  
3.Click Enter  
4.Login form will come up. Provide the credentials as mentioned above  
5.Submit  
6.Once authenticated we will get the screenshot of url mentioned in request as output

#### 2.Swagger-ui ####
1.**Swagger ui** is configured on end point url :  <http://localhost:8080/screenshot-application/>  
2.Open any browser and go to url mentioned above  
3.Click enter  
4.Login form will come up. Provide the credentials as mentioned above  
5.Submit  
6.Once authenticated **Swagger ui** will come up  
7.Click on **image-controller**  
8.Click on **GET**  
9.Under parameters , 
&nbsp;&nbsp;&nbsp;url=> provide url of page whose screenshot need to be captured as value
value => <https://www.google.com>  
10.Click on **Try It** output  
11.Output will be generated in **Response Body**  


#### 3.Postman ####
1.Open postman  
2.In **Authentication** tab select **Basic Auth** and provide credentials as mentioned above  
3.Send get request to endpoint url <http://localhost:8080/screenshot-application/api?url=urlToPageWhoseScreenshotNeedToBeCaptured>   
eg: <http://localhost:8080/screenshot-application/api?url=https://www.google.com>  
4.Output will be generated in body
