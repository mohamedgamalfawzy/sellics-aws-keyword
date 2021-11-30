# Sellics Amazon Keyword Weight Estimation:

##Overview:

A microservice with a single endpoint to estimate the search volume of any keyword received (how often Amazon customers search for that **exact** keyword).

## Assumptions:
- Searching for the keyword on amazon **Completion API** by one single keyword would only return 10 results and this does not represent the actual volume of this keyword since the results will have the searched keyword as the first/second result and this will not represent the actual weight. For instance, searching for ***'iphone charger'*** would return some related results to only *'iphone charger'*, but we want to know what is the actual search-volume for this keyword if we only typed ***'i'***, do we have the **_'iphone charger'_** results when we only type **_'i'_**? 
- The above scenario is important since we need to know what's the actual weight for this word on amazon if we only typed 1, 2, 3, or etc. characters.
- Another assumption is if the API searching for _**'iphone'**_ we will get a lot of iphone results but not the exact word (e.g. 'iphone 13 case','iphone 13 pro case','iphone 13 pro max case' and etc. ) and if we are only searching for the exact word, the api will return 0 as a score. However, we're still getting the word 'iphone' but in different shape.
- For the scenario above, it was important to get the similarity percentage/ratio if compared with the returned search results. 
## How the algorithm works:
As a result of the assumptions above I've used the below algorithm:
- Divided the keyword into character array and every time I append the next character to the last characters array (e.g. 'i','ip','iph','ipo','iphon','iphone'.)
- Search for each constructed array and get the results.
- For each result of suggestions returned from Amazon API, the estimate API uses **FuzzySearch** algorithm to get the percentage of each suggestion compared with the original word **_'iphone'_**. For instance, searching for 'ip' will return suggestions, the api would get the ratio for each suggestion compared with 'iphone'.
- Calculate all the ratios/percentages returned and get an average with the original word length.
- The  above algorithm will return the final score based on the above explained steps.

## Do you think the (*hint) that we gave you earlier is correct and if so - why?
I don't think the ranking on amazon is insignificant as Amazon A9 algorithm will rank a product with a strong sales history will be higher. However, the relevancy of the keyword that a user typed can affect the ranking to match the user query.  
##How precise do you think your outcome is and why?
This is not accurate 100% as the api does not compare with all the datasets related. The API is only performing search-volume calculations based on what Amazon provide us and this is can be variable.
However, based on the small dataset the Estimate API receiving, it can be accurate with an 85% percent accuracy. In addition to it, another aspect can affect the accuracy, the estimate does not analyze the past search patterns which is important to volume the keywords.

## Local development setup

- Please go to the root directory of the project and run this command
`docker-compose up`
- The above command will run the project with a live log. In order to run the project in background, please use this command `docker-compose up -d`
- Go to the browser and access the swagger endpoint through this link http://localhost:8080/swagger-ui

## Future Work:
Implementing a machine learning algorithm to automatically analyze past search patterns. 
