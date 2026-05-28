package web.modele;

import dao.EtablissementDao;
import dao.JpaUtil;
import jakarta.servlet.http.HttpServletRequest;
import metier.modele.Etablissement;
import metier.service.Service;

public class StatsNbEleveEtablissementAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Service service = new Service();
        String code = request.getParameter("codeEtablissement");
        if (code == null || code.isBlank()) {
            var etablissements = service.getListeEtablissments();
            if (!etablissements.isEmpty()) {
                code = etablissements.get(0).getCode();
            }
        }
        Long nbEleves = 0L;
        if (code != null && !code.isBlank()) {
            JpaUtil.creerContextePersistance();
            EtablissementDao dao = new EtablissementDao();
            Etablissement etablissement = dao.findByCode(code);
            JpaUtil.fermerContextePersistance();
            if (etablissement != null) {
                nbEleves = service.statsNbEleveEtablissement(etablissement);
            }
        }
        request.setAttribute("codeEtablissement", code != null ? code : "");
        request.setAttribute("nbEleveEtablissement", nbEleves);
    }
}
