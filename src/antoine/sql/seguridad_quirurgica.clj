(ns antoine.sql.seguridad-quirurgica
  "queries que se encargan de modificar la seguridad quirurgica ambulatoria"
  (:require [antoine.servicios.conexiones :refer [consulta-asistencial]]
            [honey.sql :as sql]
            [antoine.utils.utils :as utils]))

(defn borrar
  "borra la seguridad quirurgica" 
  [{:keys [tbc_guardia/Guar_HistClinica tbc_guardia/Guar_FechaIngreso tbc_guardia/Guar_HoraIngreso]}] 
  (let [query (sql/format {:delete-from :tbc_seguqui_new
                           :where [:and
                                   [:= :tbc_seguqui_new.SegHistClinica Guar_HistClinica]
                                   [:= :tbc_seguqui_new.SegFechaCarga Guar_FechaIngreso]
                                   [:= :tbc_seguqui_new.SegHoraCarga Guar_HoraIngreso]]})]
    (consulta-asistencial query)))