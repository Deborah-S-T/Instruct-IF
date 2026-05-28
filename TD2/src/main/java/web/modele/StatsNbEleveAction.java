package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import metier.modele.Intervenant;
import metier.service.Service;

public class StatsNbEleveAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession s = request.getSession();
        Long idIntervenant = Long.parseLong(s.getAttribute("intervenantId").toString());
        Intervenant intervenant = service.getIntervenantById(idIntervenant);
        request.setAttribute("nbEleve", service.statsNbEleve(intervenant));
    }
}
