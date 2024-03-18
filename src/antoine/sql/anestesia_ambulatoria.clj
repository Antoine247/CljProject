(ns antoine.sql.anestesia-ambulatoria
   (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
             [honey.sql :as sql]
             [antoine.utils.utils :as utils]))

(defn borrar
  "Borra la evaluacion anestesica preoperatoria ambulatoria"
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso
           tbc_admision_scroll/Adm_HistClin tbc_admision_scroll/Adm_FecIng tbc_admision_scroll/Adm_HorIng]}]
  (let [query (sql/format {:delete-from :tbc_anes_ambu
                           :where [:and
                                   [:= :tbc_anes_ambu.AnesHc (or Guar_HistClinica Adm_HistClin)]
                                   [:= :tbc_anes_ambu.AnesFecIngreso (or Guar_FechaIngreso Adm_FecIng)]
                                   [:= :tbc_anes_ambu.AnesHoraIngreso (or Guar_HoraIngreso Adm_HorIng)]]})]
    (consulta-asistencial query)))

(defn crear
  "crea una evaluacion preanestesica ambulatoria"
  [valores]
  {:pre [(or (utils/vector-doble? valores) (utils/vector-mapa? valores))]}
  (let [query (sql/format {:insert-into :tbc_anes_ambu
                           :values valores})]
    (consulta-asistencial query)))

(defn actualizar
  "actualiza evaluacion preanestesica ambulatoria se envia mapa de paciente y mapa de valores a ingresar"
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso]} mapa-valores]
  {:pre [(map? mapa-valores)]}
  (let [query (sql/format {:update :tbc_anes_ambu
                           :set mapa-valores
                           :where [:and
                                   [:= :tbc_anes_ambu.AnesHc Guar_HistClinica]
                                   [:= :tbc_anes_ambu.AnesFecIngreso Guar_FechaIngreso]
                                   [:= :tbc_anes_ambu.AnesHoraIngreso Guar_HoraIngreso]]})]
    (consulta-asistencial query)))