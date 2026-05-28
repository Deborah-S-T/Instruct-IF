package web.vue;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StatsSerialisation extends Serialisation {

    @Override
    public void appliquer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = (String) request.getAttribute("statsType");
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("type", type != null ? type : "unknown");

        if ("nb-soutien".equals(type)) {
            json.add("valeur", (Integer) request.getAttribute("nbSoutien"));
        } else if ("moyenne-duree".equals(type)) {
            json.add("valeur", (Integer) request.getAttribute("moyenneDuree"));
        } else if ("nb-eleve".equals(type)) {
            json.add("valeur", (Integer) request.getAttribute("nbEleve"));
        } else if ("nb-eleve-etablissement".equals(type)) {
            json.add("valeur", (Long) request.getAttribute("nbEleveEtablissement"));
            json.add("codeEtablissement", (String) request.getAttribute("codeEtablissement"));
        } else if ("proportions-duree".equals(type)) {
            @SuppressWarnings("unchecked")
            List<Integer> proportions = (List<Integer>) request.getAttribute("proportions");
            JsonArrayBuilder array = Json.createArrayBuilder();
            if (proportions != null) {
                for (Integer p : proportions) {
                    array.add(p);
                }
            }
            json.add("valeurs", array);
            json.add("labels", Json.createArrayBuilder()
                    .add("≤ 20 min")
                    .add("20–40 min")
                    .add("40–60 min")
                    .add("> 60 min"));
        } else {
            json.add("erreur", "Type de statistique inconnu");
        }

        PrintWriter out = response.getWriter();
        out.print(json.build().toString());
    }
}
