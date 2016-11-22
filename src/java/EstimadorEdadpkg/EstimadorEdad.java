/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstimadorEdadpkg;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;


/**
 * REST Web Service
 *
 * @author Hitzu
 */
@Path("EstimadorEdad")
public class EstimadorEdad {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EstimadorEdad
     */
    public EstimadorEdad() 
    {

    }

    /**
     * Retrieves representation of an instance of EstimadorEdadpkg.EstimadorEdad
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        return "Este es un mensaje sin parametros";
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/Get")
    public String getXml(@QueryParam("mensaje") String mensaje) {
        
        return "<Datos> Este es un mensaje de get: " + mensaje + "que devuelve los mismos datos </datos>";
    }
    
    @POST
    @Path("/Post")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response postXml(String mensaje) 
    {
        try
        {
            byte[] buffer = Base64.getDecoder().decode(mensaje);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(buffer));
            OpenCVFrameConverter.ToMat cv = new OpenCVFrameConverter.ToMat(); 
            org.bytedeco.javacpp.opencv_core.Mat resultado = cv.convertToMat(new Java2DFrameConverter().convert(img));
            mensaje = "La imagen tiene: " + resultado.cols() + " pixeles de alto y " + resultado.rows() + " pixeles de ancho";
            
            
        }   
        catch(Exception e)
        {
            return Response.status(200).entity("Error " + e.toString()).build();
        }
    return Response.status(200).entity("Mensaje " + mensaje).build();
        
        
    }

    /**
     * PUT method for updating or creating an instance of EstimadorEdad
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
