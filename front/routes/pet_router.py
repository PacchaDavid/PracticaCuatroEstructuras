from .router import *

@router.route('/pets')
def pets():
    response = requests.get(f'{back_url}/mascota/list').json()
    return render_template('fragment/pets/list.html', pets=response['data'])

@router.route('/pets/create/')
def pets_create():
    tipos = requests.get(f'{back_url}/mascota/enumerations').json()
    return render_template('fragment/pets/create.html',tipos=tipos['data'])

@router.route('/pet/save/', methods=['POST'])
def pets_save():
    headers = {'Content-Type': 'application/json'}
    response = requests.post(f'{back_url}/mascota/save', json=request.form.to_dict(), headers=headers)
    flash('Pet created successfully')
    return redirect('/pets')

@router.route('/pet/confirm/delete/<int:id>')
def pets_confirm_delete(id):
    return render_template('fragment/pets/delete.html', id=id)

@router.route('/pet/delete/<int:id>',methods=['POST'])
def pets_delete(id):
    response = requests.delete(f'{back_url}/mascota/delete/{id}')
    flash('Pet deleted successfully')
    return redirect('/pets')

@router.route('/pet/edit/<int:id>')
def pets_edit(id):
    response = requests.get(f'{back_url}/mascota/get/{id}').json()
    tipos = requests.get(f'{back_url}/mascota/enumerations').json()
    return render_template('fragment/pets/edit.html', pet=response['data'], tipos=tipos['data'])

@router.route('/pet/update/', methods=['POST'])
def pets_update():
    headers = {'Content-Type': 'application/json'}
    response = requests.post(f'{back_url}/mascota/update', json=request.form.to_dict(), headers=headers)
    print(response)
    flash('Pet updated successfully')
    return redirect('/pets')