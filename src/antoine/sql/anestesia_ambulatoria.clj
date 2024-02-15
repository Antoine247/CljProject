(ns antoine.sql.anestesia_ambulatoria
   (:require [antoine.system :refer [configuracion]]
             [antoine.servicios.conexiones :as conn]
             [honey.sql :as sql]))

(defn borrar
  "borra la evaluacion anestesica preoperatoria ambulatoria"
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso]}]
  (let [query (sql/format {:delete-from :tbc_anes_ambu
                           :where [:and
                                   [:= :tbc_anes_ambu.AnesHc Guar_HistClinica]
                                   [:= :tbc_anes_ambu.AnesFecIngreso Guar_FechaIngreso]
                                   [:= :tbc_anes_ambu.AnesHoraIngreso Guar_HoraIngreso]]})]
    (conn/ejecutar-enunciado configuracion :asistencial query)))