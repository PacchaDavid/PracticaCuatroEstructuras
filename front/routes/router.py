from flask import Blueprint, render_template, request, redirect, url_for, flash
import requests

router = Blueprint('router', __name__)

back_url = 'http://localhost:8080/api'
