from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

back_url = 'http://localhost:8080/api'

@router.route('/')
def dashboard():
    return render_template('fragment/index.html')

@router.route('/graphs')
def graphs():
    text = requests.get(f'{back_url}/graph/get-as-js/0').text
    with open('static/js/grafo.js', 'w') as file:
        file.write(str(text))
    return render_template('fragment/graphs/ingraph.html')
