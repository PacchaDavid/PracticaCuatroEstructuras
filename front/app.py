from flask import Flask

def create_app():
    app = Flask(__name__,instance_relative_config=False)
    app.secret_key = 'secretito'
    with app.app_context():
        from routes.router import router
        from routes.pet_router import router
        from routes.vet_router import router
        app.register_blueprint(router)
    return app