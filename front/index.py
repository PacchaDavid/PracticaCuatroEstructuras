from app import create_app
from flask import render_template

app = create_app()

@app.errorhandler(404)
def page_not_found(error):
    return render_template('not_found.html'), 404

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0',port=5000)