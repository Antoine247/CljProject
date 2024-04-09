(ns antoine.sql.anestesia-internado
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  [{:keys [tbc_admision_scroll/Adm_HistClin]}]
  (let [query (sql/format {:delete-from :tbc_anestesia
                           :where [:= :tbc_anestesia.Anes_HistClin Adm_HistClin]})]
    (consulta-asistencial query)))