import random

from flask import Flask, request, jsonify

import numpy as np
from statsmodels.tsa.holtwinters import ExponentialSmoothing
import traces as traces
import pandas as pd
from numpy import *
import numpy as np
from sklearn import preprocessing
from sklearn import datasets, linear_model
from sklearn.metrics import mean_squared_error, r2_score
from sklearn import metrics
from sklearn.model_selection import train_test_split
from sklearn import neighbors
# from pandas import datetime
app = Flask(__name__)


# root
@app.route("/")
def index():
    return "This is root!!!!"


# GET
@app.route('/users/<user>')
def hello_user(user):
    """
    this serves as a demo purpose
    :param user:
    :return: str
    """
    return "Hello %s!" % user


@app.route('/api')
def predict():
    age = int(request.args['age'])
    gender = int(request.args['gender'])
    openness = int(request.args['openness'])
    neuroticism = int(request.args['neuroticism'])
    conscientiousness = int(request.args['conscientiousness'])
    agreeableness = int(request.args['agreeableness'])
    extraversion = int(request.args['extraversion'])
    df = pd.DataFrame({'Gender': gender, 'Age': age, 'openness': openness, 'neuroticism': neuroticism, 'conscientiousness': conscientiousness, 'agreeableness': agreeableness, 'extraversion': extraversion}, index=[0])
    print(mul_lr.predict(df))
    return mul_lr.predict(df)[0]

# # POST
# @app.route('/api/post_some_data', methods=['POST'])
# def get_text_prediction():
# running web app in local machine


if __name__ == '__main__':
    data = pd.read_csv('train dataset.csv')
    drop_columns = ['Personality (Class label)']
    X = data.drop(drop_columns, axis=1)
    equiv = {"Male": 1, 'Female': 0}
    X['Gender'] = X['Gender'].map(equiv)
    y = data['Personality (Class label)']
    mul_lr = linear_model.LogisticRegression(multi_class='multinomial', solver='newton-cg', max_iter=1000)
    mul_lr.fit(X, y)
    app.run(host='0.0.0.0', port=5000)
