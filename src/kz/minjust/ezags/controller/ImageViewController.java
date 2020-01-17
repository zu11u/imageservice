package kz.minjust.ezags.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;
import kz.minjust.ezags.model.*;
import kz.minjust.ezags.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ImageViewController
{
    @Autowired(required=false)
    private ScanImage13Repository scanImage13Repository;
    @Autowired(required=false)
    private ScanImage14Repository scanImage14Repository;
    @Autowired(required=false)
    private ScanImage15Repository scanImage15Repository;
    @Autowired(required=false)
    private ScanImage16Repository scanImage16Repository;
    @Autowired(required=false)
    private ScanImage17Repository scanImage17Repository;
    @Autowired(required=false)
    private ScanImage18Repository scanImage18Repository;

    @RequestMapping({"/view/{id}/{number}"})
    public void view(@PathVariable String id, @PathVariable Integer number, HttpServletResponse response)
            throws IOException
    {
        int scanYear = Integer.parseInt(id.split("-")[2]);
        ScanImage scan;

        switch (scanYear) {
            case 13 :
                scan = (ScanImage13)this.scanImage13Repository.findOne(id);
                break;
            case 14 :
                scan = (ScanImage14)this.scanImage14Repository.findOne(id);
                break;
            case 15 :
                scan = (ScanImage15)this.scanImage15Repository.findOne(id);
                break;
            case 16:
                scan = (ScanImage16)this.scanImage16Repository.findOne(id);
                break;
            case 17:
                scan = (ScanImage17)this.scanImage17Repository.findOne(id);
                break;
             default:
                 scan = (ScanImage18)this.scanImage18Repository.findOne(id);;
                 break;
        }

        System.out.println("Inside OpenViewController");
        if (scan == null)
        {
            String message = "No picture found with id " + id;
            System.out.println(message);
            response.setContentType("text/plain");
            OutputStream out = response.getOutputStream();
            out.write(message.getBytes());
        }
        else
        {
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();
            if (number.intValue() == 1) {
                out.write(scan.getImage1());
            } else {
                out.write(scan.getImage2());
            }
        }
    }

    @RequestMapping({"/viewAll/{id}"})
    public void viewAll(@PathVariable String id, HttpServletResponse response)
            throws IOException
    {
        System.out.println("Inside OpenViewController");

        response.setContentType("text/html");
        Writer out = response.getWriter();

        out.write("<html>");
        out.write("<body>");
        out.write("<img width=\"585px\" height=\"408px\" src='../view/" + id + "/1'/>");
        out.write("<br/>");
        out.write("<img width=\"585px\" height=\"408px\" src='../view/" + id + "/2'/>");
        out.write("</body>");
        out.write("</html>");
    }
}
