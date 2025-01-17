package com.graph.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.graph.controller.models.builder.MascotaBuilder;
import com.graph.controller.models.enumeration.TipoMascota;

public class MascotaResourceTest {

    private final Gson gson = new Gson();
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new
        // org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testGetAll() {
        Response response = target.path("/mascota/list").request().get();
        assertEquals(200, response.getStatus()); // Verifica que la respuesta tenga el código 200 OK
        // También puedes verificar el contenido de la respuesta, por ejemplo, el tipo
        // de contenido o el cuerpo
        // String responseEntity = response.readEntity(String.class);
        // assertTrue(responseEntity.contains("expectedValue"));
    }

    @Test
    public void testSave() {
        String mascotaJson = gson.toJson(
            new MascotaBuilder()
                        .mascotaWithGenericData()
                        .peso(33.25f)
                        .nombre("Pollito")
                        .altura(24.43f)
                        .tipoMascota(TipoMascota.GALLINA)
                        .build()
        );

        Response response = target.path("/mascota/save")
                .request()
                .post(Entity.entity(mascotaJson, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus()); // Verifica que la respuesta sea 200 OK
        // Puedes verificar si la respuesta contiene los valores correctos, dependiendo
        // de lo que haga tu ResponseFactory
    }

    @Test
    public void testGetById() {
        Response response = target.path("/mascota/get/1").request().get();
        assertEquals(200, response.getStatus()); // Verifica que la respuesta sea 200 OK
        // También puedes verificar el contenido de la respuesta
    }

    @Test
    public void testUpdate() {
        String mascotaJson = gson.toJson(
                new MascotaBuilder()
                        .mascotaWithGenericData()
                        .id(1)
                        .nombre("Perrito")
                        .tipoMascota(TipoMascota.PERRO)
                        .build()
        );

        Response response = target.path("/mascota/update")
                .request()
                .post(Entity.entity(mascotaJson, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus()); // Verifica que la respuesta sea 200 OK
    }

    @Test
    public void testDelete() {
        Response response = target.path("/mascota/delete/2").request().delete();
        assertEquals(200, response.getStatus()); // Verifica que la respuesta sea 200 OK
    }

    @Test
    public void testEnumerations() {
        Response response = target.path("/mascota/enumerations").request().get();
        assertEquals(200, response.getStatus()); // Verifica que la respuesta sea 200 OK
    }
}
