![icon](https://user-images.githubusercontent.com/40843946/101735576-ae8f7300-3af4-11eb-82ea-f42218643137.png)

# Assignment_Intelligent_System

# Description
{About project}
Nowadays, choosing candidate from a plethora of CV is not easy since we also need to determine whether their personality is suitable for the job. So the goal of this project is to create an application for employer to predict the characteristic of each candidate through CV analysis.

{Why this project}
The turnover rate in Viet Nam is increasing each year due to several reasons. One of them is the wrong way the company work with their employee due to lack of knowledge about employee personality. Knowing a person's nature is very important in working together especially in a company. So we thought that this app can help employer to know their candidate more and find more suitable employees for them.


{AI model in this project}
In this project, we will Multiple Class Logistic Regression to predict. Using the data-set collected from google and also from survey, we train the AI and then using the model to test on real input.
# AI model
In this project we used multiclass logistic regression model for predicting personality
![image](https://user-images.githubusercontent.com/40843946/101851971-2c07c180-3b8f-11eb-8a9c-c89f031dbdf3.png)

Our input include 7 features: Age, Gender, Openness, Concientiousness, Neuroticism, Aggreableness, Extraversion.
- Gender will be labeled 1 for Male and 0 for Female
- Openness will have value from 0 to 10
- Concientiousness will have value from 0 to 10
- Neuroticism will have value from 0 to 10
- Aggreableness will have value from 0 to 10
- Extraversion will have value from 0 to 10

The output will be one of 5 classes: Lively, Extravert, Responsible, Serious, Dependable

# Dataset
Our train dataset is stored inside AI server folder.

The dataset include 708 data points for training the model.

# Work flow

# Activity diagram
![ActivityDiagram](https://user-images.githubusercontent.com/40843946/101735655-c7982400-3af4-11eb-8b14-87c0b85751d1.png)

# Architecture diagram
![image](https://user-images.githubusercontent.com/40843946/101855702-72145380-3b96-11eb-99ea-407e42bf11e5.png)


# Installation
- Run application with Android Studio
- Run server.py in AI_Server folder
- Change AI_SERVER_URL in Helper/Constant.java to `http://Your IP:5000`


# Future development
- We will implement read a curtain CV format and increase the scope of feature and train dataset

# Demo 
Our demostation video:
https://www.youtube.com/watch?v=zhzX9jau9BY
