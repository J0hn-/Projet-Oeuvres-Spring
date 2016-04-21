package com.epul.oeuvres.controller;

import com.epul.oeuvres.dao.ServiceAdherent;
import com.epul.oeuvres.dao.ServiceOeuvre;
import com.epul.oeuvres.dao.ServiceReservation;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class ReservationController extends MultiActionController {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "/reservation/lister")
    public ModelAndView lister(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("reservations", ServiceReservation.gets());
            destinationPage = "/pages/reservation/liste";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/reservation/resume")
    public ModelAndView resume(HttpServletRequest request) throws Exception {

        String destinationPage;
        try {
            request.setAttribute("adherents", ServiceAdherent.consulterListe());
            request.setAttribute("oeuvre", ServiceOeuvre.get(Integer.parseInt(request.getParameter("oeuvre"))));
            destinationPage = "/pages/reservation/reservation";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/reservation/reserver")
    public ModelAndView reserver(HttpServletRequest request) throws Exception {
        String destinationPage;

        try {
            int idOeuvre = Integer.parseInt(request.getParameter("oeuvre"));
            Reservation re = new Reservation();
            re.setAdherent(ServiceAdherent.consulter(Integer.parseInt(request.getParameter("adherent"))));
            re.setDate(new Date());
            re.setOeuvrevente(ServiceOeuvre.get(idOeuvre));
            ServiceReservation.insert(re);

            Oeuvrevente ov = ServiceOeuvre.get(idOeuvre);
            ov.setEtatOeuvrevente("R");
            ServiceOeuvre.modif(ov);
            destinationPage = "/pages/reservation/reserve";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}
