from .router import *

@router.route('/vets')
def vets():
    response = requests.get(f'{back_url}/veterinaria/list').json()
    return render_template('fragment/vets/list.html', vets=response['data'])

@router.route('/vets/create/')
def vets_create():
    return render_template('fragment/vets/create.html')

@router.route('/vet/save/', methods=['POST'])
def vets_save():
    headers = {'Content-Type': 'application/json'}
    response = requests.post(f'{back_url}/veterinaria/save', json=request.form.to_dict(), headers=headers)
    flash('vet created successfully')
    return redirect('/vets')

@router.route('/vet/confirm/delete/<int:id>')
def vets_confirm_delete(id):
    return render_template('fragment/vets/delete.html', id=id)

@router.route('/vet/delete/<int:id>',methods=['POST'])
def vets_delete(id):
    response = requests.delete(f'{back_url}/veterinaria/delete/{id}')
    flash('vet deleted successfully')
    return redirect('/vets')

@router.route('/vet/edit/<int:id>')
def vets_edit(id):
    response = requests.get(f'{back_url}/veterinaria/get/{id}').json()

    return render_template('fragment/vets/edit.html', vet=response['data'])

@router.route('/vet/update/', methods=['POST'])
def vets_update():
    headers = {'Content-Type': 'application/json'}
    response = requests.post(f'{back_url}/veterinaria/update', json=request.form.to_dict(), headers=headers)
    print(response)
    flash('vet updated successfully')
    return redirect('/vets')