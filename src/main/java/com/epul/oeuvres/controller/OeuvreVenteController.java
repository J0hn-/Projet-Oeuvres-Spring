package com.epul.oeuvres.controller;

import com.epul.oeuvres.dao.ServiceOeuvre;
import com.epul.oeuvres.dao.ServiceProprietaire;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Oeuvrevente;
import com.epul.oeuvres.metier.Proprietaire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OeuvreVenteController extends MultiActionController {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "/oeuvre/lister")
    public ModelAndView lister(HttpServletRequest request) throws Exception {
        String destinationPage;

        try {
            request.setAttribute("mesOeuvres", ServiceOeuvre.gets());
            destinationPage = "/pages/oeuvre/liste";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/oeuvre/inserer")
    public ModelAndView inserer(HttpServletRequest request) throws Exception {

        String destinationPage;
        try {
            Oeuvrevente ov = new Oeuvrevente();
            ov.setTitreOeuvrevente(request.getParameter("titre"));
            ov.setEtatOeuvrevente(request.getParameter("etat"));
            ov.setPrixOeuvrevente(Float.parseFloat(request.getParameter("prix")));
            ov.setProprietaire(ServiceProprietaire.get(Integer.parseInt(request.getParameter("proprio"))));
            ServiceOeuvre.insert(ov);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/oeuvre/ajout")
    public ModelAndView ajouter(HttpServletRequest request) throws Exception {
        request.setAttribute("proprios", ServiceProprietaire.gets());
        return new ModelAndView("/pages/oeuvre/ajout");
    }

    @RequestMapping(value = "/oeuvre/proprio")
    public ModelAndView proprio() throws Exception {
        return new ModelAndView("/pages/oeuvre/proprio");
    }

    @RequestMapping(value = "/oeuvre/modif")
    public ModelAndView modifier(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("proprios", ServiceProprietaire.gets());
            request.setAttribute("o", ServiceOeuvre.get(Integer.parseInt(request.getParameter("oeuvre"))));
            destinationPage = "/pages/oeuvre/modif";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/oeuvre/modifier")
    public ModelAndView modifierOeuvre(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            Oeuvrevente ov = ServiceOeuvre.get(Integer.parseInt(request.getParameter("id")));
            ov.setTitreOeuvrevente(request.getParameter("titre"));
            ov.setEtatOeuvrevente(request.getParameter("etat"));
            ov.setPrixOeuvrevente(Float.parseFloat(request.getParameter("prix")));
            ov.setProprietaire(ServiceProprietaire.get(Integer.parseInt(request.getParameter("proprio"))));
            ServiceOeuvre.modif(ov);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/oeuvre/insertion")
    public ModelAndView insertionProprio(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            Proprietaire p = new Proprietaire();
            p.setNomProprietaire(request.getParameter("nom"));
            p.setPrenomProprietaire(request.getParameter("prenom"));
            ServiceProprietaire.insert(p);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

}

