from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

back_url = 'http://localhost:8080/api'

@router.route('/')
def dashboard():
    return redirect(url_for('router.pets'))

@router.route('/graph/pet/<randomize>')
def graph_pet(randomize):
    text = requests.get(f'{back_url}/graph/get-as-js/0/{randomize}').text
    vertices = requests.get(f'{back_url}/graph/get-vertices/0').json()['data']
    with open('static/js/grafoMascota.js', 'w') as file:
        file.write(str(text))

    return render_template('fragment/graphs/ingraph.html', vet=False, vertices=vertices)

@router.route('/graph/vet/<randomize>')
def graph_vet(randomize):
    text = requests.get(f'{back_url}/graph/get-as-js/1/{randomize}').text
    vertices = requests.get(f'{back_url}/graph/get-vertices/1').json()['data']
    print(vertices)
    with open('static/js/grafoVeterinaria.js', 'w') as file:
        file.write(str(text))

    return render_template('fragment/graphs/ingraph.html', vet=True, vertices=vertices)

@router.route('/agregar-adyacencia/<int:graph_type>', methods=['POST']) 
def add_adjacency(graph_type):
    if request.method == 'POST':
        data = request.form.to_dict()
        v1 = data['v1']
        v2 = data['v2']
        print(data)
        response = requests.get(f'{back_url}/graph/add-adjacency/{graph_type}/{v1}/{v2}')
        print("RESPONSEeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
        print(response)
        flash(response.json()['data'])
    return redirect(url_for('router.graph_pet' if graph_type == 0 else 'router.graph_vet', randomize=0))

@router.route('/shortest-path/<int:graph_type>', methods=['POST'])
def shortest_path(graph_type):
    source = request.form.to_dict()['source']
    response = requests.get(f'{back_url}/graph/shortest-paths/{graph_type}/{source}')

    vertices = ''

    if graph_type == 0:
        vertices = requests.get(f'{back_url}/graph/get-vertices/0').json()['data']
    else:
        vertices = requests.get(f'{back_url}/graph/get-vertices/1').json()['data']

    shortest_path = response.json()['data']['paths']


    return render_template('fragment/graphs/graph_shortest_path.html', shortest_path=shortest_path, vet=graph_type, vertices=vertices)   