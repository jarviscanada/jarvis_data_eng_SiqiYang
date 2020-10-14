# Introduction
The background for this project is that there is an online store named London Gift Shop (LGS). It sells gift-ware products and has been running
for more than 10 years. LGS's revenue is not growing in recent years, and its market team wants to utilize the latest technologies to improve the situation. Since LGS's marketing team does not have IT capability, Jarvis engages with the LGS marketing team to provide the data engineering service. This project used several technologies to provide proof of concept.
  LGS IT team provides the transaction data from between 01/12/2009 and 09/12/2011 into a SQL file as the sample data set. A SQL database was created and loaded the SQL file for the 
  OLAP purpose. Python, Jupyter Notebook, Pandas Dataframe, and Numpy as the main tools for this PoC project and analytic purpose.
  Also, this project used these tools to solve several business questions.


# Project Architecture
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/python_data_wrangling/asset/diagram.png).
LGS web app uses the Azure resource group as the container. Content Delivery Network and azure blob to consist of the front-end structure.
It also uses the AKS cluster and Azure SQL server to handle back-end stuff. It retrieves the sample data set from the Azure SQL data server and uses ETL to process the data. As part of the ETL process, the LGS IT team removed the customers'sensitive information. Then a PSQL database was created as the data warehouse for the OLAP.
 The Jupyter notebook is the tool that uses python and other libraries to do the analytic works on the data.

# Data Analytics and Wrangling
The Jupyter notebook for this project can be found [here](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/python_data_wrangling/retail_data_analytics_wrangling.ipynb).



# Improvements
- Solve more business problems to provide more helpful suggestion.
- Try to plot the RFM value to make the result more straight forward.
- Examine more data rather than if possible.