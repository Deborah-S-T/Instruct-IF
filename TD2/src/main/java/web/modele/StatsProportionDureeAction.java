package web.modele;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import metier.modele.Intervenant;
import metier.service.Service;

public class StatsProportionDureeAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        HttpSession s = request.getSession();
        Long idIntervenant = Long.parseLong(s.getAttribute("intervenantId").toString());
        Intervenant intervenant = service.getIntervenantById(idIntervenant);
        List<Integer> proportions = service.statsProportionsDurees(intervenant);
        request.setAttribute("proportions", proportions);
    }
}
