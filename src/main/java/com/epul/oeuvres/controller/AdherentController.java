package com.epul.oeuvres.controller;

import com.epul.oeuvres.dao.ServiceAdherent;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.Adherent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdherentController extends MultiActionController {

//	private static final Logger logger = LoggerFactory.getLogger(MultiControleur.class);

    @RequestMapping(value = "/adherent/lister")
    public ModelAndView afficherLesStages(HttpServletRequest request) throws Exception {
        String destinationPage;

        try {
            request.setAttribute("mesAdherents", ServiceAdherent.consulterListe());
            destinationPage = "/pages/adherent/liste";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/adherent/inserer")
    public ModelAndView insererAdherent(HttpServletRequest request) throws Exception {

        String destinationPage;
        try {
            Adherent unAdherent = new Adherent();
            unAdherent.setNomAdherent(request.getParameter("txtnom"));
            unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
            unAdherent.setVilleAdherent(request.getParameter("txtville"));
            ServiceAdherent.insert(unAdherent);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/adherent/ajout")
    public ModelAndView ajouterAdherent() throws Exception {
        return new ModelAndView("/pages/adherent/ajout");
    }

    @RequestMapping(value = "/adherent/modif")
    public ModelAndView modifier(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("adherent", ServiceAdherent.consulter(Integer.parseInt(request.getParameter("adherent"))));
            destinationPage = "/pages/adherent/modif";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "/adherent/modifier")
    public ModelAndView modifierAdherent(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            Adherent unAdherent = ServiceAdherent.consulter(Integer.parseInt(request.getParameter("txtid")));
            unAdherent.setNomAdherent(request.getParameter("txtnom"));
            unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
            unAdherent.setVilleAdherent(request.getParameter("txtville"));
            ServiceAdherent.modif(unAdherent);
            destinationPage = "home";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}

